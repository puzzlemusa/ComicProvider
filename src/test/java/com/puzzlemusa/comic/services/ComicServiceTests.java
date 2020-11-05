package com.puzzlemusa.comic.services;

import com.puzzlemusa.comic.entries.ComicResponseEntry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComicServiceTests {

    @MockBean
    XKCDComicService xkcdComicService;
    @Autowired
    private ComicService comicService;
    @MockBean
    private PDLComicFeedService pdlComicFeedService;

    @Before
    public void setUp() {
        when(this.xkcdComicService.getComics()).thenReturn(new ArrayList<>() {{
            String string = "January 2, 2010";
            DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
            Date date = null;
            try {
                date = format.parse(string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            add(new ComicResponseEntry("comic 1", date, "a", "a"));
        }});

        when(this.pdlComicFeedService.getComics()).thenReturn(new ArrayList<>() {{
            String string = "January 3, 2010";
            DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
            Date date = null;
            try {
                date = format.parse(string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            add(new ComicResponseEntry("comic 2", new Date(), "a", "a"));
        }});
    }

    @Test
    public void Not_Empty_When_getComic() {
        assertThat(!this.comicService.getComics().isEmpty());
    }

    @Test
    public void Current_Number_of_Comic_When_getComic() {
        assertThat(this.comicService.getComics().size()).isEqualTo(2);
    }

    @Test
    public void Latest_Comic_at_Top_When_getComic() {
        List<ComicResponseEntry> comics = this.comicService.getComics();
        assertThat(comics.get(0).getTitle()).isEqualTo("comic 2");
        assertThat(comics.get(1).getTitle()).isEqualTo("comic 1");
    }
}
