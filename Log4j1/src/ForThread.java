import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class ForThread extends Thread{
	
	/**日之类*/
	private final static Logger logger = LoggerFactory.getLogger(ForThread.class);
	
	/**线程名 */
	private String threadName;
	
	
	/**插入线程*/
	public Thread t;

	@Override
	public void run() {
		for(int i = 1;i <= 3;i++){
			System.out.println(threadName+"---------->"+i);
		}
	}

	/**
	 * 线程启动方法
	 */
	public void start() {
		System.out.println("Starting " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
	
	
	/**
	 * 构造线程对象
	 * @param name
	 * @param source
	 * @param simSchema
	 * @param insertType
	 * @param inviteCodeBankList
	 */
	public ForThread(String name) {
		this.threadName = name;
		logger.debug("线程{}初始化完成",name);
	}
}
