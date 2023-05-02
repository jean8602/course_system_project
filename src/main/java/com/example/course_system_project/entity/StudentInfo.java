package com.example.course_system_project.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student_info")

public class StudentInfo {

	@Id
	@Column(name = "student_ID")
	private String studentID;
	@Column(name = "student_name")
	private String studentName;
	@Column(name = "course_ID")
	private String courseID;

	public StudentInfo() {

	}
	
	

	public StudentInfo(String courseID) {
		super();
		this.courseID = courseID;
	}



	public StudentInfo(String studentID, String studentName) {
		super();
		this.studentID = studentID;
		this.studentName = studentName;
	}



	public StudentInfo(String studentID, String studentName, String courseID) {
		super();
		this.studentID = studentID;
		this.studentName = studentName;
		this.courseID = courseID;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

}
