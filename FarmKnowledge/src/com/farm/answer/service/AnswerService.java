package com.farm.answer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.farm.model.Question2Num;
import com.farm.model.Question3Num;

public class AnswerService {
	public List<Question2Num> getQuestion2OneUpMath(){
		Random random = new Random();
		List list  = new ArrayList<Question2Num>();
		int i = 100;
		while(i-->0) {
			String signal = random.nextBoolean()? "+": "-";
			int a = random.nextInt(20);
			int b = random.nextInt(20);
			int result = 0;
			if(signal.equals("+")) {
				result = a + b;
			}else {
				b = random.nextInt(a+1);
				result = a - b;
			}
			if(result>20) {
				i++;
				continue;
			}
			
			list.add(new Question2Num(a,signal,b,result));
		}
		return list;
	}
	public List<Question3Num> getQuestion3OneUpMath(){
		Random random = new Random();
		List list  = new ArrayList<Question3Num>();
		int i = 100;
		while(i-->0) {
			String signal1 = random.nextBoolean()? "+": "-";
			String signal2 = random.nextBoolean()? "+": "-";
			int num1 = random.nextInt(15)+5;
			int num2 = random.nextInt(20);
			int num3 = random.nextInt(20);
			int result = 0;
			int num12 = 0;
			if(signal1.equals("-")) {
				num2 = random.nextInt(num1+1);
				num12 = num1 - num2;
				if(signal2.equals("-")) {
					num3 = random.nextInt(num12+1);
					result = num12 - num3;
				}else {
					result = num12 + num3;
				}
			}else {
				num12 = num1 + num2;
				if(signal2.equals("-")) {
					num3 = random.nextInt(num12+1);
					result = num12 - num3;
				}else {
					result = num12 + num3;
				}
			}
			if(result>20) {
				i++;
				continue;
			}
			list.add(new Question3Num(num1,signal1,num2,signal2,num3,result));
		}
		return list;
	}
	
	public List<Question2Num> getQuestion2OneDownMath(){
		Random random = new Random();
		List list  = new ArrayList<Question2Num>();
		int i = 100;
		while(i-->0) {
			String signal = random.nextBoolean()? "+": "-";
			int a = random.nextInt(100);
			int b = a>10 ?random.nextInt(100) : random.nextInt(90)+10;
			int result = 0;
			if(signal.equals("+")) {
				result = a + b;
			}else {
				b = random.nextInt(a+1);
				result = a - b;
			}
			if(result>100) {
				i++;
				continue;
			}
			
			list.add(new Question2Num(a,signal,b,result));
		}
		return list;
	}
	public List<Question3Num> getQuestion3OneDownMath(){
		Random random = new Random();
		List list  = new ArrayList<Question3Num>();
		int i = 100;
		while(i-->0) {
			String signal1 = random.nextBoolean()? "+": "-";
			String signal2 = random.nextBoolean()? "+": "-";
			int num1 = random.nextInt(90)+10;
			int num2 = random.nextInt(100);
			int num3 = random.nextInt(100);
			int result = 0;
			int num12 = 0;
			if(signal1.equals("-")) {
				num2 = random.nextInt(num1+1);
				num12 = num1 - num2;
				if(signal2.equals("-")) {
					num3 = random.nextInt(num12+1);
					result = num12 - num3;
				}else {
					result = num12 + num3;
				}
			}else {
				num12 = num1 + num2;
				if(signal2.equals("-")) {
					num3 = random.nextInt(num12+1);
					result = num12 - num3;
				}else {
					result = num12 + num3;
				}
			}
			if(result>100) {
				i++;
				continue;
			}
			list.add(new Question3Num(num1,signal1,num2,signal2,num3,result));
		}
		return list;
	}
}
