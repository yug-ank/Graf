package com.example.graf;

import android.graphics.Color;
import android.os.Handler;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.logging.LogRecord;

public class Dfs extends Result{

    public void result() {
        finalResult.setData(graph , prevheight , type , start);
        Map<String , Boolean> visited = new HashMap<String , Boolean>();
        for(String i:graph.getNodeList().keySet()){
                visited.put(i , false);
        }
        Stack<String> stk=new Stack<>();
        stk.push(start);
        while(!stk.empty()){
                String u=stk.pop();
                graph.getNodeList().get(u).updateHex(Color.YELLOW);
                visited.put(u , true);
              for(String v:graph.getAdjacencylist().get(u)){
                    if(!visited.get(v)){
                        stk.push(v);
                        visited.put(v , true);
                        graph.getEdgeList().get(u+v).updateHex(Color.YELLOW);
                        graph.getEdgeList().get(v+u).updateHex(Color.YELLOW);
                     }
                }
            Handler handler=new Handler();
              handler.postDelayed(new Runnable() {
                  @Override
                  public void run() {
                      finalResult.setData(graph , prevheight , type , start);
                  }
              }, 30000);
        }
    }
}

