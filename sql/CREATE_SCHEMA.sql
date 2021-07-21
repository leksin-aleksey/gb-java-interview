/* Использую Oracle DB 19c */
/* https://livesql.oracle.com/apex/ */


/* movies */
DROP TABLE movies;

CREATE TABLE movies (
    movie_id INTEGER GENERATED ALWAYS AS IDENTITY,
    title varchar2(4000) NOT NULL,
    running_time INTEGER NOT NULL,
    CONSTRAINT pk_movie_id
        PRIMARY KEY (movie_id),
    CONSTRAINT check_running_time
        CHECK (running_time IN (60, 90, 120)));

/* shows */
DROP TABLE shows;

CREATE TABLE shows (
    show_id INTEGER  GENERATED ALWAYS AS IDENTITY,
    movie_id INTEGER NOT NULL,
    show_time DATE NOT NULL,
    ticket_price NUMBER NOT NULL,
    CONSTRAINT pk_show_id
        PRIMARY KEY (show_id),
    CONSTRAINT fk_movie_id
        FOREIGN KEY (movie_id)
        REFERENCES movies (movie_id)
);

/* ticket_purchases */
DROP TABLE ticket_purchases;

CREATE TABLE ticket_purchases (
    ticket_id INTEGER  GENERATED ALWAYS AS IDENTITY,
    show_id INTEGER NOT NULL,
    CONSTRAINT pk_ticket_id
        PRIMARY KEY (ticket_id),
    CONSTRAINT fk_show_id
        FOREIGN KEY (show_id)
        REFERENCES shows (show_id)
);