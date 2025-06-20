#!/bin/bash
cp src/view/*.fxml bin/view/
javac -d bin --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls,javafx.fxml $(find src/exception src/tri src/modele src/JDBC  src/view src/controleur -name "*.java" ! -name "affichageConsole.java")
java -cp bin:/usr/share/java/mariadb-java-client.jar  --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls,javafx.fxml view.LivreExpress
