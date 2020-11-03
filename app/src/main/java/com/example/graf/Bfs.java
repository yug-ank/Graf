package com.example.graf;

import android.graphics.Color;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Bfs extends ResultWithOutput {
    StringBuilder res = new StringBuilder();
    public void result(){
        Map<String, Boolean> visited = new HashMap<String, Boolean>();
        String u = start;
        for (String i : graph.getNodeList().keySet()) {
            visited.put(i, false);
        }
        LinkedList<String> queue = new LinkedList<>();
        visited.put(u ,true);
        queue.add(u);
        while (queue.size() != 0){
            u = queue.poll();
            graph.getNodeList().get(u).updateHex(Color.YELLOW);
            res.append(u+" ");
            for(String v : graph.getAdjacencylist().get(u)){
                if(!visited.get(v)){
                    visited.put(v,true);
                    queue.add(v);
                    graph.getEdgeList().get(u + v).updateHex(Color.YELLOW);
                    if (type == 0) {
                        graph.getEdgeList().get(v + u).updateHex(Color.YELLOW);
                    }
                }
            }
        }
    }
    public String getRes(){
        return res.toString();
    }

}
