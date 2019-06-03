package GroovyBasics.Classes

// Classic ! Sauf qu'il n'y pas de modificateut d'accés,
// les méthodes sont public par defaut !

class Livre {

    private String titre

    Livre(String titre) {
        this.titre = titre
    }

    String getTitre() {
        return titre
    }
}
