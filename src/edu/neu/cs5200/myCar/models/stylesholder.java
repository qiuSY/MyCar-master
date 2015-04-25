package edu.neu.cs5200.myCar.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class stylesholder {
		@JsonProperty("styles")
			   private List<styles> style;

			public List<styles> getStyle() {
				return style;
			}
			public void setStyle(List<styles> style) {
				this.style = style;
			} 


}
