package com.epam.wallpaper.model;

public class RectangularRoom extends Room implements AreaCalculator{

    public RectangularRoom(int length, int width, int height) {
        super(length, width, height);
    }

    @Override
    public int calculateArea() {
        // area is calculated as
        // 2 * length * width + 2 * width * height + 2 * height * length.
        return (2 * this.getLength() * this.getWidth()) +
                (2 * this.getWidth() * this.getHeight()) +
                (2 * this.getHeight() * this.getLength());
    }
}
