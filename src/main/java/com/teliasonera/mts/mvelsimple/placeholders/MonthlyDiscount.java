package com.teliasonera.mts.mvelsimple.placeholders;

public class MonthlyDiscount {
    public double inVat;
    private double monthlyDiscount;
    public int durationInMonths;

    public double getInVat() {
        return inVat;
    }

    public void setInVat(int inVat) {
        this.inVat = inVat;
    }

    public double getMonthlyDiscount() {
        return monthlyDiscount;
    }

    public void setMonthlyDiscount(int monthlyDiscount) {
        this.monthlyDiscount = monthlyDiscount;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(int durationInMonths) {
        this.durationInMonths = durationInMonths;
    }
}
