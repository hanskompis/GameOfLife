/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gol;

import java.util.LinkedList;

/**
 *
 * @author hkeijone
 */
public class Cell {
    private boolean active;
        private int x;
        private int y;

        public Cell(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

     
}
