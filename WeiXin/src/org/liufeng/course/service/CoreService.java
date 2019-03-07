
package org.liufeng.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.liufeng.course.history.event.TodayInHistoryService;
import org.liufeng.course.message.resp.Article;
import org.liufeng.course.message.resp.NewsMessage;
import org.liufeng.course.message.resp.TextMessage;
import org.liufeng.course.translation.service.BaiduTranslateService;
import org.liufeng.course.util.MessageUtil;

import com.baidu.translate.demo.TransApi;

/**
 * 核心服务类
 * 
 * @author ccm
 * @date 2013-05-20
 */
public class CoreService {
	
	
	/** 
     * 处理微信发来的请求 ：图文响应处理
     *  
     * @param request 
     * @return 
     */  
    public static String processRequest(HttpServletRequest request) {
    	int temp = 2;
    	if(temp == 1){//图文测试
    		return processRequest1(request);
    	}else if(temp == 2){//菜单测试
    		return processRequest2(request);
    	}else{
    		return processRequest2(request);
    	}
    }
	
	
	
	/** 
     * 处理微信发来的请求 ：图文响应处理
     *  
     * @param request 
     * @return 
     */  
    public static String processRequest1(HttpServletRequest request) {  
        String respMessage = null;  
        try {  
            // xml请求解析  
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
  
            // 发送方帐号（open_id）  
            String fromUserName = requestMap.get("FromUserName");  
            // 公众帐号  
            String toUserName = requestMap.get("ToUserName");  
            // 消息类型  
            String msgType = requestMap.get("MsgType");  
  
            // 默认回复此文本消息  
            TextMessage textMessage = new TextMessage();  
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setFuncFlag(0);  
            // 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义  
            textMessage.setContent("欢迎访问<a href=\"https://www.baidu.com/s?wd=%E6%B1%9F%E8%A5%BF%E5%86%9C%E4%B8%9A%E5%A4%A7%E5%AD%A6&ie=UTF-8\">江西农业大学</a>!");  
            // 将文本消息对象转换成xml字符串  
            respMessage = MessageUtil.textMessageToXml(textMessage);  
  
            // 文本消息  
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
                // 接收用户发送的文本消息内容  
                String content = requestMap.get("Content");  
                // 创建图文消息  
                NewsMessage newsMessage = new NewsMessage();  
                newsMessage.setToUserName(fromUserName);  
                newsMessage.setFromUserName(toUserName);  
                newsMessage.setCreateTime(new Date().getTime());  
                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
                newsMessage.setFuncFlag(0);  
  
                List<Article> articleList = new ArrayList<Article>();  
                // 单图文消息  
                if ("11".equals(content)) {  
                    Article article = new Article();  
                    article.setTitle("ccm love china");  
                    article.setDescription("ccm love you,一部印象深刻的电影，值得你看！");  
                    article.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526476068213&di=8d065eb91172d5d2f36124d343450799&imgtype=0&src=http%3A%2F%2Ffile.100xuexi.com%2FXXExam%2FMatUpPT%2FSchoolAd%2F201407051007429637177.jpg");  
                    article.setUrl("http://www.jxau.edu.cn/");  
                    articleList.add(article);  
                    // 设置图文消息个数  
                    newsMessage.setArticleCount(articleList.size());  
                    // 设置图文消息包含的图文集合  
                    newsMessage.setArticles(articleList);  
                    // 将图文消息对象转换成xml字符串  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);  
                }
                else if("33".equals(content)){
                	Article article = new Article();  
                    article.setTitle("zsx love you,一部印象深刻的电影，值得你看！");  
                    article.setDescription("张帅星，00后，" + emoji(0x1F6B9)+"典型的多金男一个，浙商银行金牌开发男，想看他开发的项目请点进去看哦！单身的赶紧下手哦！" );  
                    article.setPicUrl("http://p0.ifengimg.com/pmop/2017/0606/69EE4163824B57B005B6C0643E479CCED0D1E46E_size26_w640_h1027.jpeg");  
                    article.setUrl("https://ecb.czbank.com/ebpool/");
                    articleList.add(article);  
                    
                    // 设置图文消息个数  
                    newsMessage.setArticleCount(articleList.size());  
                    // 设置图文消息包含的图文集合  
                    newsMessage.setArticles(articleList);  
                    // 将图文消息对象转换成xml字符串  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);  
                }
                // 单图文消息---不含图片  
                else if ("22".equals(content)) {  
                    Article article = new Article();  
                    article.setTitle("zsx love you,一部印象深刻的电影，值得你看！");  
                    // 图文消息中可以使用QQ表情、符号表情  
                    article.setDescription("张帅星，00后，" + emoji(0x1F6B9)+"典型的多金男一个，浙商银行金牌开发男，想看他开发的项目请点进去看哦！单身的赶紧下手哦！" );  
                    // 将图片置为空  
                    article.setPicUrl("http://p0.ifengimg.com/pmop/2017/0606/69EE4163824B57B005B6C0643E479CCED0D1E46E_size26_w640_h1027.jpeg");  
                    article.setUrl("https://ecb.czbank.com/ebpool/");  
                    
                    Article article1 = new Article();  
                    article1.setTitle("张锐 love you,一部印象深刻的电影，值得你看！");  
                    // 图文消息中可以使用QQ表情、符号表情  
                    article1.setDescription("张锐 ，00后，" + emoji(0x1F6B9)+"典型的多金男一个，浙商银行金牌开发男，想看他开发的项目请点进去看哦！单身的赶紧下手哦！" );  
                    // 将图片置为空  
                    article1.setPicUrl("http://wx3.sinaimg.cn/large/a94a8951ly1fdnfj8nf69j20j60sr42z.jpg");  
                    article1.setUrl("https://ecb.czbank.com/ebpool/");
                    
                    articleList.add(article);  
                    articleList.add(article1);  
                    newsMessage.setArticleCount(articleList.size());  
                    newsMessage.setArticles(articleList);  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);  
                }  
                /*// 多图文消息  
                else if ("3".equals(content)) {  
                    Article article1 = new Article();  
                    article1.setTitle("微信公众帐号开发教程\n引言");  
                    article1.setDescription("");  
                    article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");  
                    article1.setUrl("http://blog.csdn.net/lyq8479/article/details/8937622");  
  
                    Article article2 = new Article();  
                    article2.setTitle("第2篇\n微信公众帐号的类型");  
                    article2.setDescription("");  
                    article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8941577");  
  
                    Article article3 = new Article();  
                    article3.setTitle("第3篇\n开发模式启用及接口配置");  
                    article3.setDescription("");  
                    article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8944988");  
  
                    articleList.add(article1);  
                    articleList.add(article2);  
                    articleList.add(article3);  
                    newsMessage.setArticleCount(articleList.size());  
                    newsMessage.setArticles(articleList);  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);  
                }  
                // 多图文消息---首条消息不含图片  
                else if ("4".equals(content)) {  
                    Article article1 = new Article();  
                    article1.setTitle("微信公众帐号开发教程Java版");  
                    article1.setDescription("");  
                    // 将图片置为空  
                    article1.setPicUrl("");  
                    article1.setUrl("http://blog.csdn.net/lyq8479");  
  
                    Article article2 = new Article();  
                    article2.setTitle("第4篇\n消息及消息处理工具的封装");  
                    article2.setDescription("");  
                    article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8949088");  
  
                    Article article3 = new Article();  
                    article3.setTitle("第5篇\n各种消息的接收与响应");  
                    article3.setDescription("");  
                    article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8952173");  
  
                    Article article4 = new Article();  
                    article4.setTitle("第6篇\n文本消息的内容长度限制揭秘");  
                    article4.setDescription("");  
                    article4.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article4.setUrl("http://blog.csdn.net/lyq8479/article/details/8967824");  
  
                    articleList.add(article1);  
                    articleList.add(article2);  
                    articleList.add(article3);  
                    articleList.add(article4);  
                    newsMessage.setArticleCount(articleList.size());  
                    newsMessage.setArticles(articleList);  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);  
                }  
                // 多图文消息---最后一条消息不含图片  
                else if ("5".equals(content)) {  
                    Article article1 = new Article();  
                    article1.setTitle("第7篇\n文本消息中换行符的使用");  
                    article1.setDescription("");  
                    article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");  
                    article1.setUrl("http://blog.csdn.net/lyq8479/article/details/9141467");  
  
                    Article article2 = new Article();  
                    article2.setTitle("第8篇\n文本消息中使用网页超链接");  
                    article2.setDescription("");  
                    article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article2.setUrl("http://blog.csdn.net/lyq8479/article/details/9157455");  
  
                    Article article3 = new Article();  
                    article3.setTitle("如果觉得文章对你有所帮助，请通过博客留言或关注微信公众帐号xiaoqrobot来支持柳峰！");  
                    article3.setDescription("");  
                    // 将图片置为空  
                    article3.setPicUrl("");  
                    article3.setUrl("http://blog.csdn.net/lyq8479");  
  
                    articleList.add(article1);  
                    articleList.add(article2);  
                    articleList.add(article3);  
                    newsMessage.setArticleCount(articleList.size());  
                    newsMessage.setArticles(articleList);  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);  
                }*/  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return respMessage;  
    }  
	
	
	
	
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest2(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				//respContent = "您发送的是文本消息！";
				
				//信息形式菜单
				//respContent = getMainMenu();
				
				//回复网页超链接
				//respContent = "<a href=\"www.baidu.com\">baidu</a>" ;
				
				// 发送表情图
				//respContent = "自行车" + emoji(0x1F6B2) + " 男性" + emoji(0x1F6B9) + " 钱袋" + emoji(0x1F4B0);
				
				// 历史消息展示
				//respContent = TodayInHistoryService.getTodayInHistoryInfo();
				
			    String content = requestMap.get("Content").trim();  
			    if("1".equals(content)){//发送表情图
			    	respContent = "自行车" + emoji(0x1F6B2) + " 男性" + emoji(0x1F6B9) + " 钱袋" + emoji(0x1F4B0);
			    }else if("2".equals(content)){//百度搜索
			    	respContent = "<a href=\"www.baidu.com\">baidu</a>" ;
			    }else if("11".equals(content)){//图文消息1
			    	processRequest1(request);
			    }else if("22".equals(content)){//图文消息2
			    	processRequest1(request);
			    }
			    else if("33".equals(content)){//图文消息3
			    	processRequest1(request);
			    }else if("翻译".equals(content)){//翻译
			    	if (content.startsWith("翻译")) {  
				        String keyWord = content.replaceAll("^翻译", "").trim();  
				        System.out.println("----------"+keyWord);
				        if ("".equals(keyWord)) {  
				        	respContent = getTranslateUsage();
				        } else {
				        	respContent = BaiduTranslateService.translate(keyWord);
				        }  
				    }
			    }
			    else{//默认服务菜单
			    	respContent = getMainMenu();
			    }
			    
			    
			}  
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "谢谢您的关注！";
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					 // 事件KEY值，与创建自定义菜单时指定的KEY值对应  
                    String eventKey = requestMap.get("EventKey");  
                    if (eventKey.equals("11")) {  
                        respContent = "天气预报菜单项被点击！";  
                    } else if (eventKey.equals("12")) {  
                        respContent = "公交查询菜单项被点击！";  
                    } else if (eventKey.equals("13")) {  
                        respContent = "周边搜索菜单项被点击！";  
                    } else if (eventKey.equals("14")) {  
                        respContent = "历史上的今天菜单项被点击！";  
                    } else if (eventKey.equals("21")) {  
                        respContent = "歌曲点播菜单项被点击！";  
                    } else if (eventKey.equals("22")) {  
                        respContent = "经典游戏菜单项被点击！";  
                    } else if (eventKey.equals("23")) {  
                        respContent = "美女电台菜单项被点击！";  
                    } else if (eventKey.equals("24")) {  
                        respContent = "人脸识别菜单项被点击！";  
                    } else if (eventKey.equals("25")) {  
                        respContent = "聊天唠嗑菜单项被点击！";  
                    } else if (eventKey.equals("31")) {  
                        respContent = "Q友圈菜单项被点击！";  
                    } else if (eventKey.equals("32")) {  
                        respContent = "电影排行榜菜单项被点击！";  
                    } else if (eventKey.equals("33")) {  
                        respContent = "幽默笑话菜单项被点击！";  
                    }  
				}
			}

			textMessage.setContent(respContent);
			respMessage = MessageUtil.textMessageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}
	
	
	/**
	 * Q译通使用指南
	 * 
	 * @return
	 */
	public static String getTranslateUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ccm翻译通使用指南").append("\n\n");
		buffer.append("ccm翻译通为用户提供专业的多语言翻译服务，目前支持以下翻译方向：").append("\n");
		buffer.append("    中 -> 英").append("\n");
		buffer.append("    英 -> 中").append("\n");
		buffer.append("    日 -> 中").append("\n\n");
		buffer.append("使用示例：").append("\n");
		buffer.append("    翻译我是中国人").append("\n");
		buffer.append("    翻译dream").append("\n");
		buffer.append("    翻译さようなら").append("\n\n");
		buffer.append("回复“?”显示主菜单");
		return buffer.toString();
	}



	/** 
	 * xiaoqrobot的主菜单 
	 *  
	 * @return 
	 */  
	public static String getMainMenu() {  
	    StringBuffer buffer = new StringBuffer();  
	   /* buffer.append("您好，我是小q，请回复数字选择服务：").append("\n\n");  
	    buffer.append("1  天气预报").append("\n");  
	    buffer.append("2  公交查询").append("\n");  
	    buffer.append("3  周边搜索").append("\n");  
	    buffer.append("4  歌曲点播").append("\n");  
	    buffer.append("5  经典游戏").append("\n");  
	    buffer.append("6  美女电台").append("\n");  
	    buffer.append("7  人脸识别").append("\n");  
	    buffer.append("8  聊天唠嗑").append("\n");  
	    buffer.append("9 <a href=\"www.baidu.com\">百度搜索</a>").append("\n\n");  
	    buffer.append("回复“?”显示此帮助菜单");  */
	    
	    buffer.append("您好，我是小c，请回复数字选择服务：").append("\n\n");  
	    buffer.append("1  发送表情图").append("\n");  
	    buffer.append("2 <a href=\"www.baidu.com\">百度搜索</a>").append("\n\n");  
	    buffer.append("11  图文消息1").append("\n");  
	    buffer.append("22  图文消息2").append("\n");  
	    buffer.append("33  图文消息3").append("\n");  
	    buffer.append("翻译  翻译功能").append("\n");  
	    return buffer.toString();  
	} 
	
	
	/** 
     * emoji表情转换(hex -> utf-16) 
     *  
     * @param hexEmoji 
     * @return 
     */  
    public static String emoji(int hexEmoji) {  
        return String.valueOf(Character.toChars(hexEmoji));  
    }  
}

