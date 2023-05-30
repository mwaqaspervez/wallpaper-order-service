package com.epam.wallpaper.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Room {

    private int length;
    private int width;
    private int height;


    public int calculateSmallest() {
        // if the length of one side is smaller than width then return
        // smaller value from length and height
        int side1 = length * width;
        int side2 = width * height;
        int side3 = height * length;
        if (side1 < side2) {
            return Math.min(side1, side3);
        }
        return Math.min(side2, side3);
    }

    @Override
    public String toString() {
        return "%dx%dx%d".formatted(this.length, this.width, this.height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return length == room.length && width == room.width && height == room.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width, height);
    }

}
