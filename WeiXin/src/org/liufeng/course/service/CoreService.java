
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
 * ���ķ�����
 * 
 * @author ccm
 * @date 2013-05-20
 */
public class CoreService {
	
	
	/** 
     * ����΢�ŷ��������� ��ͼ����Ӧ����
     *  
     * @param request 
     * @return 
     */  
    public static String processRequest(HttpServletRequest request) {
    	int temp = 2;
    	if(temp == 1){//ͼ�Ĳ���
    		return processRequest1(request);
    	}else if(temp == 2){//�˵�����
    		return processRequest2(request);
    	}else{
    		return processRequest2(request);
    	}
    }
	
	
	
	/** 
     * ����΢�ŷ��������� ��ͼ����Ӧ����
     *  
     * @param request 
     * @return 
     */  
    public static String processRequest1(HttpServletRequest request) {  
        String respMessage = null;  
        try {  
            // xml�������  
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
  
            // ���ͷ��ʺţ�open_id��  
            String fromUserName = requestMap.get("FromUserName");  
            // �����ʺ�  
            String toUserName = requestMap.get("ToUserName");  
            // ��Ϣ����  
            String msgType = requestMap.get("MsgType");  
  
            // Ĭ�ϻظ����ı���Ϣ  
            TextMessage textMessage = new TextMessage();  
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setFuncFlag(0);  
            // ����href����ֵ������˫�������������ַ��������˫���ų�ͻ������Ҫת��  
            textMessage.setContent("��ӭ����<a href=\"https://www.baidu.com/s?wd=%E6%B1%9F%E8%A5%BF%E5%86%9C%E4%B8%9A%E5%A4%A7%E5%AD%A6&ie=UTF-8\">����ũҵ��ѧ</a>!");  
            // ���ı���Ϣ����ת����xml�ַ���  
            respMessage = MessageUtil.textMessageToXml(textMessage);  
  
            // �ı���Ϣ  
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
                // �����û����͵��ı���Ϣ����  
                String content = requestMap.get("Content");  
                // ����ͼ����Ϣ  
                NewsMessage newsMessage = new NewsMessage();  
                newsMessage.setToUserName(fromUserName);  
                newsMessage.setFromUserName(toUserName);  
                newsMessage.setCreateTime(new Date().getTime());  
                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
                newsMessage.setFuncFlag(0);  
  
                List<Article> articleList = new ArrayList<Article>();  
                // ��ͼ����Ϣ  
                if ("11".equals(content)) {  
                    Article article = new Article();  
                    article.setTitle("ccm love china");  
                    article.setDescription("ccm love you,һ��ӡ����̵ĵ�Ӱ��ֵ���㿴��");  
                    article.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526476068213&di=8d065eb91172d5d2f36124d343450799&imgtype=0&src=http%3A%2F%2Ffile.100xuexi.com%2FXXExam%2FMatUpPT%2FSchoolAd%2F201407051007429637177.jpg");  
                    article.setUrl("http://www.jxau.edu.cn/");  
                    articleList.add(article);  
                    // ����ͼ����Ϣ����  
                    newsMessage.setArticleCount(articleList.size());  
                    // ����ͼ����Ϣ������ͼ�ļ���  
                    newsMessage.setArticles(articleList);  
                    // ��ͼ����Ϣ����ת����xml�ַ���  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);  
                }
                else if("33".equals(content)){
                	Article article = new Article();  
                    article.setTitle("zsx love you,һ��ӡ����̵ĵ�Ӱ��ֵ���㿴��");  
                    article.setDescription("��˧�ǣ�00��" + emoji(0x1F6B9)+"���͵Ķ����һ�����������н��ƿ����У��뿴����������Ŀ����ȥ��Ŷ������ĸϽ�����Ŷ��" );  
                    article.setPicUrl("http://p0.ifengimg.com/pmop/2017/0606/69EE4163824B57B005B6C0643E479CCED0D1E46E_size26_w640_h1027.jpeg");  
                    article.setUrl("https://ecb.czbank.com/ebpool/");
                    articleList.add(article);  
                    
                    // ����ͼ����Ϣ����  
                    newsMessage.setArticleCount(articleList.size());  
                    // ����ͼ����Ϣ������ͼ�ļ���  
                    newsMessage.setArticles(articleList);  
                    // ��ͼ����Ϣ����ת����xml�ַ���  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);  
                }
                // ��ͼ����Ϣ---����ͼƬ  
                else if ("22".equals(content)) {  
                    Article article = new Article();  
                    article.setTitle("zsx love you,һ��ӡ����̵ĵ�Ӱ��ֵ���㿴��");  
                    // ͼ����Ϣ�п���ʹ��QQ���顢���ű���  
                    article.setDescription("��˧�ǣ�00��" + emoji(0x1F6B9)+"���͵Ķ����һ�����������н��ƿ����У��뿴����������Ŀ����ȥ��Ŷ������ĸϽ�����Ŷ��" );  
                    // ��ͼƬ��Ϊ��  
                    article.setPicUrl("http://p0.ifengimg.com/pmop/2017/0606/69EE4163824B57B005B6C0643E479CCED0D1E46E_size26_w640_h1027.jpeg");  
                    article.setUrl("https://ecb.czbank.com/ebpool/");  
                    
                    Article article1 = new Article();  
                    article1.setTitle("���� love you,һ��ӡ����̵ĵ�Ӱ��ֵ���㿴��");  
                    // ͼ����Ϣ�п���ʹ��QQ���顢���ű���  
                    article1.setDescription("���� ��00��" + emoji(0x1F6B9)+"���͵Ķ����һ�����������н��ƿ����У��뿴����������Ŀ����ȥ��Ŷ������ĸϽ�����Ŷ��" );  
                    // ��ͼƬ��Ϊ��  
                    article1.setPicUrl("http://wx3.sinaimg.cn/large/a94a8951ly1fdnfj8nf69j20j60sr42z.jpg");  
                    article1.setUrl("https://ecb.czbank.com/ebpool/");
                    
                    articleList.add(article);  
                    articleList.add(article1);  
                    newsMessage.setArticleCount(articleList.size());  
                    newsMessage.setArticles(articleList);  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);  
                }  
                /*// ��ͼ����Ϣ  
                else if ("3".equals(content)) {  
                    Article article1 = new Article();  
                    article1.setTitle("΢�Ź����ʺſ����̳�\n����");  
                    article1.setDescription("");  
                    article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");  
                    article1.setUrl("http://blog.csdn.net/lyq8479/article/details/8937622");  
  
                    Article article2 = new Article();  
                    article2.setTitle("��2ƪ\n΢�Ź����ʺŵ�����");  
                    article2.setDescription("");  
                    article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8941577");  
  
                    Article article3 = new Article();  
                    article3.setTitle("��3ƪ\n����ģʽ���ü��ӿ�����");  
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
                // ��ͼ����Ϣ---������Ϣ����ͼƬ  
                else if ("4".equals(content)) {  
                    Article article1 = new Article();  
                    article1.setTitle("΢�Ź����ʺſ����̳�Java��");  
                    article1.setDescription("");  
                    // ��ͼƬ��Ϊ��  
                    article1.setPicUrl("");  
                    article1.setUrl("http://blog.csdn.net/lyq8479");  
  
                    Article article2 = new Article();  
                    article2.setTitle("��4ƪ\n��Ϣ����Ϣ�����ߵķ�װ");  
                    article2.setDescription("");  
                    article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8949088");  
  
                    Article article3 = new Article();  
                    article3.setTitle("��5ƪ\n������Ϣ�Ľ�������Ӧ");  
                    article3.setDescription("");  
                    article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8952173");  
  
                    Article article4 = new Article();  
                    article4.setTitle("��6ƪ\n�ı���Ϣ�����ݳ������ƽ���");  
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
                // ��ͼ����Ϣ---���һ����Ϣ����ͼƬ  
                else if ("5".equals(content)) {  
                    Article article1 = new Article();  
                    article1.setTitle("��7ƪ\n�ı���Ϣ�л��з���ʹ��");  
                    article1.setDescription("");  
                    article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");  
                    article1.setUrl("http://blog.csdn.net/lyq8479/article/details/9141467");  
  
                    Article article2 = new Article();  
                    article2.setTitle("��8ƪ\n�ı���Ϣ��ʹ����ҳ������");  
                    article2.setDescription("");  
                    article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
                    article2.setUrl("http://blog.csdn.net/lyq8479/article/details/9157455");  
  
                    Article article3 = new Article();  
                    article3.setTitle("����������¶���������������ͨ���������Ի��ע΢�Ź����ʺ�xiaoqrobot��֧�����壡");  
                    article3.setDescription("");  
                    // ��ͼƬ��Ϊ��  
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
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest2(HttpServletRequest request) {
		String respMessage = null;
		try {
			// Ĭ�Ϸ��ص��ı���Ϣ����
			String respContent = "�������쳣�����Ժ��ԣ�";

			// xml�������
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// ���ͷ��ʺţ�open_id��
			String fromUserName = requestMap.get("FromUserName");
			// �����ʺ�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");

			// �ظ��ı���Ϣ
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				//respContent = "�����͵����ı���Ϣ��";
				
				//��Ϣ��ʽ�˵�
				//respContent = getMainMenu();
				
				//�ظ���ҳ������
				//respContent = "<a href=\"www.baidu.com\">baidu</a>" ;
				
				// ���ͱ���ͼ
				//respContent = "���г�" + emoji(0x1F6B2) + " ����" + emoji(0x1F6B9) + " Ǯ��" + emoji(0x1F4B0);
				
				// ��ʷ��Ϣչʾ
				//respContent = TodayInHistoryService.getTodayInHistoryInfo();
				
			    String content = requestMap.get("Content").trim();  
			    if("1".equals(content)){//���ͱ���ͼ
			    	respContent = "���г�" + emoji(0x1F6B2) + " ����" + emoji(0x1F6B9) + " Ǯ��" + emoji(0x1F4B0);
			    }else if("2".equals(content)){//�ٶ�����
			    	respContent = "<a href=\"www.baidu.com\">baidu</a>" ;
			    }else if("11".equals(content)){//ͼ����Ϣ1
			    	processRequest1(request);
			    }else if("22".equals(content)){//ͼ����Ϣ2
			    	processRequest1(request);
			    }
			    else if("33".equals(content)){//ͼ����Ϣ3
			    	processRequest1(request);
			    }else if("����".equals(content)){//����
			    	if (content.startsWith("����")) {  
				        String keyWord = content.replaceAll("^����", "").trim();  
				        System.out.println("----------"+keyWord);
				        if ("".equals(keyWord)) {  
				        	respContent = getTranslateUsage();
				        } else {
				        	respContent = BaiduTranslateService.translate(keyWord);
				        }  
				    }
			    }
			    else{//Ĭ�Ϸ���˵�
			    	respContent = getMainMenu();
			    }
			    
			    
			}  
			// ͼƬ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "�����͵���ͼƬ��Ϣ��";
			}
			// ����λ����Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "�����͵��ǵ���λ����Ϣ��";
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "�����͵���������Ϣ��";
			}
			// ��Ƶ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "�����͵�����Ƶ��Ϣ��";
			}
			// �¼�����
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ����
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "лл���Ĺ�ע��";
				}
				// ȡ������
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ
				}
				// �Զ���˵�����¼�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					 // �¼�KEYֵ���봴���Զ���˵�ʱָ����KEYֵ��Ӧ  
                    String eventKey = requestMap.get("EventKey");  
                    if (eventKey.equals("11")) {  
                        respContent = "����Ԥ���˵�������";  
                    } else if (eventKey.equals("12")) {  
                        respContent = "������ѯ�˵�������";  
                    } else if (eventKey.equals("13")) {  
                        respContent = "�ܱ������˵�������";  
                    } else if (eventKey.equals("14")) {  
                        respContent = "��ʷ�ϵĽ���˵�������";  
                    } else if (eventKey.equals("21")) {  
                        respContent = "�����㲥�˵�������";  
                    } else if (eventKey.equals("22")) {  
                        respContent = "������Ϸ�˵�������";  
                    } else if (eventKey.equals("23")) {  
                        respContent = "��Ů��̨�˵�������";  
                    } else if (eventKey.equals("24")) {  
                        respContent = "����ʶ��˵�������";  
                    } else if (eventKey.equals("25")) {  
                        respContent = "������ྲ˵�������";  
                    } else if (eventKey.equals("31")) {  
                        respContent = "Q��Ȧ�˵�������";  
                    } else if (eventKey.equals("32")) {  
                        respContent = "��Ӱ���а�˵�������";  
                    } else if (eventKey.equals("33")) {  
                        respContent = "��ĬЦ���˵�������";  
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
	 * Q��ͨʹ��ָ��
	 * 
	 * @return
	 */
	public static String getTranslateUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ccm����ͨʹ��ָ��").append("\n\n");
		buffer.append("ccm����ͨΪ�û��ṩרҵ�Ķ����Է������Ŀǰ֧�����·��뷽��").append("\n");
		buffer.append("    �� -> Ӣ").append("\n");
		buffer.append("    Ӣ -> ��").append("\n");
		buffer.append("    �� -> ��").append("\n\n");
		buffer.append("ʹ��ʾ����").append("\n");
		buffer.append("    ���������й���").append("\n");
		buffer.append("    ����dream").append("\n");
		buffer.append("    ���뤵�褦�ʤ�").append("\n\n");
		buffer.append("�ظ���?����ʾ���˵�");
		return buffer.toString();
	}



	/** 
	 * xiaoqrobot�����˵� 
	 *  
	 * @return 
	 */  
	public static String getMainMenu() {  
	    StringBuffer buffer = new StringBuffer();  
	   /* buffer.append("���ã�����Сq����ظ�����ѡ�����").append("\n\n");  
	    buffer.append("1  ����Ԥ��").append("\n");  
	    buffer.append("2  ������ѯ").append("\n");  
	    buffer.append("3  �ܱ�����").append("\n");  
	    buffer.append("4  �����㲥").append("\n");  
	    buffer.append("5  ������Ϸ").append("\n");  
	    buffer.append("6  ��Ů��̨").append("\n");  
	    buffer.append("7  ����ʶ��").append("\n");  
	    buffer.append("8  �������").append("\n");  
	    buffer.append("9 <a href=\"www.baidu.com\">�ٶ�����</a>").append("\n\n");  
	    buffer.append("�ظ���?����ʾ�˰����˵�");  */
	    
	    buffer.append("���ã�����Сc����ظ�����ѡ�����").append("\n\n");  
	    buffer.append("1  ���ͱ���ͼ").append("\n");  
	    buffer.append("2 <a href=\"www.baidu.com\">�ٶ�����</a>").append("\n\n");  
	    buffer.append("11  ͼ����Ϣ1").append("\n");  
	    buffer.append("22  ͼ����Ϣ2").append("\n");  
	    buffer.append("33  ͼ����Ϣ3").append("\n");  
	    buffer.append("����  ���빦��").append("\n");  
	    return buffer.toString();  
	} 
	
	
	/** 
     * emoji����ת��(hex -> utf-16) 
     *  
     * @param hexEmoji 
     * @return 
     */  
    public static String emoji(int hexEmoji) {  
        return String.valueOf(Character.toChars(hexEmoji));  
    }  
}

