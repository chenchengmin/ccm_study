import org.apache.log4j.Logger;



public class Log4jTest {
	
	public  static Logger log = Logger.getLogger("Log4jTest.class");
	
	public static void main(String[] args) {
		log.trace("trade��Ϣ");
		log.debug("debug��Ϣ");
		log.info("info��Ϣ");
		log.warn("warn��Ϣ");
		log.error("error��Ϣ");
		log.fatal("fatal��Ϣ");
		
		try {
			String s=null;
			s.length();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.trace("trace һ���쳣",e);
			log.debug("debug һ���쳣",e);
			log.info("info һ���쳣",e);
			log.warn("warn һ���쳣",e);
			log.error("error һ���쳣",e);
			log.fatal("fatal һ���쳣",e);
		}
				
		
		
	}
}
