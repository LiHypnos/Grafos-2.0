
public class TopSort extends AlgAbstract {
  public TopSort(Grafo grafo) {
    super(grafo);
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

}
