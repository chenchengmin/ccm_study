package org.liufeng.course.message.resp;

/**
 * ��Ϣ���ࣨ�����ʺ� -> ��ͨ�û���
 * 
 * ͬ��������Ϣ�ظ��ж����������Ϣ���е��ֶ���ȡ��������װ��һ�����࣬��Щ���е��ֶΰ�����ToUserName�����շ��ʺţ��û���OPEN_ID����FromUserName�������ߵ�΢�źţ���CreateTime����Ϣ�Ĵ���ʱ�䣩��MsgType����Ϣ���ͣ���FuncFlag����Ϣ���Ǳ��ʶ������װ�����org.liufeng.course.message.resp.BaseMessage
 * 
 * 
 * @author liufeng
 * @date 2013-05-19
 */
public class BaseMessage {
	// ���շ��ʺţ��յ���OpenID��
	private String ToUserName;
	// ������΢�ź�
	private String FromUserName;
	// ��Ϣ����ʱ�� �����ͣ�
	private long CreateTime;
	// ��Ϣ���ͣ�text/music/news��
	private String MsgType;
	// λ0x0001����־ʱ���Ǳ���յ�����Ϣ
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
