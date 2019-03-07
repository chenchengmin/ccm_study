package com.ccm.ognl;
  
public class SlEmployee implements java.io.Serializable {  
  
    private static final long serialVersionUID = 4873217019660076767L;  
  
    private SlDept slDept;  
      
    private String name;  
  
    public SlEmployee() {  
    }  
  
    public SlDept getSlDept() {  
        return slDept;  
    }  
  
    public void setSlDept(SlDept slDept) {  
        this.slDept = slDept;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
}  