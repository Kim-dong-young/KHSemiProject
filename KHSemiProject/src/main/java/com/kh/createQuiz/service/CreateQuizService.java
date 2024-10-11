package com.kh.createQuiz.service;

import com.kh.createQuiz.model.vo.Answer;
import com.kh.createQuiz.model.vo.CreateQuiz;
import com.kh.createQuiz.model.vo.Problem;
import com.kh.createQuiz.model.vo.QuizTag;

public interface CreateQuizService {
    int createQuiz(CreateQuiz quiz, QuizTag tag);
    int insertProblems(Problem pr, Problem pr2, Problem pr3, Problem pr4, Problem pr5, Answer a, Answer a2,
			Answer a3, Answer a4, Answer a5);
	
}
