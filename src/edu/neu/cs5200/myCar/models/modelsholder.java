package edu.neu.cs5200.myCar.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class modelsholder {
		  @JsonProperty("models")
		   private List<models> model;

		public List<models> getModel() {
			return model;
		}
		public void setMake(List<models> model) {
			this.model = model;
		} 

}
