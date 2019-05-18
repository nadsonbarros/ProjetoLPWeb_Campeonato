CREATE TABLE Campeonato(
id_campeonato INT NOT NULL auto_increment PRIMARY KEY,
ano_campeonato YEAR NOT NULL,
nome_campeonato VARCHAR (50) NOT NULL
);

INSERT INTO Campeonato (ano_campeonato, nome_campeonato) VALUES (2019, 'Premier League');