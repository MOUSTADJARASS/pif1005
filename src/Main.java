import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    private static Graphe newGraphe;
    private static int[][] Adjacence;
    public static void main(String[] args) {
        Menu();
    }

    private static void Options() {
        System.out.println("\t\t\t veuiller faire votre choix :\n");
        System.out.println("1 - pour cree un graphe.\n");
        System.out.println("2 - pour afficher les caracteristique du graphe.\n");
        System.out.println("3 - pour voir les testes\n");
    }

    private static int Valide(int a, int b) {
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        while (x > b || x < a) {
            System.out.println("le chiffre que vous avez entré sont incorrecte ");
            System.out.println("veuillez entrer un chiffre entre " + a + " et " + b);
            x = scan.nextInt();
        }
        return x;
    }

    private static void Menu() {
        boolean boucle= true;
        while (boucle!= false) {
        Options();
        int choix = Valide(1, 3);
            switch (choix) {
                case 1:

                    cree();

                    break;
                case 2:
                    if (newGraphe != null) {
                        carac();
                    } else {
                        System.out.println("-----vous n'avez pas encore cree de graphes -----");
                        Menu();
                    }
                    break;
                case 3:
                    test();
                    break;

            }
        }
    }

    private static void cree() {

        System.out.println("veuillez entrer le nombres de sommet du graphe :");

        int nombre = Valide(1, 1000);
       newGraphe = new Graphe(nombre);
        creeArc(nombre);
    }

    private static void creeArc( int nombre) {
         boolean doublons = true;
        int choix = 0;
        while (choix != 2) {
            System.out.println("\n\t\t\t veuillez entrer votre choix :\n");
            System.out.println("1- creer un arc :");
            System.out.println("2- terminé! :");
            choix = Valide(1, 2);
            if (choix == 1) {
                System.out.print("veuiller entrer le premier sommet (sommet 1 ou 2 ou 3 par exemple) : ");
                int sommet1 = Valide(1, nombre);
                System.out.print("veuiller entrer le deuxieme sommet (sommet 1 ou 2 ou 3 par exemple) : ");
                int sommet2 = Valide(1, nombre);

                doublons = false;
                for(int i =0;i<newGraphe.getCouple().size();i++) {
                    if((newGraphe.getCouple().get(i).contains(sommet1-1))&&(newGraphe.getCouple().get(i).contains(sommet2-1)) ){
                        doublons = true;
                        break;
                    }
                }
                if (!doublons) {
                    newGraphe.ajouterArc(sommet1 - 1, sommet2 - 1);
                } else {
                    System.out.println("\n il semble que cet arc existe deja... \n"+doublons);
                }
                newGraphe.afficher();
            } else {
                matriceAdjacence(nombre,newGraphe);
                System.out.println("\n la complexité temporelle de ce programme est de O(N^2),\n et la complexité spatiale est de O(N^2).\n où N est le nombre de sommets dans le graphe\n Le nombre d'opérations effectuées est de l'ordre de O(N^2).\n");

                matriceIncidence(newGraphe.getCouple(),newGraphe);
                System.out.println(" \n et la complexité spatiale est quadratique O(AxN)\n où N est le nombre de sommets dans le graphe et A est le nombre d'arêtes\n Le nombre d'opérations effectuées est de l'ordre de O(A).\n");

                break;}
        }
    }
    private static void matriceAdjacence(int a,Graphe graphe){
        Adjacence = new int[a][a];
            System.out.println("\t\t\t"+ "Matrice d'adjacencence" +
                    "\n");
               for (int i =0;i< a;i++) {
                   System.out.print("\t\t" + ((i + 1)));
               }
        for(int i =0;i < a;i++) {
            for (int j = 0; j < a; j++) {
                if (graphe.getGraphe().get(i).contains(j)) {
                    Adjacence[i][j] = 1;
                    Adjacence[j][i] = 1;
                }
            }
        }
        for (int i = 0; i < a; i++) {
            System.out.print("\n" + (i + 1));
            for (int j = 0; j < a; j++) {
                System.out.print("\t\t" + Adjacence[i][j]);
            }
        }
    }

    private static void matriceIncidence(ArrayList<ArrayList<Integer>> adj,Graphe graphe)
        {
            int a =0;
            int b =0;
            int vertices = graphe.getSommet(), edges = 0;
        int[][] Incidence = new int[graphe.getSommet()][graphe.getCouple().size()];
            System.out.println();
                System.out.println("\n\t\t\t Matrice d'Incidence");
                for (int i=0;i<graphe.getCouple().size() ;i++ ){
                    a = graphe.getCouple().get(i).get(0);
                    b = graphe.getCouple().get(i).get(1);
                System.out.print("\t\t"+((a+1)+","+(b+1)));
                    Incidence[a][i]= 1;
                    Incidence[b][i]= 1;
            }

            for (int i = 0; i < graphe.getSommet(); i++) {
                System.out.print("\n" + (i + 1));
                for (int j = 0; j < graphe.getCouple().size(); j++) {
                    System.out.print("\t\t" + Incidence[i][j]);
                }
            }
    }
private static void carac(){
                System.out.println("le graphe entrée possede "+newGraphe.getSommet()+" sommets, et "+newGraphe.getArc()+" arcs." );
    cycleEulerien();
    chaineEulerien();
    if(planaire(Adjacence) == true){
                System.out.println("ce graphe est plannaire ");
    }else {
                System.out.println("ce graphe n'est pas plannaire ");

    }
}
private static void cycleEulerien() {
    int p = 0;
                for(int i =0;i<newGraphe.getSommet();i++){
                System.out.println("le sommet "+(i+1)+", est de degre : "+newGraphe.getGraphe().get(i).size() );
                    if(newGraphe.getGraphe().get(i).contains(i) ){
                System.out.println("(le sommet "+(i+1)+" contient egalement une boucle )");

                    }

                }
                for (int i =0; i< newGraphe.getGraphe().size();i++) {
                    if (newGraphe.getGraphe().get(i).size() % 2 == 0) {
                        p++;
                    }
                }
                    if(newGraphe.getArc()==p ){
                System.out.println("le graphe entrée contient un cycle eulerien");
                    }
                else{
                System.out.println("le graphe entrée ne contient pas de cycle eulerien");
                        }
                }
private static void chaineEulerien() {
        int p = 0;
    for(int i =0;i<newGraphe.getCouple().size();i++){
        if(newGraphe.getGraphe().get(i).size()%2!=0 ){p++;
        }
    }
        if(p ==2 ) {
            System.out.println("ce graphe contient une chaine Eulerienne ");
        }else {
            System.out.println("ce graphe ne contient pas de chaine Eulerienne ");
        }
}
    public static boolean planaire(int[][] matriceAdjacence) {
        int n = matriceAdjacence.length;

        // Cas particuliers

        if (n <= 4) {
            return true;
        }
        if (n >= 5 && newGraphe.getArc() > 3*n - 6) {
            return false;
        }


        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (matriceAdjacence[i][j] == 1) {
                    for (int k = 0; k < n; k++) {
                        if (k != i && k != j && matriceAdjacence[i][k] == 1 && matriceAdjacence[k][j] == 1) {
                            return false; // Contient une sous-division de K5 ou K3,3
                        }
                    }
                }
            }
        }

        return true;
    }
    public static void test(){
        Graphe Graphe1 = new Graphe(6);
        Graphe Graphe2 = new Graphe(5);
        Graphe Graphe3 = new Graphe(4);
        Graphe1.ajouterArc(0,1);
        Graphe1.ajouterArc(1,2);
        Graphe1.ajouterArc(2,3);
        Graphe1.ajouterArc(3,4);
        Graphe1.ajouterArc(4,5);
        Graphe1.ajouterArc(5,1);

        Graphe2.ajouterArc(0,1);
        Graphe2.ajouterArc(1,2);
        Graphe2.ajouterArc(2,3);
        Graphe2.ajouterArc(3,1);
        Graphe2.ajouterArc(0,4);

        Graphe3.ajouterArc(0,1);
        Graphe3.ajouterArc(1,2);
        Graphe3.ajouterArc(1,3);


        System.out.println("\ngraphe 1");
        Graphe1.afficher();
        System.out.println();
        matriceAdjacence(6,Graphe1);
        matriceIncidence(Graphe1.getCouple(),Graphe1);

        System.out.println("\ngraphe 2");
        Graphe2.afficher();
        System.out.println();
        matriceAdjacence(5,Graphe2);
        matriceIncidence(Graphe2.getCouple(),Graphe2);

        System.out.println("\ngraphe 3");
        Graphe3.afficher();
        System.out.println();
        matriceAdjacence(4,Graphe3);
        matriceIncidence(Graphe3.getCouple(),Graphe3);
    }
}









