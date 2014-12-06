package com.myprojects.tutorme.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myprojects.tutorme.model.QuizQuestion;

public class QuizQuestionRowMapper  implements RowMapper<QuizQuestion> {
	//@Override -- To use this change Java Compiler compliance to 1.6 
	public QuizQuestion mapRow(ResultSet resultSet, int rowNum) throws SQLException {
	// TODO Auto-generated method stub
	QuizQuestion dbQuestion = new QuizQuestion();
	dbQuestion.setCourseId(resultSet.getString(1));
	dbQuestion.setQuizId(resultSet.getString(2));
	dbQuestion.setQuestionNum(resultSet.getString(3));
	dbQuestion.setQuestion(resultSet.getString(4));
	dbQuestion.setOption1(resultSet.getString(5));
	dbQuestion.setOption2(resultSet.getString(6));
	dbQuestion.setOption3(resultSet.getString(7));
	dbQuestion.setOption4(resultSet.getString(8));
	dbQuestion.setAnswer(resultSet.getString(9));
	return dbQuestion;
	}
}
