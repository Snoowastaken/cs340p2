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
       VertexNode v1Node = vertices;
       VertexNode v2Node = vertices;
       while(v1Node.vertexName.compareTo(v1) != 0) {
           v1Node = v1Node.nextV;
       }
       while(v2Node.vertexName.compareTo(v2) != 0) {
           v2Node = v2Node.nextV;
       }
       EdgeNode newEdge = new EdgeNode(v1Node, v2Node);
       if(v2Node.edges[0] == null){
              v2Node.edges[0] = newEdge;
       } else {
           EdgeNode currentNode = v2Node.edges[0];
           while(currentNode.nextE[0] != null) {
               currentNode = currentNode.nextE[0];
           }
           currentNode.nextE[0] = newEdge;
       }
       if(v1Node.edges[1] == null){
              v1Node.edges[1] = newEdge;
       } else {
           EdgeNode currentNode = v1Node.edges[1];
           while(currentNode.nextE[1] != null) {
               currentNode = currentNode.nextE[1];
           }
           currentNode.nextE[1] = newEdge;
       }

    }

    public void printGraph() {
        //print the graph using the format shown in class
        StringBuilder sb = new StringBuilder();
        VertexNode currentNode = vertices;
        while(currentNode != null){
            sb.append(currentNode.vertexName).append(" ");
            EdgeNode currentEdge = currentNode.edges[0];
            while(currentEdge != null) {
                sb.append(currentEdge.edge[1].vertexName).append(" ");
                currentEdge = currentEdge.nextE[0];
            }
            currentNode = currentNode.nextV;
        }
        System.out.println(sb.toString());


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