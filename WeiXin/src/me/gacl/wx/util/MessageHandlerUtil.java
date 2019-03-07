package me.gacl.wx.util;



import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 
 * ����΢�ŷ��������͵���Ϣ��������Ӧ ����
 * ��������������,���ǿ���ǰ��׼�������Ѿ������,������Ҫ���ľ��ǽ���΢�ŷ��������͵���Ϣ��������Ӧ
 * 
 * ������΢�Ź���ƽ̨�ӿ���Ϣָ���п����˽⵽�����û������ʺŷ���Ϣʱ��΢�ŷ������Ὣ��Ϣͨ��POST��ʽ�ύ�������ڽӿ�������Ϣ����д��URL��
 * �����Ǿ���Ҫ��URL��ָ�����������WxServlet��doPost�����н�����Ϣ��������Ϣ����Ӧ��Ϣ��
 * 
 * ��Ϣ�������� Created by xdp on 2016/1/26.
 */
public class MessageHandlerUtil {

    /**
     * ����΢�ŷ���������XML��
     * @param request
     * @return map
     * @throws Exception
     */
    public static Map<String,String> parseXml(HttpServletRequest request) throws Exception {
        // ����������洢��HashMap��
        Map<String,String> map = new HashMap<String,String>();
        // ��request��ȡ��������
        InputStream inputStream = request.getInputStream();
        System.out.println("��ȡ������");
        // ��ȡ������
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // �õ�xml��Ԫ��
        Element root = document.getRootElement();
        // �õ���Ԫ�ص������ӽڵ�
        List<Element> elementList = root.elements();

        // ���������ӽڵ�
        for (Element e : elementList) {
            System.out.println(e.getName() + "|" + e.getText());
            map.put(e.getName(), e.getText());
        }

        // �ͷ���Դ
        inputStream.close();
        inputStream = null;
        return map;
    }
    
    
    /**********************************************************�򵥲��Խ�����Ϣ********************************/
    // ������Ϣ���� ���췵����Ϣ
    public static String buildXml(Map<String,String> map) {
        String result;
        String msgType = map.get("MsgType").toString();
        System.out.println("MsgType:" + msgType);
        if(msgType.toUpperCase().equals("TEXT")){
            //result = buildTextMessage1(map, "�°�������ѧϰ���ܽ�΢�ſ�����,����һ���ı���Ϣ:Hello World!");
        	result = buildTextMessage1(map, "����ҽ͵͵��˵:Hello everyOne,��Ҫ˵�Ұ��ҵ�С����!");
        }else{
            String fromUserName = map.get("FromUserName");
            // ������΢�ź�
            String toUserName = map.get("ToUserName");
            result = String
                    .format(
                            "<xml>" +
                                    "<ToUserName><![CDATA[%s]]></ToUserName>" +
                                    "<FromUserName><![CDATA[%s]]></FromUserName>" +
                                    "<CreateTime>%s</CreateTime>" +
                                    "<MsgType><![CDATA[text]]></MsgType>" +
                                    "<Content><![CDATA[%s]]></Content>" +
                                    "</xml>",
                            fromUserName, toUserName, getUtcTime(),
                            "��ظ����¹ؼ��ʣ�\n�ı�\nͼƬ\n����\n��Ƶ\n����\nͼ��");
        }

        return result;
    }

    /**
     * �����ı���Ϣ
     *
     * @param map
     * @param content
     * @return
     */
    private static String buildTextMessage1(Map<String,String> map, String content) {
        //���ͷ��ʺ�
        String fromUserName = map.get("FromUserName");
        // ������΢�ź�
        String toUserName = map.get("ToUserName");
        /**
         * �ı���ϢXML���ݸ�ʽ
         * <xml>
             <ToUserName><![CDATA[toUser]]></ToUserName>
             <FromUserName><![CDATA[fromUser]]></FromUserName>
             <CreateTime>1348831860</CreateTime>
             <MsgType><![CDATA[text]]></MsgType>
             <Content><![CDATA[this is a test]]></Content>
             <MsgId>1234567890123456</MsgId>
         </xml>
         */
        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[text]]></MsgType>" +
                        "<Content><![CDATA[%s]]></Content>" + "</xml>",
                fromUserName, toUserName, getUtcTime(), content);
    }

    private static String getUtcTime() {
        Date dt = new Date();// �������Ҫ��ʽ,��ֱ����dt,dt���ǵ�ǰϵͳʱ��
        DateFormat df = new SimpleDateFormat("yyyyMMddhhmm");// ������ʾ��ʽ
        String nowTime = df.format(dt);
        long dd = (long) 0;
        try {
            dd = df.parse(nowTime).getTime();
        } catch (Exception e) {

        }
        return String.valueOf(dd);
    }
    /**********************************************************�򵥲��Խ�����Ϣ********************************/
    

    /**
     * ������Ϣ���͹��췵����Ϣ
     * @param map ��װ�˽��������Map
     * @return responseMessage(��Ӧ��Ϣ)
     */
    public static String buildResponseMessage(Map map) {
        //��Ӧ��Ϣ
        String responseMessage = "";
        //�õ���Ϣ����
        String msgType = map.get("MsgType").toString();
        System.out.println("MsgType:" + msgType);
        //��Ϣ����
        MessageType messageEnumType = MessageType.valueOf(MessageType.class, msgType.toUpperCase());
        switch (messageEnumType) {
            case TEXT:
                //�����ı���Ϣ
                responseMessage = handleTextMessage(map);
                break;
            case IMAGE:
                //����ͼƬ��Ϣ
                responseMessage = handleImageMessage(map);
                break;
            case VOICE:
                //����������Ϣ
                responseMessage = handleVoiceMessage(map);
                break;
            case VIDEO:
                //������Ƶ��Ϣ
                responseMessage = handleVideoMessage(map);
                break;
            case SHORTVIDEO:
                //����С��Ƶ��Ϣ
                responseMessage = handleSmallVideoMessage(map);
                break;
            case LOCATION:
                //����λ����Ϣ
                responseMessage = handleLocationMessage(map);
                break;
            case LINK:
                //����������Ϣ
                responseMessage = handleLinkMessage(map);
                break;
            case EVENT:
                //�����¼���Ϣ,�û��ڹ�ע��ȡ����ע���ں�ʱ��΢�Ż������ǵĹ��ںŷ����������¼���Ϣ,�����߽��յ��¼���Ϣ��Ϳ��Ը��û��·���ӭ��Ϣ
                responseMessage = handleEventMessage(map);
            default:
                break;
        }
        //������Ӧ��Ϣ
        return responseMessage;
    }

    /**
     * ���յ��ı���Ϣ����
     * @param map ��װ�˽��������Map
     * @return
     */
    private static String handleTextMessage(Map<String, String> map) {
        //��Ӧ��Ϣ
        String responseMessage;
        // ��Ϣ����
        String content = map.get("Content");
        switch (content) {
            case "�ı�":
                String msgText = "�°�������Ҫ��ʼд�����ܽ���,��ӭ�����Ƿ������ڲ���԰����д�Ĳ���\n" +
                        "<a href=\"http://www.cnblogs.com/xdp-gacl\">�°����ǵĲ���</a>";
                responseMessage = buildTextMessage(map, msgText);
                break;
            case "ͼƬ":
                //ͨ���زĹ���ӿ��ϴ�ͼƬʱ�õ���media_id
                String imgMediaId = "DrfPxEEe56ZRH1c3PYuyrYJMM7r48az1lpd0u24hxPRA1a9d04SkLaf_lLoPUBKy";
                responseMessage = buildImageMessage(map, imgMediaId);
                break;
            case "��Ҫ����˧��":
                //ͨ���زĹ���ӿ��ϴ�ͼƬʱ�õ���media_id
                String imgMediaId1 = "DrfPxEEe56ZRH1c3PYuyrYJMM7r48az1lpd0u24hxPRA1a9d04SkLaf_lLoPUBKy";
                responseMessage = buildImageMessage(map, imgMediaId1);
                break;
            case "����":
                //ͨ���زĹ���ӿ��ϴ������ļ�ʱ�õ���media_id
                String voiceMediaId = "h3ul0TnwaRPut6Tl1Xlf0kk_9aUqtQvfM5Oq21unoWqJrwks505pkMGMbHnCHBBZ";
                responseMessage = buildVoiceMessage(map,voiceMediaId);
                break;
            case "ͼ��":
                responseMessage = buildNewsMessage(map);
                break;
            case "����":
                Music music = new Music();
                music.title = "����ӱ����־�� - ��������";
                music.description = "���Ӿ硶��ɽս�͡�����";
                music.musicUrl = "http://gacl.ngrok.natapp.cn/media/music/music.mp3";
                music.hqMusicUrl = "http://gacl.ngrok.natapp.cn/media/music/music.mp3";
                responseMessage = buildMusicMessage(map, music);
                break;
            case "��Ƶ":
                Video video = new Video();
                video.mediaId = "GqmIGpLu41rtwaY7WCVtJAL3ZbslzKiuLEXfWIKYDnHXGObH1CBH71xtgrGwyCa3";
                video.title = "Сƻ��";
                video.description = "Сƻ����Ц��Ƶ";
                responseMessage = buildVideoMessage(map, video);
                break;
            default:
                responseMessage = buildWelcomeTextMessage(map);
                break;

        }
        //������Ӧ��Ϣ
        return responseMessage;
    }

    /**
     * ������Ϣ����ʱ�� �����ͣ�
     * @return ��Ϣ����ʱ��
     */
    private static String getMessageCreateTime() {
        Date dt = new Date();// �������Ҫ��ʽ,��ֱ����dt,dt���ǵ�ǰϵͳʱ��
        DateFormat df = new SimpleDateFormat("yyyyMMddhhmm");// ������ʾ��ʽ
        String nowTime = df.format(dt);
        long dd = (long) 0;
        try {
            dd = df.parse(nowTime).getTime();
        } catch (Exception e) {

        }
        return String.valueOf(dd);
    }


    /**
     * ������ʾ��Ϣ
     * @param map ��װ�˽��������Map
     * @return responseMessageXml
     */
    private static String buildWelcomeTextMessage(Map<String, String> map) {
        String responseMessageXml;
        String fromUserName = map.get("FromUserName");
        // ������΢�ź�
        String toUserName = map.get("ToUserName");
        responseMessageXml = String
                .format(
                        "<xml>" +
                                "<ToUserName><![CDATA[%s]]></ToUserName>" +
                                "<FromUserName><![CDATA[%s]]></FromUserName>" +
                                "<CreateTime>%s</CreateTime>" +
                                "<MsgType><![CDATA[text]]></MsgType>" +
                                "<Content><![CDATA[%s]]></Content>" +
                                "</xml>",
                        fromUserName, toUserName, getMessageCreateTime(),
                        "��л����ע�ҵĸ��˹��ںţ���ظ����¹ؼ�����ʹ�ù��ں��ṩ�ķ���\n�ı�\nͼƬ\n��Ҫ����˧��\n����\n��Ƶ\n����\nͼ��");
        return responseMessageXml;
    }

    /**
     * �����ı���Ϣ
     * @param map ��װ�˽��������Map
     * @param content �ı���Ϣ����
     * @return �ı���ϢXML�ַ���
     */
    private static String buildTextMessage(Map<String, String> map, String content) {
        //���ͷ��ʺ�
        String fromUserName = map.get("FromUserName");
        // ������΢�ź�
        String toUserName = map.get("ToUserName");
        /**
         * �ı���ϢXML���ݸ�ʽ
         * <xml>
         <ToUserName><![CDATA[toUser]]></ToUserName>
         <FromUserName><![CDATA[fromUser]]></FromUserName>
         <CreateTime>1348831860</CreateTime>
         <MsgType><![CDATA[text]]></MsgType>
         <Content><![CDATA[this is a test]]></Content>
         <MsgId>1234567890123456</MsgId>
         </xml>
         */
        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[text]]></MsgType>" +
                        "<Content><![CDATA[%s]]></Content>" +
                        "</xml>",
                fromUserName, toUserName, getMessageCreateTime(), content);
    }

    /**
     * ����ͼƬ��Ϣ
     * @param map ��װ�˽��������Map
     * @param mediaId ͨ���زĹ���ӿ��ϴ���ý���ļ��õ���id
     * @return ͼƬ��ϢXML�ַ���
     */
    private static String buildImageMessage(Map<String, String> map, String mediaId) {
        //���ͷ��ʺ�
        String fromUserName = map.get("FromUserName");
        // ������΢�ź�
        String toUserName = map.get("ToUserName");
        /**
         * ͼƬ��ϢXML���ݸ�ʽ
         *<xml>
         <ToUserName><![CDATA[toUser]]></ToUserName>
         <FromUserName><![CDATA[fromUser]]></FromUserName>
         <CreateTime>12345678</CreateTime>
         <MsgType><![CDATA[image]]></MsgType>
         <Image>
         <MediaId><![CDATA[media_id]]></MediaId>
         </Image>
         </xml>
         */
        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[image]]></MsgType>" +
                        "<Image>" +
                        "   <MediaId><![CDATA[%s]]></MediaId>" +
                        "</Image>" +
                        "</xml>",
                fromUserName, toUserName, getMessageCreateTime(), mediaId);
    }

    /**
     * ����������Ϣ
     * @param map ��װ�˽��������Map
     * @param music ��װ�õ�������Ϣ����
     * @return ������ϢXML�ַ���
     */
    private static String buildMusicMessage(Map<String, String> map, Music music) {
        //���ͷ��ʺ�
        String fromUserName = map.get("FromUserName");
        // ������΢�ź�
        String toUserName = map.get("ToUserName");
        /**
         * ������ϢXML���ݸ�ʽ
         *<xml>
         <ToUserName><![CDATA[toUser]]></ToUserName>
         <FromUserName><![CDATA[fromUser]]></FromUserName>
         <CreateTime>12345678</CreateTime>
         <MsgType><![CDATA[music]]></MsgType>
         <Music>
         <Title><![CDATA[TITLE]]></Title>
         <Description><![CDATA[DESCRIPTION]]></Description>
         <MusicUrl><![CDATA[MUSIC_Url]]></MusicUrl>
         <HQMusicUrl><![CDATA[HQ_MUSIC_Url]]></HQMusicUrl>
         <ThumbMediaId><![CDATA[media_id]]></ThumbMediaId>
         </Music>
         </xml>
         */
        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[music]]></MsgType>" +
                        "<Music>" +
                        "   <Title><![CDATA[%s]]></Title>" +
                        "   <Description><![CDATA[%s]]></Description>" +
                        "   <MusicUrl><![CDATA[%s]]></MusicUrl>" +
                        "   <HQMusicUrl><![CDATA[%s]]></HQMusicUrl>" +
                        "</Music>" +
                        "</xml>",
                fromUserName, toUserName, getMessageCreateTime(), music.title, music.description, music.musicUrl, music.hqMusicUrl);
    }

    /**
     * ������Ƶ��Ϣ
     * @param map ��װ�˽��������Map
     * @param video ��װ�õ���Ƶ��Ϣ����
     * @return ��Ƶ��ϢXML�ַ���
     */
    private static String buildVideoMessage(Map<String, String> map, Video video) {
        //���ͷ��ʺ�
        String fromUserName = map.get("FromUserName");
        // ������΢�ź�
        String toUserName = map.get("ToUserName");
        /**
         * ������ϢXML���ݸ�ʽ
         *<xml>
         <ToUserName><![CDATA[toUser]]></ToUserName>
         <FromUserName><![CDATA[fromUser]]></FromUserName>
         <CreateTime>12345678</CreateTime>
         <MsgType><![CDATA[video]]></MsgType>
         <Video>
         <MediaId><![CDATA[media_id]]></MediaId>
         <Title><![CDATA[title]]></Title>
         <Description><![CDATA[description]]></Description>
         </Video>
         </xml>
         */
        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[video]]></MsgType>" +
                        "<Video>" +
                        "   <MediaId><![CDATA[%s]]></MediaId>" +
                        "   <Title><![CDATA[%s]]></Title>" +
                        "   <Description><![CDATA[%s]]></Description>" +
                        "</Video>" +
                        "</xml>",
                fromUserName, toUserName, getMessageCreateTime(), video.mediaId, video.title, video.description);
    }

    /**
     * ����������Ϣ
     * @param map ��װ�˽��������Map
     * @param mediaId ͨ���زĹ���ӿ��ϴ���ý���ļ��õ���id
     * @return ������ϢXML�ַ���
     */
    private static String buildVoiceMessage(Map<String, String> map, String mediaId) {
        //���ͷ��ʺ�
        String fromUserName = map.get("FromUserName");
        // ������΢�ź�
        String toUserName = map.get("ToUserName");
        /**
         * ������ϢXML���ݸ�ʽ
         *<xml>
         <ToUserName><![CDATA[toUser]]></ToUserName>
         <FromUserName><![CDATA[fromUser]]></FromUserName>
         <CreateTime>12345678</CreateTime>
         <MsgType><![CDATA[voice]]></MsgType>
         <Voice>
         <MediaId><![CDATA[media_id]]></MediaId>
         </Voice>
         </xml>
         */
        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[voice]]></MsgType>" +
                        "<Voice>" +
                        "   <MediaId><![CDATA[%s]]></MediaId>" +
                        "</Voice>" +
                        "</xml>",
                fromUserName, toUserName, getMessageCreateTime(), mediaId);
    }

    /**
     * ����ͼ����Ϣ
     * @param map ��װ�˽��������Map
     * @return ͼ����ϢXML�ַ���
     */
    private static String buildNewsMessage(Map<String, String> map) {
        String fromUserName = map.get("FromUserName");
        // ������΢�ź�
        String toUserName = map.get("ToUserName");
        NewsItem item = new NewsItem();
        item.Title = "΢�ſ���ѧϰ�ܽᣨһ������΢�ſ��������";
        item.Description = "���������£�������������Ҫ��΢�Ź��ںſ�������ôҪ��׼���������ز����ٵĶ�����\n" +
                "\n" +
                "����1��Ҫ��һ���������ԵĹ��ںš�\n" +
                "\n" +
                "����2��������ʽ����Ŀ�������";
        item.PicUrl = "http://images2015.cnblogs.com/blog/289233/201601/289233-20160121164317343-2145023644.png";
        item.Url = "http://www.cnblogs.com/xdp-gacl/p/5149171.html";
        String itemContent1 = buildSingleItem(item);

        NewsItem item2 = new NewsItem();
        item2.Title = "΢�ſ���ѧϰ�ܽᣨ��������΢�ſ�������";
        item2.Description = "΢�ŷ��������൱��һ��ת�����������նˣ��ֻ���Pad�ȣ�����������΢�ŷ�������΢�ŷ�����Ȼ������ת�������ǵ�Ӧ�÷�������Ӧ�÷�����������Ϻ󣬽���Ӧ���ݻط���΢�ŷ�������΢�ŷ������ٽ�������Ӧ��Ϣ�ظ���΢��App�նˡ�";
        item2.PicUrl = "";
        item2.Url = "http://www.cnblogs.com/xdp-gacl/p/5151857.html";
        String itemContent2 = buildSingleItem(item2);


        String content = String.format("<xml>\n" +
                "<ToUserName><![CDATA[%s]]></ToUserName>\n" +
                "<FromUserName><![CDATA[%s]]></FromUserName>\n" +
                "<CreateTime>%s</CreateTime>\n" +
                "<MsgType><![CDATA[news]]></MsgType>\n" +
                "<ArticleCount>%s</ArticleCount>\n" +
                "<Articles>\n" + "%s" +
                "</Articles>\n" +
                "</xml> ", fromUserName, toUserName, getMessageCreateTime(), 2, itemContent1 + itemContent2);
        return content;

    }

    /**
     * ����ͼ����Ϣ��һ����¼
     *
     * @param item
     * @return
     */
    private static String buildSingleItem(NewsItem item) {
        String itemContent = String.format("<item>\n" +
                "<Title><![CDATA[%s]]></Title> \n" +
                "<Description><![CDATA[%s]]></Description>\n" +
                "<PicUrl><![CDATA[%s]]></PicUrl>\n" +
                "<Url><![CDATA[%s]]></Url>\n" +
                "</item>", item.Title, item.Description, item.PicUrl, item.Url);
        return itemContent;
    }


    /**
     * ������յ�ͼƬ��Ϣ
     *
     * @param map
     * @return
     */
    private static String handleImageMessage(Map<String, String> map) {
        String picUrl = map.get("PicUrl");
        String mediaId = map.get("MediaId");
        System.out.print("picUrl:" + picUrl);
        System.out.print("mediaId:" + mediaId);
        String result = String.format("���յ���������ͼƬ��ͼƬUrlΪ��%s\nͼƬ�ز�IdΪ��%s", picUrl, mediaId);
        return buildTextMessage(map, result);
    }

    /**
     * ������յ�������Ϣ
     * @param map
     * @return
     */
    private static String handleVoiceMessage(Map<String, String> map) {
        String format = map.get("Format");
        String mediaId = map.get("MediaId");
        System.out.print("format:" + format);
        System.out.print("mediaId:" + mediaId);
        String result = String.format("���յ���������������������ʽΪ��%s\n�����ز�IdΪ��%s", format, mediaId);
        return buildTextMessage(map, result);
    }

    /**
     * ������յ�����Ƶ��Ϣ
     * @param map
     * @return
     */
    private static String handleVideoMessage(Map<String, String> map) {
        String thumbMediaId = map.get("ThumbMediaId");
        String mediaId = map.get("MediaId");
        System.out.print("thumbMediaId:" + thumbMediaId);
        System.out.print("mediaId:" + mediaId);
        String result = String.format("���յ�����������Ƶ����Ƶ�е��ز�IDΪ��%s\n��ƵIdΪ��%s", thumbMediaId, mediaId);
        return buildTextMessage(map, result);
    }

    /**
     * ������յ���С��Ƶ��Ϣ
     * @param map
     * @return
     */
    private static String handleSmallVideoMessage(Map<String, String> map) {
        String thumbMediaId = map.get("ThumbMediaId");
        String mediaId = map.get("MediaId");
        System.out.print("thumbMediaId:" + thumbMediaId);
        System.out.print("mediaId:" + mediaId);
        String result = String.format("���յ���������С��Ƶ��С��Ƶ���ز�IDΪ��%s,\nС��ƵIdΪ��%s", thumbMediaId, mediaId);
        return buildTextMessage(map, result);
    }

    /**
     * ������յ��ĵ���λ����Ϣ
     * @param map
     * @return
     */
    private static String handleLocationMessage(Map<String, String> map) {
        String latitude = map.get("Location_X");  //γ��
        String longitude = map.get("Location_Y");  //����
        String label = map.get("Label");  //����λ�þ���
        String result = String.format("γ�ȣ�%s\n���ȣ�%s\n����λ�ã�%s", latitude, longitude, label);
        return buildTextMessage(map, result);
    }

    /**
     * ������յ���������Ϣ
     * @param map
     * @return
     */
    private static String handleLinkMessage(Map<String, String> map) {
        String title = map.get("Title");
        String description = map.get("Description");
        String url = map.get("Url");
        String result = String.format("���յ������������ӣ����ӱ���Ϊ��%s,\n����Ϊ��%s\n,���ӵ�ַΪ��%s", title, description, url);
        return buildTextMessage(map, result);
    }

    /**
     * ������ϢMessage
     * @param map ��װ�˽��������Map
     * @return
     */
    private static String handleEventMessage(Map<String, String> map) {
        String responseMessage = buildWelcomeTextMessage(map);
        return responseMessage;
    }

}





/**
 * ͼ����Ϣ
 */
class NewsItem {
    public String Title;

    public String Description;

    public String PicUrl;

    public String Url;
}

/**
 * ������Ϣ
 */
class Music {
    public String title;
    public String description;
    public String musicUrl;
    public String hqMusicUrl;
}

/**
 * ��Ƶ��Ϣ
 */
class Video {
    public String title;
    public String description;
    public String mediaId;
}



