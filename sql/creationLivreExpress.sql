
/* grant ALL on DBaudor.* to 'audor'@'%' with grant option;
grant ALL on DBaudor.* to 'boissel'@'%' with grant option;
grant ALL on DBaudor.* to 'cher'@'%' with grant option;
grant ALL on DBaudor.* to 'hameau'@'%' with grant option;
 */

CREATE TABLE ADMINISTRATEUR (
  PRIMARY KEY (idadmin),
  idadmin      int NOT NULL,
  nomadmin     varchar(50),
  prenomadmin  varchar(30),
  mdpadmin     varchar(50)
);

CREATE TABLE AUTEUR (
  PRIMARY KEY (idauteur),
  idauteur   varchar(11) NOT NULL,
  nomauteur  varchar(100),
  anneenais  int,
  anneedeces int
);

CREATE TABLE CLASSIFICATION (
  PRIMARY KEY (iddewey),
  iddewey  varchar(3) NOT NULL,
  nomclass varchar(50)
);

CREATE TABLE CLIENT (
  PRIMARY KEY (idcli),
  idcli      int NOT NULL,
  nomcli     varchar(50),
  prenomcli  varchar(30),
  adressecli varchar(100),
  codepostal varchar(5),
  villecli   varchar(100),
  mdpcli     varchar(50)
);

CREATE TABLE COMMANDE (
  PRIMARY KEY (numcom),
  numcom  int NOT NULL,
  datecom date,
  enligne char(1),
  livraison char(1),
  idcli   int NOT NULL,
  idmag   int NOT NULL
);

CREATE TABLE DETAILCOMMANDE (
  PRIMARY KEY (numcom, numlig),
  numcom    int NOT NULL,
  numlig    int NOT NULL,
  qte       int,
  prixvente decimal(6,2),
  isbn      varchar(13) NOT NULL
);

CREATE TABLE ECRIRE (
  PRIMARY KEY (isbn, idauteur),
  isbn     varchar(13) NOT NULL,
  idauteur varchar(11) NOT NULL
);

CREATE TABLE EDITER (
  PRIMARY KEY (isbn, idedit),
  isbn   varchar(13) NOT NULL,
  idedit int NOT NULL
);

CREATE TABLE EDITEUR (
  PRIMARY KEY (idedit),
  idedit  int NOT NULL,
  nomedit varchar(100)
);

CREATE TABLE LIVRE (
  PRIMARY KEY (isbn),
  isbn      varchar(13) NOT NULL,
  titre     varchar(200),
  nbpages   int,
  datepubli int,
  prix      decimal(6,2)
);

CREATE TABLE MAGASIN (
  PRIMARY KEY (idmag),
  idmag    int NOT NULL,
  nommag   VARCHAR(42),
  villemag VARCHAR(42)
);

CREATE TABLE POSSEDER (
  PRIMARY KEY (idmag, isbn),
  idmag int NOT NULL,
  isbn  varchar(13) NOT NULL,
  qte   int
);

CREATE TABLE THEMES (
  PRIMARY KEY (isbn, iddewey),
  isbn    varchar(13) NOT NULL,
  iddewey varchar(3) NOT NULL
);

CREATE TABLE VENDEUR (
  PRIMARY KEY (idven),
  idven      int NOT NULL,
  nomven     varchar(50),
  prenomven  varchar(30),
  mdpven     varchar(50),
  idmag      int NOT NULL
);

ALTER TABLE COMMANDE ADD FOREIGN KEY (idmag) REFERENCES MAGASIN (idmag);
ALTER TABLE COMMANDE ADD FOREIGN KEY (idcli) REFERENCES CLIENT (idcli);

ALTER TABLE DETAILCOMMANDE ADD FOREIGN KEY (isbn) REFERENCES LIVRE (isbn);
ALTER TABLE DETAILCOMMANDE ADD FOREIGN KEY (numcom) REFERENCES COMMANDE (numcom);

ALTER TABLE ECRIRE ADD FOREIGN KEY (idauteur) REFERENCES AUTEUR (idauteur);
ALTER TABLE ECRIRE ADD FOREIGN KEY (isbn) REFERENCES LIVRE (isbn);

ALTER TABLE EDITER ADD FOREIGN KEY (idedit) REFERENCES EDITEUR (idedit);
ALTER TABLE EDITER ADD FOREIGN KEY (isbn) REFERENCES LIVRE (isbn);

ALTER TABLE POSSEDER ADD FOREIGN KEY (isbn) REFERENCES LIVRE (isbn);
ALTER TABLE POSSEDER ADD FOREIGN KEY (idmag) REFERENCES MAGASIN (idmag);

ALTER TABLE THEMES ADD FOREIGN KEY (iddewey) REFERENCES CLASSIFICATION (iddewey);
ALTER TABLE THEMES ADD FOREIGN KEY (isbn) REFERENCES LIVRE (isbn);

ALTER TABLE VENDEUR ADD FOREIGN KEY (idmag) REFERENCES MAGASIN (idmag);


