package com.epam.wallpaper.model;

public class CubicRoom  extends Room implements AreaCalculator{

    public CubicRoom(int length, int width, int height) {
        super(length, width, height);
    }

    @Override
    public int calculateArea() {
        // Area of the cubic room is 6 * length ^ 2
        return (int) (6 * Math.pow(this.getLength(), 2));
    }
}
