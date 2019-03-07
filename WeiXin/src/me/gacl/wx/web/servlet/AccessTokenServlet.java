package me.gacl.wx.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.gacl.wx.Common.AccessTokenInfo;
import me.gacl.wx.entry.AccessToken;
import me.gacl.wx.util.NetWorkHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * ����һ��Ĭ��������servlet����init����������һ���µ��߳�ȥ��ȡaccessToken
 * 
 * ��ȡaccess_token�����Լ�����ʵ��
 * 
 * ����������õķ����������ģ�����һ��Ĭ��������servlet����init����������һ��Thread,��������ж���һ������ѭ���ķ�����
 * ������ȡaccess_token������ȡ�ɹ��󣬴˽�������7000��(7000��=1.944444444444444Сʱ)����������3���Ӽ�����ȡ
 * 
 * 
 * ���ڻ�ȡaccessToken��Servlet Created by xdp on 2016/1/25.
 */
@WebServlet(
        name = "AccessTokenServlet",
        urlPatterns = {"/AccessTokenServlet"},
        loadOnStartup = 1,
        initParams = {
                @WebInitParam(name = "appId", value = "wx8222584e6a9cd6b3"),
                @WebInitParam(name = "appSecret", value = "36b96ccd710ece7476ca879e63fa55dd")
        })
public class AccessTokenServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public void init() throws ServletException {
        System.out.println("����WebServlet");
        super.init();

        final String appId = getInitParameter("appId");
        final String appSecret = getInitParameter("appSecret");

        //����һ���µ��߳�
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        //��ȡaccessToken
                        AccessTokenInfo.accessToken = getAccessToken(appId, appSecret);
                        //��ȡ�ɹ�
                        if (AccessTokenInfo.accessToken != null) {
                            //��ȡ��access_token ����7000��,��Լ2��Сʱ����
                            Thread.sleep(7000 * 1000);
                            //Thread.sleep(10 * 1000);//10���ӻ�ȡһ��
                        } else {
                            //��ȡʧ��
                            Thread.sleep(1000 * 3); //��ȡ��access_tokenΪ�� ����3��
                        }
                    } catch (Exception e) {
                        System.out.println("�����쳣��" + e.getMessage());
                        e.printStackTrace();
                        try {
                            Thread.sleep(1000 * 10); //�����쳣����1��
                        } catch (Exception e1) {

                        }
                    }
                }

            }
        }).start();
    }

    /**
     * ��ȡaccess_token
     *
     * @return AccessToken
     */
    private AccessToken getAccessToken(String appId, String appSecret) {
        NetWorkHelper netHelper = new NetWorkHelper();
        /**
         * �ӿڵ�ַΪhttps://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET������grant_type�̶�дΪclient_credential���ɡ�
         */
        
        String Url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", appId, appSecret);
        //������Ϊhttps��get���󣬷��ص����ݸ�ʽΪ{"access_token":"ACCESS_TOKEN","expires_in":7200}
        String result = netHelper.getHttpsResponse(Url, "");
        System.out.println("��ȡ����access_token="+result);
        //ʹ��FastJson��Json�ַ���������Json����
        JSONObject json = JSON.parseObject(result);
        AccessToken token = new AccessToken();
        token.setAccessToken(json.getString("access_token"));
        token.setExpiresin(json.getInteger("expires_in"));
        return token;
    }
}