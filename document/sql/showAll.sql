SELECT id,name,content FROM articles;
SELECT id,name,content,article_id FROM comments;

SELECT
a.id as article_id,a.name as article_name,a.content as article_content,
c.id as comment_id,c.name as comment_name,c.content as comment_content,c.article_id
FROM articles a left outer join comments c on c.article_id=a.id ORDER BY a.id,c.id DESC;
