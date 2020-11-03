package com.example.graf;

import android.graphics.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class scc extends ResultWithOutput {
    //main function for changing colors of graph is DFSUtil
    StringBuilder res = new StringBuilder();
    public void result(){
        Map<String, Boolean> visited = new HashMap<String, Boolean>();
        Stack<String> stk = new Stack<>();
        String u;
        // Mark all the vertices as not visited (For first DFS)
        for (String i : graph.getNodeList().keySet()) {
            visited.put(i, false);
        }

        // Fill vertices in stack according to their finishing times
        for(String i : graph.getNodeList().keySet()){
            if(!visited.get(i)){
                fillOrder(i, visited, stk);
            }
        }

        // Create a reversed graph
        Graph gr = getTranspose();

        // Mark all the vertices as not visited (For second DFS)
        for (String i : gr.getNodeList().keySet()) {
            visited.put(i, false);
        }

        // Now process all vertices in order defined by Stack
        while (!stk.empty()){
            // Pop a vertex from stack
            u = stk.pop();

            // Print Strongly connected component of the popped vertex
            if(!visited.get(u)){
                DFSUtil(u, visited, gr);
                res.append("\n");
            }
        }
    }

    public void fillOrder(String u, Map<String, Boolean> visited, Stack<String> stk){
        // Mark the current node as visited and print it
        visited.put(u, true);

        // Recur for all the vertices adjacent to this vertex
        for(String v : graph.getAdjacencylist().get(u)){
            if(!visited.get(v)){
                fillOrder(v, visited, stk);
            }
        }
        stk.push(u);
    }

    public Graph getTranspose(){
        Graph g = new Graph();
        //yugank
        return g;
    }

    // A recursive function to print DFS starting from u
    public void DFSUtil(String u, Map<String, Boolean> visited, Graph gr){
        visited.put(u,true);
//        gr.getNodeList().get(u).updateHex(Color.YELLOW);
        res.append(u+" ");
        for(String v : gr.getAdjacencylist().get(u)){
            if(!visited.get(v)){
//                gr.getEdgeList().get(u + v).updateHex(Color.YELLOW);
//                if (type == 0) {
//                    gr.getEdgeList().get(v + u).updateHex(Color.YELLOW);
//                }
                DFSUtil(v, visited, gr);
            }
        }
    }
}
