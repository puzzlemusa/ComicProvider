package com.puzzlemusa.comic.mappers;

import com.puzzlemusa.comic.entries.ComicResponseEntry;
import com.rometools.rome.feed.synd.SyndEntry;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SyndEntryToResponseEntryMapper {
    public List<ComicResponseEntry> map(List<SyndEntry> syndEntries) {
        List<ComicResponseEntry> comicResponseEntries = new ArrayList<>();
        for (SyndEntry syndEntry : syndEntries) {
            ComicResponseEntry comicResponseEntry = new ComicResponseEntry(syndEntry.getTitle(), syndEntry.getPublishedDate(), syndEntry.getUri(), syndEntry.getLink());
            comicResponseEntries.add(comicResponseEntry);
        }

        return comicResponseEntries;
    }
}
