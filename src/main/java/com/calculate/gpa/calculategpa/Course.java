package com.calculate.gpa.calculategpa;

public class Course {

    private String name;
    private int creditHours;
    private double grade;
    private double score;

    public Course(String name, int creditHours, double grade) {
        this.name = name;
        this.creditHours = creditHours;
        this.grade = grade;
    }

    public Course(){

    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + " credit hours: " + this.creditHours + " grade: " + this.grade;
    }

    //creaditHOurs
    //grade

}
