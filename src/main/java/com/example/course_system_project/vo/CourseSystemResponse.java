package com.example.course_system_project.vo;

import com.example.course_system_project.entity.CourseInfo;
import com.example.course_system_project.entity.StudentInfo;

public class CourseSystemResponse {

	private String message;

	private CourseInfo courseList;

	private StudentInfo studentList;

	public CourseSystemResponse() {

	}
	
	

	public CourseSystemResponse(String message, CourseInfo courseList) {
		super();
		this.message = message;
		this.courseList = courseList;
	}



	public CourseSystemResponse(String message) {
		super();
		this.message = message;
	}

	public CourseSystemResponse(String message, CourseInfo courseList, StudentInfo studentList) {
		super();
		this.message = message;
		this.courseList = courseList;
		this.studentList = studentList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CourseInfo getCourseList() {
		return courseList;
	}

	public void setCourseList(CourseInfo courseList) {
		this.courseList = courseList;
	}

	public StudentInfo getStudentList() {
		return studentList;
	}

	public void setStudentList(StudentInfo studentList) {
		this.studentList = studentList;
	}

}
