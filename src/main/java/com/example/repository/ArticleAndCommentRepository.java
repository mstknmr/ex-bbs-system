package com.example.repository;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

@Repository
public class ArticleAndCommentRepository {
	
	final static private RowMapper<Article> ARTICLE_AND_COMMENT_ROW_MAPPER=(rs,i)->{
		Article article = new Article();
		article.setId(rs.getInt("article_id"));
		article.setName(rs.getString("article_name"));
		article.setContent(rs.getString("article_content"));
		Comment comment = new Comment();
		comment.setId(rs.getInt("comment_id"));
		comment.setName(rs.getString("comment_name"));
		comment.setContent(rs.getString("comment_content"));
		comment.setArticleId(rs.getInt("article_id"));
		List<Comment> commentList = new ArrayList<>();
		commentList.add(comment);
		
		return article;
	};
}
