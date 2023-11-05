package com.sparta.spartadoc.service;

import com.sparta.spartadoc.dto.DocRequestDto;
import com.sparta.spartadoc.dto.DocResponseDto;
import com.sparta.spartadoc.entity.Doc;
import com.sparta.spartadoc.repository.DocRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocService {
    private final DocRepository docRepository;

    public DocService(JdbcTemplate jdbcTemplate) {
        this. docRepository = new DocRepository(jdbcTemplate);
    }

    public DocResponseDto createDoc(DocRequestDto requestDto) {
        Doc doc = new Doc(requestDto);
        docRepository.save(doc);
        DocResponseDto docResponseDto = new DocResponseDto(doc);
        return docResponseDto;
    }

    public List<DocResponseDto> getTitleList() {
        return docRepository.findAllTitle();
    }

    public List<DocResponseDto> getDocument(Long id) {
        return docRepository.oneDocument(id);
    }

    public void getUpdateDoc(Long id, String password, DocRequestDto requestDto) {
        Doc doc = docRepository.findById(id);

        if(doc != null){
            if(doc.getPassword().equals(password)){
                docRepository.updateDoc(id, requestDto);

            } else {
                throw new IllegalArgumentException("That is wrong password");
            }
        } else {
            throw new IllegalArgumentException("That Doc is not exist");
        }
    }

    public void getDeleteDoc(Long id, String password) {
        Doc doc = docRepository.findById(id);

        if (doc != null) {
            if (doc.getPassword().equals(password)) {
                docRepository.deleteDoc(id, password);
                System.out.println("Delecte Success");
            } else {
                throw new IllegalArgumentException("That is wrong password");
            }
        } else {
            throw new IllegalArgumentException("That Doc is not exist");
        }
    }
}
