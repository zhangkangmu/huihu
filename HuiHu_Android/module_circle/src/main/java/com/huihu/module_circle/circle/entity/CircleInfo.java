package com.huihu.module_circle.circle.entity;

import java.util.List;

public class CircleInfo {
    private List<ActiveCircleBean> activeCircle;
    private List<PopularCircleBean> popularCircle;
    private List<RecentCircleBean> recentCircle;

    public List<ActiveCircleBean> getActiveCircle() {
        return activeCircle;
    }

    public void setActiveCircle(List<ActiveCircleBean> activeCircle) {
        this.activeCircle = activeCircle;
    }

    public List<PopularCircleBean> getPopularCircle() {
        return popularCircle;
    }

    public void setPopularCircle(List<PopularCircleBean> popularCircle) {
        this.popularCircle = popularCircle;
    }

    public List<RecentCircleBean> getRecentCircle() {
        return recentCircle;
    }

    public void setRecentCircle(List<RecentCircleBean> recentCircle) {
        this.recentCircle = recentCircle;
    }

    public static class ActiveCircleBean extends CircleBaseBean{

    }

    public static class PopularCircleBean extends CircleBaseBean{


    }

    public static class RecentCircleBean extends CircleBaseBean{

    }
}
