package org.pvronlineModel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class CityBean {
	
	@JsonProperty("theaters")
	private List<TheaterBean> theaters;

	@JsonProperty("city")
	private String city;

	public List<TheaterBean> getTheaters() {
		return theaters;
	}

	public void setTheaters(List<TheaterBean> theaters) {
		this.theaters = theaters;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "CityBean [theaters=" + theaters + ", city=" + city + "]";
	}
}