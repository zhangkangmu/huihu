package com.huihu.module_notification.reply.entity;

/**
 * create by wangjing on 2019/4/13 0013
 * description:
 */
public class AnswerReadState {

    /**
     * allAnswerRead : true
     * questionId : 0
     * questionUnReadCount : 0
     */

    private boolean allAnswerRead;
    private long questionId;
    private int questionUnReadCount;
    private boolean isAnswer;

    public boolean isAnswer() {
        return isAnswer;
    }

    public void setAnswer(boolean answer) {
        isAnswer = answer;
    }

    public boolean isAllAnswerRead() {
        return allAnswerRead;
    }

    public void setAllAnswerRead(boolean allAnswerRead) {
        this.allAnswerRead = allAnswerRead;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public int getQuestionUnReadCount() {
        return questionUnReadCount;
    }

    public void setQuestionUnReadCount(int questionUnReadCount) {
        this.questionUnReadCount = questionUnReadCount;
    }
}
