DROP TABLE user_film_reviews IF EXISTS;
DROP TABLE film_actors IF EXISTS;
DROP TABLE films IF EXISTS;
DROP TABLE persons IF EXISTS;
DROP TABLE users IF EXISTS;

CREATE TABLE users (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1 ) PRIMARY KEY,
  name VARCHAR(50) NOT NULL ,
  email VARCHAR(50) NOT NULL,
  password VARCHAR(20)
);
CREATE UNIQUE INDEX user_email_key ON USERS ( email );

CREATE TABLE persons (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1 ) PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  surname VARCHAR(50) NOT NULL,
  date_of_birth DATE
);

CREATE TABLE films (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1 ) PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  genre INTEGER,
  description VARCHAR(255),
  director_id INTEGER,
  FOREIGN KEY (director_id) REFERENCES persons (id) ON DELETE NO ACTION
);

CREATE TABLE film_actors (
  film_id INTEGER NOT NULL,
  person_id INTEGER NOT NULL,
  CONSTRAINT film_actors_idx UNIQUE (film_id, person_id),
  FOREIGN KEY ( film_id ) REFERENCES films ( id ) ON DELETE CASCADE,
  FOREIGN KEY ( person_id ) REFERENCES persons (id) ON DELETE CASCADE
);

CREATE TABLE user_film_reviews (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1 ) PRIMARY KEY,
  user_id INTEGER NOT NULL,
  film_id INTEGER NOT NULL,
  review VARCHAR(1024),
  rating INTEGER,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ,
  FOREIGN KEY (film_id) REFERENCES films (id) ON DELETE CASCADE
)