package com.epam.wallpaper.service.Imp;


import com.epam.wallpaper.Util.FileReader;
import com.epam.wallpaper.model.Room;
import com.epam.wallpaper.model.WallpaperResponse;
import com.epam.wallpaper.service.WallpaperOrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class WallpaperOrderServiceTest {

    @Mock
    private FileReader fileReader;
    private WallpaperOrderService wallpaperOrderService;


    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllAreas_returnList() {

        Mockito.when(fileReader.getResult())
                .thenReturn(List.of(new Room(1, 2, 3),
                        new Room(1, 1, 5)));
        wallpaperOrderService = new WallpaperOrderServiceImp(fileReader);
        List<WallpaperResponse> result = wallpaperOrderService.getAllAreas();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(result.get(0).getArea(), Integer.valueOf(24));
        assertEquals(result.get(1).getArea(), Integer.valueOf(23));
    }

    @Test
    public void getDuplicates_returnList() {

        Mockito.when(fileReader.getResult())
                .thenReturn(List.of(new Room(1, 2, 3),
                        new Room(4, 4, 4),
                        new Room(4, 4, 4)));
        wallpaperOrderService = new WallpaperOrderServiceImp(fileReader);
        List<WallpaperResponse> result = wallpaperOrderService.getDuplicates();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(result.get(0).getDimension(), "4x4x4");
        assertEquals(result.get(0).getCount(), Integer.valueOf(2));
    }

    @Test
    public void getCubes_returnList() {

        Mockito.when(fileReader.getResult())
                .thenReturn(List.of(new Room(1, 1, 1),
                        new Room(3, 4, 5),
                        new Room(4, 4, 4)));
        wallpaperOrderService = new WallpaperOrderServiceImp(fileReader);
        List<WallpaperResponse> result = wallpaperOrderService.getCubicShapes();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals( "4x4x4",result.get(0).getDimension());
        assertEquals( "1x1x1", result.get(1).getDimension());
    }
}