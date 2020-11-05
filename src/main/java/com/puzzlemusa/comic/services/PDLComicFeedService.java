package com.puzzlemusa.comic.services;

import com.puzzlemusa.comic.entries.ComicResponseEntry;
import com.puzzlemusa.comic.mappers.SyndEntryToResponseEntryMapper;
import com.puzzlemusa.comic.utils.Constants;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Service
public class PDLComicFeedService {

    @Autowired
    private SyndEntryToResponseEntryMapper syndEntryToResponseEntryMapper;

    public List<ComicResponseEntry> getComics(){
        List<SyndEntry> syndEntries = new ArrayList<>();
        try {
            XmlReader xmlReader = new XmlReader(new URL(Constants.PDLComicFeedURL));
            SyndFeed syndFeed = new SyndFeedInput().build(xmlReader);
            syndEntries = syndFeed.getEntries();
        } catch (IOException | FeedException e) {
            e.printStackTrace();
        }

        List<ComicResponseEntry> responseEntries = syndEntryToResponseEntryMapper.map(syndEntries);
        return responseEntries;
    }
}
