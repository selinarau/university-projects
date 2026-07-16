-- Database Fundamentals, spring 2024
-- Selina Rautakoski
-- assignment: Query 2, B

-- Retrieve the following information for the shortest group exercise class:
-- class name, duration, and the name of the class category.
-- The result rows are sorted in ascending order by class name.

SELECT rl.rlnimi, MIN(rl.kesto) AS kesto, k.knimi
FROM rltunti rl
JOIN kategoria k ON rl.ktunnus = k.ktunnus
ORDER BY rl.rlnimi;