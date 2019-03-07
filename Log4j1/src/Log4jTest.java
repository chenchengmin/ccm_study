import org.apache.log4j.Logger;



public class Log4jTest {
	
	public  static Logger log = Logger.getLogger("Log4jTest.class");
	
	public static void main(String[] args) {
		log.trace("trade信息");
		log.debug("debug信息");
		log.info("info信息");
		log.warn("warn信息");
		log.error("error信息");
		log.fatal("fatal信息");
		
		try {
			String s=null;
			s.length();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.trace("trace 一个异常",e);
			log.debug("debug 一个异常",e);
			log.info("info 一个异常",e);
			log.warn("warn 一个异常",e);
			log.error("error 一个异常",e);
			log.fatal("fatal 一个异常",e);
		}
				
		
		
	}
}
