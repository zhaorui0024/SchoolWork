package com.law.casetuijian.pojo;

public class LawLabel {
    private String anjianmiaoshu;
    private String anjianmingcheng;
    private String wenshuleixing;

    public LawLabel(String anjianmiaoshu, String anjianmingcheng, String wenshuleixing) {
        this.anjianmiaoshu = anjianmiaoshu;
        this.anjianmingcheng = anjianmingcheng;
        this.wenshuleixing = wenshuleixing;
    }

    public String getAnjianmiaoshu() {
        return anjianmiaoshu;
    }

    public void setAnjianmiaoshu(String anjianmiaoshu) {
        this.anjianmiaoshu = anjianmiaoshu;
    }

    public String getAnjianmingcheng() {
        return anjianmingcheng;
    }

    public void setAnjianmingcheng(String anjianmingcheng) {
        this.anjianmingcheng = anjianmingcheng;
    }

    public String getWenshuleixing() {
        return wenshuleixing;
    }

    public void setWenshuleixing(String wenshuleixing) {
        this.wenshuleixing = wenshuleixing;
    }
}
