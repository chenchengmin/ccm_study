package me.gacl.wx.Common;

import me.gacl.wx.entry.AccessToken;

/**
 * 定义一个AccessTokenInfo类,用于存放获取到的AccessToken
 * Created by xdp on 2016/1/25.
 */
public class AccessTokenInfo {

    //注意是静态的：一直保持着
    public static AccessToken accessToken = null;
}