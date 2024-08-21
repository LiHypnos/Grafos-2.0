import java.util.ArrayList;
import java.util.HashMap;

public class TopSort{
  private ArrayList<String> vertices;
  private HashMap<String, ArrayList<String>> listaAdj;
  private HashMap<String, Integer> cores;
  private HashMap<String, Integer> tempoDescoberta;
  private HashMap<String, Integer> tempoFinalizacao;
  
  public TopSort(Grafo grafo) {
    this.vertices = grafo.getVertices();
    this.cores = new HashMap<>();
    this.tempoDescoberta = new HashMap<>();
    this.tempoFinalizacao = new HashMap<>();
    this.listaAdj = grafo.getListaAdj();
  }

  public String executar() {
    String s = "";
    DFS();

    for (int i = 0; i < vertices.size(); i++) {
      int max = 0;
      String maxVertice = null;
      for (String vertice : vertices) {
        if (tempoFinalizacao.get(vertice) > max) {
          max = tempoFinalizacao.get(vertice);
          maxVertice = vertice;
        }
      }
      if (i == 0) {
        s += maxVertice;
      } else {
        s += " -> " + maxVertice;
      }
      tempoFinalizacao.put(maxVertice, -1); // anula as futuras comparacoes com este valor
    }
    return s;
  }

  private void DFS() {
    inicializarValores();
    int tempo = 0;
    for (String vertice : vertices) {
      if (cores.get(vertice) == Cores.BRANCO) {
        visitaDFS(vertice, tempo);
      }
    }
  }

  private void visitaDFS(String vertice, int tempo) {
    cores.put(vertice, Cores.CINZA);
    tempoDescoberta.put(vertice, ++tempo);
    ArrayList<String> vizinhos = listaAdj.get(vertice);
    for (String verticeVizinho : vizinhos) {
      if (cores.get(verticeVizinho) == Cores.BRANCO) {
        visitaDFS(verticeVizinho, tempo);
      }
    }
    cores.put(vertice, Cores.PRETO);
    tempoFinalizacao.put(vertice, ++tempo);
  }

  public void inicializarValores() {
    for (String vertice : vertices) {
      cores.put(vertice, Cores.BRANCO);
      tempoDescoberta.put(vertice, 0);
      tempoFinalizacao.put(vertice, 0);
    }
  }
}
