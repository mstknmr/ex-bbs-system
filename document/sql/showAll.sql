SELECT id,name,content FROM articles;
SELECT id,name,content,article_id FROM comments;

SELECT
a.id as article_id,a.name as article_name,a.content as article_content,
c.id as comment_id,c.name as comment_name,c.content as comment_conetnt
FROM articles a join comments c on c.article_id=a.id;

<form  action="index.html" th:action="@{/article/insertComment}"method="post">
  <input type="text" name="name" >
  <input type="text" name="content">
  <input type="hidden" name="articleId" value="" th:value="${article.id}">
</form>

<form  action="article.html" th:action="@{/articles/insert-article}" method="post" >
   投稿者名：<input type="text" name="name" > <br>
   投稿内容：<input type="text" name="content"> <br>
   <input type="submit"  value="記事投稿">
 </form>
