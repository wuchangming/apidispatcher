package com.woqu.apidispatcher.api.requestheader;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 请求头
 * @author houdiandian
 *
 */
public class Req_header {

	private String appid;                            //app的id信息，用于标记当前请求的app的唯一性，iPhone/iPad/Android/.. 的appid不同
	private String app_version;                      //app的版本号
	private String dev_uuid;                         //设备的uuid
	private String network_type;                        //请求的网络类型
	private String client_ip;                      //设备的ip地址
	private String msg_seq;                          //请求的序列号，对应的响应返回该序列号（并发请求）。  
	private int encrypt;                            //加密的类型，用于扩展以及校验
	private String cmd ;                              //请求的业务命令字
	private String user_id;                        //用户的id
	@JSONField(name="BToken")
	private String BToken;  					   //BToken
	//private String business_key;                 //标记用户的业务key （加密）
	private String sign;                           //Add 用户签名，可以用user_id和密码 md5
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getApp_version() {
		return app_version;
	}
	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}
	public String getDev_uuid() {
		return dev_uuid;
	}
	public void setDev_uuid(String dev_uuid) {
		this.dev_uuid = dev_uuid;
	}
	public String getNetwork_type() {
		return network_type;
	}
	public void setNetwork_type(String network_type) {
		this.network_type = network_type;
	}
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
	public String getMsg_seq() {
		return msg_seq;
	}
	public void setMsg_seq(String msg_seq) {
		this.msg_seq = msg_seq;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public int getEncrypt() {
		return encrypt;
	}
	public void setEncrypt(int encrypt) {
		this.encrypt = encrypt;
	}
	public String getBToken() {
		return BToken;
	}
	@JSONField(name="BToken")
	public void setBToken(String bToken) {
		BToken = bToken;
	}
}
