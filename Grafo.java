import java.util.ArrayList;
import java.util.Arrays;
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

public class Grafo {
    Boolean direcionado; // Indica se o grafo é direcionado
    // Id aresta, array de <origem, valor aresta, destino>
    Map<String, ArrayList<Pair<String, Integer, String>>> grafo;

    Grafo(Map<String, ArrayList<Pair<String, Integer, String>>> input) {
        grafo = new HashMap<>();
        grafo = input;
        // Verifica se o grafo é direcionado
        if (grafo.get("0").get(1).getOrigin() == grafo.get("0").get(0).getDestiny()) {
            direcionado = false;
        } else {
            direcionado = true;
        }
    }

    public boolean isDirecionado() {
        return direcionado;
    }

    public Map<String, ArrayList<Pair<String, Integer, String>>> getGrafo() {
        return grafo;
    }

    public ArrayList<String> getVertices() {
        ArrayList<String> vertices = new ArrayList<>();
        // Coleta todos os vértices do grafo
        for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.entrySet()) {
            if (entry.getValue() != null) {
                for (Pair<String, Integer, String> par : entry.getValue()) {
                    if (!vertices.contains(par.getOrigin())) {
                        vertices.add(par.getOrigin());
                    }
                    if (!vertices.contains(par.getDestiny())) {
                        vertices.add(par.getDestiny());
                    }
                }
            }
        }
        // Ordena os vértices
        Collections.sort(vertices, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // Converte as strings para números e compara
                return Integer.compare(Integer.parseInt(s1), Integer.parseInt(s2));
            }
        });
        return vertices;
    }

    public HashMap<String, ArrayList<String>> getListaAdj() {
        HashMap<String, ArrayList<String>> listaAdj = new HashMap<>();
        // Cria a lista de adjacência
        for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.entrySet()) {
            if (entry.getValue() != null) {
                for (Pair<String, Integer, String> par : entry.getValue()) {
                    if (listaAdj.containsKey(par.getOrigin())) {
                        listaAdj.get(par.getOrigin()).add(par.getDestiny());
                    } else {
                        ArrayList<String> adj = new ArrayList<>();
                        adj.add(par.getDestiny());
                        listaAdj.put(par.getOrigin(), adj);
                    }
                }
            }
        }
        return listaAdj;
    }

    public String Vertices() {
        String vertices = "";
        HashMap<String, Integer> verticesMap = new HashMap<>();
        // Coleta todos os vértices do grafo
        for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.entrySet()) {
            if (entry.getValue() != null) {
                for (Pair<String, Integer, String> par : entry.getValue()) {
                    verticesMap.put(par.getDestiny(), 0);
                }
            }
        }
        // Adiciona os vértices à string de saída
        for (String vertice : verticesMap.keySet()) {
            vertices += vertice + " ";
        }
        return vertices;
    }

    public Set<String> Arestas() {
        Set<String> arestas = new HashSet<>();
        // Coleta todas as arestas do grafo
        for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.entrySet()) {
            if (entry.getValue() != null) {
                arestas.add(entry.getKey());
            }
        }
        return arestas;
    }

    public String getIdValue(Pair<String, Integer, String> valor) {
        String id = "";
        // Encontra o ID da aresta correspondente ao valor fornecido
        for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.entrySet()) {
            if (entry.getValue() != null) {
                for (Pair<String, Integer, String> par : entry.getValue()) {
                    if (entry.getValue().contains(par) && par.equals(valor)) {
                        id = entry.getKey();
                    }
                }
            }
        }
        return id;
    }

    public Pair<String, Integer, String> getLigacoes(String vertice) {
        Pair<String, Integer, String> ligacoes = null;
        // Encontra as ligações do vértice fornecido
        for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.entrySet()) {
            if (entry.getValue() != null) {
                for (Pair<String, Integer, String> par : entry.getValue()) {
                    if (par.getDestiny().equals(vertice)) {
                        ligacoes = par;
                    }
                }
            }
        }
        return ligacoes;
    }

    public int getGrau(String vertice) {
        int cont = 0;
        // Calcula o grau do vértice fornecido
        for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.entrySet()) {
            if (entry.getValue() != null) {
                for (Pair<String, Integer, String> par : entry.getValue()) {
                    if (par.getDestiny().equals(vertice)) {
                        cont++;
                    }
                }
            }
        }
        return cont;
    }

    public List<String> BFS(String initial) {
        List<String> visitados = new ArrayList<>();
        List<String> arestas = new ArrayList<>();
        Queue<String> fila = new LinkedList<>();
        fila.add(initial);
        // Realiza a busca em largura (BFS)
        while (!fila.isEmpty()) {
            String aresta = fila.poll();
            if (!arestas.contains(aresta)) {
                arestas.add(aresta);
                if (grafo.get(aresta) != null) {
                    for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.entrySet()) {
                        if (entry.getValue() != null) {
                            for (Pair<String, Integer, String> par : entry.getValue()) {
                                if (par.getDestiny().equals(grafo.get(getIdValue(par)).get(0).getOrigin()) && !visitados.contains(par.getOrigin())) {
                                    visitados.add(par.getOrigin());
                                    visitados.add(par.getDestiny());
                                    fila.add(entry.getKey());
                                }
                            }
                        }
                    }
                }
            }
        }
        arestas.sort(Comparator.naturalOrder());
        return arestas;
    }

    public Set<String> DFS(String initial) {
        Stack<String> pilha = new Stack<>();
        Set<String> verticesVisitados = new HashSet<>();
        Set<String> arestasVisitadas = new HashSet<>();

        pilha.add(initial);
        verticesVisitados.add(grafo.get(initial).get(0).getDestiny());
        verticesVisitados.add(grafo.get(initial).get(0).getOrigin());
        arestasVisitadas.add(initial);
        // Realiza a busca em profundidade (DFS)
        while (!pilha.isEmpty()) {
            String aresta = pilha.pop();

            if (grafo.get(aresta) != null) {
                for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.entrySet()) {
                    if (entry.getValue() != null) {
                        for (Pair<String, Integer, String> par : entry.getValue()) {
                            if (par.getOrigin().equals(grafo.get(aresta).get(0).getDestiny()) && !verticesVisitados.contains(par.getDestiny())) {
                                pilha.add(entry.getKey());
                                arestasVisitadas.add(entry.getKey());
                                verticesVisitados.add(par.getOrigin());
                                verticesVisitados.add(par.getDestiny());
                                aresta = pilha.pop();
                            }
                        }
                    }
                }
            }
        }
        List<String> sortedList = new ArrayList<>(arestasVisitadas);
        Collections.sort(sortedList);
        return new HashSet<>(sortedList);
    }

    public void prim(String origem) {
        Map<String, Integer> pesos = new HashMap<>();
        Map<String, String> predecessores = new HashMap<>();
        PriorityQueue<Pair<String, Integer, String>> filaPrioridade = new PriorityQueue<>(Comparator.comparingInt(Pair::getValue));
        Set<String> visitados = new HashSet<>();

        // Inicializa os pesos dos vértices
        for (String vertice : Vertices().split(" ")) {
            pesos.put(vertice, Integer.MAX_VALUE);
        }
        pesos.put(origem, 0);
        filaPrioridade.add(new Pair<>(origem, 0, origem));

        // Executa o algoritmo de Prim
        while (!filaPrioridade.isEmpty()) {
            Pair<String, Integer, String> atual = filaPrioridade.poll();
            String u = atual.getOrigin();

            if (visitados.contains(u)) continue;
            visitados.add(u);

            if (grafo.containsKey(u)) {
                for (Pair<String, Integer, String> vizinho : grafo.get(u)) {
                    String v = vizinho.getDestiny();
                    int peso = vizinho.getValue();

                    if (!visitados.contains(v) && peso < pesos.get(v)) {
                        pesos.put(v, peso);
                        predecessores.put(v, u);
                        filaPrioridade.add(new Pair<>(v, peso, v));
                    }
                }
            }
        }

        // Imprime a árvore geradora mínima
        System.out.println("A árvore geradora mínima é:");
        for (Map.Entry<String, String> entry : predecessores.entrySet()) {
            System.out.println(entry.getValue() + " - " + entry.getKey());
        }
    }

    public int fordFulkerson(String source, String sink) {
        Map<String, ArrayList<Pair<String, Integer, String>>> residualGraph = new HashMap<>();
        // Cria o grafo residual
        for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.entrySet()) {
            residualGraph.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }

        Map<String, String> parent = new HashMap<>();
        int maxFlow = 0;

        // Executa o algoritmo de Ford-Fulkerson
        while (dfs(residualGraph, source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;

            // Encontra o fluxo máximo no caminho encontrado
            for (String v = sink; !v.equals(source); v = parent.get(v)) {
                String u = parent.get(v);
                for (Pair<String, Integer, String> pair : residualGraph.get(u)) {
                    if (pair.getDestiny().equals(v)) {
                        pathFlow = Math.min(pathFlow, pair.getValue());
                    }
                }
            }

            // Atualiza as capacidades das arestas no grafo residual
            for (String v = sink; !v.equals(source); v = parent.get(v)) {
                String u = parent.get(v);
                for (Pair<String, Integer, String> pair : residualGraph.get(u)) {
                    if (pair.getDestiny().equals(v)) {
                        pair.value -= pathFlow;
                    }
                }
                boolean reverseEdgeFound = false;
                for (Pair<String, Integer, String> pair : residualGraph.get(v)) {
                    if (pair.getDestiny().equals(u)) {
                        pair.value += pathFlow;
                        reverseEdgeFound = true;
                    }
                }
                if (!reverseEdgeFound) {
                    residualGraph.get(v).add(new Pair<>(v, pathFlow, u));
                }
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    private boolean dfs(Map<String, ArrayList<Pair<String, Integer, String>>> residualGraph, String source, String sink, Map<String, String> parent) {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();
        stack.push(source);
        visited.add(source);

        // Realiza a busca em profundidade (DFS) no grafo residual
        while (!stack.isEmpty()) {
            String u = stack.pop();

            if (residualGraph.containsKey(u)) {
                for (Pair<String, Integer, String> pair : residualGraph.get(u)) {
                    String v = pair.getDestiny();
                    int capacity = pair.getValue();

                    if (!visited.contains(v) && capacity > 0) {
                        stack.push(v);
                        visited.add(v);
                        parent.put(v, u);

                        if (v.equals(sink)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}