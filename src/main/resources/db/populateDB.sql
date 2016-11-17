DELETE FROM USER_FILM_REVIEWS;
DELETE FROM FILM_ACTORS;
DELETE FROM FILMS;
DELETE FROM PERSONS;
DELETE FROM USERS;

ALTER TABLE USER_FILM_REVIEWS ALTER COLUMN id RESTART WITH 1;
ALTER TABLE FILMS ALTER COLUMN id RESTART WITH 1;
ALTER TABLE PERSONS ALTER COLUMN id RESTART WITH 1;
ALTER TABLE USERS ALTER COLUMN id RESTART WITH 1;

INSERT INTO USERS(NAME, EMAIL, PASSWORD) VALUES ('admin', 'admin@gmail.com', '111');
INSERT INTO USERS(NAME, EMAIL, PASSWORD) VALUES ('user1', 'user1@gmail.com', '222');
INSERT INTO USERS(NAME, EMAIL, PASSWORD) VALUES ('user2', 'user2@gmail.com', '333');

INSERT INTO PERSONS (NAME, SURNAME, DATE_OF_BIRTH) VALUES ('Arnold','Schwarzenegger','1947-07-30');
INSERT INTO PERSONS (NAME, SURNAME, DATE_OF_BIRTH) VALUES ('Linda','Hamilton','1956-09-26');
INSERT INTO PERSONS (NAME, SURNAME, DATE_OF_BIRTH) VALUES ('James','Cameron','1954-08-16');
INSERT INTO PERSONS (NAME, SURNAME, DATE_OF_BIRTH) VALUES ('George','Lucas','1944-05-14');
INSERT INTO PERSONS (NAME, SURNAME, DATE_OF_BIRTH) VALUES ('Mark','Hamill','1951-09-25');
INSERT INTO PERSONS (NAME, SURNAME, DATE_OF_BIRTH) VALUES ('Harrison','Ford','1942-07-13');
INSERT INTO PERSONS (NAME, SURNAME, DATE_OF_BIRTH) VALUES ('Steven','Spielberg','1946-12-18');
INSERT INTO PERSONS (NAME, SURNAME, DATE_OF_BIRTH) VALUES ('Kate','Capshaw','1953-11-3');

INSERT INTO FILMS (NAME, GENRE, DIRECTOR_ID, DESCRIPTION) VALUES ('Terminator 2: Judgment Day',0,3,'A cyborg, identical to the one who failed to kill Sarah Connor, must now protect her young son, John Connor, from a more advanced cyborg, made out of liquid metal.');
INSERT INTO FILMS (NAME, GENRE, DIRECTOR_ID, DESCRIPTION) VALUES ('Star Wars: Episode IV - A New Hope',1,4,'Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a wookiee and two droids to save the galaxy from the Empire''s world-destroying battle-station, while also attempting to rescue Princess Leia from the evil Darth Vader.');
INSERT INTO FILMS (NAME, GENRE, DIRECTOR_ID, DESCRIPTION) VALUES ('Indiana Jones and the Temple of Doom',2,7,'After arriving in India, Indiana Jones is asked by a desperate village to find a mystical stone. He agrees, and stumbles upon a secret cult plotting a terrible plan in the catacombs of an ancient palace.');

INSERT INTO FILM_ACTORS (FILM_ID, PERSON_ID) VALUES (1,1);
INSERT INTO FILM_ACTORS (FILM_ID, PERSON_ID) VALUES (1,2);
INSERT INTO FILM_ACTORS (FILM_ID, PERSON_ID) VALUES (2,5);
INSERT INTO FILM_ACTORS (FILM_ID, PERSON_ID) VALUES (2,6);
INSERT INTO FILM_ACTORS (FILM_ID, PERSON_ID) VALUES (3,6);
INSERT INTO FILM_ACTORS (FILM_ID, PERSON_ID) VALUES (3,8);

INSERT INTO USER_FILM_REVIEWS (USER_ID, FILM_ID, RATING, REVIEW) VALUES (2,1,10,'I am not a big fan of sequels,as most of them disappoint,but T2 certainly does not.In fact,it''s a rare case,at least in my opinion, of a sequel actually surpassing the original film in terms of greatness.As in 99.9% of his films,Arnold Schwarzenegger is the good guy once again,but you don''t mind once you witness the incredible villain performance of Robert Patrick.This film is nothing short of a beginning to end thrill ride.Let us not forget the talents of Linda Hamilton and Edward Furlong,who gave great supporting efforts.Thumbs up!');
INSERT INTO USER_FILM_REVIEWS (USER_ID, FILM_ID, RATING, REVIEW) VALUES (2,2,5,'Star Wars is a movie that has had great social impact, a fact that has often gone unnoticed. A harbinger of a changing mood within the United States, Star Wars was one of the few movies rated General that was released in 1977. Where movies had for a decade been depicting ever more dark topics (Taxi Driver, The Exorcist) Star Wars was a lighthearted adventure. While some may decry the move back to swashbuckling from social comment, I for one celebrate the fact that Star Wars made it possible for families to go to movies together once again.');
INSERT INTO USER_FILM_REVIEWS (USER_ID, FILM_ID, RATING, REVIEW) VALUES (2,3,0,'i understand its just a movie, but this movie is full of stupid lies, deliberately made to show India and Hinduism in a very bad way. The depiction of Indian dishes such as baby snakes, eyeball soup, beetles and chilled monkey brains are ridiculous in the country where majority are vegetarians. in a dialogue where villain says Kali ma will take over Muslim Jews Christian god in the world were made to look like Hindus are evil people. depiction of the goddess Kali as a representative of the underworld and evil was met with much criticism, as she is almost exclusively depicted as a goddess of change and empowerment (Shakti), meaning that while she does destroy, she almost always does so in order to affect positive change. showing the things wrongly will sure offend everyone.');
INSERT INTO USER_FILM_REVIEWS (USER_ID, FILM_ID, RATING, REVIEW) VALUES (3,3,7,'Temple of Doom may not be as good as Raiders, but it doesn''t deserve all this negative flak. The story is a little darker but that doesn''t take anything away from the film. It makes the situation that much more dire. John Williams'' score infuses the sacrifice sequence with a sense of building dread. The chanting, the heavy drums all building into a wild climax of heart burning and lava filled mayhem. The mine car chase is wild fun and Indy''s bridge manuver is one hell of a climax. Still don''t know why everyone''s so down on this movie.');
INSERT INTO USER_FILM_REVIEWS (USER_ID, FILM_ID, RATING, REVIEW) VALUES (3,2,3,'Star wars made epic fantasy real. For a generation of people it has defined what the cinema experience is meant to be. Today it is probable that pc games will offer a deeper and more satisfying entertainment solution, but for pure visual and aural pleasure, mixed with basic emotional manipulation, there has never and will never be a better example of cinema than when star wars appeared over 25 years ago. When you think of star wars, you must remember what else was happening at the time. In America, the war in Vietnam had been lost. In the U.K economic disaster was occurring(a 3 day working week, and the army collecting rubbish). It was almost like the two most technically advanced countries in the world were going backwards. Star wars let everybody escape from that reality and reach for a future that was uncertain but ultimately good.');