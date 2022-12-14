package com.sanedge.implservice.payload.response;

import java.io.Serializable;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TutorialResponseDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private boolean published;
    private List<CommentResponseDto> comments;

}
