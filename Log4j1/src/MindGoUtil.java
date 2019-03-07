

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.Set;

import com.erayt.base.sequence.SequenceUtils;
import com.erayt.solar2.logging.Log;
import com.erayt.solar2.logging.LogFactory;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.mina.core.buffer.IoBuffer;

import com.alibaba.fastjson.JSONObject;


/**
 * 同花顺通信模块工具类
 * 
 * @作者: ccm
 * @日期: 2018-05-30 下午09:57:33
 *
 */
public final class MindGoUtil {

	
	private MindGoUtil() {}

	private static final Log LOGGER = LogFactory.getLogger(MindGoUtil.class);
	
	/**
	 * 
	 * @方法描述: mina未配置解析器,调用此方法解析获取ApaceMina server端获取到的请求字符串
	 * @param message
	 * @返回类型: String
	 * @作者: ccm
	 * @日期: 2018-05-30 下午09:57:33
	 */
	public static String getRequestStr (Object message){
		IoBuffer ioBuffer = (IoBuffer)message;
		byte[] b = new byte [ioBuffer.limit()];
		ioBuffer.get(b); 
		StringBuffer buffer = new StringBuffer();   
		for (int i = 0; i < b.length; i++) {   
		  buffer.append((char) b [i]);   
		}   
		String requestStr = buffer.toString();
		return requestStr;
	}
	
	/**
	 * 解析请求，封装到map中待用
	 * @param jsonStr
	 * @return
	 */
	public static Map<String, Object> getRequestParamValueMap(String jsonStr) {

		Map<String, Object> map = new HashMap<String, Object>();

		JSONObject job = JSONObject.parseObject(jsonStr);
		LOGGER.debug("*********jsonJob=" + job);
		
		/*Set<String> keySet = job.keySet();
		for (String key : keySet) {
			Object value = job.get(key);
			map.put(key, value);
		}*/
		
		//findbugs修改keyset遍历改entrySet遍历
		Set<Entry<String, Object>> entrySet = job.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			map.put(entry.getKey(), entry.getValue());
		}
		
		map.put(MindGoConstants.MINDGO_TOPBANK, MindGoConstants.MINDGO_TOPBANK_VALUE);
		return map;
	}
	
	/**
	 *判定给定字符串是否是json格式的
	 * @param content
	 * @return true：是json格式
	 */
	public static boolean isJson(String jsonStr){
	    try {
	        JSONObject jsonObject= JSONObject.parseObject(jsonStr);
	        LOGGER.debug("%%%%%%jsonObject", jsonStr);
	        return  true;
	   } catch (Exception e) {
	        return false;
	  }
	}
	
	 /**
     * @Description: 判断字符是否为空
     *               字符串==null or 空字符串(包括都是空格的) ，都算为空<br>
     * @Author: ccm
     * @Since: 2018年05月30日
     * @param str
     * @return
     */
    public static boolean isStrEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

	
	/**
	 * 获取json中指定的值
	 * @param json
	 * @param spiltStr
	 * @return
	 */
	public static String getJsonValue(String jsonstr,String spiltStr){
		if(jsonstr.indexOf(spiltStr) != -1){  
			int spiltIndex = jsonstr.indexOf(spiltStr);
			String spiltString = jsonstr.substring(spiltIndex);
			
			int indexStart = spiltString.indexOf(":\"",1)+2;
			int indexEnd = spiltString.indexOf("\"}", 1);
			String returnStr = spiltString.substring(indexStart,indexEnd);
			return returnStr;
			
		}else{
			return "";
		}
	}
	
	/*public static void main(String[] args) {
		
		String json = "{\"data\":{\"result\":{\"sendS90001\":{\"account\":\"00001180610031980010\",\"address\":\"testAdress\",\"bankId\":\"4030300\",\"cardId\":\"00001180610031980010\",\"certificateCode\":\"1320\",\"certificateId\":\"1\",\"clientId\":\"0003198001\",\"custManager\":\"test\",\"customerName\":\"test\"," +
				"}},\"messageId\":\"/login.do\"},\"success\":true}";
		
		String a = getJsonValue(json, "\"account\":\"");
		System.out.println(a);
		
		//String json = "{\"j1\":\"json1\",\"j2\":\"2\",\"j3\":false}";
		//Map map = getRequestParamValueMap(json);
		Map map = JSONObject.parseObject(json, Map.class);
		System.out.println(map.toString());
		Map map = new HashMap<>();
		map.put("j1", "4");
		map.put("j2", "3");
		System.out.println(map.size());
		System.out.println(map.get("j1").toString());
		System.out.println(map.get("j2").toString());
		System.out.println(map.get("j3").toString());
		System.out.println(map.get("j4").toString());
		 String tdJsonStr= "{\"data\":{\"result\":{\"order\":{\"errMsg\":\"空头持仓不足\",\"packname\":\"E50001\",\"reserveField\":\"\",\"returnCode\":\"S024\",\"seqId\":\"0000000000000000\",\"timeStamp\":20180604155939}},\"messageId\":\"/doAutdForward.do\"},\"success\":true}";
		 System.out.println(formatTDResponse(tdJsonStr, "S50001"));
	}*/
	
	
	
	
	
	/**
	 失败：
     * {
	    "data": {
	        "result": {
	            "packname": "S50001", 
	            "errMsg": "持仓不足", 
	        }
	    }, 
	    "responseId": "876754553456", 
	    "telePhone":"13456726260",
	    "success": false
	 }
	 * 失败处理返回
	 * 
	 * @param errorMessage
	 *            错误信息
	 * @param tranCode
	 *            错误交易码
	 * @return
	 */
	public static String handlerFailedResponse(String errorMessage,String tranCode) {
		JSONObject result = new JSONObject();
		JSONObject dataJson = new JSONObject();
		JSONObject formatJson = new JSONObject();

		result.put("packname", tranCode);
		result.put("errMsg", errorMessage);

		dataJson.put("result", result);

		formatJson.put("success", false);
		formatJson.put("data", dataJson);
		String  returnJson = StringEscapeUtils.unescapeJavaScript(formatJson.toJSONString());//消除json转义
		return returnJson;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public static String getTimeString() {
		// 设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		// new Date()为获取当前系统时间，也可使用当前时间戳
		String date = df.format(new Date());
		return date;
	}
	
	/**
	 * 获取系统当前日期    yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date getDateTimeString() {
		// 设置日期格式
		DateFormat dateTimeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// new Date()为获取当前系统时间
		String dateStr = dateTimeformat.format(new Date());
		Date date = null;
		try {
			date = dateTimeformat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 获取虚拟设备号、雪花算法
	 * @author ccm
	 * @return
	 */
	public static String makeDeviceNumber(){
		synchronized (MindGoUtil.class) {
			String deviceNumber = String.valueOf(SequenceUtils.getSequenceNo());
			return deviceNumber;
		}
	}
	
	/**
	 * hibernate校验框架错误信息处理
	 * @param errorMap
	 * @Author: ccm
	 * @return
	 */
	public static String formatErrorMsg(Map<String, StringBuffer> errorMap) {
		StringBuilder builder =  new StringBuilder();
		for(Map.Entry<String, StringBuffer> m : errorMap.entrySet()){
			//builder.append(m.getKey()).append(':').append(m.getValue().toString()).append(';');
			builder.append(m.getValue().toString()).append(';');
		}
		return builder.toString();
	}
	
	
	/**
	 * 判定输入的字符串是否整数：速度最快
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {  
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
        return pattern.matcher(str).matches();  
	}
	
	
	/**
	 * 缓存黑名单数据导文件系统
	 * @param fileName	文件名
	 * @param data		黑名单的json数据
	 * @param filePath	文件路径
	 * @since 2018-98-17
	 * @author ccm
	 */
	public  static void saveDataToFile(String fileName,String data,String filePath) {
		BufferedWriter writer = null;
		File file1 = new File(filePath);
        File file = new File(filePath+fileName);
        
        if(!file1.exists()){
        	 if (file1.mkdirs()) {
        		 LOGGER.error("-->量化黑名单创建目录成功：{}",file1.getPath());
             }
		}
        
        //如果文件不存在，则新建一个
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
            	LOGGER.info("%%%%%%%%%%saveDataToFile异常={}", e.getMessage());
            }
        }
        
        //写入
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false), "UTF-8"));
            writer.write(data);
        } catch (IOException e) {
        	LOGGER.info("%%%%%%%%%%saveDataToFile异常={}", e.getMessage());
        }finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
            	LOGGER.info("%%%%%%%%%%saveDataToFile异常={}", e.getMessage());
            }
        }
    }
	
	/**
	 * 获取黑名单数据
	 * @param fileName
	 * @param filePath
	 * @return
	 */
	public static String getDatafromFile(String fileName,String filePath) {
			
		String path = filePath+fileName;
		
		// return blackList String
		String laststr = "";
		
		//jude blackfile is exists
		File file = new File(path);
        if(!file.exists()){
        	return laststr;//not use io
        }
		
		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer(); 
		try {
			FileInputStream fileInputStream = new FileInputStream(path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				buffer.append(tempString);
			}
			laststr = buffer.toString();
		} catch (IOException e) {
			LOGGER.info("%%%%%%%%%%getDatafromFile异常={}", e.getMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					LOGGER.info("%%%%%%%%%%getDatafromFile异常={}", e.getMessage());
				}
			}
		}
		return laststr;
	}
	
	
	
	/**
	 * note:list均分成n个list
	 * @param source	待拆分List
	 * @param n			拆分子集合个数
	 * @return
	 */
	public static <T> List<List<T>> averageAssign(List<T> source,int n){
		List<List<T>> result=new ArrayList<List<T>>();
		int remaider=source.size()%n;  //(先计算出余数)
		int number=source.size()/n;  //然后是商
		int offset=0;//偏移量
		for(int i=0;i<n;i++){
			List<T> value=null;
			if(remaider>0){
				value=source.subList(i*number+offset, (i+1)*number+offset+1);
				remaider--;
				offset++;
			}else{
				value=source.subList(i*number+offset, (i+1)*number+offset);
			}
			result.add(value);
		}
		return result;
	}


}
