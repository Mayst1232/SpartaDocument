package com.sparta.spartadoc.repository;

import com.sparta.spartadoc.entity.Doc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocRepository extends JpaRepository<Doc, Long> {

}
