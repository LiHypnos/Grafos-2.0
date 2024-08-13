import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    void menu() {
        Scanner scanner = new Scanner(System.in);
        String escolha = "";
        Input input = new Input();
        Grafo grafo = new Grafo(input.input());
        for (Map.Entry<String, ArrayList<Pair<String, Integer>>> entry : grafo.getGrafo().entrySet()) {
            if(entry.getValue() != null){
                System.out.print("Vértice " + entry.getKey() + " conectado a: ");
                for (Pair<String, Integer> par : entry.getValue()) {
                    System.out.print("(" + par.getKey() + ", " + par.value + ") ");
                }
                System.out.println();
            } else {
                System.out.println("Vértice " + entry.getKey() + " não conectado a nenhum outro vértice");
            }
        }
        Ferramentas ferramentas = new Ferramentas(grafo);
        System.out.println("Menu\n1. Conexo\n2. Bipartido\n3. Euleriano\n4. Possui ciclo\n5. Componentes Conexoas\n6. Componentes Fortemente Conexas\n7. Trilha Euleriana\n8. Vertices de Articulacao\n9. Arestas de Ponte\n10. DFS\n11. BFS\n12. Arvore Geradora Minima\n13. Ordem Topologica\n14. Valor Caminho Minimo Entre Dois Vertices\n15. Valor Fluxo Maximo\n16. Fecho Transitivo\n0. Sair");
        while(!escolha.equals("0")) {
            escolha = scanner.nextLine();
            switch (escolha) {
                case "1":
                    System.out.println("Conexo");
                    System.out.println(ferramentas.isConexo());
                    break;
                case "2":
                    System.out.println("Bipartido");
                    System.out.println(ferramentas.isBipartido());
                    break;
                case "3":
                    System.out.println("Euleriano");
                    System.out.println(ferramentas.isEuleriano());
                    break;
                case "4":
                    System.out.println("Possui ciclo");
                    break;
                case "5":
                    System.out.println("Componentes Conexoas");
                    break;
                case "6":
                    System.out.println("Componentes Fortemente Conexas");
                    break;
                case "7":
                    System.out.println("Trilha Euleriana");
                    break;
                case "8":
                    System.out.println("Vertices de Articulacao");
                    break;
                case "9":
                    System.out.println("Arestas de Ponte");
                    break;
                case "10":
                    System.out.println(grafo.DFS());
                    break;
                case "11":
                    System.out.println(grafo.BFS());
                    break;
                case "12":
                    System.out.println("Arvore Geradora Minima");
                    break;
                case "13":
                    System.out.println("Ordem Topologica");
                    break;
                case "14":
                    System.out.println("Valor Caminho Minimo Entre Dois Vertices");
                    break;
                case "15":
                    System.out.println("Valor Fluxo Maximo");
                    break;
                case "16":
                    System.out.println("Fecho Transitivo");
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
        }
        System.out.println("Menu");
        scanner.close();
    }
}
