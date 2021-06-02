package com.example.payoneerchallenge.model;

import com.google.gson.annotations.SerializedName;

public class Style{

	@SerializedName("language")
	private String language;

	public String getLanguage(){
		return language;
	}
}