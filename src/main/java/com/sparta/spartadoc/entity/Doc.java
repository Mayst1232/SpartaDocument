package com.sparta.spartadoc.entity;

import com.sparta.spartadoc.dto.DocRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Doc {
    private Long id;
    private String title;
    private String userName;
    private String content;
    private LocalDateTime writeDay;
    private String password;

    public Doc(DocRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.userName = requestDto.getUserName();
        this.content = requestDto.getContent();
        this.writeDay = requestDto.getWriteDay();
        this.password = requestDto.getPassword();
    }

    public void update(DocRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.userName = requestDto.getUserName();
        this.content = requestDto.getContent();
        this.writeDay = requestDto.getWriteDay();
    }
}
