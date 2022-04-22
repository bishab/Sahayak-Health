package com.sabkokura.sahayekhelath.ModelClasses;


import com.google.gson.annotations.SerializedName;

public class FAQsModelClass {

    String question;
    String question_np;
    String answer;
    String answer_np;

    private boolean expanded;


    public FAQsModelClass(String question, String question_np, String answer, String answer_np) {
        this.question = question;
        this.question_np = question_np;
        this.answer = answer;
        this.answer_np = answer_np;
        this.expanded = false;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion_np() {
        return question_np;
    }

    public void setQuestion_np(String question_np) {
        this.question_np = question_np;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer_np() {
        return answer_np;
    }

    public void setAnswer_np(String answer_np) {
        this.answer_np = answer_np;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
