package com.kh.createQuiz.service;

import java.io.InputStream;
import com.kh.createQuiz.model.dao.CreateQuizDao;

public class CreateQuizService {

	private CreateQuizDao createQuizDao;

	public boolean createQuiz(String title, String explanation, String category, String tag,
			InputStream thumbnailInputStream) {
		try {
			// DAO 호출하여 Quiz 및 이미지 데이터를 DB에 저장
			createQuizDao.insertquiz(title, explanation, category, tag, thumbnailInputStream);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
