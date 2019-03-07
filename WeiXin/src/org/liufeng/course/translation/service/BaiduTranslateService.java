package org.liufeng.course.translation.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.liufeng.courese.translation.pojo.TranslateResult;

import com.baidu.translate.demo.TransApi;
import com.google.gson.Gson;

/**
 * 
 * @author ccm
 * @date 2013-10-21
 */
public class BaiduTranslateService {
	
	/**
	 * �ٶȷ���appId����Կ
	 */
	 private static final String APP_ID = "20180518000161404";
	 private static final String SECURITY_KEY = "b_GEyQs0yoPeEXyy8LpL";
	
	/**
	 * ����http�����ȡ���ؽ��
	 * 
	 * @param requestUrl �����ַ
	 * @return
	 */
	public static String httpRequest(String requestUrl) {
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

			httpUrlConn.setDoOutput(false);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);

			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();

			// �����ص�������ת�����ַ���
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// �ͷ���Դ
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();

		} catch (Exception e) {
		}
		return buffer.toString();
	}

	/**
	 * utf����
	 * 
	 * @param source
	 * @return
	 */
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * ���루��->Ӣ Ӣ->�� ��->�� ��
	 * 
	 * @param source
	 * @return
	 */
	public static String translate(String source) {
		String dst = null;
		
		//ʵ�ʿ�����������ĳɵ���ģʽ---����ֻ�ǲ��Է��빦�ܾ�ֱ��new��
		TransApi api = new TransApi(APP_ID, SECURITY_KEY);

		// ��ѯ���������
		try {
			// ��ѯ����ȡ���ؽ��
			//String json =  api.getTransResult(source, "auto", "");
			String json =  api.getTransResult(source, "auto", "auto");
			// ͨ��Gson���߽�jsonת����TranslateResult����
			TranslateResult translateResult = new Gson().fromJson(json, TranslateResult.class);
			// ȡ��translateResult�е�����
			dst = translateResult.getTrans_result().get(0).getDst();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (null == dst)
			dst = "����ϵͳ�쳣�����Ժ��ԣ�";
		return dst;
	}

	public static void main(String[] args) {
		// ��������The network really powerful
		System.out.println(translate("������ǿ��"));
	}
}
