package com.ccm.ognl;

import com.ccm.ognl.SlDept;  
import com.ccm.ognl.SlEmployee;  
  
import ognl.Ognl;  
import ognl.OgnlContext;  
import ognl.OgnlException;  
  
public class OgnlTest {  
      
    public static void main(String[] args) throws OgnlException {  
        // 新建一个部门对象并设置部门名称  
        SlDept dept = new SlDept();  
        dept.setName("销售部");  
          
        // 新建一个员工对象并设置员工姓名  
        SlEmployee emp = new SlEmployee();  
        emp.setName("张三");  
          
        // 构建一个OgnlContext对象  
        OgnlContext context = new OgnlContext();  
          
        // 将上述部门和员工对象放入Ognl上下文环境中  
        context.put("dept", dept);  
        context.put("emp", emp);  
          
        // 将员工设置为根对象  
        context.setRoot(emp);  
        
        
        /*最终输出结果为销售部，需要注意的是看到上述的"#"是不是特别眼熟，对在Struts2中特别常见，而且大家应该发现所谓的对象图导航语言指的就是通过 "放置到OgnlContext中的名字.属性名字" 的方式去获取对应对象的属性值；
        首先介绍一下Ognl中，常用到的两个类：
        ognl.Ognl类：这个类主要用来解析和解释执行Ognl表达式
        ognl.OgnlContext类：这个类为Ognl表达式提供了一个执行环境，这个类实现了Map接口，所以允许通过put(key,obj)方法向OgnlContext环境中方式各种类型的对象，需要注意的是在OgnlContext中对象分为两种，第一种是叫做root对象（根对象），在整个OgnlContext中有且最多只能有一个根对象，可以通过调用OgnlContext.setRoot(obj)设置为根对象，另外一种就是OgnlContext中的普通对象，这种个数类型不受限制，那么既然分为两种方式，肯定在获取对象属性的方式上是有所不同的，下面通过代码比较下：
        1、获取普通对象的属性值方式；
        比如上述例子当中，dept对象就是放置到OgnlContext中的普通对象，对于这种普通对象，只能通过“#dept.name”的方式去获取属性值，需要注意的是dept指的是放置到上下文中key的值，另外在Dept类型中要提供getName方法；

        2、获取根对象的属性值的方式，有两种，第一种也是跟上述方式一样，不多做叙述*/
        
         
        // 从普通对象中直接获取部门名称 
        // 构建Ognl表达式的树状表示,用来获取  
        Object expression = Ognl.parseExpression("#dept.name");  
        // 解析树状表达式，返回结果  
        Object deptName = Ognl.getValue(expression, context, context.getRoot());  
        System.out.println(deptName); 
        
        // 间接获取部门名称  
        expression = Ognl.parseExpression("#emp.slDept.name");  
        deptName = Ognl.getValue(expression, context, context.getRoot());  
        System.out.println(deptName);  
        System.out.println("-------------------------------------------");  
        // 从根对象中直接获取部门名称  
        expression = Ognl.parseExpression("slDept.name");  
        deptName = Ognl.getValue(expression, context, context.getRoot());  
        System.out.println(deptName); 
        
        
        
        
        
        
    }  
      
}  