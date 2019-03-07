package com.ccm.ognl;
  
import java.util.HashSet;  
import java.util.Set;  
  
public class SlDept implements java.io.Serializable {  
  
    private static final long serialVersionUID = 3537237434024057830L;  
      
    private String name;  
      
    private Set<SlEmployee> slEmployees = new HashSet<SlEmployee>(0);  
  
    public SlDept() {  
      
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public Set<SlEmployee> getSlEmployees() {  
        return slEmployees;  
    }  
  
    public void setSlEmployees(Set<SlEmployee> slEmployees) {  
        this.slEmployees = slEmployees;  
    }  
  
}  