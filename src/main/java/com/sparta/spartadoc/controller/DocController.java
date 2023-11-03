package com.sparta.spartadoc.controller;

import com.sparta.spartadoc.dto.DocRequestDto;
import com.sparta.spartadoc.dto.DocResponseDto;
import com.sparta.spartadoc.service.DocService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DocController {
    private final DocService docService;

    public DocController(JdbcTemplate jdbcTemplate) {
        this.docService = new DocService(jdbcTemplate);
    }

    @PostMapping("/docs")
    public DocResponseDto createDoc(@RequestBody DocRequestDto requestDto){
        return docService.createDoc(requestDto);
    }

    @GetMapping("/docs")
    public List<DocResponseDto> getTitleList(){
        return docService.getTitleList();
    }

    @GetMapping("/docs/{id}")
    public List<DocResponseDto> getDocument(@PathVariable Long id){
        return docService.getDocument(id);
    }

    @PutMapping("/docs/{id}/{password}")
    public String updateDoc(@PathVariable Long id, @PathVariable String password, @RequestBody DocRequestDto requestDto){
        docService.getUpdateDoc(id,password,requestDto);
        return "업데이트가 성공했습니다.";
    }

    @DeleteMapping("/docs/{id}/{password}")
    public String deleteDoc(@PathVariable Long id, @PathVariable String password) {
        docService.getDeleteDoc(id, password);
        return "선택한 게시물이 삭제됐습니다.";
    }
}