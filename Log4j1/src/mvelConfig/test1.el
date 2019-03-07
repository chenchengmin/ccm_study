import com.ccm.mvel.GameWinngerScoreHandler;
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
			// 复赛递变参数fixedRematchParam  复赛递变算术运算符arithOperators（支持加减乘除+、-、*、/）
			fixedRematchParam = 3;  arithOperators='+';
//========系列赛决赛配置分数
			//一等奖finalsGame1 	二等奖finalsGame2 	三等奖finalsGame3 	其他奖finalsGame99
			finalsGame1 = 15;	finalsGame2 = 12;  finalsGame3 = 9; 	finalsGame99=1;
//========****=========****=========配置分数参数区域========****=========****=========
// levelCode-大赛层级赛编号;rewardType-奖励等级(1,2,3,4,5...10);maxLevelCode最大层级赛编号	
// java对象构造----获得大赛赛事等级和多个复赛的编号
integralHandlerStage = new GameWinngerScoreHandler(levelCode,maxLevelCode);
// 普通赛0 初赛 1 复赛2 决赛 3
gameStage = integralHandlerStage.gameStage;
// 复赛有多个对应的复赛编号；普通赛和决赛默认给0
lowStage = integralHandlerStage.lowStage;
// java对象构造----获取对应大赛对应奖励等级分数
if(gameStage == '0'){
	integralHandlerScore 
	= new GameWinngerScoreHandler(rewardType,OrdinaryGame1,OrdinaryGame2,OrdinaryGame3,OrdinaryGame99);
}
else if(gameStage == '1'){
	integralHandlerScore 
	= new GameWinngerScoreHandler(rewardType,preliminaryGame1,preliminaryGame2,preliminaryGame3,preliminaryGame99);
}
else if(gameStage == '2'){
	integralHandlerScore 
	= new GameWinngerScoreHandler(rewardType,rematchGame1,rematchGame2,rematchGame3,rematchGame99,lowStage,fixedRematchParam,arithOperators);
}
else if(gameStage == '3'){
	integralHandlerScore 
	= new GameWinngerScoreHandler(rewardType,finalsGame1,finalsGame2,finalsGame3,finalsGame99);
}
//	获得的积分
integerScore = integralHandlerScore.integerScore;
return integerScore;
}
