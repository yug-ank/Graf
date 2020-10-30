package com.example.graf;

import android.graphics.Color;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Dfs extends ResultWithOutput{
    StringBuilder res=new StringBuilder();
    public void result() {
        Map<String, Boolean> visited = new HashMap<String, Boolean>();
        for (String i : graph.getNodeList().keySet()) {
            visited.put(i, false);
        }
        Stack<String> stk = new Stack<>();
        stk.push(start);
        while (!stk.empty()) {
            String u = stk.pop();
            graph.getNodeList().get(u).updateHex(Color.YELLOW);
            visited.put(u, true);
            res.append(u+" ");
            for (String v : graph.getAdjacencylist().get(u)) {
                if (!visited.get(v)) {
                    stk.push(v);
                    visited.put(v, true);
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

