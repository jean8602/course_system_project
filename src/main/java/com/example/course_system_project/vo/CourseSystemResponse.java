package com.example.course_system_project.vo;

import java.util.List;

import com.example.course_system_project.entity.CourseInfo;
import com.example.course_system_project.entity.StudentInfo;

public class CourseSystemResponse {

	private String message;

	private CourseInfo courseList;

	private StudentInfo studentList;

	private List<CourseInfo> courseInfoList;
	
	private List<StudentEnrollInfoCheck> studentEnrollInfoCheck;
	
	public CourseSystemResponse() {

	}
	
	

	public CourseSystemResponse(List<StudentEnrollInfoCheck> studentEnrollInfoCheck) {
		super();
		this.studentEnrollInfoCheck = studentEnrollInfoCheck;
	}



	public CourseSystemResponse(String message, List<CourseInfo> courseInfoList) {
		super();
		this.message = message;
		this.courseInfoList = courseInfoList;
	}



	public CourseSystemResponse(CourseInfo courseList) {
		super();
		this.courseList = courseList;
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



	public List<CourseInfo> getCourseInfoList() {
		return courseInfoList;
	}



	public void setCourseInfoList(List<CourseInfo> courseInfoList) {
		this.courseInfoList = courseInfoList;
	}



	public List<StudentEnrollInfoCheck> getStudentEnrollInfoCheck() {
		return studentEnrollInfoCheck;
	}



	public void setStudentEnrollInfoCheck(List<StudentEnrollInfoCheck> studentEnrollInfoCheck) {
		this.studentEnrollInfoCheck = studentEnrollInfoCheck;
	}
	
	

}
