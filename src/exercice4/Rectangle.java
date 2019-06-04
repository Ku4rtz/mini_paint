/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice4;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author JEROME
 */
public class Rectangle extends Shape implements Serializable {
    
    //Properties
    private boolean isFilled;
    private int width;
    private int height;

    //Constructor
    public Rectangle(boolean isFilled, int width, int height, int x, int y, Color color) {
        super(x, y, color);
        this.isFilled = isFilled;
        this.width = width;
        this.height = height;
    }

    //Getters and setters
    public boolean isIsFilled() {
        return isFilled;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setIsFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }  
}
