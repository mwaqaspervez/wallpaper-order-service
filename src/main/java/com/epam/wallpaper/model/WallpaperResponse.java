package com.epam.wallpaper.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WallpaperResponse {

    private String dimension;
    private Integer area;
    private Integer count;
}
