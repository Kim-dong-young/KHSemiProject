package com.kh.createQuiz.service;

import com.kh.createQuiz.model.vo.Answer;
import com.kh.createQuiz.model.vo.CreateQuiz;
import com.kh.createQuiz.model.vo.Problem;

public interface CreateQuizService {
    int createQuiz(CreateQuiz quiz);
    int insertProblems(Problem p, Answer a);
}
