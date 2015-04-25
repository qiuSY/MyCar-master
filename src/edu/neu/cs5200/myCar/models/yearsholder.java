package edu.neu.cs5200.myCar.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class yearsholder {
	  @JsonProperty("years")
	   private List<years> year;
		public List<years> getYear() {
			return year;
		}
		public void setYear(List<years> year) {
			this.year = year;
		} 


}
