package com.sparta.spartadoc.repository;

import com.sparta.spartadoc.dto.DocRequestDto;
import com.sparta.spartadoc.dto.DocResponseDto;
import com.sparta.spartadoc.entity.Doc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DocRepository {

    private final JdbcTemplate jdbcTemplate;

    public DocRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void save(Doc doc) {
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체
        String sql = "INSERT INTO doc (title, username, content, password, writeDay) VALUES (?, ?, ?, ?, DEFAULT)";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, doc.getTitle());
                    preparedStatement.setString(2, doc.getUserName());
                    preparedStatement.setString(3, doc.getContent());
                    preparedStatement.setString(4, doc.getPassword());
                    return preparedStatement;
                },
                keyHolder);
        Long id = keyHolder.getKey().longValue();
        doc.setId(id);
    }

    public List<DocResponseDto> findAllTitle() {
        // DB 조회
        String sql = "SELECT id, title, writeDay FROM doc ORDER BY writeDay DESC";

        return jdbcTemplate.query(sql, new RowMapper<DocResponseDto>() {
            @Override
            public DocResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                LocalDateTime writeDay = rs.getTimestamp("writeDay").toLocalDateTime();
                return new DocResponseDto(id, title, writeDay);
            }
        });
    }

    public List<DocResponseDto> oneDocument(Long id) {
        String sql = "SELECT id, title, username, content, writeDay FROM doc WHERE id = ?";
        return jdbcTemplate.query(sql, new RowMapper<DocResponseDto>(){
            @Override
            public DocResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String userName = rs.getString("username");
                String content = rs.getString("content");
                LocalDateTime writeDay = rs.getTimestamp("writeDay").toLocalDateTime();
                return new DocResponseDto(id, title, userName, content, writeDay);
            }
        }, id);
    }

    public void updateDoc(Long id, String password, DocRequestDto requestDto) {
        String sql = "UPDATE doc SET title = ?, username = ?, content = ?, writeDay = default WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getTitle(), requestDto.getUserName(), requestDto.getContent(), id);
    }

    public void deleteDoc(Long id, String password) {
        String sql = "Delete FROM doc WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }

    public Doc findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM doc WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Doc doc = new Doc();
                doc.setTitle(resultSet.getString("title"));
                doc.setUserName(resultSet.getString("username"));
                doc.setContent(resultSet.getString("content"));
                doc.setWriteDay(resultSet.getTimestamp("writeDay").toLocalDateTime());
                doc.setPassword(resultSet.getString("password"));
                return doc;
            } else {
                return null;
            }
        }, id);
    }
}
