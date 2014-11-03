package com.myprojects.tutorme.model;

public class Course {
	private String courseId;
	private String courseName;
	private String courseCategory;
	
	public String getCourseId(){
		return courseId;
	}
	public void setCourseId(String courseId){
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName){
		this.courseName = courseName;
	}
	public String getCourseCategory(){
		return courseCategory;
	}
	public void setCourseCategory(String courseCategory){
		this.courseCategory = courseCategory;
	}
}
