package com.puzzlemusa.comic.services;

import com.puzzlemusa.comic.entries.ComicResponseEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ComicService {

    @Autowired
    private XKCDComicService xkcdComicService;

    @Autowired
    private PDLComicFeedService pdlComicFeedService;

    public List<ComicResponseEntry> getComics(){
        List<ComicResponseEntry> xkcdComicEntries = xkcdComicService.getComics();
        List<ComicResponseEntry> pdlComicEntries = pdlComicFeedService.getComics();


        return Collections.emptyList();
    }
}
