import java.util.HashMap;
import java.util.ArrayList;

public abstract class AlgAbstract {
  protected ArrayList<String> vertices;
  protected HashMap<String, ArrayList<String>> listaAdj;
  protected HashMap<String, Integer> cores;
  protected HashMap<String, Integer> tempoDescoberta;
  protected HashMap<String, Integer> tempoFinalizacao;

  public AlgAbstract(Grafo grafo) {
    this.vertices = grafo.getVertices();
    this.cores = new HashMap<>();
    this.tempoDescoberta = new HashMap<>();
    this.tempoFinalizacao = new HashMap<>();
    this.listaAdj = grafo.getListaAdj();
  }

  public void inicializarValores() {
    for (String vertice : vertices) {
      setVerticeNaoVisitado(vertice);
      tempoDescoberta.put(vertice, 0);
      tempoFinalizacao.put(vertice, 0);
    }
  }

  protected void setVerticeNaoVisitado(String vertice) {
    cores.put(vertice, Cores.BRANCO);
  }

  protected void setVerticeVisitado(String vertice) {
    cores.put(vertice, Cores.CINZA);
  }

  protected void setVerticeExplorado(String vertice) {
    cores.put(vertice, Cores.PRETO);
  }

  protected void DFS() {
    inicializarValores();
    int tempo = 0;
    for (String vertice : vertices) {
      if (cores.get(vertice) == Cores.BRANCO) {
        visitaDFS(vertice, tempo);
      }
    }
  }

  protected void visitaDFS(String vertice, int tempo) {
    setVerticeVisitado(vertice);
    tempoDescoberta.put(vertice, ++tempo);
    ArrayList<String> vizinhos = listaAdj.get(vertice);
    for (String verticeVizinho : vizinhos) {
      if (cores.get(verticeVizinho) == Cores.BRANCO) {
        visitaDFS(verticeVizinho, tempo);
      }
    }
    setVerticeExplorado(vertice);
    tempoFinalizacao.put(vertice, ++tempo);
  }

  public abstract String executar();
}
