-- Database Fundamentals, spring 2024
-- Selina Rautakoski
-- assignment: Query 1, B

-- The following information is retrieved from the timetable classes belonging to
-- the category named Kehonhuolto, where the weekday is Tuesday (abbreviated as 'ti')
-- or the duration is 50 minutes:
-- weekday, start time, group exercise class name, duration, and difficulty level.
-- The result rows are sorted in ascending order by weekday, start time, and name.

SELECT l.viikonpaiva, 
       l.alkamisaika, 
       r.rlnimi, 
       r.kesto,
       r.taso
FROM ljtunti l
    JOIN rltunti r ON l.rltunnus = r.rltunnus
    JOIN kategoria k ON r.ktunnus = k.ktunnus
WHERE k.knimi = 'Kehonhuolto' AND (l.viikonpaiva = 'ti' OR r.kesto = 50)
ORDER BY l.viikonpaiva, l.alkamisaika, r.rlnimi;