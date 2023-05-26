package com.epam.wallpaper.service;

import com.epam.wallpaper.model.WallpaperResponse;

import java.util.List;

public interface WallpaperOrderService {

     List<WallpaperResponse> getAllAreas();
     List<WallpaperResponse> getCubicShapes();
     List<WallpaperResponse> getDuplicates();
}
