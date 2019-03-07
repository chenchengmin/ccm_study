package com.ccm.mvel;

public class Student {

	
	private String name;
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", adderss="
				+ adderss + "]";
	}

	private int age;
	private String adderss;

	
	public Student(String name){
		this.name=name;
	}
	
	
	public Student(String name ,int age,String adderss){
		this.name=name;
		this.adderss=adderss;
		this.age=age;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAdderss() {
		return adderss;
	}
	public void setAdderss(String adderss) {
		this.adderss = adderss;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
