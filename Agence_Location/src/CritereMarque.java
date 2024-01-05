public class CritereMarque implements Critere {
    private String marque;

    public CritereMarque(String marque) {
        this.marque = marque;
    }

    public boolean estSatisfaitPar(Voiture v) {
        // Vérifie si la marque de la voiture correspond au critère
        return v.getMarque().equalsIgnoreCase(marque);
    }
}
