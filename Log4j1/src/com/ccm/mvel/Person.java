package com.ccm.mvel;

public class Person {

	private String name;
	
	private Student student;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public  Person(){
		
	}
	public Person(String name, Student student) {
		super();
		this.name = name;
		this.student = student;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", student=" + student
				+ ", getStudent()=" + getStudent() + ", getName()=" + getName()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
