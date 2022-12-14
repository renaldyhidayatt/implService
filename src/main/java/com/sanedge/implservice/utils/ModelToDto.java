package com.sanedge.implservice.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sanedge.implservice.models.Comment;
import com.sanedge.implservice.models.Tutorial;
import com.sanedge.implservice.payload.response.CommentResponseDto;
import com.sanedge.implservice.payload.response.TutorialResponseDto;

@Component
public class ModelToDto {
    public List<TutorialResponseDto> tutorialToDtoTutorials(List<Tutorial> _tutorial) {
        return _tutorial.stream()
                .map(e -> new TutorialResponseDto(e.getId(), e.getTitle(), e.getDescription(),
                        e.isPublished(),
                        e.getComments().stream().map(c -> new CommentResponseDto(c.getId(), c.getContent()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public List<CommentResponseDto> commentToDtoComments(List<Comment> _comments) {
        return _comments.stream().map(c -> new CommentResponseDto(c.getId(), c.getContent()))
                .collect(Collectors.toList());
    }

    public TutorialResponseDto tutorialToDoTutorial(Tutorial _tutorial) {
        return tutorialToDtoTutorials(Arrays.asList(_tutorial)).get(0);
    }

    public CommentResponseDto commentToDtoComment(Comment _comment) {
        return commentToDtoComments(Arrays.asList(_comment)).get(0);
    }
}
