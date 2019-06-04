package com.example.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Article;
import com.example.domain.ArticleAndComment;
import com.example.domain.Comment;
import com.example.repository.ArticleAndCommentRepository;
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
	@Autowired
	private ArticleAndCommentRepository articleAndCommentRepository;
	
	
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
	
	/**
	 * 投稿情報の全件取得.
	 * <p>
	 * 投稿IDをキー,投稿情報をバリューとしてマップにセットしていく。
	 * </p>
	 * 
	 * @return　投稿情報のマップ
	 */
	public LinkedHashMap<Integer,Article> findAllArticleJoinComment(){
		List<ArticleAndComment> ArticleJoinCommentlist = articleAndCommentRepository.findAll();;
		LinkedHashMap<Integer, Article> articleMap = new LinkedHashMap<>();
		for (ArticleAndComment articleAndComment : ArticleJoinCommentlist) {
			System.out.println(articleAndComment);
			List<Comment> commentList = new ArrayList<>();
			Article article = new Article();
			//マップにすでに投稿IDがキーになっている投稿情報がある場合。
			//一致するマップから投稿情報を取得してその中のコメント情報のリストにコメントを追加してマップにセットし直す
			if (articleMap.containsKey(articleAndComment.getId())) {
				article = articleMap.get(articleAndComment.getId());
				commentList = article.getCommentList();
				commentList.add(0, articleAndCommentForComment(articleAndComment));
				article.setCommentList(commentList);
				articleMap.put(articleAndComment.getId(), article);
				
			} else {
				BeanUtils.copyProperties(articleAndComment, article);
				if (articleAndComment.getCommentId() != 0) {
					commentList.add(0, articleAndCommentForComment(articleAndComment));
					article.setCommentList(commentList);
				}
				articleMap.put(articleAndComment.getId(), article);
			}

		}
		return articleMap;
		
	}
	/**
	 * 投稿とコメントが結合された情報からコメントの情報のみを抽出する.
	 * 
	 * @param articleAndComment 投稿とコメントが結合された情報
	 * @return　コメントの情報
	 */
	private static Comment articleAndCommentForComment(ArticleAndComment articleAndComment) {

		Comment comment = new Comment();
		comment.setId(articleAndComment.getCommentId());
		comment.setName(articleAndComment.getCommentName());
		comment.setContent(articleAndComment.getCommentContent());
		comment.setArticleId(articleAndComment.getArticleId());
		System.out.println(comment);
		return comment;
	}
}
