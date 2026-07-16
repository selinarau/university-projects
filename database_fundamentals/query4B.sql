-- Database Fundamentals, spring 2024
-- Selina Rautakoski
-- assignment: Query 4, B

-- For each category:
-- category name, number of group exercise classes belonging to the category,
-- number of timetable classes belonging to the category, and number of different
-- instructors teaching the timetable classes belonging to the category.
-- The result rows are sorted in ascending order by category name.

SELECT k.knimi,
       COUNT(DISTINCT rl.rltunnus) AS rltuntilkm,
       COUNT(lj.rltunnus) AS ljtuntilkm,
       COUNT(DISTINCT lj.otunnus) AS ohjaajalkm
FROM kategoria k
LEFT JOIN rltunti rl ON k.ktunnus = rl.ktunnus
LEFT JOIN ljtunti lj ON rl.rltunnus = lj.rltunnus
LEFT JOIN ohjaaja o ON lj.otunnus = o.otunnus
GROUP BY k.ktunnus
ORDER BY k.knimi;