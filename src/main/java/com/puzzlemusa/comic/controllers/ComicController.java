package com.puzzlemusa.comic.controllers;

import com.puzzlemusa.comic.entries.ComicResponseEntry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ComicController {

    public ResponseEntity<List<ComicResponseEntry>> getComics(){
        List<ComicResponseEntry> comicResponseEntries = new ArrayList<>();
        return new ResponseEntity<>(comicResponseEntries, HttpStatus.OK);
    }
}
