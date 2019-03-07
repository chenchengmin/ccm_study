
package org.liufeng.weixin.pojo;

/**
 * 按钮的基类：首先是菜单项的基类，所有一级菜单、二级菜单都共有一个相同的属性，那就是name
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class Button {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}