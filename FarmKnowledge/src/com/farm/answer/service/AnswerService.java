package com.farm.answer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.farm.entity.Question3Num;

public class AnswerService {

	//一年级上册数学
	public List<Question3Num> getQuestion3OneUpMath(){
		Random random = new Random();
		List<Question3Num> list  = new ArrayList<Question3Num>();
		int i = 10;
		while(i-->0) {
			String signal1 = random.nextBoolean()? "+": "-";
			String s = random.nextBoolean()? "+": "-";
			String signal2 = random.nextBoolean()? s :"";
			int num1 = random.nextInt(15)+5;
			int num2 = random.nextInt(20);
			int num3 = 0;
			if(!signal2.equals("")) {
				num3 = random.nextInt(20);
			}
			int result = 0;
			int num12 = 0;
			if(signal2.equals("")) {
				if(signal1.equals("+")) {
					result = num1 + num2;
				}else {
					num2 = random.nextInt(num1+1);
					result = num1 - num2;
				}
			}else {
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
			}
			
			if(result>20) {
				i++;
				continue;
			}
			list.add(new Question3Num(num1,signal1,num2,signal2,num3,result));
		}
		return list;
	}
	
	//一年级下册数学
	public List<Question3Num> getQuestion3OneDownMath(){
		Random random = new Random();
		List<Question3Num> list  = new ArrayList<Question3Num>();
		int i = 10;
		while(i-->0) {
			String signal1 = random.nextBoolean()? "+": "-";
			String x = random.nextBoolean()? "+": "-";
			String signal2 = random.nextBoolean()? x:"";
			int num1 = random.nextInt(90)+10;
			int num2 = random.nextInt(100);
			int num3 = 0;
			if(!signal2.equals("")) {
				num3 = random.nextInt(100);
			}
			int result = 0;
			int num12 = 0;
			if(signal2.equals("")) {
				if(signal1.equals("+")) {
					result = num1 + num2;
				}else {
					num2 = random.nextInt(num1+1);
					result = num1 - num2;
				}
			}else {
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
