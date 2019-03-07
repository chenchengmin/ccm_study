package org.liufeng.course.message.req;

/**
 * 请求消息之文本消息：文本消息
 * 
 * @author ccm
 * @date 2013-05-19
 */
public class TextMessage extends BaseMessage {
	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}