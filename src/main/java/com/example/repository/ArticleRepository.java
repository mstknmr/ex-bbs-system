package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;

/**
 * 記事投稿のリポジトリ.
 * @author knmrmst
 *
 */
@Repository
public class ArticleRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * ArticleドメインのRowMapper
	 * 
	 */
	private final static RowMapper<Article> ARTICLE_ROW_MAPPER=(rs,i)->{
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		return article;
	};
	
	/**
	 * 投稿の全件検索.
	 * 
	 * @return 投稿情報のリスト
	 */
	public List<Article> findAll(){
		String findAllSql = "SELECT id,name,content FROM articles ORDER BY id DESC;";
		List<Article> articleList = template.query(findAllSql, ARTICLE_ROW_MAPPER);
		return articleList;
		
	}
	
	/**
	 * 投稿情報のデータベース登録.
	 * 
	 * @param article　投稿情報
	 */
	public void insertArticle(Article article) {
		String insertArticleSql = "INSERT INTO articles(name,content)VALUES(:name,:content);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", article.getName()).addValue("content",article.getContent());
		template.update(insertArticleSql, param);
	}
	
	public void deleteById(Integer id) {
		System.out.println("delteByIdが呼び出されました");
		String deleteByIdSql="DELETE FROM articles WHERE id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(deleteByIdSql, param);
	}

}
