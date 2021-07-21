/* movies */
INSERT INTO movies (title, running_time)
VALUES ('The Shawshank Redemption', 120);

INSERT INTO movies (title, running_time)
VALUES ('The Godfather', 120);

INSERT INTO movies (title, running_time)
VALUES ('The Godfather: Part II', 60);

INSERT INTO movies (title, running_time)
VALUES ('The Dark Knight', 90);

INSERT INTO movies (title, running_time)
VALUES ('12 Angry Men', 60);

COMMIT;

/* shows */
INSERT INTO shows (movie_id, show_time, ticket_price)
VALUES (1, TO_DATE('01.01.2022 09:30:00', 'DD.MM.YYYY HH24:MI:SS'), 10);

INSERT INTO shows (movie_id, show_time, ticket_price)
VALUES (1, TO_DATE('01.01.2022 12:00:00', 'DD.MM.YYYY HH24:MI:SS'), 13);

INSERT INTO shows (movie_id, show_time, ticket_price)
VALUES (1, TO_DATE('01.01.2022 17:00:00', 'DD.MM.YYYY HH24:MI:SS'), 20);

INSERT INTO shows (movie_id, show_time, ticket_price)
VALUES (1, TO_DATE('01.01.2022 21:30:00', 'DD.MM.YYYY HH24:MI:SS'), 30);

INSERT INTO shows (movie_id, show_time, ticket_price)
VALUES (2, TO_DATE('01.01.2022 18:30:00', 'DD.MM.YYYY HH24:MI:SS'), 5);

INSERT INTO shows (movie_id, show_time, ticket_price)
VALUES (2, TO_DATE('01.01.2022 19:00:00', 'DD.MM.YYYY HH24:MI:SS'), 7);

INSERT INTO shows (movie_id, show_time, ticket_price)
VALUES (3, TO_DATE('01.01.2022 08:00:00', 'DD.MM.YYYY HH24:MI:SS'), 3);

INSERT INTO shows (movie_id, show_time, ticket_price)
VALUES (3, TO_DATE('01.01.2022 10:30:00', 'DD.MM.YYYY HH24:MI:SS'), 1);

INSERT INTO shows (movie_id, show_time, ticket_price)
VALUES (3, TO_DATE('01.01.2022 16:30:00', 'DD.MM.YYYY HH24:MI:SS'), 2);

INSERT INTO shows (movie_id, show_time, ticket_price)
VALUES (4, TO_DATE('01.01.2022 10:30:00', 'DD.MM.YYYY HH24:MI:SS'), 11);

INSERT INTO shows (movie_id, show_time, ticket_price)
VALUES (4, TO_DATE('01.01.2022 16:30:00', 'DD.MM.YYYY HH24:MI:SS'), 11);

INSERT INTO shows (movie_id, show_time, ticket_price)
VALUES (4, TO_DATE('01.01.2022 23:30:00', 'DD.MM.YYYY HH24:MI:SS'), 11);

COMMIT;

/* ticket_purchases */
INSERT INTO ticket_purchases (show_id) VALUES (2);
INSERT INTO ticket_purchases (show_id) VALUES (2);
INSERT INTO ticket_purchases (show_id) VALUES (2);
INSERT INTO ticket_purchases (show_id) VALUES (2);
INSERT INTO ticket_purchases (show_id) VALUES (2);


INSERT INTO ticket_purchases (show_id) VALUES (3);

INSERT INTO ticket_purchases (show_id) VALUES (4);
INSERT INTO ticket_purchases (show_id) VALUES (4);

INSERT INTO ticket_purchases (show_id) VALUES (5);
INSERT INTO ticket_purchases (show_id) VALUES (5);
INSERT INTO ticket_purchases (show_id) VALUES (5);
INSERT INTO ticket_purchases (show_id) VALUES (5);
INSERT INTO ticket_purchases (show_id) VALUES (5);
INSERT INTO ticket_purchases (show_id) VALUES (5);

INSERT INTO ticket_purchases (show_id) VALUES (6);
INSERT INTO ticket_purchases (show_id) VALUES (6);

INSERT INTO ticket_purchases (show_id) VALUES (7);
INSERT INTO ticket_purchases (show_id) VALUES (7);
INSERT INTO ticket_purchases (show_id) VALUES (7);

INSERT INTO ticket_purchases (show_id) VALUES (8);

INSERT INTO ticket_purchases (show_id) VALUES (9);

INSERT INTO ticket_purchases (show_id) VALUES (10);
INSERT INTO ticket_purchases (show_id) VALUES (10);
INSERT INTO ticket_purchases (show_id) VALUES (10);
INSERT INTO ticket_purchases (show_id) VALUES (10);

COMMIT;