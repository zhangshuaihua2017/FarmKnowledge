package com.farm.model;

public class Question2Num {
	private int num1;
	private String signal;
	private int num2;
	private int result;
	
	
	
	public Question2Num(int num1, String signal, int num2, int result) {
		super();
		this.num1 = num1;
		this.signal = signal;
		this.num2 = num2;
		this.result = result;
	}
	public int getNum1() {
		return num1;
	}
	public void setNum1(int num1) {
		this.num1 = num1;
	}
	public String getSignal1() {
		return signal;
	}
	public void setSignal1(String signal1) {
		this.signal = signal1;
	}
	public int getNum2() {
		return num2;
	}
	public void setNum2(int num2) {
		this.num2 = num2;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "Question2Num [num1=" + num1 + ", signal=" + signal + ", num2=" + num2 + ", result=" + result + "]";
	}
	
	
}
