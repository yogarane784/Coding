/*
* Given a undirected Graph consisting of V vertices numbered from 0 to V-1 and E edges. 
* The ith edge is represented by [ai,bi], denoting a edge between vertex ai and bi. 
* We say two vertices u and v belong to a same component if there is a path from u to v or v to u. 
* Find the number of connected components in the graph.
* A connected component is a subgraph of a graph in which there exists a path between any two vertices, and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.
*/
class FindNumberOfComponents {
    public int findNumberOfComponent(int V, List<List<Integer>> edges) {

      List<Integer>[] adj = new ArrayList[V];

      for(int i=0;i<V;i++) {
        adj[i] = new ArrayList<>();
      }

      for(List<Integer> edge : edges) {
        int u = edge.get(0);
        int v = edge.get(1);
        adj[u].add(v);
        adj[v].add(u);
      }


      boolean visited[] = new boolean[V];
      int count = 0;

      for(int i=0;i<V;i++) {
        if(!visited[i]) {
          count++;
          dfs(i, adj, visited);
        }
      }

      return count;
    
    }

    void dfs(int i, List<Integer>[] adj, boolean visited[]) {

        visited[i] = true;

        List<Integer> neighbours = adj[i];

        for(int n : neighbours) {
          if(!visited[n]) {
            dfs(n, adj, visited);
          }
        }
    }
}
