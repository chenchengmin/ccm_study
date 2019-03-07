package com.ccm.mvel;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.compiler.CompiledExpression;
import org.mvel2.compiler.ExpressionCompiler;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;

public class TestMVEL {

	
	
	 public static Logger log = Logger.getLogger(TestMVEL.class);  
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		//1、编译模式执行mvel脚本
		/*表达式的执行有两种模式 
		一种是编译模式，就是先编译表达式并缓存，执行的时候传入对应的参数 
		一种是解析模式，跟其他脚本语言一样，边解析边执行 */
		
		//======1========一种是编译模式，就是先编译表达式并缓存，执行的时候传入对应的参数
		/*Serializable compiled = MVEL.compileExpression("");
		MVEL.executeExpression(compiled, new HashMap<String,String>());*/
		
		/*ExpressionCompiler compiler = new ExpressionCompiler("x + y");  
		log.debug("=======tip=======待执行的mvel脚本是：x+y");
		CompiledExpression exp = compiler.compile();  
		log.debug("=======tip=======编译完成");
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("x", 10);  
		params.put("y", 20);  
		Object result = MVEL.executeExpression(exp, params);  
		log.debug("=======tip=======脚本执行完毕");
		System.out.println(result);  
		System.out.println("---------1 end------------------");*/
		
		
		String compileExpression = MVEL.compileExpression("x+y").toString();
		Object compiled2 = (Object) compileExpression;
		Serializable com3 = (Serializable) compiled2;
		String a = "";
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("x", 10);  
		params.put("y", 20); 
		System.out.println(MVEL.executeExpression(com3, params));
		
		
		
		//======2========一种是解析模式，跟其他脚本语言一样，边解析边执行
		/*log.debug("=======tip=======解析模式开始执行");
		Map<String, Object> params2 = new HashMap<String, Object>();  
		params2.put("x", 10);  
		params2.put("y", 20);  
		Object result2 = MVEL.eval("x+y", params2);  
		System.out.println(result2);  
		log.debug("=======tip=======解析模式结束执行");
		System.out.println("---------2 end------------------");*/
		
		//======3========调用脚本文件中的函数 
		/*log.debug("=======tip=======调用脚本文件中的函数 ");
		File scriptFile = new File("src/mvelConfig/test.el");  
		VariableResolverFactory resolverFactory = new MapVariableResolverFactory();  
		MVEL.evalFile(scriptFile, ParserContext.create(), resolverFactory);  
		resolverFactory.createVariable("x", 10);  
		resolverFactory.createVariable("y", 20);  
		Object result3 = MVEL.eval("add(x,y);", resolverFactory);  
		System.out.println(result3);  
		System.out.println("---------3 end------------------");*/
		
		
		//======4========
	/*	log.debug("=======tip=======调用脚本文件中的函数 ");
		File scriptFile = new File("src/mvelConfig/test.el");  
		VariableResolverFactory resolverFactory = new MapVariableResolverFactory();  
		MVEL.evalFile(scriptFile, ParserContext.create(), resolverFactory);  
		resolverFactory.createVariable("x", 10);  
		resolverFactory.createVariable("y", 20);  
		Object result3 = MVEL.eval("quicksort(list);",resolverFactory);  
		System.out.println(result3);  */
		
		/*log.debug("=======tip=======调用脚本文件中的函数 ");
		File scriptFile = new File("src/mvelConfig/test.el");  
		VariableResolverFactory resolverFactory = new MapVariableResolverFactory();  
		MVEL.evalFile(scriptFile, ParserContext.create(), resolverFactory);  
		resolverFactory.createVariable("profit", "2180");  
		resolverFactory.createVariable("totalProfit", "4360.74");  
		resolverFactory.createVariable("limitDayScore","25" );  
		resolverFactory.createVariable("maxProfit","2200" );  
		Object[] result3 =(Object[]) MVEL.eval("getTradeProfitScore(profit, totalProfit, limitDayScore ,maxProfit);",resolverFactory);
		System.out.println(result3[0]);  */
		//Object result3 = MVEL.eval("getTradeProfitScore(profit, totalProfit, limitDayScore ,maxProfit);",resolverFactory);
		//System.out.println(result3);  
		
		
		
		
		//======4========
		/*log.debug("=======tip=======调用脚本文件中的函数 ");
		File scriptFile = new File("src/mvelConfig/test.el");  
		VariableResolverFactory resolverFactory = new MapVariableResolverFactory();  
		MVEL.evalFile(scriptFile, ParserContext.create(), resolverFactory);  
		Object result3 = MVEL.eval("test1();",resolverFactory);  
		System.out.println(result3);*/ 

		//让personInst对象成为表达式的上下文对象----传递一个参数
		/*Person personInst = new Person();
		personInst.setName("Mr. Foo");
		personInst.setStudent(new Student("ccm"));
		Object result1 = MVEL.eval("student.name == 'ccm'", personInst);
		Object result2 = MVEL.eval("name == 'Mr. Foo'", personInst);
		//对单个对象进行多次操作(是对作为属性的对象进行多次操作)
		Object result3 = MVEL.eval("with (student) { name = 'lmy' };", personInst);
		//Student result4 = (Student)MVEL.eval("with (student) { name = 'lmy' };", personInst);
		Student result4 = (Student)MVEL.eval
					("with (student) { name = 'lmy',age=39,adderss='上饶市广丰县嵩峰乡杨柳村' };", personInst);
		log.debug("=======result1======= "+result1);
		log.debug("=======result2======= "+result2);
		log.debug("=======result3======= "+result3);
		log.debug("=======result4======= "+result4);
		//简单的从对象中提取属性值
		String result = (String) MVEL.eval("name", personInst);
		log.debug("=======result======= "+result);
		assert "Mr. Foo".equals(result);
		log.debug("============================end============================ "+result);*/
		
		
		
		//解释版本：传递多个参数给表达式语言
		/*Map vars = new HashMap();
		vars.put("x", new Integer(5));
		vars.put("y", new Integer(10));
		Integer result = (Integer) MVEL.eval("x * y", vars);
		assert result.intValue() == 50;  // Mind the JDK 1.4 compatible code :)
		log.debug("=======result======= "+result);*/
		
		// 编译版本：The compiled expression is serializable and can be cached for re-use.
		/*ExpressionCompiler compiler = new ExpressionCompiler("x * y");  
		log.debug("=======tip=======待执行的mvel脚本是：x*y");
		CompiledExpression exp = compiler.compile();
		Map vars = new HashMap();
		vars.put("x", new Integer(5));
		vars.put("y", new Integer(10));
		// Executes the compiled expression
		Integer result = (Integer) MVEL.executeExpression(exp, vars); 
		assert result.intValue() == 50; 
		log.debug("=======result======= "+result);*/
		
		
		//测试new关键字
		/*String result = (String) MVEL.eval("new String('foo');");
		log.debug("=======result======= "+result);*/
		
		//测试脚本语言map 999999
		/*log.debug("=======tip=======调用脚本文件中的函数 ");
		File scriptFile = new File("src/mvelConfig/test.el");  
		VariableResolverFactory resolverFactory = new MapVariableResolverFactory();  
		MVEL.evalFile(scriptFile, ParserContext.create(), resolverFactory);  
		Object result3 = MVEL.eval("test2();",resolverFactory);  
		System.out.println(result3); */
		
		//x / y=3.6999999999999997
		//x / y=3.6999999999999997
		
		/*ExpressionCompiler compiler = new ExpressionCompiler("x / y");  
		log.debug("=======tip=======待执行的mvel脚本是：x+y");
		CompiledExpression exp = compiler.compile();  
		log.debug("=======tip=======编译完成");
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("x", 10);  
		params.put("y", 2);  
		String result = String.valueOf(MVEL.executeExpression(exp, params)); 
	  		String arr[] = result.split("[.]"); 
	  		int integral=0;
	  		//整数部分就是数组的第一个 
	  		String zhengshu = arr[0]; 
	  		if(Double.valueOf(result) -Integer.valueOf(zhengshu) >0){
	  			integral =  Integer.valueOf(zhengshu)+1;
	  		}else{
	  			integral = Integer.valueOf(zhengshu);
	  		}
	  	log.debug("=======tip===integral====脚本执行完毕"+integral);
		log.debug("=======tip=======脚本执行完毕");
		System.out.println("x / y="+result);  
		System.out.println();
		System.out.println("---------1 end------------------");
		*/
		
		
		/*log.debug("=======tip=======调用脚本文件中的函数 ");
		File scriptFile = new File("src/mvelConfig/test1.el");  
		VariableResolverFactory resolverFactory = new MapVariableResolverFactory();  
		MVEL.evalFile(scriptFile, ParserContext.create(), resolverFactory);  
		resolverFactory.createVariable("levelCode", "2");  
		resolverFactory.createVariable("rewardType", "6");  
		resolverFactory.createVariable("maxLevelCode", "8");  
		Object result3 = MVEL.eval("testIntegral();",resolverFactory);  
		System.out.println(result3);  */
		
		
		
        
		
	}

}
