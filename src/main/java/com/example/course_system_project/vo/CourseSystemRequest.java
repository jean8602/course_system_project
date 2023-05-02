package com.example.course_system_project.vo;

import java.util.List;

import javax.persistence.Column;

import com.example.course_system_project.entity.CourseInfo;
import com.example.course_system_project.entity.StudentInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseSystemRequest {

	@JsonProperty("courseInfo")
	private CourseInfo courseInfo;

	@JsonProperty("studentInfo")
	private StudentInfo studentInfo;

	private List<String> courseID;

	private String courseName;

	private int weekday;

	private int startTime;

	private int endTime;

	private int courseUnit;

	private String studentID;

	private String studentName;

	public CourseSystemRequest() {

	}

	public CourseSystemRequest(String studentID) {
		super();
		this.studentID = studentID;
	}

	public CourseSystemRequest(CourseInfo courseInfo, List<String> courseID, String courseName, int weekday,
			int startTime, int endTime, int courseUnit) {
		super();
		this.courseInfo = courseInfo;
		this.courseID = courseID;
		this.courseName = courseName;
		this.weekday = weekday;
		this.startTime = startTime;
		this.endTime = endTime;
		this.courseUnit = courseUnit;
	}

	public CourseSystemRequest(String studentID, List<String> courseID) {
		super();
		this.courseID = courseID;
		this.studentID = studentID;
	}

	public CourseSystemRequest(CourseInfo courseInfo, StudentInfo studentInfo, List<String> courseID, String courseName,
			int weekday, int startTime, int endTime, int courseUnit, String studentID, String studentName) {
		super();
		this.courseInfo = courseInfo;
		this.studentInfo = studentInfo;
		this.courseID = courseID;
		this.courseName = courseName;
		this.weekday = weekday;
		this.startTime = startTime;
		this.endTime = endTime;
		this.courseUnit = courseUnit;
		this.studentID = studentID;
		this.studentName = studentName;
	}

	public CourseInfo getCourseInfo() {
		return courseInfo;
	}

	public void setCourseInfo(CourseInfo courseInfo) {
		this.courseInfo = courseInfo;
	}

	public StudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public List<String> getCourseID() {
		return courseID;
	}

	public void setCourseID(List<String> courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getWeekday() {
		return weekday;
	}

	public void setWeekday(int weekday) {
		this.weekday = weekday;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public int getCourseUnit() {
		return courseUnit;
	}

	public void setCourseUnit(int courseUnit) {
		this.courseUnit = courseUnit;
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

}
