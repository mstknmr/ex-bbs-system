package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

/**
 * 掲示板システムのサービスクラス.
 * 
 * @author knmrmst
 *
 */
@Service
@Transactional
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private CommentRepository commentRepository;
	
	
	/**
	 * 投稿IDによるコメントの条件検索.
	 * 
	 * @param articleId 投稿ID
	 * @return　コメント情報のリスト
	 */
	public List<Comment> findByArticleId(Integer articleId) {
		return commentRepository.findByArticleId(articleId);
	}
	
	/**
	 * 投稿情報の全件取得.
	 * 
	 * @return　投稿情報のリスト
	 */
	public List<Article> findAll(){
		return articleRepository.findAll();
		
	}
	/**
	 * 投稿情報の登録.
	 * 
	 * @param article 投稿情報
	 */
	public void insertArticle(Article article) {
		articleRepository.insertArticle(article);
	}
	
	/**
	 * コメント情報の登録.
	 * 
	 * @param comment コメント情報
	 */
	public void insertComment(Comment comment) {
		commentRepository.insertComment(comment);
	}
	/**
	 * 記事とコメントの削除.
	 * <p>
	 * 引数の投稿IDに一致する投稿情報をデータベースから削除する。
	 * また、引数の投稿IDに一致する投稿ID情報を持っているコメントを削除する。
	 * </p>
	 * 
	 * @param articleId
	 */
	public void deleteByArticleId(Integer articleId) {
		commentRepository.deleteByArticleId(articleId);
		articleRepository.deleteById(articleId);
	}
}
