import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

public class LostUpdate implements Runnable {
	
	private CountDownLatch countDown;

	public LostUpdate(CountDownLatch countDown) {
		this.countDown = countDown;
	}

	@Override
	public void run() {
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3307/test?useUnicode=true&characterEncoding=UTF-8",
							"root", "mysql");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		try {
			conn.setAutoCommit(false);
			// 不加锁的情况
			PreparedStatement ps = conn.prepareStatement("select * from LostUpdate where id =1");
			
			// 加锁的情况
			// PreparedStatement ps=conn.prepareStatement("select * from LostUpdate where id =1 for update");
			ResultSet rs = ps.executeQuery();
			
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("count");
			}
			count++;
			
			ps = conn.prepareStatement("update LostUpdate set count=? where id =1");
			ps.setInt(1, count);
			ps.executeUpdate();

			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		// 表示一次任务完成
		countDown.countDown();
	}
	
}