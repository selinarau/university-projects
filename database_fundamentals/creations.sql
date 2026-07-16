-- Database Fundamentals, spring 2024
-- Selina Rautakoski
-- assignment: Part II, creation statements

-- enable foreign key constraint checking in SQLite
PRAGMA foreign_keys = ON;

-- categories
CREATE TABLE kategoria (
    ktunnus INT,
    knimi VARCHAR(30) NOT NULL,
    PRIMARY KEY (ktunnus),
    UNIQUE (knimi)
);

-- group exercise classes
-- duration in minutes
-- difficulty level 1-4
CREATE TABLE rltunti (
    rltunnus INT,
    rlnimi VARCHAR(30) NOT NULL,
    kuvaus VARCHAR(50),
    kesto INT,
    taso INT,
    ktunnus INT NOT NULL,
    PRIMARY KEY (rltunnus),
    FOREIGN KEY (ktunnus) REFERENCES kategoria,
    UNIQUE (rlnimi)
);

-- fitness instructors
CREATE TABLE ohjaaja (
    otunnus INT,
    Onimi VARCHAR(30) NOT NULL,
    syntymaaika DATE,
    PRIMARY KEY (otunnus),
    UNIQUE (onimi)
);

-- instructor can teach a group exercise class
CREATE TABLE voi_ohjata (
    rltunnus INT NOT NULL,
    otunnus INT NOT NULL,
    PRIMARY KEY (rltunnus, otunnus),
    FOREIGN KEY (rltunnus) REFERENCES rltunti,
    FOREIGN KEY (otunnus) REFERENCES ohjaaja
);

-- timetable
CREATE TABLE ljtunti (
    rltunnus INT,
    viikonpaiva VARCHAR(2),
    alkamisaika TIME,
    paattymisaika TIME, 
    maxosallistujalkm INT,
    otunnus INT,
    stunnus INT,
    PRIMARY KEY (rltunnus, viikonpaiva, alkamisaika),
    FOREIGN KEY (rltunnus) REFERENCES rltunti,
    FOREIGN KEY (otunnus) REFERENCES ohjaaja,
    FOREIGN KEY (stunnus) REFERENCES sali
);

-- rooms
CREATE TABLE sali (
    stunnus INT,
    snimi VARCHAR(30) NOT NULL,
    paikkalkm INT,
    PRIMARY KEY (stunnus),
    UNIQUE (snimi)
);