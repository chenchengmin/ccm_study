package org.liufeng.course.message.resp;

/**
 * 消息基类（公众帐号 -> 普通用户）
 * 
 * 同样，把消息回复中定义的所有消息都有的字段提取出来，封装成一个基类，这些公有的字段包括：ToUserName（接收方帐号，用户的OPEN_ID）、FromUserName（开发者的微信号）、CreateTime（消息的创建时间）、MsgType（消息类型）、FuncFlag（消息的星标标识），封装后基类org.liufeng.course.message.resp.BaseMessage
 * 
 * 
 * @author liufeng
 * @date 2013-05-19
 */
public class BaseMessage {
	// 接收方帐号（收到的OpenID）
	private String ToUserName;
	// 开发者微信号
	private String FromUserName;
	// 消息创建时间 （整型）
	private long CreateTime;
	// 消息类型（text/music/news）
	private String MsgType;
	// 位0x0001被标志时，星标刚收到的消息
	private int FuncFlag;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public int getFuncFlag() {
		return FuncFlag;
	}

	public void setFuncFlag(int funcFlag) {
		FuncFlag = funcFlag;
	}
}
