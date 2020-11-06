package com.puzzlemusa.comic.services;

import com.puzzlemusa.comic.entries.ComicResponseEntry;
import com.puzzlemusa.comic.entries.XKCDComicEntry;
import com.puzzlemusa.comic.mappers.XKCDComicEntryMapper;
import com.puzzlemusa.comic.utils.Constants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XKCDComicServiceTests {

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private XKCDComicEntryMapper xkcdComicEntryMapper;

    @Autowired
    private XKCDComicService xkcdComicService;

    @Before
    public void setUp() {
        XKCDComicEntry xkcdComicEntryMock = mock(XKCDComicEntry.class);
        int currentComicNumber = 2381;
        when(xkcdComicEntryMock.getNum()).thenReturn(currentComicNumber);
        ResponseEntity<XKCDComicEntry> xkcdComicEntryResponseEntity = new ResponseEntity<>(xkcdComicEntryMock, HttpStatus.OK);

        for (int i = 0; i < 9; i++) {
            when(restTemplate.getForEntity(Constants.XKCDBaseComicURL + --currentComicNumber + Constants.XKCDComicURLSuffix, XKCDComicEntry.class)).thenReturn(xkcdComicEntryResponseEntity);
        }
        when(restTemplate.getForEntity(Constants.XKCDCurrentComicURL, XKCDComicEntry.class)).thenReturn(xkcdComicEntryResponseEntity);

        when(xkcdComicEntryMapper.mapXKCDEntryToResponseEntry(anyList())).thenReturn(new ArrayList<>() {{
            add(new ComicResponseEntry("comic 1", new Date(), "a", "a"));
            add(new ComicResponseEntry("comic 1", new Date(), "a", "a"));
            add(new ComicResponseEntry("comic 1", new Date(), "a", "a"));
            add(new ComicResponseEntry("comic 1", new Date(), "a", "a"));
            add(new ComicResponseEntry("comic 1", new Date(), "a", "a"));
            add(new ComicResponseEntry("comic 1", new Date(), "a", "a"));
            add(new ComicResponseEntry("comic 1", new Date(), "a", "a"));
            add(new ComicResponseEntry("comic 1", new Date(), "a", "a"));
            add(new ComicResponseEntry("comic 1", new Date(), "a", "a"));
            add(new ComicResponseEntry("comic 1", new Date(), "a", "a"));
        }});
    }

    @Test
    public void Not_empty_When_get_Comics() {
        assertThat(!xkcdComicService.getComics().isEmpty());
    }

    @Test
    public void Current_Number_of_Comic_When_getComic() {
        assertThat(xkcdComicService.getComics().size()).isEqualTo(10);
    }
}
