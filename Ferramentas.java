import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Ferramentas {
    Grafo grafo;  // Referência para o grafo que será manipulado pela classe Ferramentas
    Boolean ciclo; // Flag para indicar se o grafo possui ciclo

    // Construtor que inicializa o grafo e define a flag ciclo como false
    public Ferramentas(Grafo grafo){
        this.grafo = grafo;
        ciclo = false;
    }

    // Verifica se o grafo é conexo, ou seja, se todos os vértices são acessíveis a partir de qualquer outro vértice
    public boolean isConexo(){
        List<String> visitadasA = new ArrayList<>();
        visitadasA = grafo.BFS("0");  // Realiza uma busca em largura (BFS) a partir do vértice "0"
        List<String> verticesV = new ArrayList<>();
        String[] verticesT = grafo.Vertices().split(" "); // Obtém todos os vértices do grafo
        for(int i=0;i < visitadasA.size();i++){
            // Adiciona os vértices origem e destino das arestas visitadas
            verticesV.add(grafo.getGrafo().get(visitadasA.get(i)).get(0).getOrigin());
            verticesV.add(grafo.getGrafo().get(visitadasA.get(i)).get(0).getDestiny());
        }
        // Verifica se todos os vértices foram visitados
        for(String a : verticesT){
            if(!verticesV.contains(a)){
                return false;
            }
        }
        return true; // O grafo é conexo
    }

    // Verifica se o grafo é bipartido, ou seja, se os vértices podem ser divididos em dois conjuntos disjuntos
    public boolean isBipartido(){
        Map<String, Integer> cores = new HashMap<>();
        Queue<String> fila = new LinkedList<>();
        String[] vertices = grafo.Vertices().split(" ");
        for(String vertice : vertices){
            cores.put(vertice, -1); // Inicializa todos os vértices com cor indefinida (-1)
        }
        fila.add("0");
        cores.put("0", 0); // Atribui a cor 0 ao vértice inicial "0"
        while(!fila.isEmpty()){
            String atual = fila.poll();
            // Verifica as arestas adjacentes ao vértice atual
            for(Pair<String, Integer, String> par : grafo.getGrafo().get(atual)){
                if(cores.get(par.getDestiny()) == -1){
                    // Atribui a cor oposta ao vértice destino e o adiciona à fila
                    cores.put(par.getDestiny(), 1 - cores.get(atual));
                    fila.add(par.getDestiny());
                } else if(cores.get(par.getDestiny()) == cores.get(atual)){
                    return false; // Encontrou dois vértices adjacentes com a mesma cor, portanto não é bipartido
                }
            }
        }
        return true; // O grafo é bipartido
    }

    // Verifica se o grafo é euleriano, ou seja, se possui um ciclo que visita todas as arestas exatamente uma vez
    public boolean isEuleriano(){
        if (!isConexo()) {
            return false; // Se o grafo não é conexo, não pode ser euleriano
        }
        int oddDegreeCount = 0; // Contador de vértices com grau ímpar
        String[] valores = grafo.Vertices().split(" ");
        for (String vertice : valores) {
            // Verifica o grau de cada vértice
            if (grafo.getGrau(vertice) % 2 != 0) {
                System.out.println(vertice + " Possui grau: " + grafo.getGrau(vertice));
                oddDegreeCount++;
                return false; // Retorna falso se encontrar um vértice com grau ímpar
            }
        }

        if (oddDegreeCount > 2) {
            return false; // Se mais de dois vértices têm grau ímpar, não é euleriano
        }
        ciclo = true; // Define que o grafo possui um ciclo
        return true;
    }

    // Verifica se o grafo possui algum ciclo
    public boolean possuiCiclo(){
        if(ciclo){
            return true; // Se a flag ciclo já está definida, retorna verdadeiro
        }
        Set<String> visitados = new HashSet<>();
        Stack<String> pilha = new Stack<>();
        pilha.push("0");
        while(!pilha.isEmpty()){
            String atual = pilha.pop();
            if(!visitados.contains(atual)){
                visitados.add(atual); // Marca o vértice como visitado
                // Adiciona os vértices adjacentes à pilha
                for(Pair<String, Integer, String> par : grafo.getGrafo().get(atual)){
                    pilha.push(par.getDestiny());
                }
            } else {
                return true; // Encontrou um vértice já visitado, logo, existe um ciclo
            }
        }
        return false; // Não encontrou ciclos
    }

    // Executa a ordenação topológica do grafo e retorna a ordem dos vértices
    public String topSort() {
        TopSort topSort = new TopSort(grafo); // Cria uma instância de TopSort
        return topSort.executar(); // Executa a ordenação topológica
    }

    // Encontra e exibe as componentes conexas do grafo
    public void componentesConexas(){
        Set<String> visitados = new HashSet<>();
        Stack<String> pilha = new Stack<>();
        pilha.push("0");
        while(!pilha.isEmpty()){
            String atual = pilha.pop();
            if(!visitados.contains(atual)){
                visitados.add(atual); // Marca o vértice como visitado
                // Adiciona os vértices adjacentes à pilha
                for(Pair<String, Integer, String> par : grafo.getGrafo().get(atual)){
                    pilha.push(par.getDestiny());
                }
            }
        }
        // Ordena e exibe os vértices da componente conexa
        List<String> sortedList = new ArrayList<>(visitados);
        Collections.sort(sortedList);
        System.out.println(sortedList);
    }

    // Encontra e exibe as componentes fortemente conexas do grafo
    public void componentesFortementeConexas(){
        HashMap<String, ArrayList<Pair<String, Integer, String>>> grafoT = new HashMap<>();
        // Inverte as arestas do grafo
        for(Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.getGrafo().entrySet()){
            if(entry.getValue() != null){
                for(Pair<String, Integer, String> par : entry.getValue()){
                    if(grafoT.containsKey(par.getDestiny())){
                        grafoT.get(par.getDestiny()).add(new Pair<>(par.getDestiny(), par.getValue(), par.getOrigin()));
                    } else {
                        ArrayList<Pair<String, Integer, String>> lista = new ArrayList<>();
                        lista.add(new Pair<>(par.getDestiny(), par.getValue(), par.getOrigin()));
                        grafoT.put(par.getDestiny(), lista);
                    }
                }
            }
        }
        Set<String> visitados = new HashSet<>();
        Stack<String> pilha = new Stack<>();
        pilha.push("0");
        while(!pilha.isEmpty()){
            String atual = pilha.pop();
            if(!visitados.contains(atual)){
                visitados.add(atual); // Marca o vértice como visitado
                // Adiciona os vértices adjacentes à pilha
                for(Pair<String, Integer, String> par : grafoT.get(atual)){
                    pilha.push(par.getDestiny());
                }
            }
        }
        // Ordena e exibe as componentes fortemente conexas
        List<String> sortedList = new ArrayList<>(visitados);
        Collections.sort(sortedList);
        if(grafo.isDirecionado() == false){
            System.out.println("Grafo não possui componentes fortemente conexas");
        } else {
            System.out.println(sortedList);
        }
    }

    // Encontra e retorna a trilha euleriana no grafo a partir de um vértice inicial
    public List<String> trilhaEuleriana(String initial){
        Stack<String> pilha = new Stack<>();
        Set<String> verticesVisitados = new HashSet<>();
        Set<String> arestasVisitadas = new HashSet<>();
        List<String> visitados = new ArrayList<>();

        pilha.add(initial);
        verticesVisitados.add(grafo.getGrafo().get(initial).get(0).getDestiny());
        verticesVisitados.add(grafo.getGrafo().get(initial).get(0).getOrigin());
        arestasVisitadas.add("0");
        while(!pilha.isEmpty()){
            String aresta = pilha.pop();

            if (grafo.getGrafo().get(aresta) != null) {
                for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.getGrafo().entrySet()){
                    if(entry.getValue() != null){
                        for (Pair<String, Integer, String> par : entry.getValue()) {
                            if(par.getOrigin().equals(grafo.getGrafo().get(aresta).get(0).getDestiny())){
                                pilha.add(entry.getKey()); // Adiciona aresta à pilha
                                arestasVisitadas.add(entry.getKey());
                                verticesVisitados.add(par.getOrigin());
                                verticesVisitados.add(par.getDestiny());
                                if(!visitados.contains(par.getOrigin())){
                                    visitados.add(entry.getKey());
                                }
                                if(!visitados.contains(par.getDestiny())){
                                    visitados.add(par.getDestiny());
                                }
                                aresta = pilha.pop();
                            }
                        }
                    }
                }
                for(Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.getGrafo().entrySet()){
                    if(visitados.get(visitados.size()-1).equals(entry.getValue().get(0).getDestiny())){
                        visitados.add(entry.getValue().get(0).getOrigin());
                    }
                }
            }
        }
        return visitados; // Retorna a lista de vértices visitados na trilha euleriana
    }

    // Encontra e exibe os vértices de articulação do grafo
    public void verticesDeArticulacao(){
        String[] vertices = grafo.Vertices().split(" ");
        List<String> articulados = new ArrayList<>();
        String[] consulta = new String[vertices.length*2];
        for(int i=0;i<vertices.length;i++){ 
            consulta = trilhaEuleriana(vertices[i]).toArray(new String[0]);
            if(consulta[0].equals(consulta[consulta.length-1])){
                articulados.add(vertices[i]); // Adiciona vértice de articulação à lista
            }
        }
        System.out.println(articulados);
    }

    // Encontra e exibe as arestas de ponte do grafo
    public void arestasDePonte(){
       String[] vertices = grafo.Vertices().split(" ");
       Set<String> arestasPonte = new HashSet<>();
       for(int i=0;i<vertices.length;i++){
           if(grafo.getGrau(vertices[i])==1){
                arestasPonte.add(grafo.getIdValue(grafo.getLigacoes(vertices[i])));
           }
       }
        System.out.println(arestasPonte); // Exibe as arestas de ponte
    }

    // Implementa o algoritmo de Bellman-Ford para encontrar o caminho mínimo entre dois vértices
    public void bellmanford(String origem, String destino) {
        Map<String, Integer> distancias = new HashMap<>();
        Map<String, String> predecessores = new HashMap<>();

        // Inicializa as distâncias com infinito (Integer.MAX_VALUE)
        for (String vertice : grafo.Vertices().split(" ")) {
            distancias.put(vertice, Integer.MAX_VALUE);
        }
        distancias.put(origem, 0); // Define a distância da origem como 0

        int numVertices = grafo.Vertices().split(" ").length;

        // Relaxa as arestas (numVertices - 1) vezes
        for (int i = 1; i < numVertices; i++) {
            for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.getGrafo().entrySet()) {
                String u = entry.getKey();
                if (grafo.getGrafo().get(u) != null) {
                    for (Pair<String, Integer, String> par : grafo.getGrafo().get(u)) {
                        String v = par.getDestiny();
                        int peso = par.getValue();
                        if(distancias.get(u) == null){
                            distancias.put(u, Integer.MAX_VALUE);
                        }
                        else if (distancias.get(u) != Integer.MAX_VALUE && distancias.get(u) + peso < distancias.get(v)) {
                            distancias.put(v, distancias.get(u) + peso); // Atualiza a distância
                            predecessores.put(v, u); // Atualiza o predecessor
                        }
                    }
                }
            }
        }

        // Verifica se há ciclos negativos
        for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.getGrafo().entrySet()) {
            String u = entry.getKey();
            if (grafo.getGrafo().get(u) != null) {
                for (Pair<String, Integer, String> par : grafo.getGrafo().get(u)) {
                    String v = par.getDestiny();
                    int peso = par.getValue();
                    if (distancias.get(u) != Integer.MAX_VALUE && distancias.get(u) + peso < distancias.get(v)) {
                        System.out.println("O grafo contém um ciclo negativo.");
                        return;
                    }
                }
            }
        }

        // Reconstrói o caminho mínimo
        List<String> caminho = new LinkedList<>();
        for (String at = destino; at != null; at = predecessores.get(at)) {
            caminho.add(at);
        }
        Collections.reverse(caminho);

        int valorCaminho = distancias.get(destino);
        System.out.println("O valor do caminho mínimo é: " + valorCaminho);
        System.out.println("O caminho mínimo de " + origem + " para " + destino + " é: " + caminho);
    }
}
