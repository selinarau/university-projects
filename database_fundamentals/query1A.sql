-- Database Fundamentals, spring 2024
-- Selina Rautakoski
-- assignment: Query 1, A

-- From the timetable classes belonging to the category named Kehonhuolto (body mainenance), 
-- select the weekday, start time, group exercise class name, and duration.:
-- weekday, start time, group exercise class name, and duration.
-- The result rows are sorted in ascending order by weekday, start time, and name.

SELECT l.viikonpaiva, 
       l.alkamisaika, 
       r.rlnimi, 
       r.kesto
FROM ljtunti l
    JOIN rltunti r ON l.rltunnus = r.rltunnus
    JOIN kategoria k ON r.ktunnus = k.ktunnus
WHERE k.knimi = 'Kehonhuolto'
ORDER BY l.viikonpaiva, l.alkamisaika, r.rlnimi;