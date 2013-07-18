DROP TABLE IF EXISTS Tweet
;
DROP TABLE IF EXISTS Persona_Seguidores
;
DROP TABLE IF EXISTS Persona
;

CREATE TABLE Tweet
(
	idTweet INTEGER NOT NULL AUTO_INCREMENT,
	texto VARCHAR(140),
	idPersona INTEGER,
	PRIMARY KEY (idTweet),
	KEY (idPersona)
) 
;


CREATE TABLE Persona_Seguidores
(
	idPersonaSeguidor INTEGER NOT NULL AUTO_INCREMENT,
	idPersona INTEGER,
	idSeguidor INTEGER,
	PRIMARY KEY (idPersonaSeguidor),
	KEY (idPersona, idSeguidor)
) 
;


CREATE TABLE Persona
(
	idPersona INTEGER NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(250),
	apellido VARCHAR(250),
	pass VARCHAR(8),
	edad INTEGER,
	usuario VARCHAR(10),
	PRIMARY KEY (idPersona)
) 
;

ALTER TABLE Tweet ADD CONSTRAINT FK_Tweet_Persona 
	FOREIGN KEY (idPersona) REFERENCES Persona (idPersona)
;

ALTER TABLE Persona_Seguidores ADD CONSTRAINT FK_Persona_Seguidor_Persona 
	FOREIGN KEY (idPersona, idSeguidor) REFERENCES Persona (idPersona)
;

ALTER TABLE Persona_Seguidores ADD CONSTRAINT FK_Persona_Seguidor_Persona 
	FOREIGN KEY (idPersona, idPersona) REFERENCES Persona (idPersona)
;


insert into persona values (1,'Cosme', 'Fulanito',123, 42);
insert into persona values (2,'Ivana', 'Leikin', 345, 22);
insert into persona values (3,'Julieta', 'Ramon', 567, 23);