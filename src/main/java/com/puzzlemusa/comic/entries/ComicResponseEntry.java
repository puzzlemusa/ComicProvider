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

}
