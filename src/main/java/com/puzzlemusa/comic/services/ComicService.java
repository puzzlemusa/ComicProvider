package com.puzzlemusa.comic.services;

import com.puzzlemusa.comic.entries.ComicResponseEntry;
import com.puzzlemusa.comic.entries.XKCDComicEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ComicService {

    @Autowired
    private XKCDComicService xkcdComicService;

    public List<ComicResponseEntry> getComics(){
        List<ComicResponseEntry> xkcdComicEntries = xkcdComicService.getComics();
        return Collections.emptyList();
    }
}
