
public class JavaBean {
	public JavaBean(String id, String name) {
        this.id = id;
        this.name = name;
    }
 
    private String id;
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    private String name;
 
    public JavaBean() {
    }
 
    @Override
    public String toString() {
        return "{用户ID：" + this.id + ",  用户名:" + this.name + " }";
    }
	
	
	
	
	
	
	
	
}
