-- Database Fundamentals, spring 2024
-- Selina Rautakoski
-- assignment: Query 5

-- Retrieve the IDs and names of instructors who teach all the timetable classes
-- of the group exercise classes they are allowed to teach.
-- The result rows are sorted in ascending order by instructor ID.

SELECT otunnus, onimi
FROM ohjaaja
WHERE otunnus NOT IN (
    SELECT otunnus 
    FROM (
    SELECT vo.rltunnus, vo.otunnus
    FROM ohjaaja o
    LEFT JOIN voi_ohjata vo ON o.otunnus = vo.otunnus
    EXCEPT
    SELECT lj.rltunnus, lj.otunnus
    FROM ljtunti lj
    )
)
ORDER BY otunnus;
