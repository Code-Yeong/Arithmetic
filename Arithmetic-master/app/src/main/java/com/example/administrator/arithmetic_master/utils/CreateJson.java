package com.example.administrator.arithmetic_master.utils;

public class CreateJson {
	private String json="";
	private String jsonArray="";
	
	public CreateJson(){
		this.jsonArray="";
	}
	
	public void add(String key,String value){
		json+=("\""+key+"\":\""+value+"\",");
	}
	
	public String toString(){
		return "{"+json.substring(0, json.length()-1)+"}";
	}
	
	public String toJsonArray(){
		if(jsonArray.length()<1){
			return "[]";
		}
		return "["+jsonArray.substring(0, jsonArray.length()-1)+"]";
	}
	
	public void cat(){
		jsonArray+=("{"+(json.substring(0, json.length()-1))+"},");
		clear();
	}
	
	public void clear(){
		json="";
	}
}
