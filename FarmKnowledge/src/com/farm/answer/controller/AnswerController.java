package com.farm.answer.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.farm.answer.service.AnswerService;
import com.google.gson.Gson;
import com.jfinal.core.Controller;

public class AnswerController extends Controller{
	public void OneUpMath() {
		List list1 = new AnswerService().getQuestion2OneUpMath();
		List list2 = new AnswerService().getQuestion3OneUpMath();
		JSONObject jo = new JSONObject();
		Gson gson = new Gson();
		jo.put("oneUp",gson.toJson(list1));
		jo.put("oneUp2",gson.toJson(list2));
		
		renderJson(jo.toString());
	}
	public void OneDownMath() {
		List list1 = new AnswerService().getQuestion2OneDownMath();
		List list2 = new AnswerService().getQuestion3OneDownMath();
		JSONObject jo = new JSONObject();
		Gson gson = new Gson();
		jo.put("oneDown",gson.toJson(list1));
		jo.put("oneDown2",gson.toJson(list2));
		
		renderJson(jo.toString());
	}

}
