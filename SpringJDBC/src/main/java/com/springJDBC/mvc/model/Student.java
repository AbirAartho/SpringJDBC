package com.springJDBC.mvc.model;


public class Student {
	
	    private Long id;
	    private String name;
	    private String rollNo;
	    private String emailId;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getRollNo() {
			return rollNo;
		}
		public void setRollNo(String rollNo) {
			this.rollNo = rollNo;
		}
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}
		
		
		public Student() {
		}
		
		public Student(Long id, String name, String rollNo, String emailId) {
			this.id = id;
			this.name = name;
			this.rollNo = rollNo;
			this.emailId = emailId;
		}
		public Student(String name, String rollNo, String emailId) {
			this.name = name;
			this.rollNo = rollNo;
			this.emailId = emailId;
		}
	    
	    

}
