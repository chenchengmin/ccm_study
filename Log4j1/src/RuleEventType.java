

/**
 * @description 事件枚举类型
 * @author zdf
 * @since 2017�?11�?24�? 上午9:23:35
 */
public enum RuleEventType {

    login888("login", "登录"),
    joinGroup("joinGroup", "加入圈子"),
    traeProfit("traeProfit", "交易盈亏"),
    joinGame("joinGame", "参加大赛"),
    gameWiner("gameWiner", "大赛获奖"),
    share("share", "分享");

    private String name;
    private String describe;

    RuleEventType(String name, String describe) {
        this.name = name;
        this.describe = describe;
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
