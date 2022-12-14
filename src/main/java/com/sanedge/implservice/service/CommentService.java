package com.sanedge.implservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sanedge.implservice.exception.ResourceNotFoundException;
import com.sanedge.implservice.models.Comment;
import com.sanedge.implservice.payload.response.CommentResponseDto;
import com.sanedge.implservice.repository.CommentRepository;
import com.sanedge.implservice.repository.TutorialRepository;
import com.sanedge.implservice.utils.ModelToDto;

@Component
public class CommentService {
    private final CommentRepository commentRepository;
    private final TutorialRepository tutorialRepository;
    private final ModelToDto modelToDto;

    @Autowired
    public CommentService(CommentRepository commentRepository, TutorialRepository tutorialRepository,
            ModelToDto modelToDto) {
        this.commentRepository = commentRepository;
        this.tutorialRepository = tutorialRepository;
        this.modelToDto = modelToDto;

    }

    public List<CommentResponseDto> getAll(Long tutorialId) {
        if (!this.tutorialRepository.existsById(tutorialId)) {
            throw new ResourceNotFoundException("Not found tutorial with id = " + tutorialId);
        }

        List<Comment> _comments = this.commentRepository.findByTutorialId(tutorialId);
        return this.modelToDto.commentToDtoComments(_comments);
    }

    public CommentResponseDto getById(long id) {
        Comment _comments = this.commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No found tutorial"));

        return this.modelToDto.commentToDtoComment(_comments);
    }

    public CommentResponseDto createComment(Long tutorialId, Comment _comment) {
        Comment comment = this.tutorialRepository.findById(tutorialId).map(tutorial -> {
            _comment.setTutorial(tutorial);
            return this.commentRepository.save(_comment);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

        return this.modelToDto.commentToDtoComment(comment);
    }

    public CommentResponseDto updateById(long id, Comment comment) {
        Comment _comment = this.commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CommentId " + id + "not found"));
        _comment.setContent(comment.getContent());

        _comment = this.commentRepository.save(comment);

        return this.modelToDto.commentToDtoComment(_comment);

    }

    public boolean deleteComment(long id) {
        this.commentRepository.deleteById(id);

        return true;

    }

    public boolean deleteCommentAll(Long tutorialId) {
        if (!this.tutorialRepository.existsById(tutorialId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
        }

        this.commentRepository.deleteByTutorialId(tutorialId);

        return true;
    }
}
