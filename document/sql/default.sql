DROP TABLE IF EXISTS articles CASCADE;
DROP TABLE IF EXISTS comments CASCADE;


CREATE TABLE articles
(
id serial NOT NULL, --投稿ID
name text NOT NULL, --名前
content text NOT NULL, --コンテント
CONSTRAINT articles_pkey PRIMARY KEY (id)
);
CREATE TABLE comments
(
id serial NOT NULL, --コメントID
name text NOT NULL, --名前
content text NOT NULL, --コンテント
article_id integer NOT NULL, --投稿ID
CONSTRAINT comments_pkey PRIMARY KEY (id),
FOREIGN KEY (article_id) REFERENCES articles (id)
);

INSERT INTO articles(name, content) VALUES('伊賀', 'この掲示板について');
INSERT INTO articles(name, content) VALUES('⼭田', '新たな投稿です。');
INSERT INTO comments(name, content, article_id) VALUES('佐藤1', '伊賀さん書き込みのコメント1',1);
INSERT INTO comments(name, content, article_id) VALUES('佐藤2', '伊賀さん書き込みのコメント2',1);
INSERT INTO comments(name, content, article_id) VALUES('佐藤3', '伊賀さん書き込みのコメント3',1);
INSERT INTO comments(name, content, article_id) VALUES('吉田1', '⼭⽥さん書き込みのコメント1',2);
INSERT INTO comments(name, content, article_id) VALUES('吉田2', '⼭田さん書き込みのコメント2',2);
