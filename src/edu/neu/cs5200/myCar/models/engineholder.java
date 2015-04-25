package edu.neu.cs5200.myCar.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class engineholder {
		  @JsonProperty("engines")
		   private List<engine> engines;

		public List<engine> getEngines() {
			return engines;
		}

		public void setEngines(List<engine> engines) {
			this.engines = engines;
		} 
		   
}