package com.ccm.enumtest;

/**
 * @description 成就组枚举类型
 * @author zdf
 * @since 2017年11月24日 上午9:23:35
 */
public enum AchievementGroup {

    cumulativeLogin("cumulativeLogin", "累计登录"),
    amount("amount", "交易量"),
    profit("profit", "交易盈亏"),
    number("number", "交易笔数"),
    specialEvent("specialEvent","特殊事件"),
    ccmTest("ccmTest","空成就组");

    private String name;
    private String describe;

    AchievementGroup(String name, String describe) {
        this.name = name;
        this.describe = describe;
    }

    public static String getDescribeByName(String name) {
        for (AchievementGroup event : AchievementGroup.values()) {
            if (event.getName().equals(name)) {
                return event.getDescribe();
            }
        }
        return null;
    }

    
    public static String getNameByDescribe(String describe) {
        for (AchievementGroup event : AchievementGroup.values()) {
            if (event.getDescribe().equals(describe)) {
                return event.getName();
            }
        }
        return null;
    }
    
    public String getName() {
        return name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

}
