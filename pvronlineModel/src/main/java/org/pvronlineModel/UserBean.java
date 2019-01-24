package org.pvronlineModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class UserBean {
	
	@JsonProperty("username")
	private String userName;
	
	@JsonProperty("fullname")
	private String fullName;
	
	@JsonProperty("city")
	private String city;
	
	@JsonProperty("citycode")
	private String cityCode;
	
	@JsonProperty("mailid")
	private String mailid;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMailid() {
		return mailid;
	}

	public void setMailid(String mailid) {
		this.mailid = mailid;
	}

	@Override
	public String toString() {
		return "UserBean [userName=" + userName + ", fullName=" + fullName + ", cityCode=" + cityCode + ", city=" + city
				+ ", email=" + mailid + "]";
	}

}