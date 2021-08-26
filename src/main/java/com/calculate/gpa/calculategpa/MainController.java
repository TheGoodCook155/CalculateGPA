package com.calculate.gpa.calculategpa;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainController{

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button buttonAddCourse;

    @FXML
    private Button buttonCalculateGPA;

    @FXML
    private Label labelCreditHours;

    @FXML
    private Label labelGradePoints;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    @FXML
    private TextField textField3;

    private TextField tempTextField1;
    private TextField tempTextField2;
    private TextField tempTextField3;
    private Label infoLabel;
    private int[] location = new int[2];
    private int lastElement = 4;
    private int[] infoLabelCoordinates = new int[2];
    private List<Course> allCourses = new ArrayList<>();
    private boolean firstObject = true;



    public void initialize(){
        infoLabel = new Label("GPA result is: ");
        Font font = new Font(16);
        infoLabel.setFont(font);
        infoLabel.setLayoutX(315);
        infoLabel.setLayoutY(280);
        anchorPane.getChildren().add(infoLabel);
        infoLabelCoordinates[0] = (int)infoLabel.getLayoutX();
        infoLabelCoordinates[1] = (int) infoLabel.getLayoutY();
        infoLabel.setVisible(false);
    }

    @FXML
    private void addCourse() {
        /*
      <TextField layoutX="495.0" layoutY="110.0" />
      <TextField layoutX="296.0" layoutY="110.0" />
      <TextField layoutX="97.0" layoutY="110.0" />
         */
        infoLabel.setVisible(false);
        if (infoLabel.getLayoutY() < 900 && labelGradePoints.getLayoutY() < 900) {
            int oldX = 97;
            int oldY = 110;

            if (location[0] == 0) {
                oldY = 110;
                oldX = 97;
            } else {
                oldX = location[0];
                oldY = location[1];
            }
            tempTextField1 = new TextField();
            tempTextField1.setId("textField" + lastElement);
            lastElement++;
            tempTextField1.setLayoutX(oldX);
            tempTextField1.setLayoutY(oldY + 40);

            //===================================
            oldY = (int) tempTextField1.getLayoutY();
            oldX = (int) tempTextField1.getLayoutX();

            tempTextField2 = new TextField();
            tempTextField2.setId("textField" + lastElement);
            lastElement++;
            tempTextField2.setLayoutX(oldX + 200);
            tempTextField2.setLayoutY(oldY);
            //===================================

            oldY = (int) tempTextField2.getLayoutY();
            oldX = (int) tempTextField2.getLayoutX();

            tempTextField3 = new TextField();
            tempTextField3.setId("textField" + lastElement);
            lastElement++;
            tempTextField3.setLayoutX(oldX + 200);
            tempTextField3.setLayoutY(oldY);

            location[0] = (int) tempTextField1.getLayoutX();
            location[1] = (int) tempTextField1.getLayoutY();

            anchorPane.getChildren().addAll(tempTextField1, tempTextField2, tempTextField3);
            moveElements();
        }
    }




    public void moveElements() {
        if (infoLabel.getLayoutY() < 900 && labelGradePoints.getLayoutY() < 900) {
            buttonAddCourse.setLayoutX(buttonAddCourse.getLayoutX());
            buttonAddCourse.setLayoutY(buttonAddCourse.getLayoutY() + 40);

            buttonCalculateGPA.setLayoutX(buttonCalculateGPA.getLayoutX());
            buttonCalculateGPA.setLayoutY(buttonCalculateGPA.getLayoutY() + 40);


            labelCreditHours.setLayoutX(labelCreditHours.getLayoutX());
            labelCreditHours.setLayoutY(labelCreditHours.getLayoutY() + 40);

            labelGradePoints.setLayoutX(labelGradePoints.getLayoutX());
            labelGradePoints.setLayoutY(labelGradePoints.getLayoutY() + 40);
            //=======================================================
            int oldLabelX = infoLabelCoordinates[0];
            int oldLabelY = infoLabelCoordinates[1];
            anchorPane.getChildren().remove(infoLabel);
            infoLabel.setLayoutX(oldLabelX);
            infoLabel.setLayoutY(oldLabelY + 40);
            anchorPane.getChildren().add(infoLabel);
            infoLabelCoordinates[0] = (int)infoLabel.getLayoutX();
            infoLabelCoordinates[1] = (int) infoLabel.getLayoutY();
        }
    }


    public void calculateGPA(){

//        removeEmptyFields();
//        compensatePosition();

//        System.out.println("==================================");
//        System.out.println("Printing all objects");
//        allCourses.forEach(e->{
//            System.out.println("Name is " + e.getName());
//            System.out.println("Credit hours is " + e.getCreditHours());
//            System.out.println("Grade is " + e.getGrade());
//            System.out.println("Score is " + e.getScore());
//            System.out.println("==========================");
//        });
        allCourses.clear();
        createAllObjects();
        infoLabel.setText("GPA result is: ");
        labelCreditHours.setText("Total credit hours attempted: ");
        labelGradePoints.setText("Total grade points: ");
        int allCredits = 0;
        double allScore = 0;
        double totalGradePoints = 0;
        for (int i = 0; i < allCourses.size(); i++) {
            Course picked = allCourses.get(i);
            allCredits += picked.getCreditHours();
            totalGradePoints+=picked.getGrade();
            allScore += picked.getScore();
        }
        double GPA = allScore / allCredits;
        int totalCreditHours = allCredits;
//
//        System.out.println("=====================");
//        System.out.println("GPA is " + GPA);
//        System.out.println("=====================");
//        System.out.println("Calculate GPA DONE");
        infoLabel.setText(infoLabel.getText() + String.format("%.2f",GPA));
        infoLabel.setVisible(true);
        labelCreditHours.setText(labelCreditHours.getText() + totalCreditHours);
        labelGradePoints.setText(labelGradePoints.getText() + String.format("%.2f",totalGradePoints));

    }

    private void removeEmptyFields() {
        List<TextField> allTextFields = getAllTextFieldValues();
        List<TextField> emptyGroups = allTextFields.stream().filter(e-> e.getText().isEmpty()).collect(Collectors.toList());
        anchorPane.getChildren().removeAll(emptyGroups);
    }

    private void createCourses(TextField textField1, TextField textField2, TextField textField3) {
        String txt2 = textField2.getText();
        String txt3 = textField3.getText();
        int commaIndex = 0;


        if(txt2.matches("[a-zA-Z]{1,50}") || txt3.matches("[a-zA-Z]{1,50}")){
            txt2 = "0";
            txt3 = "0.0001";
        }
        if (txt2.contains(",")){
            commaIndex = txt2.indexOf(",");
            txt2 = txt2.substring(0,commaIndex);
        } else if (txt2.contains(".")){
            commaIndex = txt2.indexOf(".");
            txt2 = txt2.substring(0,commaIndex);
        }
        Course course = new Course(textField1.getText(), Integer.parseInt(txt2),Double.parseDouble(txt3));
        double grades = course.getGrade();
        int credits = course.getCreditHours();
        double score = grades * credits;
        course.setScore(score);
        allCourses.add(course);
    }

    private List<TextField> getAllTextFieldValues() {
        List<TextField> allTextFields = anchorPane.getChildren().stream().filter(e-> e instanceof TextField).map(e-> (TextField)e).collect(Collectors.toList());
//        allTextFields.forEach(e-> {
//            System.out.println("Id is " + e.getId());
//            System.out.println("Text is " + e.getText());
//        });
        return allTextFields;
    }

    private void createAllObjects()  {
        List<TextField> allTextFields = getAllTextFieldValues();
        TextField t1 = new TextField();
        TextField t2 = new TextField();
        TextField t3 = new TextField();
        for (int i = 0; i < allTextFields.size(); i = i + 3){
            if (allTextFields.get(i).getText().isEmpty() && allTextFields.get(i+1).getText().isEmpty() && allTextFields.get(i+2).getText().isEmpty()){
                t1.setText(" ");
                t2.setText("0");
                t3.setText("0.00001");
                createCourses(t1,t2,t3);
            }else if (allTextFields.get(i+1).getText().isEmpty() || allTextFields.get(i+2).getText().isEmpty()){
                t1.setText(" ");
                t2.setText("0");
                t3.setText("0.00001");
                createCourses(t1,t2,t3);
            }else{
                createCourses(allTextFields.get(i),allTextFields.get(i+1),allTextFields.get(i+2));
            }
        }
    }

}