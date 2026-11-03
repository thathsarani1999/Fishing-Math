package com.perisic.fish.trivia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TriviaQuestion {

    public QuestionText question;
    public String correctAnswer;
    public List<String> incorrectAnswers;

    public static class QuestionText {
        public String text;
    }

    public String getQuestion() {
        return question.text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getOptions() {

        List<String> options = new ArrayList<>();

        options.add(correctAnswer);
        options.addAll(incorrectAnswers);

        Collections.shuffle(options);

        return options;
    }
}