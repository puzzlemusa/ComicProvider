package com.puzzlemusa.comic.services;

import com.puzzlemusa.comic.entries.ComicResponseEntry;
import com.puzzlemusa.comic.entries.XKCDComicEntry;
import com.puzzlemusa.comic.mappers.XKCDComicEntryMapper;
import com.puzzlemusa.comic.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class XKCDComicService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    XKCDComicEntryMapper xkcdComicEntryMapper;

    public List<ComicResponseEntry> getComics() {
        List<XKCDComicEntry> xkcdComics = new ArrayList<>();

        XKCDComicEntry currentXKCDComicEntry = getCurrentComic();
        if (currentXKCDComicEntry != null) {
            xkcdComics.add(currentXKCDComicEntry);

            int currentComicNumber = currentXKCDComicEntry.getNum();
            for (int i = 0; i < 9; i++) {
                ResponseEntity<XKCDComicEntry> entity = restTemplate.getForEntity(Constants.XKCDBaseComicURL + --currentComicNumber + Constants.XKCDComicURLSuffix, XKCDComicEntry.class);
                if (entity.getStatusCode() == HttpStatus.OK)
                    xkcdComics.add(entity.getBody());
            }
        }
        List<ComicResponseEntry> XKCDComicResponseEntries = xkcdComicEntryMapper.mapXKCDEntryToResponseEntry(xkcdComics);

        return XKCDComicResponseEntries;
    }

    private XKCDComicEntry getCurrentComic() {
        ResponseEntity<XKCDComicEntry> entity = restTemplate.getForEntity(Constants.XKCDCurrentComicURL, XKCDComicEntry.class);
        if (entity.getStatusCode() == HttpStatus.OK)
            return entity.getBody();
        else return null;
    }
}
