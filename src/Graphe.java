import java.util.ArrayList;

// Définition de la classe Graphe
public class Graphe {
    // Déclaration des variables membres
    private ArrayList<ArrayList<Integer>> graphe; // une liste de listes d'entiers pour stocker les sommets et leurs voisins
    private ArrayList<ArrayList<Integer>> couples = new ArrayList<ArrayList<Integer>>(); // une liste de listes d'entiers pour stocker les couples de sommets reliés par un arc
    private int arc = 0; // un compteur pour le nombre d'arcs dans le graphe
    private int sommet; // le nombre de sommets dans le graphe

    // Constructeur de la classe Graphe
    public Graphe(int sommet) {
        this.sommet = sommet;
        // Initialisation de la liste graphe avec sommet listes vides
        graphe = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < sommet; i++) {
            graphe.add(new ArrayList<Integer>());
        }
    }

    // Méthode pour récupérer la liste graphe
    public ArrayList<ArrayList<Integer>> getGraphe() {
        return graphe;
    }

    // Méthode pour récupérer la liste couples
    public ArrayList<ArrayList<Integer>> getCouple() {
        return this.couples;
    }
    // Méthode pour ajouter un arc entre deux sommets
    void ajouterArc(int a, int b) {
        // Ajout des sommets à la liste de voisins de chacun
        graphe.get(a).add(b);
        graphe.get(b).add(a);
        // Ajout du couple de sommets à la liste couples
        couples.add(new ArrayList<Integer>());
        couples.get(arc).add(a);
        couples.get(arc).add(b);
        arc++;
    }


    // Méthode pour afficher le graphe
    void afficher() {
        for (int i = 0; i < sommet; i++) {
            int index1 = i + 1;
            System.out.print("noeud " + index1 + " :");
            for (int x : graphe.get(i)) {
                System.out.print(" " + (x + 1));
            }
            System.out.println();
        }
    }

    // Méthode pour récupérer le nombre de sommets dans le graphe
    public int getSommet() {
        return sommet;
    }

    // Méthode pour récupérer le nombre d'arcs dans le graphe
    public int getArc() {
        return arc;
    }
}
