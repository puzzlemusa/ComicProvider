package com.puzzlemusa.comic.mappers;

import com.puzzlemusa.comic.entries.ComicResponseEntry;
import com.puzzlemusa.comic.entries.XKCDComicEntry;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class XKCDComicEntryMapper {

    public List<ComicResponseEntry> mapXKCDEntryToResponseEntry(List<XKCDComicEntry> xkcdComicEntries) {

        List<ComicResponseEntry> comicResponseEntries = new ArrayList<>();
        for (XKCDComicEntry xkcdComicEntry : xkcdComicEntries) {
            String title = xkcdComicEntry.getTitle();
            String pictureURL = xkcdComicEntry.getImg();
            String webURL = xkcdComicEntry.getLink();
            String dateText = xkcdComicEntry.getDay() + "-" + xkcdComicEntry.getMonth() + "-" + xkcdComicEntry.getYear();
            Date publishingDate = null;
            try {
                publishingDate = new SimpleDateFormat("dd-MM-yyyy").parse(dateText);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ComicResponseEntry comicResponseEntry = new ComicResponseEntry(title, publishingDate, pictureURL, webURL);
            comicResponseEntries.add(comicResponseEntry);
        }

        return comicResponseEntries;
    }
}
