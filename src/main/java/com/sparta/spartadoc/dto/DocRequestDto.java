package com.sparta.spartadoc.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DocRequestDto {
    private String title;
    private String userName;
    private String content;
    private LocalDateTime writeDay;
    private String password;
}
