-- Database Fundamentals, spring 2024
-- Selina Rautakoski
-- assignment: Query 2, C

-- Retrieve the following information for the shortest group exercise class
-- belonging to the category named Kehonhuolto:
-- class name, duration, and the name of the class category.
-- The result rows are sorted in ascending order by class name.

SELECT rl.rlnimi, rl.kesto, k.knimi
FROM rltunti rl
JOIN kategoria k ON rl.ktunnus = k.ktunnus
WHERE k.knimi = 'Kehonhuolto' 
    AND rl.kesto = (SELECT MIN(rl.kesto) FROM rltunti rl 
        JOIN kategoria k ON rl.ktunnus = k.ktunnus
        WHERE k.knimi = 'Kehonhuolto')
ORDER BY rl.rlnimi;