package com.sparta.spartadoc.dto;

import com.sparta.spartadoc.entity.Doc;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DocResponseDto {
    private Long id;
    private String title;
    private String userName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public DocResponseDto(Doc doc) {
        this.id = doc.getId();
        this.userName = doc.getUserName();
        this.title = doc.getTitle();
        this.content = doc.getContent();
        this.createdAt = doc.getCreatedAt();
        this.modifiedAt = doc.getModifiedAt();
    }
}
