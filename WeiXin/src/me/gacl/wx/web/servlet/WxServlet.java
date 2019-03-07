package me.gacl.wx.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.liufeng.course.service.CoreService;


/**
 * 
 * 在微信公众平台开发者文档上，关于公众号接入这一节内容在接入指南上写的比较详细的，文档中说接入公众号需要3个步骤，分别是：
 * 
 * 　　1、填写服务器配置 　　2、验证服务器地址的有效性 　　3、依据接口文档实现业务逻辑
 * 
 * 　　其实，第3步已经不能算做公众号接入的步骤，而是接入之后，开发人员可以根据微信公众号提供的接口所能做的一些开发。
 * 
 * 　　第1步中服务器配置包含服务器地址（URL）、Token和EncodingAESKey。
 * 　　		服务器地址即公众号后台提供业务逻辑的入口地址，目前只支持80端口，之后包括接入验证以及任何其它的操作的请求（例如消息的发送、菜单管理、素材管理等）
 * 	都要从这个地址进入。接入验证和其它请求的区别就是，接入验证时是get请求，其它时候是post请求；
 * 　　Token可由开发者可以任意填写，用作生成签名（该Token会和接口URL中包含的Token进行比对，从而验证安全性）；
 * 
 * 　　EncodingAESKey由开发者手动填写或随机生成，将用作消息体加解密密钥。本例中全部以未加密的明文消息方式，不涉及此配置项。
 * 
 * 　　第2步，验证服务器地址的有效性，当点击“提交”按钮后，微信服务器将发送一个http的get请求到刚刚填写的服务器地址，并且携带四个参数：
 * 　　接到请求后，我们需要做如下三步，若确认此次GET请求来自微信服务器，原样返回echostr参数内容，则接入生效，否则接入失败。
 * 
 * 　　1. 将token、timestamp、nonce三个参数进行字典序排序 　　
 * 	2. 将三个参数字符串拼接成一个字符串进行sha1加密 　　
 * 	3.开发者获得加密后的字符串可与signature对比，标识该请求来源于微信 编写一个servlevt,在其中的doGet方法中定义校验方法
 * 
 * 
 * 　	我这里用的Servlet3.0,使用Servlet3.0的好处就是可以直接使用@WebServlet注解映射Servlet的访问路径,不再需要在web.xml文件中进行配置.
 * 
 * Created by xdp on 2016/1/25.
 * 使用@WebServlet注解配置WxServlet,urlPatterns属性指明了WxServlet的访问路径
 */
@WebServlet(urlPatterns="/WxServlet")
public class WxServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * Token可由开发者可以任意填写，用作生成签名（该Token会和接口URL中包含的Token进行比对，从而验证安全性）
     * 比如这里我将Token设置为gacl
     */
    private final String TOKEN = "gacl1";

    /**
     * 处理微信服务器发来的消息
     */
    /*
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO 接收、处理、响应由微信服务器转发的用户发送给公众帐号的消息
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("请求进入");
        String result = "";
        try {
            Map<String,String> map = MessageHandlerUtil.parseXml(request);
            System.out.println("开始构造消息");
            result = MessageHandlerUtil.buildXml(map);
            System.out.println(result);
            if(result.equals("")){
                result = "未正确响应";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发生异常："+ e.getMessage());
        }
        response.getWriter().println(result);
    }*/
    
    
    /**
     * 处理微信服务器发来的消息
     */
   /* protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO 接收、处理、响应由微信服务器转发的用户发送给公众帐号的消息
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("请求进入");
        String responseMessage;
        try {
            //解析微信发来的请求,将解析后的结果封装成Map返回
            Map<String,String> map = MessageHandlerUtil.parseXml(request);
            System.out.println("开始构造响应消息");
            responseMessage = MessageHandlerUtil.buildResponseMessage(map);
            System.out.println(responseMessage);
            if(responseMessage.equals("")){
                responseMessage ="未正确响应";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发生异常："+ e.getMessage());
            responseMessage ="未正确响应";
        }
        //发送响应消息
        response.getWriter().println(responseMessage);
    }*/
    
    
    /** 
     * 处理微信服务器发来的消息 
     */  
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）  微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
        request.setCharacterEncoding("UTF-8");
        //在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上 
        response.setCharacterEncoding("UTF-8");  
  
        // 调用核心业务类接收消息、处理消息  
       /* 从doPost方法的实现可以看到，它是通过调用CoreService类的processRequest方法接收、处理消息的，这样做的目的是为了解耦，
          	即业务相关的操作都不在Servlet里处理，而是完全交由业务核心类CoreService去做*/
        String respMessage = CoreService.processRequest(request);  
          
        // 响应消息  
        PrintWriter out = response.getWriter();  
        out.print(respMessage);
        // 返回给微信服务器
        out.write(respMessage);
        out.close();  
    }  
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("开始校验签名");
        /**
         * 接收微信服务器发送请求时传递过来的4个参数
         */
        String signature = request.getParameter("signature");//微信加密签名signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String timestamp = request.getParameter("timestamp");//时间戳
        String nonce = request.getParameter("nonce");//随机数
        String echostr = request.getParameter("echostr");//随机字符串
        //排序
        String sortString = sort(TOKEN, timestamp, nonce);
        //加密
        String mySignature = sha1(sortString);
        //校验签名
        if (mySignature != null && mySignature != "" && mySignature.equals(signature)) {
            System.out.println("签名校验通过。");
            //如果检验成功输出echostr，微信服务器接收到此输出，才会确认检验完成。
            response.getWriter().println(echostr);
            // 接入成功原样返回echostr给微信服务器
            //response.getWriter().write(echostr);
        } else {
            System.out.println("签名校验失败.");
        }

    }

    /**
     * 排序方法
     *
     * @param token
     * @param timestamp
     * @param nonce
     * @return
     */
    public String sort(String token, String timestamp, String nonce) {
        String[] strArray = {token, timestamp, nonce};
        Arrays.sort(strArray);
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str);
        }

        return sb.toString();
    }

    /**
     * 将字符串进行sha1加密
     *
     * @param str 需要加密的字符串
     * @return 加密后的内容
     */
    public String sha1(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}