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
    private LocalDateTime writeDay;

    public DocResponseDto(Doc doc) {
        this.id = doc.getId();
        this.userName = doc.getUserName();
        this.title = doc.getTitle();
        this.content = doc.getContent();
        this.writeDay = doc.getWriteDay();
    }

    public DocResponseDto(Long id, String title, LocalDateTime writeDay) {
        this.id = id;
        this.title = title;
        this.writeDay = writeDay;
    }

    public DocResponseDto(Long id, String title, String userName, String content, LocalDateTime writeDay) {
        this.id = id;
        this.title = title;
        this.userName = userName;
        this.content = content;
        this.writeDay = writeDay;
    }
}
