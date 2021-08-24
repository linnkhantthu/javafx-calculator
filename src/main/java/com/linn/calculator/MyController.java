package com.linn.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Objects;

public class MyController {

    private Stage stage;
    private String number_str = "";
    private String resume = "";
    private String num_1_str = "";
    private double num_1 = 0.0;
    private double num_2 = 0.0;
    private String op = "";

    @FXML
    private TextField result_field;

    @FXML
    protected void onClickClose(MouseEvent mouseEvent) {
        stage.close();
    }

    @FXML
    protected void onClickMinimize(MouseEvent mouseEvent){
        stage.setIconified(true);
    }

    protected void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    protected void onClickNumber(MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        try{
            String result = calculate(btn.getText());
            number_str += btn.getText() + result;
            result_field.setText(number_str);
        }
        catch (Exception e){
            // not needed
        }
    }

    @FXML
    protected void onClickBackspace(MouseEvent mouseEvent){
        if(!number_str.contains("=")){
           try{
               num_1_str = num_1_str.substring(0, num_1_str.length() - 1);
               number_str = number_str.substring(0, number_str.length() - 1);
               result_field.setText(number_str);
           }catch (Exception e){

           }
        }
        else {
            resetApp();
        }
    }

    @FXML
    protected void onClickReset(MouseEvent mouseEvent){
        resetApp();
    }

    protected void resetApp(){
        number_str = "";
        resume = "";
        num_1_str = "";
        num_1 = 0.0;
        num_2 = 0.0;
        op = "";
        result_field.setText(number_str);
    }

    protected String calculate(String input){
        Util util = new Util();
        if (util.isConvertible(input) || Objects.equals(input, ".")){
            if(!Objects.equals(resume, "")){
                num_1_str = "";
                number_str = "";
                resume = "";
            }
            num_1_str += input;
        }
        else {
            if(!Objects.equals(resume, "")){
                num_1_str = resume;
                number_str = resume;
                resume = "";
            }
            if (Objects.equals(op, "")){
                op = input;
                num_1 = Double.parseDouble(num_1_str);
                num_1_str = "";
            }
            else {
                if (Objects.equals(input, "=")){
                    num_2 = Double.parseDouble(num_1_str);
                    switch (op){
                        case "+":
                            op = "";
                            resume = String.valueOf(num_1 + num_2);
                            break;
                        case "-": op = ""; resume = String.valueOf(num_1 - num_2); break;
                        case "x": op = ""; resume = String.valueOf(num_1 * num_2); break;
                        case "/": op = ""; resume = String.valueOf(num_1 / num_2); break;
                    }
                    num_1 = 0.0; num_2 = 0.0;
                    return resume;
                }
                else {
                    num_2 = Double.parseDouble(num_1_str);
                    switch (op){
                        case "+":
                            num_1 = num_1 + num_2;
                            break;
                        case "-":
                            num_1 = num_1 - num_2;
                            break;
                        case "x": num_1 = num_1 * num_2; break;
                        case "/": num_1 = num_1 / num_2; break;
                    }
                    op = input;
                    num_1_str = "";
                    num_2 = 0.0;
                }
            }
        }
        return "";
    }
}