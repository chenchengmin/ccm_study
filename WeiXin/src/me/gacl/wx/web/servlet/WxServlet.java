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
 * ��΢�Ź���ƽ̨�������ĵ��ϣ����ڹ��ںŽ�����һ�������ڽ���ָ����д�ıȽ���ϸ�ģ��ĵ���˵���빫�ں���Ҫ3�����裬�ֱ��ǣ�
 * 
 * ����1����д���������� ����2����֤��������ַ����Ч�� ����3�����ݽӿ��ĵ�ʵ��ҵ���߼�
 * 
 * ������ʵ����3���Ѿ������������ںŽ���Ĳ��裬���ǽ���֮�󣬿�����Ա���Ը���΢�Ź��ں��ṩ�Ľӿ���������һЩ������
 * 
 * ������1���з��������ð�����������ַ��URL����Token��EncodingAESKey��
 * ����		��������ַ�����ںź�̨�ṩҵ���߼�����ڵ�ַ��Ŀǰֻ֧��80�˿ڣ�֮�����������֤�Լ��κ������Ĳ���������������Ϣ�ķ��͡��˵������زĹ���ȣ�
 * 	��Ҫ�������ַ���롣������֤�����������������ǣ�������֤ʱ��get��������ʱ����post����
 * ����Token���ɿ����߿���������д����������ǩ������Token��ͽӿ�URL�а�����Token���бȶԣ��Ӷ���֤��ȫ�ԣ���
 * 
 * ����EncodingAESKey�ɿ������ֶ���д��������ɣ���������Ϣ��ӽ�����Կ��������ȫ����δ���ܵ�������Ϣ��ʽ�����漰�������
 * 
 * ������2������֤��������ַ����Ч�ԣ���������ύ����ť��΢�ŷ�����������һ��http��get���󵽸ո���д�ķ�������ַ������Я���ĸ�������
 * �����ӵ������������Ҫ��������������ȷ�ϴ˴�GET��������΢�ŷ�������ԭ������echostr�������ݣ��������Ч���������ʧ�ܡ�
 * 
 * ����1. ��token��timestamp��nonce�������������ֵ������� ����
 * 	2. �����������ַ���ƴ�ӳ�һ���ַ�������sha1���� ����
 * 	3.�����߻�ü��ܺ���ַ�������signature�Աȣ���ʶ��������Դ��΢�� ��дһ��servlevt,�����е�doGet�����ж���У�鷽��
 * 
 * 
 * ��	�������õ�Servlet3.0,ʹ��Servlet3.0�ĺô����ǿ���ֱ��ʹ��@WebServletע��ӳ��Servlet�ķ���·��,������Ҫ��web.xml�ļ��н�������.
 * 
 * Created by xdp on 2016/1/25.
 * ʹ��@WebServletע������WxServlet,urlPatterns����ָ����WxServlet�ķ���·��
 */
@WebServlet(urlPatterns="/WxServlet")
public class WxServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * Token���ɿ����߿���������д����������ǩ������Token��ͽӿ�URL�а�����Token���бȶԣ��Ӷ���֤��ȫ�ԣ�
     * ���������ҽ�Token����Ϊgacl
     */
    private final String TOKEN = "gacl1";

    /**
     * ����΢�ŷ�������������Ϣ
     */
    /*
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO ���ա�������Ӧ��΢�ŷ�����ת�����û����͸������ʺŵ���Ϣ
        // ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("�������");
        String result = "";
        try {
            Map<String,String> map = MessageHandlerUtil.parseXml(request);
            System.out.println("��ʼ������Ϣ");
            result = MessageHandlerUtil.buildXml(map);
            System.out.println(result);
            if(result.equals("")){
                result = "δ��ȷ��Ӧ";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("�����쳣��"+ e.getMessage());
        }
        response.getWriter().println(result);
    }*/
    
    
    /**
     * ����΢�ŷ�������������Ϣ
     */
   /* protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO ���ա�������Ӧ��΢�ŷ�����ת�����û����͸������ʺŵ���Ϣ
        // ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("�������");
        String responseMessage;
        try {
            //����΢�ŷ���������,��������Ľ����װ��Map����
            Map<String,String> map = MessageHandlerUtil.parseXml(request);
            System.out.println("��ʼ������Ӧ��Ϣ");
            responseMessage = MessageHandlerUtil.buildResponseMessage(map);
            System.out.println(responseMessage);
            if(responseMessage.equals("")){
                responseMessage ="δ��ȷ��Ӧ";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("�����쳣��"+ e.getMessage());
            responseMessage ="δ��ȷ��Ӧ";
        }
        //������Ӧ��Ϣ
        response.getWriter().println(responseMessage);
    }*/
    
    
    /** 
     * ����΢�ŷ�������������Ϣ 
     */  
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        // ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩  ΢�ŷ�����POST��Ϣʱ�õ���UTF-8���룬�ڽ���ʱҲҪ��ͬ���ı��룬�������Ļ����룻
        request.setCharacterEncoding("UTF-8");
        //����Ӧ��Ϣ���ظ���Ϣ���û���ʱ��Ҳ�����뷽ʽ����ΪUTF-8��ԭ��ͬ�� 
        response.setCharacterEncoding("UTF-8");  
  
        // ���ú���ҵ���������Ϣ��������Ϣ  
       /* ��doPost������ʵ�ֿ��Կ���������ͨ������CoreService���processRequest�������ա�������Ϣ�ģ���������Ŀ����Ϊ�˽��
          	��ҵ����صĲ���������Servlet�ﴦ��������ȫ����ҵ�������CoreServiceȥ��*/
        String respMessage = CoreService.processRequest(request);  
          
        // ��Ӧ��Ϣ  
        PrintWriter out = response.getWriter();  
        out.print(respMessage);
        // ���ظ�΢�ŷ�����
        out.write(respMessage);
        out.close();  
    }  
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("��ʼУ��ǩ��");
        /**
         * ����΢�ŷ�������������ʱ���ݹ�����4������
         */
        String signature = request.getParameter("signature");//΢�ż���ǩ��signature����˿�������д��token�����������е�timestamp������nonce������
        String timestamp = request.getParameter("timestamp");//ʱ���
        String nonce = request.getParameter("nonce");//�����
        String echostr = request.getParameter("echostr");//����ַ���
        //����
        String sortString = sort(TOKEN, timestamp, nonce);
        //����
        String mySignature = sha1(sortString);
        //У��ǩ��
        if (mySignature != null && mySignature != "" && mySignature.equals(signature)) {
            System.out.println("ǩ��У��ͨ����");
            //�������ɹ����echostr��΢�ŷ��������յ���������Ż�ȷ�ϼ�����ɡ�
            response.getWriter().println(echostr);
            // ����ɹ�ԭ������echostr��΢�ŷ�����
            //response.getWriter().write(echostr);
        } else {
            System.out.println("ǩ��У��ʧ��.");
        }

    }

    /**
     * ���򷽷�
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
     * ���ַ�������sha1����
     *
     * @param str ��Ҫ���ܵ��ַ���
     * @return ���ܺ������
     */
    public String sha1(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // �ֽ�����ת��Ϊ ʮ������ ��
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