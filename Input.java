import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList; // Add this import statement
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Input {
    @SuppressWarnings("resource")
    Map<String, ArrayList<Pair<String,Integer>>> input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("INPUT POR:\n1. Arquivo | 2. Terminal");
        String escolha = scanner.nextLine();
        Map<String, ArrayList<Pair<String,Integer>>> grafo = new HashMap<>();
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

                            String v1 = dados[0].trim();
                            String v2 = dados[1].trim();
                            int valor = Integer.parseInt(dados[2].trim()); // Lê o peso da aresta

                            // Adiciona a aresta no grafo
                            if (grafo.containsKey(v1)) {
                                grafo.get(v1).add(new Pair<>(v2, valor));
                            } else {
                                ArrayList<Pair<String, Integer>> lista = new ArrayList<>();
                                lista.add(new Pair<>(v2, valor));
                                grafo.put(v1, lista);
                            }

                            // Se o grafo não for direcionado, adiciona a aresta na direção oposta
                            if (tipoGrafo.equals("nao_direcionado")) {
                                if (grafo.containsKey(v2)) {
                                    grafo.get(v2).add(new Pair<>(v1, valor));
                                } else {
                                    ArrayList<Pair<String, Integer>> lista = new ArrayList<>();
                                    lista.add(new Pair<>(v1, valor));
                                    grafo.put(v2, lista);
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
                            System.out.println("V1:");
                            String v1 = scanner.nextLine();
                            System.out.println("V2:");
                            String v2 = scanner.nextLine();
                            System.out.println("Valor:");
                            valor = scanner.nextLine();
        
                            // Adiciona a aresta v1 -> v2
                            if (grafo.containsKey(v1)) {
                                grafo.get(v1).add(new Pair<>(v2, Integer.parseInt(valor)));
                            } else {
                                ArrayList<Pair<String, Integer>> lista = new ArrayList<>();
                                lista.add(new Pair<>(v2, Integer.parseInt(valor)));
                                grafo.put(v1, lista);
                            }
        
                            // Adiciona a aresta v2 -> v1 para garantir a bidirecionalidade
                            if (grafo.containsKey(v2)) {
                                grafo.get(v2).add(new Pair<>(v1, Integer.parseInt(valor)));
                            } else {
                                ArrayList<Pair<String, Integer>> lista = new ArrayList<>();
                                lista.add(new Pair<>(v1, Integer.parseInt(valor)));
                                grafo.put(v2, lista);
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
                            System.out.println("V1:");
                            String v1 = scanner.nextLine();
                            System.out.println("V2:");
                            String v2 = scanner.nextLine();
                            System.out.println("Valor:");
                            valor = scanner.nextLine();
                            if(grafo.containsKey(v1)){
                                grafo.get(v1).add(new Pair<>(v2,Integer.parseInt(valor)));
                            } else {
                                contadorV++;
                                ArrayList<Pair<String,Integer>> lista = new ArrayList<>();
                                lista.add(new Pair<>(v2,Integer.parseInt(valor)));
                                grafo.put(v1, lista);
                            }
                        }
                        
                    }
                } else {
                    System.out.println("Caracteristica invalida");
                }
                return grafo;
            default:
                System.out.println("Opcao invalida");
                return null;
            }
        }
        
    }