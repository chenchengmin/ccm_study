
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.erayt.solar2.logging.Log;
import com.erayt.solar2.logging.LogFactory;
/**
 * HashMap对象深克隆处理
 * @author ccm
 * @since 2018-09-14
 */
public class CloneUtils {
	private static final Log logger = LogFactory.getLogger(CloneUtils.class);
	 
	    @SuppressWarnings("unchecked")
	    public static <T extends Serializable> T clone(T obj){
	        T clonedObj = null;
	        try {
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            ObjectOutputStream oos = new ObjectOutputStream(baos);
	            oos.writeObject(obj);
	            oos.close();
	             
	            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
	            ObjectInputStream ois = new ObjectInputStream(bais);
	            clonedObj = (T) ois.readObject();
	            ois.close();
	        }catch (Exception e){
	            e.printStackTrace();
	            logger.info("%%%%%%map深克隆失败,错误信息errorMsg={}", e.getMessage());
	        }
	        return clonedObj;
	    }
}