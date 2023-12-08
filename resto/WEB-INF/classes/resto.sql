CREATE TABLE produit (
    idproduit SERIAL PRIMARY KEY,
    nomproduit VARCHAR(256) NOT NULL,
    prix decimal NOT NULL,
    prixIntermediaire decimal NOT NULL,
    prixRevient decimal NOT NULL
);


CREATE TABLE facture (
    idfacture int,
    idproduit INT REFERENCES produit(idproduit),
    idintermediaire int REFERENCES intermediaire(idintermediaire),
    quantite int, 
    statut int,
    dateFacture DATE NOT NULL
);


CREATE TABLE intermediaire (
    idintermediaire SERIAL PRIMARY KEY,
    nom VARCHAR(256) NOT NULL,
    mdp VARCHAR(256)not null,
    plafond decimal NOT NULL,
    seuil decimal NOT NULL,
    vola decimal NOT NULL
);


INSERT INTO produit (nomproduit, prix, prixIntermediaire, prixRevient)
VALUES
    ('pizza', 50.00, 45.00, 35.00),
    ('misao', 30.00, 25.00, 20.00),
    ('riz cantonnais', 20.00, 15.00, 10.00),
    ('jus gm', 60.00, 55.00, 50.00),
    ('soupe', 80.00, 75.00, 70.00),
    ('none', 0, 0,0),
    ('panini', 75.00, 70.00, 60.00);


INSERT INTO intermediaire (nom, mdp, plafond, seuil, vola)
VALUES
    ('miam', 'mdp1', 1000.00, 500.00, 0.05),
    ('miantsa', 'mdp2', 2000.00, 1000.00, 0.1),
    ('jean', 'mdp3', 1500.00, 750.00, 0.07);


INSERT INTO facture (idfacture,idproduit, idintermediaire, dateFacture)
VALUES
    (1,13, 7, '2023-01-15'),
    (1,18, 7, '2023-02-20'),
    (2,13, 8, '2023-03-25'),
    (2,14, 8, '2023-04-10');


SELECT
    SUM(p.prix) AS somme_factures,
    (SELECT SUM(prixRevient) FROM produit) AS cout_produits,
    (SELECT SUM(vola) FROM intermediaire) AS somme_intermediaires,
    SUM(p.prix) - (SELECT SUM(prixRevient) FROM produit) - (SELECT SUM(vola) FROM intermediaire) AS benefice
FROM facture f
JOIN produit p ON f.idproduit = p.idproduit;


-- Calcul de la somme totale des factures
SELECT
    SUM(p.prix) AS somme_factures,

    -- Calcul de la somme totale des prix de revient des produits vendus
    (SELECT SUM(prixRevient) FROM produit) AS cout_produits,

    -- Calcul de la somme totale de l'argent versé à chaque intermédiaire
    (SELECT SUM(vola) FROM intermediaire) AS somme_intermediaires,

    -- Calcul du bénéfice
    SUM(p.prix) - (SELECT SUM(prixRevient) FROM produit) - (SELECT SUM(vola) FROM intermediaire) AS benefice

FROM facture f
JOIN produit p ON f.idproduit = p.idproduit;



vola azony intermediaire entre deux daty
SELECT
    idintermediaire,
    SUM(  f.quantite*(p.prix - p.prixIntermediaire ) ) AS argent_acquis_pour_le_mois
FROM facture f
JOIN produit p ON f.idproduit = p.idproduit
WHERE f.idintermediaire = 7
    AND f.dateFacture >= '2023-12-05'::DATE
    AND f.dateFacture <= '2023-12-05'::DATE
    AND f.statut=0
GROUP BY f.idintermediaire;


bilan des produits vendu entre deux daty
SELECT
    p.nomproduit AS produit,
    SUM(f.quantite) AS quantite_vendue,
    SUM(f.quantite * p.prix) AS prix_vendu,
    SUM(f.quantite * p.prixRevient) AS prix_revient,
    SUM(f.quantite * p.prixIntermediaire) AS prix_intermediaire
FROM
    facture f
JOIN
    produit p ON f.idproduit = p.idproduit
WHERE
    f.dateFacture >= '2023-12-05' AND f.dateFacture <= '2023-12-05'
GROUP BY
    p.nomproduit;


benefice brut du resto entre deux daty
SELECT
    SUM(f.quantite * (p.prix - p.prixRevient)) AS benefice_total
FROM
    facture f
JOIN
    produit p ON f.idproduit = p.idproduit
WHERE
    f.dateFacture >= 'start_date' AND f.dateFacture <= 'end_date';


somme facture lie a un intermediaires
SELECT
    SUM(p.prix*f.quantite) AS argent_total
FROM facture f
JOIN produit p ON f.idproduit = p.idproduit
WHERE f.idintermediaire = 7
    AND f.dateFacture >= '2023-12-05'::DATE
    AND f.dateFacture <= '2023-12-05'::DATE
GROUP BY f.idintermediaire;

toute les facture d un intermediaires entre deux daty
select 
    idfacture
from facture
 where idintermediaire=7 
 and dateFacture >= '2023-12-05' 
 and datefacture<='2023-12-05'

uptdateeee
 UPDATE facture
SET quantite = COALESCE(quantite, 0);
