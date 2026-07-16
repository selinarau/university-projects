-- Database Fundamentals, spring 2024
-- Selina Rautakoski
-- assignment: Query 2, A

-- The following information is retrieved for the shortest group exercise class:
-- class name and duration.
-- The result rows are sorted in ascending order by class name.

SELECT rlnimi, MIN(kesto) AS kesto
FROM rltunti
ORDER BY rlnimi;