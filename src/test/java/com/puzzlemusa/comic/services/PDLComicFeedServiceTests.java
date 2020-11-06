package com.puzzlemusa.comic.services;

import com.puzzlemusa.comic.entries.ComicResponseEntry;
import com.puzzlemusa.comic.mappers.SyndEntryToResponseEntryMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PDLComicFeedServiceTests {

    @MockBean
    private SyndEntryToResponseEntryMapper syndEntryToResponseEntryMapper;

    @Autowired
    private PDLComicFeedService pdlComicFeedService;

    @Before
    public void setUp() {
        when(syndEntryToResponseEntryMapper.map(anyList())).thenReturn(new ArrayList<>() {{
            add(new ComicResponseEntry("comic 1", new Date(), "a", "a"));
        }});
    }

    @Test
    public void Not_empty_When_get_Comics() {
        assertThat(!pdlComicFeedService.getComics().isEmpty());
    }

    @Test
    public void Current_Number_of_Comic_When_getComic() {
        assertThat(pdlComicFeedService.getComics().size()).isEqualTo(1);
    }
}
