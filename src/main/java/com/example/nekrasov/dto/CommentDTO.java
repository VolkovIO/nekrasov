package com.example.nekrasov.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDTO {
    private Long id;
    private String comment;
//    private List<>

}
