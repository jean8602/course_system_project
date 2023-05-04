package com.example.course_system_project.vo;

public class StudentEnrollInfoCheck {

	private String studentID;

	private String studentName;

	private String courseID;

	private String courseName;

	private int weekday;

	private int startTime;

	private int endTime;

	private int courseUnit;

	public StudentEnrollInfoCheck() {

	}

	public StudentEnrollInfoCheck(String studentID, String studentName, String courseID, String courseName, int weekday,
			int startTime, int endTime, int courseUnit) {
		super();
		this.studentID = studentID;
		this.studentName = studentName;
		this.courseID = courseID;
		this.courseName = courseName;
		this.weekday = weekday;
		this.startTime = startTime;
		this.endTime = endTime;
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

}
