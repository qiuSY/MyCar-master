package edu.neu.cs5200.myCar.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class makesholder {
	  @JsonProperty("makes")
	   private List<makes> make;

	public List<makes> getMake() {
		return make;
	}

	public void setMake(List<makes> make) {
		this.make = make;
	} 
	   
}