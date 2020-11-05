package com.puzzlemusa.comic.entries;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class ComicResponseEntry {
    private String title;
    private Date publishingDate;
    private String pictureURL;
    private String webURL;

    public ComicResponseEntry(String title, Date publishingDate, String pictureURL, String webURL) {
        this.title = title;
        this.publishingDate = publishingDate;
        this.pictureURL = pictureURL;
        this.webURL = webURL;
    }
}
