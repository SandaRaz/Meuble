CREATE OR REPLACE VIEW MatiereStyleComplet AS
SELECT ms.id,s.id as idstyle,s.nomstyle,m.id as idmatiere,m.nommatiere
FROM Matiere as m
JOIN MatiereStyle as ms ON ms.idmatiere = m.id
JOIN Style as s ON ms.idstyle = s.id;

CREATE OR REPLACE VIEW ModeleComplet AS 
SELECT m.id,m.nommodele,c.id as idcategorie,c.nom as nomcategorie,s.id as idstyle,s.nomstyle,v.id as idvolume,v.nomvolume,v.echelle,m.statut
FROM Modele as m
JOIN Categorie as c ON m.idcategorie = c.id
JOIN Style as s ON m.idstyle = s.id
JOIN Volume as v ON m.idvolume = v.id;

----------------------------------------------------------------

CREATE OR REPLACE VIEW QuantiteMatiereDetail AS
SELECT qmm.id,qmm.idmodele,m.id as idmatiere,m.nommatiere,m.prix,qmm.quantite
FROM Matiere as m
JOIN QuantiteMatiereModele as qmm ON qmm.idmatiere = m.id;

CREATE OR REPLACE VIEW MvtStockMatiereDetail AS
SELECT msm.id,m.id as idmatiere,m.nommatiere,msm.entrer,msm.sortie,msm.datemvt
FROM Matiere as m 
JOIN MvtStockMatiere as msm
ON msm.idmatiere = m.id;

CREATE OR REPLACE VIEW ModelePosteComplet AS
SELECT pe.id, pe.idModele,mc.nommodele,mc.echelle as echelleVolume,pe.idposte,p.titre,p.salaireHeure,pe.nombre,pe.minheuretravail
FROM ModeleComplet as mc 
JOIN ModeleEmployes as pe 
ON pe.idModele = mc.id
JOIN Poste as p
ON pe.idPoste = p.id;

CREATE OR REPLACE VIEW TotaleSalaireModele AS
SELECT idmodele,SUM(echelleVolume*minheuretravail*salaireHeure*nombre) AS totaleSalaire 
FROM ModelePosteComplet
GROUP BY idmodele;

CREATE OR REPLACE VIEW PrixMatiereModele AS
SELECT qmm.idModele,SUM((qmm.quantite*mt.prix)) as prixMatieres
FROM Modele as md JOIN QuantiteMatiereModele as qmm ON qmm.idModele=md.id
JOIN Matiere as mt ON qmm.idmatiere = mt.id GROUP BY qmm.idModele;

CREATE or replace view ModeleAvecPrix AS 
SELECT m.id,m.nommodele,pm.prixMatieres,tsm.totaleSalaire,m.statut
FROM ModeleComplet as m
JOIN PrixMatiereModele as pm 
ON pm.idmodele = m.id
JOIN TotaleSalaireModele as tsm
ON tsm.idmodele = m.id;

CREATE OR REPLACE VIEW LastHistoriqueEmbauche AS
SELECT h.*
FROM HistoriqueEmbauche as h
JOIN (SELECT MAX(id) as id,idEmploye FROM historiqueEmbauche GROUP BY idEmploye) AS maxHE
ON maxHE.id = h.id;

CREATE OR REPLACE VIEW DateEmbauche AS
SELECT h.id,h.idEmploye,h.dateDebut as dateEmbauche
FROM HistoriqueEmbauche as h
JOIN (SELECT MIN(id) as id,idEmploye FROM historiqueEmbauche GROUP BY idEmploye) AS de
ON de.id = h.id;


CREATE OR REPLACE VIEW InfosEmploye AS 
SELECT he.id,he.idEmploye,e.Nom,he.idPoste,p.titre,he.tauxHoraire,de.dateEmbauche,he.dateDebut as dateDebutPoste
FROM Poste as p
JOIN LastHistoriqueEmbauche as he
ON he.idPoste = p.id
JOIN Employe as e
ON he.idEmploye = e.id
JOIN DateEmbauche as de
ON he.idEmploye = de.idEmploye;

CREATE OR REPLACE VIEW ClientComplet AS
SELECT c.id,c.nom,c.datenaissance,c.idGenre,g.type as genre
FROM Genre as g
JOIN Client as c
ON c.idGenre = g.id;
------------------------------------------------------------------------
SELECT id,idEmploye, (now() - dateDebut) as dureeTravail
FROM HistoriqueEmbauche
WHERE id = (SELECT MIN(id) FROM HistoriqueEmbauche WHERE idEmploye = );
------------------------------------------------------------------------

CREATE OR REPLACE VIEW StatistiqueGenre AS
SELECT ac.idModele,g.id as idGenre,SUM(ac.quantite) as quantite
FROM Genre as g
JOIN Client as c
ON c.idGenre = g.id
JOIN AchatClient as ac
ON ac.idClient = c.id
JOIN Modele as m
ON ac.idModele = m.id
GROUP BY idModele,g.id;

CREATE OR REPLACE VIEW StatistiqueGenreDetail AS
SELECT sg.idModele,m.nommodele,sg.idGenre,g.type,quantite
FROM Modele as m
JOIN StatistiqueGenre as sg
ON sg.idModele = m.id
JOIN Genre as g
ON sg.idGenre = g.id;

CREATE OR REPLACE VIEW StockProduitDetail AS
SELECT sp.id,sp.idmodele,m.nommodele,sp.entrer,sp.sortie,sp.prixrevient,sp.prixvente,sp.datemvt,m.statut
FROM Modele as m
JOIN StockProduit as sp
ON sp.idModele = m.id;

CREATE OR REPLACE VIEW StockEtat AS
SELECT idmodele,Sum(entrer) as entrer,Sum(sortie) as sortie, Sum(entrer - sortie) as reste, sum(entrer * prixvente) as valeur
FROM StockProduit 
GROUP BY idmodele;

CREATE OR REPLACE VIEW DetailModele AS
SELECT
	md.id,
	md.nommodele,
	c.id as idcategorie,
	c.nom as nomcategorie,
	s.id as idstyle,
	s.nomstyle,
	v.id as idvolume,
	v.nomvolume,
	v.echelle,
	md.statut,
	map.prixMatieres,
	map.totaleSalaire,
	(map.prixMatieres + map.totaleSalaire) as prixDeRevient, 
	((map.prixMatieres + map.totaleSalaire) + (((map.prixMatieres + map.totaleSalaire) * mg.pourcentage) / 100)) as prixDeVente,
	(((map.prixMatieres + map.totaleSalaire) * mg.pourcentage) / 100) as benefice
FROM Marge as mg
JOIN ModeleAvecPrix as map
ON (map.prixMatieres + map.totaleSalaire) >= mg.prixmin and (map.prixMatieres + map.totaleSalaire) < mg.prixmax
JOIN Modele as md ON map.id = md.id
JOIN Categorie as c ON md.idcategorie = c.id
JOIN Style as s ON md.idstyle = s.id
JOIN Volume as v ON md.idvolume = v.id
WHERE md.statut > 0;