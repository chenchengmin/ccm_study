

/**
 * descript :
 * Created by hnp on 2018-05-28.
 */
public class MindGoConstants {
	/** td接口交易码*/
	public static final String S30015 = "S30015";
    public static final String S50001 = "S50001";
    public static final String S50002 = "S50002";
    public static final String S90014 = "S90014";
    public static final String S90013 = "S90013";
    public static final String S90015 = "S90015";
    public static final String S90016 = "S90016";
    public static final String S70001 = "S70001";
    public static final String S70002 = "S70002";
    public static final String S70003 = "S70003";
    public static final String S70004 = "S70004";
    public static final String S70005 = "S70005";
    public static final String S70006 = "S70006";
    //td牌价推送packname
    public static final String S80000 = "S80000";

    
    /** td接口交易必输字段校验*/
    public static final String INSTID = "instId";
    public static final String ORDERFLAG = "orderFlag";
    public static final String TOPBANK = "40303";
    public static final String PARAM_DEVICENUMBER = "param.deviceNumber";
    public static final String PARAM_CUSTOMERID = "param.customerId";
    
    
    /** session管理相关*/
    public static final String SESSION_KEY = "uniqueClientId";
    
    
    
    /** its接口交易码*/
    public static final String A10001 = "A10001";
    public static final String A10002 = "A10002";
    public static final String A70002 = "A70002";
    public static final String A10003 = "A10003";
    public static final String A10004 = "A10004";
    public static final String A10005 = "A10005";
    public static final String A10006 = "A10006";
    public static final String A10007 = "A10007";
    public static final String A10008 = "A10008";
    public static final String A10009 = "A10009";
    public static final String A10010 = "A10010";
    public static final String A10011 = "A10011";
    public static final String A10012 = "A10012";
    public static final String A10013 = "A10013";
    //its牌价推送packname
    public static final String A80000 = "A80000";

    
    /**双向心跳包内容：服务端心跳请求命令	service_request*/
	public static final String CMD_HEARTBEAT_REQUEST_SERVICE = "service_request";
	/**双向心跳包内容：服务端心跳反馈命令  	service_response*/
	public static final String CMD_HEARTBEAT_RESPONSE_SERVICE = "service_response";
	/**双向心跳包内容：客户端心跳请求命令  	client_request*/
	public static final String CMD_HEARTBEAT_REQUEST_CLIENT = "client_request";
	/**双向心跳包内容：客户端心跳反馈命令	client_response*/
	public static final String CMD_HEARTBEAT_RESPONSE_CLIENT = "client_response";
	
    
    /** 接口key关键字*/
    public static final String MINDGO_PACKNAME = "packname";
    public static final String MINDGO_TELEPHONE = "telephone";
    public static final String MINDGO_CUSTOMERID = "customerId";
    public static final String MINDGO_CLIENTID = "clientId";
    public static final String MINDGO_TOPBANK = "topBank";
    public static final String MINDGO_CARDID = "cardId";
    public static final String MINDGO_TOPBANK_VALUE = "40303";
    public static final String MINDGO_REQUESTID = "requestId";
    public static final String MINDGO_RESPONSEID = "responseId";
    public static final String MINDGO_DEVICENUMBER = "deviceNumber";
    public static final String MINDGO_CUSTOMERNAME = "customerName";
    public static final String MINDGO_DEALFLAG = "dealFlag";
    public static final String MINDGO_SERIALNO = "serialNo";
    public static final String MINDGO_RELATIONNO = "relationNo";
    public static final String MINDGO_SERIALFLAG = "serialFlag";
    public static final String UNIQUE_CLIENTID = "uniqueClientId";
    public static final String PUSH_FLAG = "pushFlag";
    
    /** solar*/
    public static final String MARGIN_ACCOUNT = "marginAccount";
    public static final String PARAM_MARGIN_ACCOUNT = "param.marginAccount";
    public static final String CARD_ID = "cardId";
    public static final String PARAM_CUSTOMER_ID = "param.customerId";
    public static final String REQ_JSON = "reqJson";

    public static final String DO_MARGIN_TRADE = "doMarginTrade.action";
    public static final String DO_MARGIN_ORDER = "doMarginOrder.action";
    public static final String DO_UPDATE_MARGIN_ORDER = "doUpdateMarginOrder.action";
    public static final String DO_CANCEL_MARGIN_ORDER = "doCancelMarginOrder.action";
    public static final String QUERY_MARGIN_TRADE_LOG = "queryMarginTradeLog.action";
    public static final String QUERY_MARGIN_ORDER_LOG = "queryMarginOrderLog.action";
    public static final String QUERY_MARGIN_ACC_USD_AMT = "queryMarginAccUSDAmt.action";
    public static final String QUERY_CUST_POSITION = "queryCustPosition.action";
    public static final String QUERY_MARGIN_PRICE = "queryMarginPrice.action";
    public static final String GET_LAST = "getLast.do";

    public final static String MIND_GO = "mindGo";
    public static final String DO_AUTD_FORWARD= "doAutdForward.do";
    public static final String DO_CIM_FORWARD= "signDirect.action";
    public static final String DO_LOGIN_FORWARD= "login.do";
    public static final String RESETCUSTFUNDSBYTELEPHONE= "resetCustFundsByTelephone.action";
    public static final String DO_AUTD_FORWARD_QUEYR= "queryAutdForward.do";
    
    
    public static final String SGEB_MATCHHIS_ = "SGEB_MATCHHIS_";
    public static final String SGEB_ORDERHIS_ = "SGEB_ORDERHIS_";
	public static final String TABLENAME = "tableName";
	public static final String SYSDATE = "sysDate";
	//its业务日期
	public static final String SYS_CURRDATE = "SystemDate";
	//td业务日期
	public static final String SYS_CURRTRADEDATE = "SysTradeDate";
	public static final String SYS_CURRTIME = "SystemTime";
	public static final String SYS_ACCOUNT_DATE = "accountDate";
	
	public static final String COMMA = ",";
	public static final String TDMatchData = "TDMatchData";
	public static final String TDOrderData = "TDOrderData";
	public static final String ITSMatchData = "ITSMatchData";
	public static final String ITSOrderData = "ITSOrderData";

	public static final String AUTD = "AUTD";
	public static final String MARGIN_FEX = "MARGIN_FEX";
	public static final String MARGIN_COM = "MARGIN_COM";

	public static final String PARAM_ORDER_STATUS = "param.orderStatus";
    /** 委托状态：1-下单 2-显示 3-下单+显示 */
	public static final int ORDER_STATUS_PLACE = 1;
	public static final int ORDER_STATUS_SHOW = 2;
	public static final int ORDER_STATUS_ALL = 3;
	public static final String PRICESORT = "priceSort";
	//TD牌价推送客户端：客户端uniqueClientId contains 此字符串就推送牌价
	public static final String PUSHED_TD_CLIENTID = "pushTdPriceClient";
	//ITS牌价推送客户端：客户端uniqueClientId contains 此字符串就推送牌价
	public static final String PUSHED_ITS_CLIENTID = "pushItsPriceClient";
	
	public static final String SEMICOLON = ";";
	//量化客户渠道来源
	public static final String CHANNEL_NO = "channelFlag";
}
