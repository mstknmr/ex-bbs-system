package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * コメント投稿のフォーム.
 * 
 * @author knmrmst
 *
 */
public class CommentForm {
	/** 投稿ID*/
	@NotNull
	private String articleId;
	/** コメント者名*/
	@NotBlank(message="名前を入力してください")
	@Size(min=1,max=50,message="名前は１〜５０文字で入力してください")
	private String name;
	/** コメント内容*/
	@NotBlank(message="コメントを入力してください")
	private String content;

	@Override
	public String toString() {
		return "CommentForm [articleId=" + articleId + ", name=" + name + ", content=" + content + "]";
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
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

}
