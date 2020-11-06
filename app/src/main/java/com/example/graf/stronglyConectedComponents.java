package com.example.graf;

import android.graphics.Color;
import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import static android.content.ContentValues.TAG;

public class stronglyConectedComponents extends ResultWithOutput {
    //main function for changing colors of graph is DFSUtil
    StringBuilder res = new StringBuilder();

    public void result(){
        Stack<String> stk = new Stack<>();

        // Mark all the vertices as not visited (For first DFS)
        Map<String, Boolean> visited = new HashMap<String, Boolean>();

        for (String i : graph.getNodeList().keySet())
            visited.put(i, false);


        // Fill vertices in stack according to their finishing times
        for(String i : graph.getNodeList().keySet())
            if(!visited.get(i))
                fillOrder(i, visited, stk);


        // Create a reversed graph
        Map<String ,ArrayList<String>> gr = getTranspose();

        // Mark all the vertices as not visited (For second DFS)
        for (String i : graph.getNodeList().keySet()) {
            visited.put(i, false);
        }

        // Now process all vertices in order defined by Stack
        while (!stk.empty()){
            // Pop a vertex from stack
            String u = stk.pop();
            // Print Strongly connected component of the popped vertex
            if(!visited.get(u)){
                res.append(u+" ");
                DFSUtil(u, visited, gr);
                graph.getNodeList().get(u).updateHex(Color.YELLOW);
                res.append(": ");
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

    public Map<String ,ArrayList<String>> getTranspose(){
        Map<String , ArrayList<String>> g = new HashMap<>();
        for (String u : graph.getAdjacencylist().keySet())
            g.put(u ,new ArrayList<String>());
        for (String u : graph.getAdjacencylist().keySet()){
            for (String v : graph.getAdjacencylist().get(u))
                g.get(v).add(u);
        }
        return g;
    }

    // A recursive function to print DFS starting from u
    public void DFSUtil(String u, Map<String, Boolean> visited, Map<String ,ArrayList<String>> gr){
        visited.put(u,true);
        for(String v : gr.get(u)){
            if(!visited.get(v)){
                DFSUtil(v, visited, gr);
                res.append(v+" ");
                graph.getNodeList().get(v).updateHex(Color.YELLOW);
            }
            graph.getEdgeList().get(v + u).updateHex(Color.YELLOW);
            if (type == 0) {
                graph.getEdgeList().get(u + v).updateHex(Color.YELLOW);
            }
        }
    }
    public String getRes(){
        return res.toString();
    }

}
