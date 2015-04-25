package edu.neu.cs5200.myCar.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class photo {

	private String photoSrcs[];

	public String[] getPhotoSrcs() {
		return photoSrcs;
	}

	public void setPhotoSrcs(String[] photoSrcs) {
		this.photoSrcs = photoSrcs;
	}



}