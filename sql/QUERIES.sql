-- 1
-- ошибки в расписании (фильмы накладываются друг на друга),
-- отсортированные по возрастанию времени.
-- Выводить надо колонки «фильм 1», «время начала», «длительность»,
-- «фильм 2», «время начала», «длительность»;


/* !ВАЖНО!                                                                  */
/* Если начало и конец у двух фильмов совпадают, то выводим эту пару 2 раза */
/* На этот случай нет условия, поэтому сделал так                           */

SELECT  m1.title, s1.show_time, m1.running_time,
        m2.title, s2.show_time, m2.running_time
FROM shows s1
JOIN movies m1 ON m1.movie_id = s1.movie_id
JOIN shows s2  ON s2.show_time >= s1.show_time
              AND s2.show_time < s1.show_time + (m1.running_time / 1440)
JOIN movies m2 ON m2.movie_id = s2.movie_id
WHERE s1.show_id <> s2.show_id
ORDER BY s1.show_time, s2.show_time;


-- 2
-- перерывы 30 минут и более между фильмами —
-- выводить по уменьшению длительности перерыва.
-- Колонки «фильм 1», «время начала», «длительность»,
-- «время начала второго фильма», «длительность перерыва»;

SELECT  t.m1_movie_id,
        t.m1_title,
        t.s1_show_time,
        t.m1_running_time,
        t.s2_show_time,
        t.void_time
FROM (
    SELECT  m1.movie_id m1_movie_id,
            m1.title m1_title,
            s1.show_id s1_show_id,
            s1.show_time s1_show_time,
            m1.running_time m1_running_time,
            s2.show_id s2_show_id,
            s2.show_time s2_show_time,
            ROUND((s2.show_time - (s1.show_time + (m1.running_time / 1440))) * 24 * 60) void_time,
            RANK() OVER (PARTITION BY m1.movie_id, s1.show_id
                                ORDER BY s2.show_time - (s1.show_time + (m1.running_time / 1440))) rn
    FROM movies m1
    JOIN shows s1   ON s1.movie_id = m1.movie_id
    JOIN shows s2   ON s2.show_time >= s1.show_time
    JOIN movies m2  ON m2.movie_id = s2.movie_id
                   AND s2.show_time + (m2.running_time / 1440) > s1.show_time + (m1.running_time / 1440)
) t
WHERE t.rn = 1
AND t.void_time >= 30
ORDER BY t.m1_movie_id, t.s1_show_id, t.s2_show_id
;


-- 3
-- список фильмов,
-- для каждого — с указанием общего числа посетителей за все время,
-- среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму
-- (отсортировать по убыванию прибыли).
-- Внизу таблицы должна быть строчка «итого»,
-- содержащая данные по всем фильмам сразу;

SELECT  CASE WHEN t.movie_id = 0 THEN NULL ELSE t.movie_id END movie_id,
        t.title,
        t.total_tickets,
        t.avg_tickets_per_show,
        t.total_earnings
FROM (
    SELECT  m.movie_id,
            m.title,
            SUM(CASE
                    WHEN ps.movie_id IS NULL
                        THEN 0
                    ELSE ps.cnt_ticket_id
                END) total_tickets,
            TO_CHAR(ROUND(NVL(SUM(ps.cnt_ticket_id) / COUNT(ps.cnt_ticket_id), 0), 2), '0.00') avg_tickets_per_show,
            NVL(SUM(ps.sum_ticket_price), 0) total_earnings

    FROM (SELECT m.movie_id, m.title FROM movies m
            UNION ALL
          SELECT 0, '--- TOTAL ---' FROM dual) m
    LEFT JOIN (
        SELECT m.movie_id, s.show_id, COUNT(tp.ticket_id) cnt_ticket_id, SUM(s.ticket_price) sum_ticket_price
        FROM movies m
        JOIN shows s ON s.movie_id = m.movie_id
        JOIN ticket_purchases tp ON tp.show_id = s.show_id
        GROUP BY m.movie_id, s.show_id
    ) ps ON ps.movie_id = m.movie_id
         OR m.movie_id = 0
    GROUP BY m.movie_id, m.title
) t
ORDER BY (CASE WHEN t.movie_id = 0 THEN 1 ELSE 0 END), t.total_earnings DESC
;


-- 4
-- число посетителей и кассовые сборы,
-- сгруппированные по времени начала фильма:
-- с 9 до 15,
-- с 15 до 18,
-- с 18 до 21,
-- с 21 до 00:00
-- (сколько посетителей пришло с 9 до 15 часов и т.д.).

SELECT  f.frame_name time_frame,
        NVL(d.visitors, 0) visitors,
        NVL(d.earnings, 0) earnings
FROM (
    SELECT 1 frame_id, '09-15' frame_name from dual
        UNION ALL
    SELECT 2 frame_id, '15-18' frame_name from dual
        UNION ALL
    SELECT 3 frame_id, '18-21' frame_name from dual
        UNION ALL
    SELECT 4 frame_id, '21-00' frame_name from dual
) f
LEFT JOIN (
SELECT  CASE
            WHEN extract(hour from CAST(s.show_time AS TIMESTAMP)) >= 9
                AND
                extract(hour from CAST(s.show_time AS TIMESTAMP)) < 15
            THEN 1
            WHEN extract(hour from CAST(s.show_time AS TIMESTAMP)) >= 15
                AND
                extract(hour from CAST(s.show_time AS TIMESTAMP)) < 18
            THEN 2
            WHEN extract(hour from CAST(s.show_time AS TIMESTAMP)) >= 18
                AND
                extract(hour from CAST(s.show_time AS TIMESTAMP)) < 21
            THEN 3
            WHEN extract(hour from CAST(s.show_time AS TIMESTAMP)) >= 21
                OR
                extract(hour from CAST(s.show_time AS TIMESTAMP)) = 0
            THEN 4
            ELSE 0
        END frame_id,
        COUNT(tp.ticket_id) visitors,
        SUM(s.ticket_price) earnings

FROM shows s
JOIN ticket_purchases tp ON tp.show_id = s.show_id
WHERE   extract(hour from CAST(s.show_time AS TIMESTAMP)) >= 9
    OR
        extract(hour from CAST(s.show_time AS TIMESTAMP)) = 0
GROUP BY
        CASE
            WHEN extract(hour from CAST(s.show_time AS TIMESTAMP)) >= 9
                AND
                extract(hour from CAST(s.show_time AS TIMESTAMP)) < 15
            THEN 1
            WHEN extract(hour from CAST(s.show_time AS TIMESTAMP)) >= 15
                AND
                extract(hour from CAST(s.show_time AS TIMESTAMP)) < 18
            THEN 2
            WHEN extract(hour from CAST(s.show_time AS TIMESTAMP)) >= 18
                AND
                extract(hour from CAST(s.show_time AS TIMESTAMP)) < 21
            THEN 3
            WHEN extract(hour from CAST(s.show_time AS TIMESTAMP)) >= 21
                OR
                extract(hour from CAST(s.show_time AS TIMESTAMP)) = 0
            THEN 4
            ELSE 0
        END
) d ON d.frame_id = f.frame_id
ORDER BY d.frame_id
;