package org.liufeng.course.history.event;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 历史上的今天查询服务:爬虫网页信息
 * 
开发步骤

为了便于读者理解，我们需要清楚该应用实例的开发步骤，主要如下：

1）发起HTTP GET请求，获取网页源代码。

2）运用正则表达式从网页源代码中抽取我们需要的数据。

3）对抽取得到的数据进行加工（使内容呈现更加美观）。

4）将以上三步进行封装，供外部调用。

5）在公众账号后台调用封装好的“历史上的今天”查询方法。
 * 
 * @author liufeng
 * @date 2013-10-16
 * 
 */
public class TodayInHistoryService {

	/**
	 * 发起http get请求获取网页源代码
	 * 
	 * @param requestUrl
	 * @return
	 */
	private static String httpRequest(String requestUrl) {
		StringBuffer buffer = null;

		try {
			// 建立连接
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");

			// 获取输入流
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			// 读取返回结果
			buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}

			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			httpUrlConn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	/**
	 * 从html中抽取出历史上的今天信息
	 * 
	 * @param html
	 * @return
	 */
	private static String extract(String html) {
		StringBuffer buffer = null;
		// 日期标签：区分是昨天还是今天
		String dateTag = getMonthDay(0);

		Pattern p = Pattern.compile("(.*)(<div class=\"listren\">)(.*?)(</div>)(.*)");
		Matcher m = p.matcher(html);
		if (m.matches()) {
			buffer = new StringBuffer();
			if (m.group(3).contains(getMonthDay(-1)))
				dateTag = getMonthDay(-1);

			// 拼装标题
			//buffer.append("≡≡ ").append("历史上的").append(dateTag).append(" ≡≡").append("\n\n");
			buffer.append("≡≡ ").append("历史上的").append("10月16日").append(" ≡≡").append("\n\n");

			// 抽取需要的数据
			for (String info : m.group(3).split("  ")) {
				info = info.replace(dateTag, "").replace("（图）", "").replaceAll("</?[^>]+>", "").trim();
				// 在每行末尾追加2个换行符
				if (!"".equals(info)) {
					buffer.append(info).append("\n\n");
				}
			}
		}
		// 将buffer最后两个换行符移除并返回
		return (null == buffer) ? null : buffer.substring(0, buffer.lastIndexOf("\n\n"));
	}

	/**
	 * 获取前/后n天日期(M月d日)
	 * 
	 * @return
	 */
	private static String getMonthDay(int diff) {
		DateFormat df = new SimpleDateFormat("M月d日");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, diff);
		return df.format(c.getTime());
	}

	/**
	 * 封装历史上的今天查询方法，供外部调用
	 * 
	 * @return
	 */
	public static String getTodayInHistoryInfo() {
		// 获取网页源代码
		//String html = httpRequest("http://www.rijiben.com/");
		// 域名解析失败，采用直接页面源码html来获取历史上今天的数据信息
		String html1 = "<div class=\"listren\"><ul><li><a href=\"/news6836/\" title=\"0690年10月16日 武则天登上皇位\">0690年10月16日 武则天登上皇位</a>  （图）</li>                      <li><a href=\"/news6837/\" title=\"1854年10月16日 唯美主义运动的倡导者王尔德诞辰\">1854年10月16日 唯美主义运动的倡导者王尔德诞辰</a>  </li>                      <li><a href=\"/news6838/\" title=\"1854年10月16日 德国社会主义活动家考茨基诞生\">1854年10月16日 德国社会主义活动家考茨基诞生</a>  </li>                      <li><a href=\"/news6839/\" title=\"1908年10月16日 阿尔巴尼亚领导人恩维尔·霍查诞辰\">1908年10月16日 阿尔巴尼亚领导人恩维尔·霍查诞辰</a>  （图）</li>                      <li><a href=\"/news6840/\" title=\"1913年10月16日 中国“两弹一星”元勋钱三强诞辰\">1913年10月16日 中国“两弹一星”元勋钱三强诞辰</a>  （图）</li>                      <li><a href=\"/news6841/\" title=\"1922年10月16日 开滦煤矿工人罢工失败\">1922年10月16日 开滦煤矿工人罢工失败</a>  （图）</li>                      <li><a href=\"/news6842/\" title=\"1927年10月16日 德国诺贝尔文学奖得主格拉斯诞生\">1927年10月16日 德国诺贝尔文学奖得主格拉斯诞生</a>  （图）</li>                      <li><a href=\"/news6843/\" title=\"1933年10月16日 抗日同盟军失败\">1933年10月16日 抗日同盟军失败</a>  （图）</li>                      <li><a href=\"/news6844/\" title=\"1950年10月16日 人民解放军进军西藏\">1950年10月16日 人民解放军进军西藏</a>  （图）</li>                      <li><a href=\"/news6845/\" title=\"1954年10月16日 俞平伯《关于红楼梦研究问题的信》发表\">1954年10月16日 俞平伯《关于红楼梦研究问题的信》发表</a>  （图）</li>                      <li><a href=\"/news6846/\" title=\"1959年10月16日 美军将领、国务卿马歇尔去世\">1959年10月16日 美军将领、国务卿马歇尔去世</a>  （图）</li>                      <li><a href=\"/news6847/\" title=\"1964年10月16日 勃列日涅夫取代赫鲁晓夫  成为苏共中央第一书记\">1964年10月16日 勃列日涅夫取代赫鲁晓夫  成为苏共中央第一书记</a>  </li>                      <li><a href=\"/news6848/\" title=\"1964年10月16日 我国第一颗原子弹爆炸成功\">1964年10月16日 我国第一颗原子弹爆炸成功</a>  （图）</li>                      <li><a href=\"/news6849/\" title=\"1973年10月16日 震撼世界的石油危机爆发\">1973年10月16日 震撼世界的石油危机爆发</a>  （图）</li>                      <li><a href=\"/news6850/\" title=\"1978年10月16日 约翰·保罗二世当选新教皇\">1978年10月16日 约翰·保罗二世当选新教皇</a>  </li>                      <li><a href=\"/news6851/\" title=\"1979年10月16日 哈克将军宣布巴基斯坦推迟大选解散政党\">1979年10月16日 哈克将军宣布巴基斯坦推迟大选解散政党</a>  </li>                      <li><a href=\"/news6852/\" title=\"1984年10月16日 图图主教荣获“诺贝尔和平奖”\">1984年10月16日 图图主教荣获“诺贝尔和平奖”</a>  </li>                      <li><a href=\"/news6853/\" title=\"1988年10月16日 北京正负电子对撞机对撞成功\">1988年10月16日 北京正负电子对撞机对撞成功</a>  （图）</li>                      <li><a href=\"/news6854/\" title=\"1991年10月16日 美国小镇枪杀案22人丧生\">1991年10月16日 美国小镇枪杀案22人丧生</a>  </li>                      <li><a href=\"/news6855/\" title=\"1991年10月16日 莫扎特死因有新说\">1991年10月16日 莫扎特死因有新说</a>  </li>                      <li><a href=\"/news6856/\" title=\"1991年10月16日 钱学森获“国家杰出贡献科学家”殊荣\">1991年10月16日 钱学森获“国家杰出贡献科学家”殊荣</a>  （图）</li>                      <li><a href=\"/news6857/\" title=\"1994年10月16日 德国总理科尔四连任\">1994年10月16日 德国总理科尔四连任</a>  </li>                      <li><a href=\"/news6858/\" title=\"1994年10月16日 第十二届广岛亚运会闭幕\">1994年10月16日 第十二届广岛亚运会闭幕</a>  </li>                      <li><a href=\"/news6859/\" title=\"1994年10月16日 修秦陵制秦俑工匠墓葬被发现\">1994年10月16日 修秦陵制秦俑工匠墓葬被发现</a>  </li>                      <li><a href=\"/news6860/\" title=\"1995年10月16日 美国百万黑人男子大游行\">1995年10月16日 美国百万黑人男子大游行</a>  （图）</li>                    </ul>  </div>     ";
		// 从网页中抽取信息
		String result = extract(html1);
		return result;
	}

	/**
	 * 通过main在本地测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String info = getTodayInHistoryInfo();
		System.out.println(info);
	}
}