package com.example.course_system_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course_info")

public class CourseInfo {

	@Id
	@Column(name = "course_ID")
	private String courseID;
	@Column(name = "course_name")
	private String courseName;
	@Column(name = "weekday")
	private int weekday;
	@Column(name = "start_time")
	private int startTime;
	@Column(name = "end_time")
	private int endTime;
	@Column(name = "course_unit")
	private int courseUnit;
	@Column(name = "student_number")
	private int studentNumber;

	public CourseInfo() {

	}

	public CourseInfo(String courseID, String courseName, int weekday, int startTime, int endTime, int courseUnit,
			int studentNumber) {
		super();
		this.courseID = courseID;
		this.courseName = courseName;
		this.weekday = weekday;
		this.startTime = startTime;
		this.endTime = endTime;
		this.courseUnit = courseUnit;
		this.studentNumber = studentNumber;
	}

	public CourseInfo(String courseID, String courseName, int weekday, int startTime, int endTime, int courseUnit) {
		super();
		this.courseID = courseID;
		this.courseName = courseName;
		this.weekday = weekday;
		this.startTime = startTime;
		this.endTime = endTime;
		this.courseUnit = courseUnit;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
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

	public int getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}

}
