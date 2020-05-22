package cn.cc.utils.msgtype.json;

import java.io.Serializable;

/**
 * 
 * @description 用户基础信息，用于在认证后返回给调用信息
 * @author zkr02
 * @date 2016年9月30日下午5:02:54
 */
public class UserInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	/** 用户编码*/
	private String userCode;
	
	/** 用户姓名*/
	private String userName;
	
	/** 归属机构*/
	private String comCode;

	/** 核算单位*/
	private String centerCode;

	/** 核算单位名称*/
	private String centerName;

	/** 用户归属机构代码*/
	private String userComCode;

	/** 用户归属机构名称*/
	private String userComName;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getCenterCode() {
		return centerCode;
	}

	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getUserComCode() {
		return userComCode;
	}

	public void setUserComCode(String userComCode) {
		this.userComCode = userComCode;
	}

	public String getUserComName() {
		return userComName;
	}

	public void setUserComName(String userComName) {
		this.userComName = userComName;
	}
}
