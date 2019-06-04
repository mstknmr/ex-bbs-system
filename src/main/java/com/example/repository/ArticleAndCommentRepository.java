package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.ArticleAndComment;

/**
 * 投稿とコメントのリポジトリクラス.
 * 
 * @author knmrmst
 *
 */
@Repository
public class ArticleAndCommentRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 投稿とコメントのRowMapper
	 */
	final static private RowMapper<ArticleAndComment> ARTICLE_AND_COMMENT_ROW_MAPPER=(rs,i)->{
		ArticleAndComment articleAndComment = new ArticleAndComment();
		articleAndComment.setId(rs.getInt("article_id"));
		articleAndComment.setName(rs.getString("article_name"));
		articleAndComment.setContent(rs.getString("article_Content"));
		articleAndComment.setCommentId(rs.getInt("comment_id"));
		articleAndComment.setCommentName(rs.getString("comment_name"));
		articleAndComment.setCommentContent(rs.getString("comment_conetnt"));
		articleAndComment.setArticleId(rs.getInt("article_id"));
		return articleAndComment;
	};
	
	/**
	 * 投稿の全件検索.
	 * 
	 * @return 投稿情報のリスト
	 */
	public List<ArticleAndComment> findAll(){
		String findAllSql = "SELECT\n" + 
				"a.id as article_id,a.name as article_name,a.content as article_content,\n" + 
				"c.id as comment_id,c.name as comment_name,c.content as comment_conetnt,c.article_id\n" + 
				"FROM articles a left outer join comments c on c.article_id=a.id ORDER BY a.id DESC , c.id;";
		List<ArticleAndComment> articleList = template.query(findAllSql, ARTICLE_AND_COMMENT_ROW_MAPPER);
		return articleList;
		
	}
}
