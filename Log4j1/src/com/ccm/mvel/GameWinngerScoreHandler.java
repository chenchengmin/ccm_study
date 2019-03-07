package com.ccm.mvel;


import org.apache.log4j.Logger;
/**
 * 大赛获奖Mvel脚本调用处理类：
 * 		1、判定当前赛所处的赛事级别：普通赛、初赛、复赛、决赛
 * 		2、动态获取获奖的分数
 * @author Administrator
 *
 */
public class GameWinngerScoreHandler {
	public static Logger log = Logger.getLogger(GameWinngerScoreHandler.class); 
	//	大赛阶段编号
	private String levelCode;
	//	赛事级别
	private String gameStage;
	//	奖品等级
	private String rewardType;
	//	最大系列赛编号
	private String maxLevelCode;
	//	系列赛复赛层级编号
	private String lowStage;
	//	获得的积分
	private double integerScore;
	//	递变参数
	private int fixedRematchParam;
	//	算术运算符
	private String  arithOperators;
	//	一等奖
	private int firstPrize;
	//	二等奖
	private int secondPrize;
	//	三等奖
	private int thirdPrize;
	//	其他奖
	private int otherPrize;	
	
	/**
	 * 根据levelCode（大赛层级赛编号），最大层级赛编号（maxLevelCode）转化gameStage为大赛等级
	 * @param gameStage
	 * 				大赛层级赛编号
	 * @param maxLevelCode
	 * 				最大层级赛编号
	 */
	public GameWinngerScoreHandler(String levelCode, String maxLevelCode) {
		super();
		this.levelCode = levelCode;
		this.maxLevelCode = maxLevelCode;
		// 普通赛
		if("0".equals(levelCode)){
			this.lowStage = "0";
			this.gameStage = "0";
		}
		//	初赛
		else if("1".equals(levelCode)){
			this.lowStage = "0";
			this.gameStage = "1";
		}
		//	复赛
		else if(Integer.valueOf(levelCode) > 1 && Integer.valueOf(levelCode) < Integer.valueOf(maxLevelCode)){
			for(int i = 2; i < Integer.valueOf(maxLevelCode); i++){
				if(i == Integer.valueOf(levelCode)){
					this.lowStage = String.valueOf(i-1);
				}
			}
			this.gameStage="2";
		}
		//	决赛
		else if(Integer.valueOf(levelCode) > 1 && gameStage.equals(maxLevelCode) ){
			this.lowStage = "0";
			this.gameStage="3";
		}
	}
	
	/**
	 * 普通赛+初赛+决赛：获取对应大赛、对应奖励等级应得的积分
	 * @param gameStage
	 * 				普通赛0 初赛 1 复赛2 决赛 3
	 * @param rewardType
	 * 				奖励等级
	 * @param maxLevelCode
	 * 				最大层级赛编号
	 */
	public GameWinngerScoreHandler(String rewardType, int firstPrize, int secondPrize,int thirdPrize,int otherPrize){
		if("1".equals(rewardType)){
			this.integerScore = firstPrize;
		}
		else if("2".equals(rewardType)){
			this.integerScore = secondPrize;
		}
		else if("3".equals(rewardType)){
			this.integerScore = thirdPrize;
		}
		else{
			this.integerScore = otherPrize;
		}
	}

	/**
	 * 获取复赛的获奖积分
	 * @param rewardType
	 * @param firstPrize
	 * @param secondPrize
	 * @param thirdPrize
	 * @param otherPrize
	 * @param lowStage
	 * @param fixedRematchParam
	 * @param arithOperators
	 */
	public GameWinngerScoreHandler(String rewardType, int firstPrize, int secondPrize,int thirdPrize,int otherPrize
			,String lowStage,int fixedRematchParam,String arithOperators){
		int tempScore = 0;
		switch(rewardType){
			case "1":
				tempScore = firstPrize;
				break;
			case "2":
				tempScore = secondPrize;
				break;
			case "3":
				tempScore = thirdPrize;
				break;
			default:
				tempScore = otherPrize;
				break;
		}
		if("+".equals(arithOperators)){
			this.integerScore = tempScore+(Integer.valueOf(lowStage)-1)*fixedRematchParam;
			log.debug("tempScore="+tempScore+";基数="+(Integer.valueOf(lowStage)-1)*fixedRematchParam);
			log.debug("+:"+this.integerScore);
		}else if("-".equals(arithOperators)){
			this.integerScore = tempScore-(Integer.valueOf(lowStage)-1)*fixedRematchParam;
			log.debug("tempScore="+tempScore+";基数="+(Integer.valueOf(lowStage)-1)*fixedRematchParam);
			log.debug("-:"+this.integerScore);
			if(this.integerScore < 0){
			}
		}else if("*".equals(arithOperators)){
			this.integerScore = tempScore*Math.pow(fixedRematchParam, (Integer.valueOf(lowStage)-1));//m的n次幂
			log.debug("tempScore="+tempScore+";基数="+Math.pow(fixedRematchParam, (Integer.valueOf(lowStage)-1)));
			log.debug("*:"+this.integerScore);
		}else if("/".equals(arithOperators)){
			this.integerScore = tempScore/Math.pow(fixedRematchParam, (Integer.valueOf(lowStage)-1));//m的n次幂
			log.debug("tempScore="+tempScore+";基数="+Math.pow(fixedRematchParam, (Integer.valueOf(lowStage)-1)));
			log.debug("/:"+this.integerScore);
		}
	}
	
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public int getFirstPrize() {
		return firstPrize;
	}

	public void setFirstPrize(int firstPrize) {
		this.firstPrize = firstPrize;
	}

	public int getSecondPrize() {
		return secondPrize;
	}

	public void setSecondPrize(int secondPrize) {
		this.secondPrize = secondPrize;
	}

	public int getThirdPrize() {
		return thirdPrize;
	}

	public void setThirdPrize(int thirdPrize) {
		this.thirdPrize = thirdPrize;
	}

	public int getOtherPrize() {
		return otherPrize;
	}

	public void setOtherPrize(int otherPrize) {
		this.otherPrize = otherPrize;
	}
	public int getFixedRematchParam() {
		return fixedRematchParam;
	}

	public void setFixedRematchParam(int fixedRematchParam) {
		this.fixedRematchParam = fixedRematchParam;
	}

	public String getArithOperators() {
		return arithOperators;
	}

	public void setArithOperators(String arithOperators) {
		this.arithOperators = arithOperators;
	}

	public double getIntegerScore() {
		return integerScore;
	}

	public void setIntegerScore(int integerScore) {
		this.integerScore = integerScore;
	}

	public String getGameStage() {
		return gameStage;
	}

	public void setGameStage(String gameStage) {
		this.gameStage = gameStage;
	}

	public String getRewardType() {
		return rewardType;
	}

	public void setRewardType(String rewardType) {
		this.rewardType = rewardType;
	}

	public String getMaxLevelCode() {
		return maxLevelCode;
	}

	public void setMaxLevelCode(String maxLevelCode) {
		this.maxLevelCode = maxLevelCode;
	}

	public String getLowStage() {
		return lowStage;
	}

	public void setLowStage(String lowStage) {
		this.lowStage = lowStage;
	}
public static void main(String[] args) {
		
		String levelCode = "2";
		String rewardType = "1";
		String maxLevelCode = "8";
		System.out.println("--begin--->levelCode="+levelCode+";rewardType="+rewardType+";maxLevelCode="+maxLevelCode);
		GameWinngerScoreHandler integralHandler1 = new GameWinngerScoreHandler(levelCode,maxLevelCode);
		System.out.println("---1--->gameStage="+integralHandler1.getGameStage());
		System.out.println("---1--->lowStage="+integralHandler1.getLowStage());
		//	获取普通赛、初赛、决赛分数
		String lowStage = integralHandler1.getLowStage();
		GameWinngerScoreHandler integralHandler2 = new GameWinngerScoreHandler(rewardType, 10,8,6,1,lowStage,3,"%");
		System.out.println("---2--->integralScore="+integralHandler2.getIntegerScore());
		//	获取复赛分数
		GameWinngerScoreHandler integralHandler3 = new GameWinngerScoreHandler(rewardType, 10,8,6,1);
		System.out.println("---3--->integralScore="+integralHandler3.getIntegerScore());
	}
}
		
