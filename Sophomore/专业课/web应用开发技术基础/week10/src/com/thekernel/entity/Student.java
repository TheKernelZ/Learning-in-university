package com.thekernel.entity;

public class Student {

	private int id;
	private double chinese;
	private double math;
	private double english;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getChinese() {
		return chinese;
	}

	public void setChinese(double chinese) {
		this.chinese = chinese;
	}

	public double getMath() {
		return math;
	}

	public void setMath(double math) {
		this.math = math;
	}

	public double getEnglish() {
		return english;
	}

	public void setEnglish(double english) {
		this.english = english;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", chinese=" + chinese + ", math=" + math + ", english=" + english + "]";
	}

	public Student() {
		super();
	}

	public Student(int id, double chinese, double math, double english) {
		super();
		this.id = id;
		this.chinese = chinese;
		this.math = math;
		this.english = english;
	}

}
