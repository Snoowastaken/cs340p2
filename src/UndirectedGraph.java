import java.io.*;
import java.util.*;

public class UndirectedGraph {
    private class VertexNode {
        private String vertexName;
        private EdgeNode edges[]; //heads of the two edge lists
        private VertexNode nextV; //next vertex in the vertex list

        private VertexNode(String v, VertexNode n) {
            vertexName = new String(v);
            edges = new EdgeNode[2];
            nextV = n;
        }


    }

    private class EdgeNode {
        private VertexNode edge[]; //the two vertices that make the edge
        private EdgeNode nextE[]; //the next edge node for each edge list
        private EdgeNode(VertexNode v1, VertexNode v2) {
            //PRE: v1.vertexName < v2.vertexName
            edge = new VertexNode[2];
            edge[0] = v1;
            edge[1] = v2;
            nextE = new EdgeNode[2];
        }
    }

    private VertexNode vertices; //head of the list of vertices

    public UndirectedGraph() {
        vertices = null; //no sentinel node
    }

    public boolean addVertex(String s) {
        //add a new vertex with name s
        //return true if a vertex is added
        //return false if a vertex with name s already exists
        //the vertex list is kept in ascending sorted order based on the name
        if(vertices == null){
            vertices = new VertexNode(s, null);
            return true;
        } else if(s.compareTo(vertices.vertexName) == 0) {
            return false;
        }
        VertexNode currentNode = vertices;
        while(currentNode.nextV != null && s.compareTo(currentNode.nextV.vertexName) > 0) {
            currentNode = currentNode.nextV;
        }
        if(currentNode.nextV != null && s.compareTo(currentNode.nextV.vertexName) == 0) {
            return false;
        }
        currentNode.nextV = new VertexNode(s, currentNode.nextV);
        return true;
    }

    public void addEdge(String v1, String v2) {
    //PRE: v1 and v2 are legitimate vertex names
    //(i.e. vertices with names v1 and v2 exist in the vertex list)
    //Assume the edge has not been added

    }

    public void printGraph() {
        //print the graph using the format shown in class


    }

    public static void main(String args[]) throws IOException {
        //DO NOT CHANGE main
        //This code assumes the input file is syntactically correct
        UndirectedGraph g = new UndirectedGraph();
        BufferedReader b = new BufferedReader(new FileReader(args[0]));
        String line = b.readLine();
        Scanner scan = new Scanner(line);
        while (scan.hasNext()) {
            g.addVertex(scan.next());
        }
        line = b.readLine();
        while (line != null) {
            scan = new Scanner(line);
            g.addEdge(scan.next(), scan.next());
            line = b.readLine();
        }
        g.printGraph();
    }



}