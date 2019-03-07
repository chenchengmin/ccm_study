package me.gacl.wx.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.SSLProtocolSocketFactory;
import org.apache.http.HttpStatus;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * ͨ��΢���زĽӿ��ϴ��ز�
 * Created by allen on 2016/1/29.
 */
public class WeChatApiUtil {
    // token �ӿ�(GET)
    private static final String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    // �ز��ϴ�(POST)https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
    private static final String UPLOAD_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/upload";
    // �ز�����:��֧����Ƶ�ļ�������(GET)
    private static final String DOWNLOAD_MEDIA = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";

    public static String getTokenUrl(String appId, String appSecret) {
        return String.format(ACCESS_TOKEN, appId, appSecret);
    }

    public static String getDownloadUrl(String token, String mediaId) {
        return String.format(DOWNLOAD_MEDIA, token, mediaId);
    }

    /**
     * ͨ�ýӿڻ�ȡTokenƾ֤
     *
     * @param appId
     * @param appSecret
     * @return
     */
    public static String getToken(String appId, String appSecret) {
        if (appId == null || appSecret == null) {
            return null;
        }

        String token = null;
        String tockenUrl = WeChatApiUtil.getTokenUrl(appId, appSecret);
        String response = httpsRequestToString(tockenUrl, "GET", null);
        JSONObject jsonObject = JSON.parseObject(response);
        if (null != jsonObject) {
            try {
                token = jsonObject.getString("access_token");
            } catch (JSONException e) {
                token = null;// ��ȡtokenʧ��
            }
        }
        return token;
    }

    /**
     * ΢�ŷ������ز��ϴ�
     *
     * @param file  ������media
     * @param token access_token
     * @param type  typeֻ֧�����������ز�(video/image/voice/thumb)
     */
    public static JSONObject uploadMedia(File file, String token, String type) {
        if (file == null || token == null || type == null) {
            return null;
        }

        if (!file.exists()) {
            System.out.println("�ϴ��ļ�������,����!");
            return null;
        }

        String url = UPLOAD_MEDIA;
        JSONObject jsonObject = null;
        PostMethod post = new PostMethod(url);
        post.setRequestHeader("Connection", "Keep-Alive");
        post.setRequestHeader("Cache-Control", "no-cache");
        FilePart media;
        HttpClient httpClient = new HttpClient();
        //�����κ����͵�֤��
        Protocol myhttps = new Protocol("https", new SSLProtocolSocketFactory(), 443);
        Protocol.registerProtocol("https", myhttps);

        try {
            media = new FilePart("media", file);
            Part[] parts = new Part[]{new StringPart("access_token", token),
                    new StringPart("type", type), media};
            MultipartRequestEntity entity = new MultipartRequestEntity(parts,
                    post.getParams());
            post.setRequestEntity(entity);
            int status = httpClient.executeMethod(post);
            if (status == HttpStatus.SC_OK) {
                String text = post.getResponseBodyAsString();
                jsonObject = JSONObject.parseObject(text);
            } else {
                System.out.println("upload Media failure status is:" + status);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * ��ý�����ؽӿ�
     *
     * @param fileName �زĴ洢�ļ�·��
     * @param token    ��֤token
     * @param mediaId  �ز�ID����Ӧ�ϴ����ȡ����ID��
     * @return �ز��ļ�
     * @comment ��֧����Ƶ�ļ�������
     */
    public static File downloadMedia(String fileName, String token,
                                     String mediaId) {
        String url = getDownloadUrl(token, mediaId);
        return httpRequestToFile(fileName, url, "GET", null);
    }

    /**
     * ��ý�����ؽӿ�
     *
     * @param fileName �زĴ洢�ļ�·��
     * @param mediaId  �ز�ID����Ӧ�ϴ����ȡ����ID��
     * @return �ز��ļ�
     * @comment ��֧����Ƶ�ļ�������
     */
    public static File downloadMedia(String fileName, String mediaId) {
        String appId = "wxbe4d433e857e8bb1";
        String appSecret = "ccbc82d560876711027b3d43a6f2ebda";
        String token = WeChatApiUtil.getToken(appId, appSecret);
        return downloadMedia(fileName,token,mediaId);
    }

    /**
     * ��http��ʽ��������,����������Ӧ����������ļ�
     *
     * @param path   ����·��
     * @param method ���󷽷�
     * @param body   ��������
     * @return ������Ӧ�Ĵ洢���ļ�
     */
    public static File httpRequestToFile(String fileName, String path, String method, String body) {
        if (fileName == null || path == null || method == null) {
            return null;
        }

        File file = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        FileOutputStream fileOut = null;
        try {
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(method);
            if (null != body) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(body.getBytes("UTF-8"));
                outputStream.close();
            }

            inputStream = conn.getInputStream();
            if (inputStream != null) {
                file = new File(fileName);
            } else {
                return file;
            }

            //д�뵽�ļ�
            fileOut = new FileOutputStream(file);
            if (fileOut != null) {
                int c = inputStream.read();
                while (c != -1) {
                    fileOut.write(c);
                    c = inputStream.read();
                }
            }
        } catch (Exception e) {
        } finally {
            if (conn != null) {
                conn.disconnect();
            }

            /*
             * ����ر��ļ���
             * ����JDK����ʱ���ļ���ռ�����������޷�����
             */
            try {
                inputStream.close();
                fileOut.close();
            } catch (IOException execption) {
            }
        }
        return file;
    }

    /**
     * �ϴ��ز�
     * @param filePath ý���ļ�·��(����·��)
     * @param type ý���ļ����ͣ��ֱ���ͼƬ��image����������voice������Ƶ��video��������ͼ��thumb��
     * @return
     */
    public static JSONObject uploadMedia(String filePath,String type){
        File f = new File(filePath); // ��ȡ�����ļ�
        String appId = "wx8222584e6a9cd6b3";
        String appSecret = "36b96ccd710ece7476ca879e63fa55dd";
        String token = WeChatApiUtil.getToken(appId, appSecret);
        JSONObject jsonObject = uploadMedia(f, token, type);
        return jsonObject;
    }

    /**
     * ����������https��ʽ�������󲢽�������Ӧ������String��ʽ����
     *
     * @param path   ����·��
     * @param method ���󷽷�
     * @param body   ����������
     * @return ������Ӧ����ת�����ַ�����Ϣ
     */
    public static String httpsRequestToString(String path, String method, String body) {
        if (path == null || method == null) {
            return null;
        }

        String response = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        HttpsURLConnection conn = null;
        try {
            TrustManager[] tm = {new JEEWeiXinX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            System.out.println(path);
            URL url = new URL(path);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(method);
            if (null != body) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(body.getBytes("UTF-8"));
                outputStream.close();
            }

            inputStream = conn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            response = buffer.toString();
        } catch (Exception e) {

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            try {
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
            } catch (IOException execption) {

            }
        }
        return response;
    }

    public static void main(String[] args) throws Exception{
        //ý���ļ�·��			
        String filePath = "D:/ccm/workspace/WeiXin/WebContent/media/image/��.jpg";
        //String filePath = "D:/JavaSoftwareDevelopeFolder/IntelliJ IDEA_Workspace/WxStudy/web/media/voice/voice.mp3";
        //String filePath = "D:\\JavaSoftwareDevelopeFolder\\IntelliJ IDEA_Workspace\\WxStudy\\web\\media\\video\\Сƻ��.mp4";
        
        //ý���ļ�����
        String type = "image";
        //String type = "voice";
        //String type = "video";
        
        JSONObject uploadResult = uploadMedia(filePath, type);
        //{"media_id":"dSQCiEHYB-pgi7ib5KpeoFlqpg09J31H28rex6xKgwWrln3HY0BTsoxnRV-xC_SQ","created_at":1455520569,"type":"image"}
        System.out.println(uploadResult.toString());

        //���ظո��ϴ���ͼƬ��id����
        String media_id = uploadResult.getString("media_id");
        File file = downloadMedia("D:/" + media_id + ".png", media_id);
        System.out.println(file.getName());

    }
}

/**
 * https��ʽ��������
 * @author Administrator
 *
 */
class JEEWeiXinX509TrustManager implements X509TrustManager {
    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}