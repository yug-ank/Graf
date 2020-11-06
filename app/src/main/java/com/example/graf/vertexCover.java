package com.example.graf;

import android.graphics.Color;

import java.util.HashMap;
import java.util.Map;

public class vertexCover extends ResultWithOutput {
    StringBuilder res = new StringBuilder();
    public void result(){
        Map<String , Boolean> visited = new HashMap<>();
        for(String i : graph.getNodeList().keySet())
            visited.put(i,false);
        for(String u : graph.getAdjacencylist().keySet()){
            if(!visited.get(u)){
                for (String v : graph.getAdjacencylist().get(u)){
                    if(!visited.get(v)){
                        visited.put(u,true);
                        visited.put(v,true);
                        graph.getNodeList().get(u).updateHex(Color.YELLOW);
                        graph.getNodeList().get(v).updateHex(Color.YELLOW);
                        res.append(u+" ");
                        res.append(v+" ");
                        break;
                    }
                }
            }
        }
    }
    public String getRes(){
        return res.toString();
    }
}
