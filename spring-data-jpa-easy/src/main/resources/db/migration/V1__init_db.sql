CREATE TABLE authors (
	id BIGSERIAL NOT NULL,
	name VARCHAR(255) NOT NULL,
	surname VARCHAR(255) NOT NULL,
	CONSTRAINT authors_pk PRIMARY KEY (id)
);
CREATE TABLE books (
	id BIGSERIAL NOT NULL,
	title VARCHAR(255) NOT NULL,
	price DECIMAL(10, 2) NULL,
	year_release INTEGER NOT NULL,
	author_id BIGINT NOT NULL,
	CONSTRAINT books_pk PRIMARY KEY (id),
	CONSTRAINT books_unique UNIQUE (title, author_id)
);

ALTER TABLE public.books ADD CONSTRAINT fk_books_author FOREIGN KEY (author_id) REFERENCES public.authors(id);
