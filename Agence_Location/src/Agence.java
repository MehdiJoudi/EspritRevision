import java.util.*;

public class Agence {
    private String nom;
    ListVoitures vs;
    private Map<Client, ListVoitures> clientVoitureLoue;

    public Agence(String nom) {
        this.nom = nom;
        this.vs = new ListVoitures();
        this.clientVoitureLoue = new HashMap<>();
    }

    public void ajoutVoiture(Voiture v) throws VoitureException {
        vs.ajoutVoiture(v);
    }

    public void suppVoiture(Voiture v) throws VoitureException {
        vs.supprimeVoiture(v);
        // Supprimer la voiture de la liste des voitures louées par les clients
        for (ListVoitures listVoitures : clientVoitureLoue.values()) {
            listVoitures.getVoitures().remove(v);
        }
    }

    public void loueClientVoiture(Client cl, Voiture v) throws VoitureException {
        if (!clientVoitureLoue.containsKey(cl)) {
            clientVoitureLoue.put(cl, new ListVoitures());
        }
        ListVoitures listVoitures = clientVoitureLoue.get(cl);
        listVoitures.ajoutVoiture(v);
    }

    public void retourClientVoiture(Client cl, Voiture v) throws VoitureException {
        if (clientVoitureLoue.containsKey(cl)) {
            ListVoitures listVoitures = clientVoitureLoue.get(cl);
            listVoitures.supprimeVoiture(v);
        } else {
            throw new VoitureException("Le client n'a pas loué de voiture.");
        }
    }

    public List<Voiture> selectVoitureSelonCritere(Critere c) {
        List<Voiture> result = new ArrayList<>();
        for (Voiture v : vs.getVoitures()) {
            if (c.estSatisfaitPar(v)) {
                result.add(v);
            }
        }
        return result;
    }

    public Set<Client> ensembleClientsLoueurs() {
        return clientVoitureLoue.keySet();
    }

    public Collection<ListVoitures> collectionVoituresLouees() {
        return clientVoitureLoue.values();
    }

    public void afficheLesClientsEtLeursListesVoitures() {
        for (Map.Entry<Client, ListVoitures> entry : clientVoitureLoue.entrySet()) {
            System.out.println("Client : " + entry.getKey());
            entry.getValue().affiche();
            System.out.println("---------------------");
        }
    }

    public Map<Client, ListVoitures> triCodeCroissant() {
        Map<Client, ListVoitures> sortedMap = new TreeMap<>(Comparator.comparing(Client::getCode));
        sortedMap.putAll(clientVoitureLoue);
        return sortedMap;
    }

    public Map<Client, ListVoitures> triNomCroissant() {
        Map<Client, ListVoitures> sortedMap = new TreeMap<>(Comparator.comparing(Client::getNom));
        sortedMap.putAll(clientVoitureLoue);
        return sortedMap;
    }
}
