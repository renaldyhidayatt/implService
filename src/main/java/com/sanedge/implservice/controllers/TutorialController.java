package com.sanedge.implservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.implservice.models.Tutorial;
import com.sanedge.implservice.payload.response.TutorialResponseDto;
import com.sanedge.implservice.service.TutorialService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    TutorialService tutorialService;

    @GetMapping("/tutorials")
    public ResponseEntity<List<TutorialResponseDto>> getAllTutorials() {
        List<TutorialResponseDto> tutorial = this.tutorialService.getAll();

        return new ResponseEntity<>(tutorial, HttpStatus.OK);
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<TutorialResponseDto> getTutorialById(@PathVariable("id") long id) {
        TutorialResponseDto _tutorial = this.tutorialService.getById(id);

        return new ResponseEntity<>(_tutorial, HttpStatus.OK);
    }

    @PostMapping("/tutorials")
    public ResponseEntity<TutorialResponseDto> createTutorial(@RequestBody Tutorial tutorial) {
        TutorialResponseDto _tutorial = this.tutorialService.createTutorial(tutorial);

        return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<TutorialResponseDto> updateTutorial(@PathVariable("id") long id,
            @RequestBody Tutorial tutorial) {
        TutorialResponseDto _tutorial = this.tutorialService.updateById(id, tutorial);

        return new ResponseEntity<>(_tutorial, HttpStatus.OK);
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        this.tutorialService.deleteTutorial(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        this.tutorialService.deleteTutorialAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<TutorialResponseDto>> findByPublished() {
        List<TutorialResponseDto> tutorials = this.tutorialService.findByPublished();

        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }
}