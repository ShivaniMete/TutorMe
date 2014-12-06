package com.myprojects.tutorme.services;

public class CourseGrade {
	private String courseId;
	private String courseName;
	private String courseCategory;
	private String grades;
	
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
	public String getGrades() {
		return grades;
	}
	public void setGrades(String grades) {
		this.grades = grades;
	}
	
	
}
