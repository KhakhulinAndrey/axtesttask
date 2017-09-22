DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE authors
(
  id               BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                  NOT NULL,
  surname          VARCHAR                  NOT NULL,
  patronymic       VARCHAR                  NOT NULL,
  birthday         DATE                     NOT NULL
);

CREATE TABLE books
(
  id BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR    NOT NULL,
  isbn VARCHAR    NOT NULL,
  subject VARCHAR NOT NULL,
  auth_id         BIGINT,
  FOREIGN KEY (auth_id) REFERENCES authors(id)
);

DELETE FROM authors;
DELETE FROM books;

INSERT INTO authors (name, patronymic, surname, birthday) VALUES
  ('Alex', 'Serg','Pushkin', '1799-06-06'),
  ('Nickolay', 'Vasil','Gogol', '1809-04-01'),
  ('Ivab', 'Vasil','Ivanov', '1929-01-11');

INSERT INTO books (name, isbn, subject, auth_id) VALUES
  ('Ruslan and Ludmila', '321-TT', 'Poem', 100000),
  ('Dubrovsky', '311-TG', 'Povest', 100000),
  ('Mertvie dushi', '417-GL', 'Poem', 100001),
  ('Revizor', '301-GG', 'Comedy', 100001);