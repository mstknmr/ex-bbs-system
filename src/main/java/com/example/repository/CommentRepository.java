package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * コメント投稿のリポジトリ.
 * 
 * @author knmrmst
 *
 */
@Repository
public class CommentRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * CommentのRowMapper.
	 */
	private RowMapper<Comment> COMMENT_ROW_MAPPER=(rs,i)->{
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setArticleId(rs.getInt("article_id"));
		return comment;
	};
	
	/**
	 * commentを投稿者IDで検索する.
	 * 
	 * @param articleId 投稿者ID
	 * @return コメント情報のリスト
	 */
	public List<Comment> findByArticleId(Integer articleId){
		String findByArticleIdSql = "SELECT id,name,content,article_id FROM comments WHERE article_id = :id order BY id desc;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id",articleId);
		List<Comment> commentList = template.query(findByArticleIdSql, param,COMMENT_ROW_MAPPER);
		return commentList;
	}
	
	/**
	 * コメント情報のデータベース登録.
	 * 
	 * @param comment コメント情報
	 */
	public void insertComment(Comment comment) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		String insertCommentSql = "INSERT INTO comments(name,content,article_id)VALUES(:name,:content,:articleId);";
		
		template.update(insertCommentSql, param);
	}
	
	/**
	 * 記事IDに一致するコメントの削除.
	 * 
	 * @param articleId
	 */
	public void deleteByArticleId(Integer articleId) {
		System.out.println("deleteByArticleIdが呼び出されました");
		String deleteByArticleIdSql="DELETE FROM comments WHERE article_id=:articleId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		template.update(deleteByArticleIdSql, param);
		
	}
}
