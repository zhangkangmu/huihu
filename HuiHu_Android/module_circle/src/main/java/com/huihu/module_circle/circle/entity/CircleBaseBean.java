package com.huihu.module_circle.circle.entity;

public class CircleBaseBean {
    /**
     * circleId : 0
     * circleName : string
     * height : 0
     * imageUrl : string
     * join : 0
     * memberNum : 0
     * width : 0
     */

    private long circleId;
    private String circleName;
    private int height;
    private String imageUrl;
    private int join;
    private int memberNum;
    private int width;

    public long getCircleId() {
        return circleId;
    }

    public void setCircleId(long circleId) {
        this.circleId = circleId;
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getJoin() {
        return join;
    }

    public void setJoin(int join) {
        this.join = join;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
