package com.epam.wallpaper.service.Imp;

import com.epam.wallpaper.Util.FileReader;
import com.epam.wallpaper.model.WallpaperResponse;
import com.epam.wallpaper.model.Room;
import com.epam.wallpaper.service.WallpaperOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WallpaperOrderServiceImp implements WallpaperOrderService {

    private final List<Room> roomsList;

    public WallpaperOrderServiceImp(FileReader fileReader) {
        roomsList = fileReader.getResult();
    }

    public List<WallpaperResponse> getAllAreas() {
        return this.roomsList.stream()
                .map(record -> {
                    int area = record.calculateArea();

                    int totalWallpaperRequired = area + record.calculateSmallest();

                    return new WallpaperResponse(record.toString(), totalWallpaperRequired, null);
                }).collect(Collectors.toList());
    }

    public List<WallpaperResponse> getCubicShapes() {
        return this.roomsList.stream()
                // filter those values which has equal sides
                .filter(r -> r.getHeight() == r.getLength() &&
                        r.getLength() == r.getWidth())
                .sorted(Comparator.comparing(Room::getLength).reversed())
                .map(r -> new WallpaperResponse(r.toString(),
                        r.calculateArea(), null))
                .collect(Collectors.toList());
    }

    public List<WallpaperResponse> getDuplicates() {
        return this.roomsList
                .stream()
                // convert to map with key as Room and value as count of their occurrence
                .collect(Collectors.toMap(k -> k, v -> 1, Integer::sum))
                .entrySet()
                .stream()
                // filter values which are appeared more than once.
                .filter(k -> k.getValue() > 1)
                .map(k -> new WallpaperResponse(k.getKey().toString(),
                        k.getKey().calculateArea(), k.getValue()))
                .collect(Collectors.toList());
    }

}
