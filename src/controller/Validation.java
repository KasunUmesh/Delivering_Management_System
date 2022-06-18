package controller;

import com.jfoenix.controls.JFXTextField;

import java.util.regex.Pattern;

public class Validation {
    public boolean patternValidation(Pattern pattern, JFXTextField textField){

        if(pattern.matcher(textField.getText()).matches()){
            textField.setStyle("-jfx-unfocus-color: black");
            return true;
        }else{
            textField.setStyle("-jfx-unfocus-color: red");
            return false;
        }

    }
}
