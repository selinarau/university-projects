-- Database Fundamentals, spring 2024
-- Selina Rautakoski
-- assignment: Query 4, A

-- For each category:
-- name, number of group exercise classes belonging to the category,
-- minimum duration, and maximum duration.
-- The result rows are sorted in ascending order by category name.

SELECT k.knimi, 
       COUNT(rl.rltunnus) AS lkm,
       MIN(rl.kesto) AS minkesto,
       MAX(rl.kesto) AS maxkesto
FROM kategoria k
JOIN rltunti rl ON k.ktunnus = rl.ktunnus
GROUP BY k.ktunnus
ORDER BY k.knimi;