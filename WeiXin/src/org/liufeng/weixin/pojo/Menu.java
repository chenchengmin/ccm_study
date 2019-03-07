
package org.liufeng.weixin.pojo;

/**
 * 菜单： 最后是整个菜单对象的封装，菜单对象包含多个菜单项（最多只能有3个），这些菜单项即可以是子菜单项（不含二级菜单的一级菜单），也可以是父菜单项（
 * 包含二级菜单的菜单项），如果能明白上面所讲的，再来看封装后的代码就很容易理解了：
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class Menu {
	private Button[] button;

	public Button[] getButton() {
		return button;
	}

	public void setButton(Button[] button) {
		this.button = button;
	}
}