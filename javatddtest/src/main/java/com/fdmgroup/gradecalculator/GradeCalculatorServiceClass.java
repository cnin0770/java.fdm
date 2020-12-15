package com.fdmgroup.gradecalculator;

class GradeCalculatorServiceClass implements GradeCalculatorService {
    @Override
    public String getClassification(double mark) {
        String result = "";
        if (mark >= 90) result = "distinction";
        else if (mark >= 80) result = "merit";
        else if (mark >= 75) result = "pass";
        else result = "fail";
        return result;
    }
}
