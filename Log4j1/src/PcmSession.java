
import java.io.Serializable;
import java.net.SocketAddress;
import java.util.HashSet;
import java.util.Set;
import org.apache.mina.core.session.IoSession;


/**
 * 
 * @author ccm
 * @Description IoSession包装类
 * @since 2018-07-19 09:51:00
 */
public class PcmSession implements Serializable {

	private static final long serialVersionUID = 1L;

	/**mina IoSession*/
	private transient IoSession session;
	// session全局ID
	private String clientId; 
	////此session维护的客户---保证不重复
	private Set<String> telephones = new HashSet<String>();
	
	//constructor
	public PcmSession() {
	}

	public PcmSession(IoSession session,String clientId) {
		this.session = session;
		this.clientId = clientId;
	}

	//get/set
	public void setIoSession(IoSession session) {
		this.session = session;
	}

	public IoSession getIoSession() {
		return session;
	}
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Set<String> getTelephones() {
		return telephones;
	}

	public void setTelephones(Set<String> telephones) {
		this.telephones = telephones;
	}

	
	/**
	 * 客户纳入客户端管理
	 */
	public void addCustPhone(String telephone){
		this.telephones.add(telephone);
	}
	
	// 将键为 key，值为 value的用户自定义的属性存储到 I/O 会话中。
	public void setAttribute(String key, Object value) {
		if (session != null) {
			session.setAttribute(key, value);
		}
	}

	public boolean containsAttribute(String key) {
		if (session != null) {
			return session.containsAttribute(key);
		}
		return false;
	}

	// 从 I/O 会话中获取键为 key的用户自定义的属性。
	public Object getAttribute(String key) {
		if (session != null) {
			return session.getAttribute(key);
		}
		return null;
	}

	// 从 I/O 会话中删除键为 key的用户自定义的属性。
	public void removeAttribute(String key) {
		if (session != null) {
			session.removeAttribute(key);
		}
	}

	public SocketAddress getRemoteAddress() {
		if (session != null) {
			return session.getRemoteAddress();
		}
		return null;
	}

	/*
	 * 将消息对象 message发送到当前连接的对等体。该方法是异步的，当消息被真正发送到对等体的时候，
	 * IoHandler.messageSent(IoSession,Object)会被调用。如果需要的话，
	 * 也可以等消息真正发送出去之后再继续执行后续操作。
	 */
	public void write(Object msg) {
		if (session != null) {
			session.write(msg).isWritten();
		}
	}

	public boolean isConnected() {
		if (session != null) {
			return session.isConnected();
		}
		return false;
	}

	/*
	 * 关闭当前连接。如果参数 immediately为 true的话， 连接会等到队列中所有的数据发送请求都完成之后才关闭；否则的话就立即关闭。
	 */
	public void close(boolean immediately) {
		if (session != null) {
			session.close(immediately);
		}
	}

	@Override
	public String toString() {
		return "PcmSession [session=" + session + ", clientId=" + clientId
				+ ", telephones=" + telephones + "]";
	}
	
}
