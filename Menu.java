import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    void menu() {
        Scanner scanner = new Scanner(System.in);
        String escolha = "";
        Input input = new Input();
        Grafo grafo = new Grafo(input.input());
        for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.getGrafo().entrySet()) {
            if(entry.getValue() != null){
                System.out.print("Aresta " + entry.getKey() + " conectando a: ");
                for (Pair<String, Integer, String> par : entry.getValue()) {
                    System.out.print("(" + par.getOrigin() + ", " + par.getDestiny() + ", " + par.getValue() + ") ");
                }
                System.out.println();
            } else {
                System.out.println("Aresta " + entry.getKey() + " não conectado a nenhum outro vértice");
            }
        }
        System.out.println("Vertices: " + grafo.Vertices());
        for(int i=0;i<grafo.Vertices().split(" ").length;i++){
            System.out.println("Grau do vertice " + grafo.Vertices().split(" ")[i] + ": " + grafo.getGrau(grafo.Vertices().split(" ")[i]));
        }
        String[] verticesxString = grafo.Vertices().split(" ");
        Integer[] verticesx = new Integer[verticesxString.length];
        for (int i = 0; i < verticesxString.length; i++) {
            verticesx[i] = Integer.parseInt(verticesxString[i]);
        }
        List<Integer> verticesList = Arrays.asList(verticesx);
        Collections.sort(verticesList);
        System.out.println("Vertices ordenados: " + verticesList);
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
                    System.out.println(ferramentas.possuiCiclo());
                    break;
                case "5":
                    System.out.println("Componentes Conexas");
                    ferramentas.componentesConexas();
                    break;
                case "6":
                    System.out.println("Componentes Fortemente Conexas");
                    ferramentas.componentesFortementeConexas();
                    break;
                case "7":
                    System.out.println("Trilha Euleriana");
                    List<String> vertices = ferramentas.trilhaEuleriana("0");
                    System.out.println(vertices);
                    break;
                case "8":
                    System.out.println("Vertices de Articulacao");
                    ferramentas.verticesDeArticulacao();
                    break;
                case "9":
                    System.out.println("Arestas de Ponte");
                    ferramentas.arestasDePonte();
                    break;
                case "10":
                    System.out.println(grafo.DFS("0"));
                    break;
                case "11":
                    System.out.println(grafo.BFS("0"));
                    break;
                case "12":
                    System.out.println("Arvore Geradora Minima");
                    break;
                case "13":
                    System.out.println("Ordem Topologica");
                    System.out.println(ferramentas.topSort());
                    break;
                case "14":
                    System.out.println("Valor Caminho Minimo Entre Dois Vertices");
                    System.out.println("Deseja escolher os vertices? (s/n) | caso n, será usado o vertice 0 e o vertice n-1");
                    String escolhaVertices = scanner.nextLine();
                    if(escolhaVertices.equals("s")){
                        System.out.println("Vertice 1:");
                        String v1 = scanner.nextLine();
                        System.out.println("Vertice 2:");
                        String v2 = scanner.nextLine();
                        //System.out.println(grafo.dijkstra(v1, v2));
                    } else {
                        //System.out.println(grafo.dijkstra("0", verticesList.get(verticesList.size()-1).toString()));
                    }
                    break;
                case "15":
                    System.out.println("Valor Caminho Minimo Entre Dois Vertices");
                    System.out.println("Deseja escolher os vertices? (s/n) | caso n, será usado o vertice 0 e o vertice n-1");
                    escolhaVertices = scanner.nextLine();
                    if(escolhaVertices.equals("s")){
                        System.out.println("Vertice 1:");
                        String v1 = scanner.nextLine();
                        System.out.println("Vertice 2:");
                        String v2 = scanner.nextLine();
                        //System.out.println(ferramentas.fluxoMaximo(v1, v2));
                    } else {
                        //System.out.println(ferramentas.fluxoMaximo("0", verticesList.get(verticesList.size()-1).toString()));
                    }
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
