-- Database Fundamentals, spring 2024
-- Selina Rautakoski
-- assignment: Query 3

-- Retrieve the IDs and names of instructors who can teach classes belonging to
-- both the category named Spinning and the category named Kehonhuolto.
-- The result rows are sorted in ascending order by instructor ID.

SELECT o.otunnus, o.onimi
FROM ohjaaja o
JOIN voi_ohjata vo ON o.otunnus = vo.otunnus
JOIN rltunti rl ON vo.rltunnus = rl.rltunnus
JOIN kategoria k ON rl.ktunnus = k.ktunnus
WHERE k.knimi = 'Spinning'
INTERSECT
SELECT o.otunnus, o.onimi
FROM ohjaaja o
JOIN voi_ohjata vo ON o.otunnus = vo.otunnus
JOIN rltunti rl ON vo.rltunnus = rl.rltunnus
JOIN kategoria k ON rl.ktunnus = k.ktunnus
WHERE k.knimi = 'Kehonhuolto'
ORDER BY o.otunnus;
