
package org.liufeng.weixin.pojo;

/**
 * ��ͨ��ť���Ӱ�ť��
 * 
 * �������Ӳ˵���ķ�װ��������Ӳ˵�����������ģ�û���Ӳ˵��Ĳ˵���п����Ƕ����˵��Ҳ�п����ǲ��������˵���һ���˵��������Ӳ˵���һ��������������ԣ�
 * type��name��key
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class CommonButton extends Button {
	private String type;
	private String key;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}