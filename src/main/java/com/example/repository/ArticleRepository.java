package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

/**
 * 記事投稿のリポジトリ.
 * 
 * @author knmrmst
 *
 */
@Repository
public class ArticleRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	// private static HashMap<Integer, Article> articleMap = new HashMap<>();
	// private static List<Article> articleList = new ArrayList<>();

	/**
	 * 投稿とコメントの情報のリザルトセット
	 */
	private final static ResultSetExtractor<List<Article>> ARTICLE_AND_COMMENT_RESULT_SET = rs -> {
		List<Article> articleList = new ArrayList<>();
		ArrayList<Comment> commentList = null;
		int lastId = 0;
		while (rs.next()) {
			if (lastId != rs.getInt("article_id")) {
				Article article = new Article();
				article.setId(rs.getInt("article_id"));
				article.setName(rs.getString("article_name"));
				article.setContent(rs.getString("article_content"));
				commentList = new ArrayList<Comment>();
				article.setCommentList(commentList);
				articleList.add(0, article);
			}
			if (rs.getInt("comment_id") != 0) {
				Comment comment = new Comment();
				comment.setArticleId(rs.getInt("article_id"));
				comment.setContent(rs.getString("comment_content"));
				comment.setId(rs.getInt("comment_id"));
				comment.setName(rs.getString("comment_name"));
				commentList.add(0,comment);
//				articleList.get(0).getCommentList().add(0, comment);
			}
			lastId = rs.getInt("article_id");

		}
		return articleList;
	};

//	/**
//	 * ArticleドメインのRowMapper
//	 * 
//	 */
//	private final static RowMapper<Article> ARTICLE_AND_COMMENT_ROW_MAPPER = (rs, i) -> {
//		if (articleList.size() == 0 || articleList.get(0).getId() != rs.getInt("article_id")) {
//			Article article = new Article();
//			article.setId(rs.getInt("article_id"));
//			article.setName(rs.getString("article_name"));
//			article.setContent(rs.getString("article_content"));
//			articleList.add(0, article);
//		}
//		Article article = articleList.get(0);
//		List<Comment> commentList;
//		if (article.getCommentList() == null) {
//			commentList = new ArrayList<>();
//		} else {
//			commentList = article.getCommentList();
//		}
//
//		Comment comment = new Comment();
//		comment.setArticleId(rs.getInt("article_id"));
//		comment.setContent(rs.getString("comment_content"));
//		comment.setId(rs.getInt("comment_id"));
//		comment.setName(rs.getString("comment_name"));
//		commentList.add(0, comment);
//		if (comment.getId() != 0) {
//			article.setCommentList(commentList);
//		}
//		articleList.set(0, article);
//		return article;
//	};

//	/**
//	 * ArticleドメインのRowMapper
//	 * 
//	 */
//	private final static RowMapper<Article> ARTICLE_ROW_MAPPER=(rs,i)->{
//		Article article = new Article();
//		article.setId(rs.getInt("id"));
//		article.setName(rs.getString("name"));
//		article.setContent(rs.getString("ontent"));
//		return  article;
//	};

	/**
	 * 投稿の全件検索.
	 * 
	 * @return 投稿情報のリスト
	 */
	public List<Article> findAll() {

		String findAllSql = "SELECT\n" + "a.id as article_id,a.name as article_name,a.content as article_content,\n"
				+ "c.id as comment_id,c.name as comment_name,c.content as comment_content,c.article_id\n"
				+ "FROM articles a\n" + "left outer join comments c\n" + "on c.article_id=a.id\n"
				+ "ORDER BY a.id  , c.id;";
		List<Article> articleList = template.query(findAllSql, ARTICLE_AND_COMMENT_RESULT_SET);
//		List<Article> articleList = new ArrayList<>();
//		for (Article article : articleMap.values()) {
//			articleList.add(0, article);
//		}
		return articleList;

	}

	/**
	 * 投稿情報のデータベース登録.
	 * 
	 * @param article 投稿情報
	 */
	public void insertArticle(Article article) {
		String insertArticleSql = "INSERT INTO articles(name,content)VALUES(:name,:content);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", article.getName()).addValue("content",
				article.getContent());
		template.update(insertArticleSql, param);
	}

	/**
	 * 投稿情報の削除.
	 * 
	 * @param id 投稿ID
	 */
	public void deleteById(Integer id) {
		System.out.println("delteByIdが呼び出されました");
		String deleteByIdSql = "DELETE FROM articles WHERE id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(deleteByIdSql, param);
	}

}
