import java.util.Map;

public class AgenceTest {

    public static void main(String[] args) {
        try {
            // Création d'une agence
            Agence agence = new Agence("AgenceLocation");

            // Ajout de quelques voitures
            agence.ajoutVoiture(new Voiture(1, "BMW M5 competition", 50.0f));
            agence.ajoutVoiture(new Voiture(2, "Mercedes CLass G", 45.0f));
            agence.ajoutVoiture(new Voiture(3, "Ford", 60.0f));

            // Affichage des voitures disponibles
            System.out.println("Voitures disponibles :");
            agence.vs.affiche();

            // Création de clients
            Client client1 = new Client(101, "Sellami", "Baha");
            Client client2 = new Client(102, "Joudi", "Mehdi");

            // Location de voitures par les clients
            agence.loueClientVoiture(client1, agence.vs.getVoitures().get(0));
            agence.loueClientVoiture(client2, agence.vs.getVoitures().get(1));

            // Affichage des clients et de leurs voitures louées
            System.out.println("\nClients et leurs voitures louées :");
            agence.afficheLesClientsEtLeursListesVoitures();

            // Retour de voiture par un client
            agence.retourClientVoiture(client1, agence.vs.getVoitures().get(0));

            // Affichage des clients et de leurs voitures louées après le retour
            System.out.println("\nClients et leurs voitures louées après retour :");
            agence.afficheLesClientsEtLeursListesVoitures();

            // Tri des clients par code croissant
            System.out.println("\nClients triés par code croissant :");
            for (Map.Entry<Client, ListVoitures> entry : agence.triCodeCroissant().entrySet()) {
                System.out.println("Client : " + entry.getKey());
                entry.getValue().affiche();
                System.out.println("---------------------");
            }

            // Tri des clients par nom croissant
            System.out.println("\nClients triés par nom croissant :");
            for (Map.Entry<Client, ListVoitures> entry : agence.triNomCroissant().entrySet()) {
                System.out.println("Client : " + entry.getKey());
                entry.getValue().affiche();
                System.out.println("---------------------");
            }

        } catch (VoitureException ve) {
            System.err.println("Erreur : " + ve.getMessage());
        }
    }
}
