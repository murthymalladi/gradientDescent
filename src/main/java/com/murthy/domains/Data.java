package com.murthy.domains;

import java.io.Serializable;

/**
 * @author dmalladi
 */
public class Data implements Serializable {

    public double x,y;
    public Data() {};

    public Data(double x ,double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "|" + y + ")";
    }
}
