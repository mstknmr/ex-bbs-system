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
