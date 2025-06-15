Groupe:
BOISSEL Gauthier
CHER Naick 
AUDOR Etienne
HAMEAU Alexis

Information importante:
dans le package JDBC, dans le fichier connexionMysql il est possible que vous aillez besion de modifier 
la ligne 18:>> "jdbc:mariadb://"+Driver+":3306/"+nomBase,nomLogin,motDePasse;
par :
jdbc:mysql://"+Driver+":3306/"+nomBase,nomLogin,motDePasse;
cela est du a une utilisation de mysql chez moi car je l'avait télécharge au début de l'année.
Merci beaucoup.
Cordialement,
BOISSEL Gauthier
