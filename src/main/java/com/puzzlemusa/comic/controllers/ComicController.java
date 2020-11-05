package com.puzzlemusa.comic.controllers;

import com.puzzlemusa.comic.entries.ComicResponseEntry;
import com.puzzlemusa.comic.services.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ComicController {

    @Autowired
    private ComicService comicService;

    @GetMapping
    public ResponseEntity<List<ComicResponseEntry>> getComics() {
        List<ComicResponseEntry> comicResponseEntries = new ArrayList<>();

        List<ComicResponseEntry> comics = comicService.getComics();
        return new ResponseEntity<>(comics, HttpStatus.OK);
    }
}
