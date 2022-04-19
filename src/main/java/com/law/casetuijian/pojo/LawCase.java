package com.law.casetuijian.pojo;

public class LawCase {
    private String anjianmiaoshu;
    private String diqu;
    private String shuliang;

    public LawCase(String anjianmiaoshu, String diqu, String shuliang) {
        this.anjianmiaoshu = anjianmiaoshu;
        this.diqu = diqu;
        this.shuliang = shuliang;
    }

    public String getAnjianmiaoshu() {
        return anjianmiaoshu;
    }

    public void setAnjianmiaoshu(String anjianmiaoshu) {
        this.anjianmiaoshu = anjianmiaoshu;
    }

    public String getDiqu() {
        return diqu;
    }

    public void setDiqu(String diqu) {
        this.diqu = diqu;
    }

    public String getShuliang() {
        return shuliang;
    }

    public void setShuliang(String shuliang) {
        this.shuliang = shuliang;
    }
}
