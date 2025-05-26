package Labs.Lab9;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Graph representation of Turkish cities with adjacency matrix
 * Supports DFS and BFS traversals with neighborhood levels
 */
public class CitiesOfTurkiye {
    public Vertex[] vertexes;    // Array to store city vertices
    public int[][] edges;       // Adjacency matrix for city connections
    public int MAX_SIZE;        // Maximum number of cities
    public int nElems;          // Current number of cities
    
    /**
     * Initializes graph with maximum capacity
     * @param max Maximum number of cities
     */
    public CitiesOfTurkiye(int max){
        MAX_SIZE = max;
        vertexes = new Vertex[max];
        edges = new int[max][max];
        nElems = 0;
        
        // Initialize adjacency matrix with 0 (no connections)
        for(int i=0; i<max; i++){
            for(int j=0; j<max; j++){
                edges[i][j] = 0;
            }
        }
    }
    
    /**
     * Adds a new city vertex to the graph
     * @param val City name
     */
    public void addVertex(String val){
        vertexes[nElems++] = new Vertex(val);
    }
    
    /**
     * Finds array index of a city
     * @param val City name to search
     * @return Index of city or -1 if not found
     */
    public int findIndex(String val){
        for(int i=0; i<nElems; i++){
            if(vertexes[i].value.equals(val)){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Creates bidirectional connection between two cities
     * @param v1 First city name
     * @param v2 Second city name
     */
    public void addEdge(String v1, String v2) {
        int ind1 = findIndex(v1);
        int ind2 = findIndex(v2);
        
        if(ind1 != -1 && ind2 != -1){
            edges[ind1][ind2] = 1;  // Set connection in both directions
            edges[ind2][ind1] = 1;
        }
    }
    
    /**
     * Finds next unvisited connected city
     * @param i Index of current city
     * @return Index of adjacent city or -1 if none found
     */
    public int getAdjacentVertex(int i){
        for(int j = 0; j<nElems; j++){
            if(edges[i][j] == 1 && vertexes[j].wasVisited == false){
                return j;
            }
        }
        return -1;
    }
    
    /**
     * Depth-First Search traversal from starting city
     * @param val Starting city name
     */
    public void dfs(String val){
        int ind = findIndex(val);
        Stack<Integer> myStack = new Stack<Integer>();
        
        myStack.push(ind);
        vertexes[ind].wasVisited = true;
        System.out.print(vertexes[ind].value);
        
        while(!myStack.isEmpty()){
            int n = getAdjacentVertex(myStack.peek());
            if(n == -1){
                myStack.pop();
            }
            else{
                vertexes[n].wasVisited = true;
                System.out.print(vertexes[n].value);
                myStack.push(n);
            }
        }
        
        // Reset visited flags after traversal
        for(int i=0; i<nElems; i++){
            vertexes[i].wasVisited = false;
        }
    }
    
    /**
     * Displays all cities and their direct connections
     */
    public void displayGraph(){
        for(int i=0; i<nElems; i++){
            System.out.print(vertexes[i].value + ": ");
            for(int j=0; j<nElems; j++){
                if(edges[i][j] == 1){
                    System.out.print(vertexes[j].value + " ");
                }
            }
            System.out.println("");
        }
    }
    
    /**
     * Breadth-First Search traversal with neighborhood levels
     * @param city Starting city name
     */
    public void bfs(String city) {
        int start = findIndex(city);
        if (start == -1) {
            System.out.println("City not found!");
            return;
        }
        
        int level = 0;
        List<Integer> currentLevel = new ArrayList<>();
        List<Integer> nextLevel = new ArrayList<>();

        vertexes[start].wasVisited = true;
        currentLevel.add(start);

        while (!currentLevel.isEmpty()) {
            System.out.println("---------------------------------");
            System.out.println("Neighbourhood Level " + level);
            System.out.println("---------------------------------");

            for (int idx : currentLevel) {
                System.out.println(vertexes[idx].value);

                // Explore all connected cities
                for (int j = 0; j < nElems; j++) {
                    if (edges[idx][j] == 1 && !vertexes[j].wasVisited) {
                        vertexes[j].wasVisited = true;
                        nextLevel.add(j);
                    }
                }
            }

            currentLevel = new ArrayList<>(nextLevel);
            nextLevel.clear();
            level++;
        }

        // Reset visited flags after traversal
        for (int i = 0; i < nElems; i++) {
            vertexes[i].wasVisited = false;
        }
    }
}