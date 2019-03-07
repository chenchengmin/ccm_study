

import java.io.Serializable;
import java.util.Date;

/**
 * 订阅关系实体类
 */
public class MindgoSubscription implements Serializable{
	
	private static final long serialVersionUID = 5952689219411916553L;
	
    /** 订阅流水号  **/
    private int id;
    /** 订阅者ID  **/
    private String subcribeUserid;
    /** 发布者ID  **/
    private String publicUserid;
    /** 订阅账户类型  **/
    private int accountType;
    /** 创建时间  **/
    private Date ctime;
    /** 修改时间  **/
    private Date mtime;
    /** 订阅类型 **/
    private int subcribeType;

    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubcribeUserid() {
		return subcribeUserid;
	}

	public void setSubcribeUserid(String subcribeUserid) {
		this.subcribeUserid = subcribeUserid;
	}

	public String getPublicUserid() {
		return publicUserid;
	}

	public void setPublicUserid(String publicUserid) {
		this.publicUserid = publicUserid;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getMtime() {
		return mtime;
	}
	
	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	public int getSubcribeType() {
		return subcribeType;
	}

	public void setSubcribeType(int subcribeType) {
		this.subcribeType = subcribeType;
	}

	@Override
	public String toString() {

		return "SubscriptionRelation [id=" + id + ", subcribeUserid="

				+ subcribeUserid + ", publicUserid=" + publicUserid

				+ ", accountType=" + accountType + ", ctime=" + ctime

				+ ", mtime=" + mtime + ", subcribeType=" + subcribeType + "]";

	}  



}

