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
public class Ligne extends Shape implements Serializable{
    private int xEnd;
    private int yEnd;

    public Ligne(int xEnd, int yEnd, int x, int y, Color color) {
        super(x, y, color);
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }

    public int getxEnd() {
        return xEnd;
    }

    public int getyEnd() {
        return yEnd;
    }

    public void setxEnd(int xEnd) {
        this.xEnd = xEnd;
    }

    public void setyEnd(int yEnd) {
        this.yEnd = yEnd;
    }
}
