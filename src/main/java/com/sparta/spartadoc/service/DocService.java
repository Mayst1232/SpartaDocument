package com.sparta.spartadoc.service;

import com.sparta.spartadoc.dto.DocRequestDto;
import com.sparta.spartadoc.dto.DocResponseDto;
import com.sparta.spartadoc.entity.Doc;
import com.sparta.spartadoc.repository.DocRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocService {
    private final DocRepository docRepository;

    public DocService(DocRepository docRepository) {
        this.docRepository = docRepository;
    }

    public DocResponseDto createDoc(DocRequestDto requestDto) {
        Doc doc = new Doc(requestDto);
        docRepository.save(doc);
        DocResponseDto docResponseDto = new DocResponseDto(doc);
        return docResponseDto;
    }

    public List<DocResponseDto> getTitleList() {
        return docRepository.findAllByOrderByModifiedAtDesc().stream().map(DocResponseDto::new).toList();
    }

    public List<DocResponseDto> getDocument(Long id) {
        return docRepository.findById(id).stream().map(DocResponseDto::new).toList();
    }

    @Transactional
    public void getUpdateDoc(Long id, DocRequestDto requestDto) {
        Doc doc = findDoc(id);

        if(doc.getPassword().equals(requestDto.getPassword())){
            doc.update(requestDto);
        }
        else {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
    }

    public void getDeleteDoc(Long id, DocRequestDto requestDto) {
        Doc doc = findDoc(id);

        if(doc.getPassword().equals(requestDto.getPassword())){
            docRepository.delete(doc);
        }
        else {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
    }

    public Doc findDoc(Long id){
        return docRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다."));
    }
}
