CREATE DATABASE koestler6518FinalProject;
USE koestler6518FinalProject;
-- DROP DATABASE koestler6518FinalProject;

CREATE TABLE Player (
	PlayerID INTEGER AUTO_INCREMENT,
    Name VARCHAR(50) CHARACTER SET ucs2 NOT NULL,
    Position VARCHAR(8) CHARACTER SET ucs2 NOT NULL,
    Age INTEGER NOT NULL,
    Games INTEGER NOT NULL,
    AtBats INTEGER NOT NULL,
    Runs INTEGER NOT NULL,
    Hits INTEGER NOT NULL,
    Doubles INTEGER NOT NULL,
    Triples INTEGER NOT NULL,
    HomeRuns INTEGER NOT NULL,
    RBI INTEGER NOT NULL,
    StolenBases INTEGER NOT NULL,
    CaughtStealing INTEGER NOT NULL,
    Walks INTEGER NOT NULL,
    StrikeOuts INTEGER NOT NULL,
    SacrificeHits INTEGER NOT NULL,
    SacrificeFlies INTEGER NOT NULL,
    HitByPitch INTEGER NOT NULL,
    Average NUMERIC(4,3) NOT NULL,
    OnBase NUMERIC(4,3) NOT NULL,
    Slugging NUMERIC(4,3) NOT NULL,
    OPS NUMERIC(4,3) NOT NULL,
    PRIMARY KEY(PlayerID)
);

CREATE TABLE Team (
	TeamID INTEGER NOT NULL,
    Name VARCHAR(15) CHARACTER SET ucs2 NOT NULL,
    City VARCHAR(25) CHARACTER SET ucs2 NOT NULL,
    State VARCHAR(25) CHARACTER SET ucs2,
    League VARCHAR(8) CHARACTER SET ucs2 NOT NULL,
    Capacity INTEGER NOT NULL,
    Attendance INTEGER NOT NULL,
    TicketPrice INTEGER NOT NULL,
    Championships INTEGER NOT NULL,
    PRIMARY KEY(TeamID)
);

CREATE TABLE Season (
	Year INTEGER NOT NULL,
    wsChamp VARCHAR(30) CHARACTER SET ucs2,
    alChamp VARCHAR(30) CHARACTER SET ucs2,
    nlChamp VARCHAR(30) CHARACTER SET ucs2,
    alMVP VARCHAR(50) CHARACTER SET ucs2,
    nlMVP VARCHAR(50) CHARACTER SET ucs2,
    alROTY VARCHAR(50) CHARACTER SET ucs2,
    nlROTY VARCHAR(50) CHARACTER SET ucs2,
    PRIMARY KEY(Year)
);

CREATE TABLE PLAYS_FOR (
	PlayerID INTEGER NOT NULL,
    TeamID INTEGER NOT NULL,
    Salary INTEGER,
	ContractBegin INTEGER,
    ContractEnd INTEGER,
    CONSTRAINT pf_player_id FOREIGN KEY (PlayerID) REFERENCES Player(PlayerID),
    CONSTRAINT pf_team_id FOREIGN KEY (TeamID) REFERENCES Team(TeamID)
);

CREATE TABLE PLAYS_DURING (
	TeamID INTEGER NOT NULL,
    Year INTEGER NOT NULL,
    Wins INTEGER,
    Losses INTEGER,
    CONSTRAINT pd_team_id FOREIGN KEY (TeamID) REFERENCES Team(TeamID),
    CONSTRAINT pd_year FOREIGN KEY (Year) REFERENCES Season(Year)
);