CREATE DATABASE MeubleData;
\c meubledata;

CREATE TABLE Categorie (
    id SERIAL PRIMARY KEY NOT NULL,
    nom VARCHAR(50)
);

TRUNCATE TABLE Categorie CASCADE;
INSERT INTO Categorie(id,nom) VALUES(7001, 'Chaise');
INSERT INTO Categorie(id,nom) VALUES(7002, 'Canape');
INSERT INTO Categorie(id,nom) VALUES(7003, 'Lit');
INSERT INTO Categorie(id,nom) VALUES(7004, 'Table');

CREATE TABLE Matiere (
    id SERIAL PRIMARY KEY NOT NULL,
    nomMatiere VARCHAR(50),
    prix DOUBLE PRECISION
);

TRUNCATE TABLE Matiere CASCADE;
INSERT INTO Matiere(id,nomMatiere,prix) VALUES(7001,'bois',1000);
INSERT INTO Matiere(id,nomMatiere,prix) VALUES(7002,'plastique',500);
INSERT INTO Matiere(id,nomMatiere,prix) VALUES(7003,'verre',2000);
INSERT INTO Matiere(id,nomMatiere,prix) VALUES(7004,'metal',1500);
INSERT INTO Matiere(id,nomMatiere,prix) VALUES(7005,'tissu',1200);
INSERT INTO Matiere(id,nomMatiere,prix) VALUES(7006,'cuir',3000);
INSERT INTO Matiere(id,nomMatiere,prix) VALUES(7007,'eponge',2000);
INSERT INTO Matiere(id,nomMatiere,prix) VALUES(7008,'perle',1500);
INSERT INTO Matiere(id,nomMatiere,prix) VALUES(7009,'chaine',2500);

CREATE TABLE Style (
    id SERIAL PRIMARY KEY NOT NULL,
    nomStyle VARCHAR(50)
);

TRUNCATE TABLE Style CASCADE;
INSERT INTO Style(id,nomStyle) VALUES(7001,'boheme');
INSERT INTO Style(id,nomStyle) VALUES(7002,'scandinave');
INSERT INTO Style(id,nomStyle) VALUES(7003,'royal');
INSERT INTO Style(id,nomStyle) VALUES(7004,'contemporaine');

CREATE TABLE MatiereStyle(
    id SERIAL PRIMARY KEY NOT NULL,
    idStyle INT,
    idMatiere INT
);
ALTER TABLE MatiereStyle ADD FOREIGN KEY (idStyle) REFERENCES Style(id);
ALTER TABLE MatiereStyle ADD FOREIGN KEY (idMatiere) REFERENCES Matiere(id);

TRUNCATE TABLE MatiereStyle CASCADE;

INSERT INTO MatiereStyle(id,idstyle,idmatiere) VALUES(default, 7001, 7001);
INSERT INTO MatiereStyle(id,idstyle,idmatiere) VALUES(default, 7001, 7006);
INSERT INTO MatiereStyle(id,idstyle,idmatiere) VALUES(default, 7001, 7007);

INSERT INTO MatiereStyle(id,idstyle,idmatiere) VALUES(default, 7002, 7001);
INSERT INTO MatiereStyle(id,idstyle,idmatiere) VALUES(default, 7002, 7005);
INSERT INTO MatiereStyle(id,idstyle,idmatiere) VALUES(default, 7002, 7007);

INSERT INTO MatiereStyle(id,idstyle,idmatiere) VALUES(default, 7003, 7004);
INSERT INTO MatiereStyle(id,idstyle,idmatiere) VALUES(default, 7003, 7006);
INSERT INTO MatiereStyle(id,idstyle,idmatiere) VALUES(default, 7003, 7007);
INSERT INTO MatiereStyle(id,idstyle,idmatiere) VALUES(default, 7003, 7009);

INSERT INTO MatiereStyle(id,idstyle,idmatiere) VALUES(default, 7004, 7001);
INSERT INTO MatiereStyle(id,idstyle,idmatiere) VALUES(default, 7004, 7004);
INSERT INTO MatiereStyle(id,idstyle,idmatiere) VALUES(default, 7004, 7005);
INSERT INTO MatiereStyle(id,idstyle,idmatiere) VALUES(default, 7004, 7007);

CREATE TABLE Volume(
    id SERIAL PRIMARY KEY NOT NULL,
    nomVolume VARCHAR(15),
    echelle int
);

TRUNCATE TABLE Volume CASCADE;

INSERT INTO Volume(id,nomVolume,echelle) VALUES(7001,'Small', 1);
INSERT INTO Volume(id,nomVolume,echelle) VALUES(7002,'Medium', 2);
INSERT INTO Volume(id,nomVolume,echelle) VALUES(7003,'Large', 3);

CREATE TABLE Modele(
    id VARCHAR(7) PRIMARY KEY NOT NULL,
    nomModele VARCHAR(30),
    idCategorie INT,
    idStyle INT,
    idVolume INT,
    statut INT
);
CREATE SEQUENCE modele_sequence START 1;

ALTER TABLE Modele ADD FOREIGN KEY (idCategorie) REFERENCES Categorie(id);
ALTER TABLE Modele ADD FOREIGN KEY (idStyle) REFERENCES Style(id);
ALTER TABLE Modele ADD FOREIGN KEY (idVolume) REFERENCES Volume(id);

TRUNCATE TABLE Modele CASCADE;

INSERT INTO Modele(id,nomModele,idCategorie,idStyle,idVolume,statut) VALUES('MOD7001','Bohemian S Chair 1',7001,7001,7001,1);
INSERT INTO Modele(id,nomModele,idCategorie,idStyle,idVolume,statut) VALUES('MOD7002','Bohemian M Chair 1',7001,7001,7002,1);
INSERT INTO Modele(id,nomModele,idCategorie,idStyle,idVolume,statut) VALUES('MOD7003','Medium Royals Sofa',7002,7003,7002,1);
INSERT INTO Modele(id,nomModele,idCategorie,idStyle,idVolume,statut) VALUES('MOD7004','Lit Scandinave L-1',7003,7002,7003,1);

CREATE TABLE QuantiteMatiereModele(
    id SERIAL PRIMARY KEY NOT NULL,
    idModele VARCHAR(7),
    idMatiere INT,
    quantite double precision
);
ALTER TABLE QuantiteMatiereModele ADD FOREIGN KEY (idModele) REFERENCES Modele(id);
ALTER TABLE QuantiteMatiereModele ADD FOREIGN KEY (idMatiere) REFERENCES Matiere(id);

TRUNCATE TABLE QuantiteMatiereModele CASCADE;

INSERT INTO QuantiteMatiereModele(id,idModele,idMatiere,quantite) VALUES(default, 'MOD7001', 7001, 2);
INSERT INTO QuantiteMatiereModele(id,idModele,idMatiere,quantite) VALUES(default, 'MOD7001', 7006, 2);
INSERT INTO QuantiteMatiereModele(id,idModele,idMatiere,quantite) VALUES(default, 'MOD7001', 7007, 1);

INSERT INTO QuantiteMatiereModele(id,idModele,idMatiere,quantite) VALUES(default, 'MOD7002', 7001, 3);
INSERT INTO QuantiteMatiereModele(id,idModele,idMatiere,quantite) VALUES(default, 'MOD7002', 7006, 4);
INSERT INTO QuantiteMatiereModele(id,idModele,idMatiere,quantite) VALUES(default, 'MOD7002', 7007, 2);

INSERT INTO QuantiteMatiereModele(id,idModele,idMatiere,quantite) VALUES(default, 'MOD7003', 7004, 3);
INSERT INTO QuantiteMatiereModele(id,idModele,idMatiere,quantite) VALUES(default, 'MOD7003', 7005, 2);
INSERT INTO QuantiteMatiereModele(id,idModele,idMatiere,quantite) VALUES(default, 'MOD7003', 7007, 2);
INSERT INTO QuantiteMatiereModele(id,idModele,idMatiere,quantite) VALUES(default, 'MOD7003', 7009, 3);

INSERT INTO QuantiteMatiereModele(id,idModele,idMatiere,quantite) VALUES(default, 'MOD7004', 7001, 5);
INSERT INTO QuantiteMatiereModele(id,idModele,idMatiere,quantite) VALUES(default, 'MOD7004', 7005, 3);
INSERT INTO QuantiteMatiereModele(id,idModele,idMatiere,quantite) VALUES(default, 'MOD7004', 7007, 2);

CREATE TABLE MvtStockMatiere(
    id SERIAL PRIMARY KEY NOT NULL,
    idMatiere INT,
    entrer double precision,  
    sortie double precision,
    dateMvt timestamp
);
ALTER TABLE MvtStockMatiere ADD FOREIGN KEY (idMatiere) REFERENCES Matiere(id);

INSERT INTO MvtStockMatiere(id,idMatiere,entre,sortie,datemvt) VALUES();

CREATE TABLE Poste(
    id SERIAL PRIMARY KEY NOT NULL,
    titre varchar(30),
    salaireHeure double precision,
    niveauTauxHoraire int,
    hierarchie int,
    dureeMin int,
    dureeMax int
);

TRUNCATE TABLE Poste CASCADE;

INSERT INTO Poste(id,titre,salaireHeure, niveauTauxHoraire, hierarchie,dureeMin,dureeMax) VALUES(7001,'Ouvrier',1500,1,1,0,2);
INSERT INTO Poste(id,titre,salaireHeure, niveauTauxHoraire, hierarchie,dureeMin,dureeMax) VALUES(7002,'Fabriquant Senior',2000, 2,2,2,5);
INSERT INTO Poste(id,titre,salaireHeure, niveauTauxHoraire, hierarchie,dureeMin,dureeMax) VALUES(7003,'Fabriquant Expert',3000,3,3,5,100);


CREATE TABLE Employe(
    id VARCHAR(7) PRIMARY KEY NOT NULL,
    nom varchar(50),
    dateNaissance date,
    idPosteActuelle int
);
CREATE SEQUENCE employe_sequence START 1;
ALTER TABLE Employe ADD FOREIGN KEY(idPosteActuelle) REFERENCES Poste(id);


CREATE TABLE HistoriqueEmbauche(
    id SERIAL PRIMARY KEY NOT NULL,
    idEmploye VARCHAR(7), 
    idPoste int,
    tauxHoraire double precision,
    dateDebut date
);
ALTER TABLE HistoriqueEmbauche ADD FOREIGN KEY(idEmploye) REFERENCES Employe(id);
ALTER TABLE HistoriqueEmbauche ADD FOREIGN KEY(idPoste) REFERENCES Poste(id);

CREATE TABLE ModeleEmployes(
    id SERIAL PRIMARY KEY NOT NULL,
    idModele varchar,
    idPoste int,
    nombre int,
    minHeureTravail double precision
);
ALTER TABLE ModeleEmployes ADD FOREIGN KEY(idModele) REFERENCES Modele(id);
ALTER TABLE ModeleEmployes ADD FOREIGN KEY(idPoste) REFERENCES Poste(id);

TRUNCATE TABLE ModeleEmployes CASCADE;

INSERT INTO ModeleEmployes(id,idModele,idPoste,nombre,minHeureTravail) VALUES(default, 'MOD7001',7001,0,1);
INSERT INTO ModeleEmployes(id,idModele,idPoste,nombre,minHeureTravail) VALUES(default, 'MOD7001',7002,2,1);
INSERT INTO ModeleEmployes(id,idModele,idPoste,nombre,minHeureTravail) VALUES(default, 'MOD7001',7003,1,1);

INSERT INTO ModeleEmployes(id,idModele,idPoste,nombre,minHeureTravail) VALUES(default, 'MOD7002',7001,0,1);
INSERT INTO ModeleEmployes(id,idModele,idPoste,nombre,minHeureTravail) VALUES(default, 'MOD7002',7002,2,1);
INSERT INTO ModeleEmployes(id,idModele,idPoste,nombre,minHeureTravail) VALUES(default, 'MOD7002',7003,1,1);

INSERT INTO ModeleEmployes(id,idModele,idPoste,nombre,minHeureTravail) VALUES(default, 'MOD7003',7001,1,1);
INSERT INTO ModeleEmployes(id,idModele,idPoste,nombre,minHeureTravail) VALUES(default, 'MOD7003',7002,2,1);
INSERT INTO ModeleEmployes(id,idModele,idPoste,nombre,minHeureTravail) VALUES(default, 'MOD7003',7003,1,1);

INSERT INTO ModeleEmployes(id,idModele,idPoste,nombre,minHeureTravail) VALUES(default, 'MOD7004',7001,1,1);
INSERT INTO ModeleEmployes(id,idModele,idPoste,nombre,minHeureTravail) VALUES(default, 'MOD7004',7002,2,1);
INSERT INTO ModeleEmployes(id,idModele,idPoste,nombre,minHeureTravail) VALUES(default, 'MOD7004',7003,2,1);

CREATE TABLE StockProduit(
    id SERIAL PRIMARY KEY NOT NULL,
    idModele VARCHAR(7),
    entrer double precision,
    sortie double precision,
    prixRevient double precision,
    prixVente double precision,
    dateMvt timestamp
);
ALTER TABLE StockProduit ADD FOREIGN KEY(idModele) REFERENCES Modele(id);

CREATE TABLE Marge(
    id SERIAL PRIMARY KEY NOT NULL,
    prixMin double precision,
    prixMax double precision,
    pourcentage double precision
);

TRUNCATE TABLE Marge CASCADE;

INSERT INTO Marge(id,prixMin,prixMax,pourcentage) VALUES(7001,0,100000,10);
INSERT INTO Marge(id,prixMin,prixMax,pourcentage) VALUES(7002,100001,1000000,7);
INSERT INTO Marge(id,prixMin,prixMax,pourcentage) VALUES(7003,1000001,999999999,5);

------------------------

CREATE TABLE Genre(
    id SERIAL PRIMARY KEY NOT NULL,
    type varchar(20)
);

INSERT INTO Genre(id,type) VALUES(7001,'Masculin');
INSERT INTO Genre(id,type) VALUES(7002,'Feminin');

CREATE TABLE Client(
    id varchar(7) PRIMARY KEY NOT NULL,
    nom varchar(50),
    dateNaissance date,
    idGenre int
);
CREATE SEQUENCE client_sequence START 1;

ALTER TABLE Client ADD FOREIGN KEY(idGenre) REFERENCES Genre(id);

INSERT INTO Client(id,nom,dateNaissance,idGenre) VALUES('CLI7001','Jean','2000-01-01',7001);
INSERT INTO Client(id,nom,dateNaissance,idGenre) VALUES('CLI7002','Rasoa','2005-01-01',7002);


CREATE TABLE AchatClient(
    id SERIAL PRIMARY KEY NOT NULL,
    idClient varchar(7),
    idModele varchar(7),
    quantite double precision,
    dateAchat date
);

ALTER TABLE AchatClient ADD FOREIGN KEY(idClient) REFERENCES Client(id);
ALTER TABLE AchatClient ADD FOREIGN KEY(idModele) REFERENCES Modele(id);