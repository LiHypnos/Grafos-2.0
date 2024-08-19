import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList; // Add this import statement
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Input {
    @SuppressWarnings("resource")
    Map<String, ArrayList<Pair<String,Integer,String>>> input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("INPUT POR:\n1. Arquivo | 2. Terminal | 3. Arquivo <Padrao>");
        String escolha = scanner.nextLine();
        Map<String, ArrayList<Pair<String,Integer,String>>> grafo = new HashMap<>();
        switch (escolha) {
            case "1":
                System.out.println("Arquivo");     
                try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
                    String tipoGrafo = br.readLine().trim(); // Leitura do tipo de grafo (direcionado ou não)
                    String line;

                    while ((line = br.readLine()) != null) {
                        // Remove espaços e as chaves
                        line = line.trim().replaceAll("[{}]", "");
                        String[] arestas = line.split("\\),\\("); // Divide as arestas

                        for (String aresta : arestas) {
                            // Remove os parênteses restantes e divide os vértices e o peso
                            aresta = aresta.replaceAll("[()]", "");
                            String[] dados = aresta.split(",");
                            String a = dados[0];
                            String v1 = dados[1].trim();
                            String v2 = dados[2].trim();
                            int valor = Integer.parseInt(dados[3].trim()); // Lê o peso da aresta

                            // Adiciona a aresta no grafo
                            if (grafo.containsKey(a)) {
                                grafo.get(a).add(new Pair<>(v1, valor,v2));
                            } else {
                                ArrayList<Pair<String, Integer, String>> lista = new ArrayList<>();
                                lista.add(new Pair<>(v1, valor, v2));
                                grafo.put(a, lista);
                            }

                            // Se o grafo não for direcionado, adiciona a aresta na direção oposta
                            if (tipoGrafo.equals("nao_direcionado")) {
                                if (grafo.containsKey(a)) {
                                    grafo.get(a).add(new Pair<>(v2, valor, v1));
                                } else {
                                    ArrayList<Pair<String, Integer, String>> lista = new ArrayList<>();
                                    lista.add(new Pair<>(v2, valor, v1));
                                    grafo.put(a, lista);
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();

                }
                return grafo;
            case "2":
                System.out.println("Terminal");
                String v; 
                String a; 
                String valor;
                String caracteristica;
                System.out.println("Numero de Vertices:");
                v = scanner.nextLine();
                System.out.println("Numero de Arestas:");
                a = scanner.nextLine();
                System.out.println("Caracteristica do Grafo: nd (nao direcionado) | d (direcionado)");
                caracteristica = scanner.nextLine();
        
                if (caracteristica.equals("nd")) {
                    int contadorV = 0;
                    int aInt = Integer.parseInt(a);
        
                    for (int i = 0; i < aInt; i++) {
                        if (contadorV == Integer.parseInt(v)) {
                            break;
                        } else {
                            System.out.println("ID aresta:");
                            String id = scanner.nextLine();
                            System.out.println("V1:");
                            String v1 = scanner.nextLine();
                            System.out.println("V2:");
                            String v2 = scanner.nextLine();
                            System.out.println("Valor:");
                            valor = scanner.nextLine();
                            ArrayList<Pair<String, Integer, String>> lista = new ArrayList<>();
                            lista.add(new Pair<>(v1, Integer.parseInt(valor), v2));
                            grafo.put(id, lista);
        
                            // Adiciona a aresta v2 -> v1 para garantir a bidirecionalidade
                            if (grafo.containsKey(id)) {
                                grafo.get(id).add(new Pair<>(v2, Integer.parseInt(valor), v1));
                            } else {
                                ArrayList<Pair<String, Integer, String>> listax = new ArrayList<>();
                                listax.add(new Pair<>(v2, Integer.parseInt(valor), v1));
                                grafo.put(id, listax);
                            }
        
                            contadorV++;
                        }
                    } 
                } else if(caracteristica.equals("d")){
                    int contadorV = 0;
                    int aInt = Integer.parseInt(a);
                    for(int i = 0; i < aInt; i++){
                        if(contadorV == Integer.parseInt(v)){
                            i=aInt;
                        } else {
                            System.out.println("ID aresta:");
                            String id = scanner.nextLine();
                            System.out.println("V1:");
                            String v1 = scanner.nextLine();
                            System.out.println("V2:");
                            String v2 = scanner.nextLine();
                            System.out.println("Valor:");
                            valor = scanner.nextLine();
                            contadorV++;
                            ArrayList<Pair<String,Integer,String>> lista = new ArrayList<>();
                            lista.add(new Pair<>(v1,Integer.parseInt(valor),v2));
                            grafo.put(id, lista);
                        }
                        
                    }
                } else {
                    System.out.println("Caracteristica invalida");
                }
                return grafo;
            case "3":
                try (BufferedReader br = new BufferedReader(new FileReader("grafo_0.txt"))) {
                    String comandos = br.readLine().trim(); // Lê a primeira linha contendo os comandos
                    String primeiraLinha = br.readLine().trim(); // Lê a primeira linha contendo número de vértices e arestas
                    String tipoGrafo = br.readLine().trim(); // Lê o tipo de grafo (direcionado ou não)
                    String line;
        
                    while ((line = br.readLine()) != null) {
                        // Divide os dados da linha
                        String[] dados = line.split(" ");
                        String idAresta = dados[0].trim();
                        String v1 = dados[1].trim();
                        String v2 = dados[2].trim();
                        int peso = Integer.parseInt(dados[3].trim()); // Lê o peso da aresta
        
                        // Adiciona a aresta no grafo
                        if (grafo.containsKey(idAresta)) {
                            grafo.get(idAresta).add(new Pair<>(v1, peso, v2));
                        } else {
                            ArrayList<Pair<String, Integer, String>> lista = new ArrayList<>();
                            lista.add(new Pair<>(v1, peso, v2));
                            grafo.put(idAresta, lista);
                        }
        
                        // Se o grafo não for direcionado, adiciona a aresta na direção oposta
                        if (tipoGrafo.equals("nao_direcionado")) {
                            if (grafo.containsKey(idAresta)) {
                                grafo.get(idAresta).add(new Pair<>(v2, peso, v1));
                            } else {
                                ArrayList<Pair<String, Integer, String>> lista = new ArrayList<>();
                                lista.add(new Pair<>(v2, peso, v1));
                                grafo.put(idAresta, lista);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return grafo;
            default:
                System.out.println("Opcao invalida");
                return null;
            }
        }
}
        