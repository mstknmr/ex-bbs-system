SELECT id,name,content FROM articles;
SELECT id,name,content,article_id FROM comments;

SELECT a.id,a.name,a.content,c.id,c.name,c.content FROM articles a join comments c on c.article_id=a.id;
