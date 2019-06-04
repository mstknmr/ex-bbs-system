package com.example.domain;

/**
 * 投稿情報とコメント情報のドメイン.
 * 
 * @author knmrmst
 *
 */
public class ArticleAndComment {
	/** 投稿者ID*/
	private Integer id;
	/** 投稿者名*/
	private String name;
	/** 投稿内容*/
	private String content;
	
	/** コメントID*/
	private Integer commentId;
	/** コメント者名*/
	private String commentName;
	/** コメント内容*/
	private String commentContent;
	/** 投稿ID*/
	private Integer articleId;
	
	@Override
	public String toString() {
		return "ArticleAndComment [id=" + id + ", name=" + name + ", content=" + content + ", commentId=" + commentId + ", commentName=" + commentName + ", commentContent="
				+ commentContent + ", articleId=" + articleId + "]";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public String getCommentName() {
		return commentName;
	}
	public void setCommentName(String commentName) {
		this.commentName = commentName;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
}
