package com.linn.calculator;

import java.util.Objects;

public class Util {
    protected boolean isConvertible(String value){
        try {
            double d = Double.parseDouble(value);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
