import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomUtil {
	public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String NUMBERCHAR = "0123456789";

	/**
	 * 返回一个定长的随机纯字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateMixString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALLCHAR.charAt(random.nextInt(LETTERCHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机纯大写字母字符串(只包含大写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateUpperString(int length) {
		return generateMixString(length).toUpperCase();
	}

	public static void main(String[] args) {
		long a = System.currentTimeMillis();
		Set<String> set = new HashSet<String>();  
	    int times = 500000;  
	    for(int i = 0 ;i<times;i++){  
	    	System.out.println("第"+i+"次邀请码是"+ generateUpperString(10));
	    	String str = generateUpperString(10);
	    	if(i == 1000000){
	    		System.out.println(1000000);
	    	}else if(i == 2000000){
	    		System.out.println(2000000);
	    	}
	    	else if(i == 3000000){
	    		System.out.println(3000000);
	    	}
	    	else if(i == 4000000){
	    		System.out.println(4000000);
	    	}
	    	else if(i == 5000000){
	    		System.out.println(5000000);
	    	}
	    	else if(i == 6000000){
	    		System.out.println(6000000);
	    	}
	    	else if(i == 7000000){
	    		System.out.println(7000000);
	    	}
	    	else if(i == 8000000){
	    		System.out.println(8000000);
	    	}
	    	else if(i == 9000000){
	    		System.out.println(9000000);
	    	}else if(i == 10000000){
	    		System.out.println(10000000);
	    	}
	    	
	    	
	    	if(i > 6000000){
	    		System.out.println(i);
	    	}
	    	
	        set.add(str);  
	    }  
	    long b = System.currentTimeMillis();
	    
	    System.out.println("耗时："+(b-a));
	    System.out.println("set的size="+set.size());
	    System.out.println("重复了："+(times-set.size())+"次");
	}
}
