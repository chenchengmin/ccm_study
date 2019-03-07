

import java.io.Serializable;

/**
 * 积分策略顺序表实体类
 * 
 * @author
 * @date
 * @rmk
 */
public class IntegralRuleSequence implements Serializable {

    /** */
    private static final long serialVersionUID = 5270466003318916345L;

    /** 事件ID:事件采用枚举值 **/
    private String eventId;
    /** 策略名称:对应积分策略表Name **/
    private String ruleName;
    /** 策略顺序号 **/
    private int sequenceNum;

    public IntegralRuleSequence() {
        super();
    }

    public IntegralRuleSequence(String eventId,String ruleName,int sequenceNum) {
        this.eventId=eventId;
        this.ruleName=ruleName;
        this.sequenceNum=sequenceNum;
    }
    
    public String getEventId() {
        return eventId;
    }

    public IntegralRuleSequence(String ruleName, int sequenceNum) {
        super();
        this.ruleName = ruleName;
        this.sequenceNum = sequenceNum;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public int getSequenceNum() {
        return sequenceNum;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public void setSequenceNum(int sequenceNum) {
        this.sequenceNum = sequenceNum;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("IntegralRuleSequence [eventId=");
        builder.append(eventId);
        builder.append(", ruleName=");
        builder.append(ruleName);
        builder.append(", sequenceNum=");
        builder.append(sequenceNum);
        builder.append("]");
        return builder.toString();
    }
}
