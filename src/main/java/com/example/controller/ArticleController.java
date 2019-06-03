package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.service.ArticleService;

/**
 * 掲示板のコントローラ.
 * 
 * @author knmrmst
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	ArticleService articleService;

	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}

	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}

	/**
	 * 記事一覧画面の表示.
	 * 
	 * @param model モデル
	 * @return 記事一覧画面
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleService.findAll();

		for (Article article : articleList) {
			List<Comment> commentList = articleService.findByArticleId(article.getId());
			article.setCommentList(commentList);
		}

		
		model.addAttribute("articleList", articleList);

		return "article";
	}

	/**
	 * 投稿情報のデータベース登録.
	 * 
	 * @param articleForm フォーム
	 * @return 記事一覧画面へのリダイレクト
	 */
	@RequestMapping("/insertArticle")
	public String insertArticle(ArticleForm articleForm) {
		Article article = new Article();
		BeanUtils.copyProperties(articleForm, article);
		articleService.insertArticle(article);
		return "redirect:/article";
	}

	/**
	 * コメント情報のデータベース登録.
	 * 
	 * @param commentForm フォーム
	 * @return 記事一覧画面へのリダイレクト
	 */
	@RequestMapping("/insertComment")
	public String insertComment(CommentForm commentForm) {
		System.out.println(commentForm);
		Comment comment = new Comment();
		BeanUtils.copyProperties(commentForm, comment);
		comment.setArticleId(Integer.parseInt(commentForm.getArticleId()));
		articleService.insertComment(comment);
		return "redirect:/article";
	}
	
	/**
	 * 投稿情報とコメントの削除機能.
	 * 
	 * @param articleId　投稿ID
	 * @return　記事一覧画面へのリダイレクト
	 */
	@RequestMapping("/deleteArticle")
	public String deleteArticle(Integer articleId) {
		
		articleService.deleteByArticleId(articleId);
		return "redirect:/article";
	}
}
