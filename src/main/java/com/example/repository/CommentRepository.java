package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

@Repository
public class CommentRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private RowMapper<Comment> COMMENT_ROW_MAPPER=(rs,i)->{
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setArticleId(rs.getInt("article_id"));
		return comment;
	};
	
	public List<Comment> findByArticleId(Integer id){
		String findByArticleIdSql = "SELECT id,name,content,article_id FROM comments WHERE article_id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
		List<Comment> commentList = template.query(findByArticleIdSql, param,COMMENT_ROW_MAPPER);
		return commentList;
	}
}
