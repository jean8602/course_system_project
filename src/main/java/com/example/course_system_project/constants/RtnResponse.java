package com.example.course_system_project.constants;

public enum RtnResponse {

	SUCCESSFUL("200", "Successful"), 
	CANNOT_EMPTY("400", "course Id or course name is empty"),
	DATA_ERROR("400", "data is error"),
	NOT_FOUND("404", "Not found"),
	INFO_ERROR("400", "Info is error"),
	DUPLICATE_INFO("400", "Info is duplicate");
	

	private String code;

	private String message;
	

	private RtnResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
