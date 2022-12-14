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

import com.sanedge.implservice.models.Comment;
import com.sanedge.implservice.payload.response.CommentResponseDto;

import com.sanedge.implservice.service.CommentService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getAllCommentsByTutorialId(
            @PathVariable(value = "tutorialId") Long tutorialId) {
        List<CommentResponseDto> comments = this.commentService.getAll(tutorialId);

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> getCommentsByTutorialId(@PathVariable(value = "id") Long id) {
        CommentResponseDto _comment = this.commentService.getById(id);

        return new ResponseEntity<>(_comment, HttpStatus.OK);
    }

    @PostMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable(value = "tutorialId") Long tutorialId,
            @RequestBody Comment commentRequest) {
        CommentResponseDto _comment = this.commentService.createComment(tutorialId, commentRequest);

        return new ResponseEntity<>(_comment, HttpStatus.CREATED);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable("id") long id,
            @RequestBody Comment commentRequest) {
        CommentResponseDto _comment = this.commentService.updateById(id, commentRequest);

        return new ResponseEntity<>(_comment, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id) {
        this.commentService.deleteComment(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<List<Comment>> deleteAllCommentsOfTutorial(
            @PathVariable(value = "tutorialId") Long tutorialId) {
        this.commentService.deleteCommentAll(tutorialId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
