

 
 // profit-单笔收益;totalProfit-累计收益;limitDayScore当日累计获得积分;maxProfit累计收益最大值
 def getTradeProfitScore(profit, totalProfit, limitDayScore ,maxProfit){
 array={0,true};
  if(profit<0){
  return array;
  }
  // 单次总积分值
  int totalScore = 0;
  if (profit < 0.1) {
   totalScore = 1;
  }else{
   totalScore = profit/0.1;
  }
  //单笔上限控制
  if(totalScore > 20){
   totalScore = 20;
  }
   //交易盈亏上限控制
  int tempTotal = totalScore + (int)limitDayScore;
  if(limitDayScore > 200){
  totalScore = 0;
  }
 if(limitDayScore < 200 && tempTotal > 200){
  totalScore = 200 - limitDayScore;
  }
  array[0] = totalScore;
  return array;
 }
 






//定义方法
def add(a,b){ 
a + b; 
} 

//-------------------------------------------------------------------
/**
 * Sample MVEL 2.0 Script
 * "Functional QuickSort"
 * by: Christopher Michael Brock, Inspired by: Dhanji Prasanna
 */
import java.util.*;
// the main quicksort algorithm
def quicksort(list) {
	    if (list.size() <= 1) {
	         list;
	    }
	    else {
	         pivot = list[0];
	         concat(quicksort(($ in list if $ < pivot)), pivot, quicksort(($ in list if $ > pivot)));
	    }
}
// define method to concatenate lists.
def concat(list1, pivot, list2) {
	    concatList = new ArrayList(list1);
	    concatList.add(pivot);
	    concatList.addAll(list2);
	    concatList;
}
// create a list to sort
list = [5,2,4,1,18,10,15,1,0];
// sort it!
quicksort(list);
//-------------------------------------------------------------------


def test1{
user='ccm';
assert user!=null;
}








import com.ccm.mvel.IntegralHandler;
def testIntegral{
//========****=========****=========配置分数参数区域========****=========****=========
//========普通赛配置分数
	//一等奖OrdinaryGame1		二等奖OrdinaryGame2 	三等奖OrdinaryGame3 	其他奖OrdinaryGame99
	OrdinaryGame1 = 8;	OrdinaryGame2 = 4;  OrdinaryGame3 = 3; 	OrdinaryGame99=1;
//========系列赛初赛配置分数
	//一等奖preliminaryGame1 	二等奖preliminaryGame2 	三等奖preliminaryGame3 	其他奖preliminaryGame99
	preliminaryGame1 = 5;	preliminaryGame2 = 4;  preliminaryGame3 = 3; 	preliminaryGame99=1;
//========系列赛复赛配置分数
	//一等奖rematchGame1 	二等奖rematchGame2 	三等奖rematchGame3 	其他奖rematchGame99
	rematchGame1 = 10;	rematchGame2 = 8;  rematchGame3 = 6; 	rematchGame99=1;
//========系列赛复赛递变参数配置(系列赛中若多个复赛的给分规则不一样，可配置此递变参数（可配置正负整数），递变基准是第一个复赛)
	// 复赛递变参数fixedRematchParam  复赛递变算术运算符arithOperators（支持加减乘除）
	fixedRematchParam = 3;  arithOperators='%';
//========系列赛决赛配置分数
	//一等奖finalsGame1 	二等奖finalsGame2 	三等奖finalsGame3 	其他奖finalsGame99
	finalsGame1 = 15;	finalsGame2 = 12;  finalsGame3 = 9; 	finalsGame99=1;
//========****=========****=========配置分数参数区域========****=========****=========

// gameStage-大赛阶段(0普通赛/1初赛/2复赛/3决赛);rewardType-奖励等级(1,2,3,4,5...10);maxLevelCode最大层级赛编号	
// java对象构造----获得大赛赛事等级和多个复赛的编号
integralHandlerStage = new IntegralHandler(gameStage,maxLevelCode);
// 普通赛0 初赛 1 复赛2 决赛 3
gameStage = integralHandler.gameStage;
// 复赛有多个对应的复赛编号；普通赛和决赛默认给0
lowStage = integralHandler.lowStage;



// java对象构造----获取对应大赛对应奖励等级分数
if(gameStage == '0'){
	integralHandlerScore 
	= new IntegralHandler(rewardType,OrdinaryGame1,OrdinaryGame2,OrdinaryGame3,OrdinaryGame99);
}
else if(gameStage == '1'){
	integralHandlerScore 
	= new IntegralHandler(rewardType,preliminaryGame1,preliminaryGame2,preliminaryGame3,preliminaryGame99);
}
else if(gameStage == '2'){
	integralHandlerScore 
	= new IntegralHandler(rewardType,rematchGame1,rematchGame2,rematchGame3,rematchGame99,
								lowStage,fixedRematchParam,arithOperators);
}
else if(gameStage == '3'){
	integralHandlerScore 
	= new IntegralHandler(rewardType,finalsGame1,finalsGame2,finalsGame3,finalsGame99);
}
integerScore = integralHandlerScore.integerScore;
return integerScore;



}




























//-------------------------------------------------------------------
import com.ccm.mvel.Person;
import com.ccm.mvel.Student;

def test2(){
//测试new引入的对象
//user= new Student("ccm",27,"广丰县");
//user='ccm';
//测试map
map = ['Bob' : new Person('Bob', new Student("ccm",44444,"广丰县")), 'Michael' : new Person('Michael', new Student("zsx",27,"郑州市"))];
fistItem=map.get('Bob').name;
studentAge=map.get('Bob').student.age;
System.out.println("==========================end==================");
map.put('lmy' , new Person('lmy', new Student("lmy",666666,"广丰县")));
System.out.println("lmy============"+map.get('lmy').name);
foreach (map : str) {
   System.out.print(); 
}







//测试list
list=["Jim", "Bob", "Smith"];
list1=list.get(0);


//对象控制检查
user1=new Person('Bob', new Student("ccm",44444,"广丰县"));//student对象不为空的情况，返回ccm
user1=new Person('Bob', null);//student对象为空的情况，返回null
user1.?student.name;


//流程控制语句
x = 5;
if (x > 0) {
   System.out.println("Greater than zero!");
}else if (x == -1) { 
   System.out.println("Minus one!");
}else { 
   System.out.println("Something else!");
}


//三元表达式  不能使用var来命名变量名称
//y =11;
//y > 0 ? "Yes" : "No";


//嵌套三元表达式
m= 0;
m > 0 ? "Yes" : (m == -1 ? "Minus One!" : "No");


//测试foreach循环遍历
str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//字符串本身就是一个字符数组
foreach (el : str) {
   System.out.print("[" + el + "]"); 
}

//foreach计数------低版本的不适用
/**
foreach (x : 100) { 
   System.out.print(x);
} 
*/


}




