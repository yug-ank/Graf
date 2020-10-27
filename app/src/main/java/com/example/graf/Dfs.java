package com.example.graf;

import android.graphics.Color;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Dfs extends Result{
    public void result() {
        finalResult.setData(graph , prevheight , type , start);
        Map<String , Boolean> visited = new HashMap<String , Boolean>();
        for(String i:graph.getNodeList().keySet()){
                visited.put(i , false);
        }
       // Log.i("rectify" , ""+visited.keySet());
        Stack<String> stk=new Stack<>();
        stk.push(start);
        while(!stk.empty()){
                String u=stk.pop();
                graph.getNodeList().get(u).updateHex(Color.YELLOW);
                visited.put(u , true);
                Log.i("rectify" , "start"+u);
                for(String v:graph.getAdjacencylist().get(u)){
                    if(!visited.get(v)){
                        Log.i("rectify" , "adajcency"+v);
                        stk.push(v);
                        visited.put(v , true);
                        graph.getNodeList().get(v).updateHex(Color.YELLOW);
                        graph.getEdge(u , v).updateHex(Color.YELLOW);
                        graph.getEdge(v , u).updateHex(Color.YELLOW);
                    }
                }

        }
       finalResult.setData(graph , prevheight , type , start);
    }
}
