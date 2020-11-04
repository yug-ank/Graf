package com.example.graf;

import android.graphics.Color;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CycleDetection extends ResultWithOutput {
    int found=0;
    public void result(){
        finalResult.setData(graph , prevheight , type , start);
        Map<String , Boolean> visited = new HashMap<>();
        Map<String , Boolean> recStack = new HashMap<>();
        for(String i : graph.getNodeList().keySet()){
            visited.put(i,false);
            recStack.put(i,false);
        }
        for(String i : graph.getNodeList().keySet()){
            if(isCyclic(i,visited,recStack)){
                //graph has a cycle cyclic
                graph.getNodeList().get(i).updateHex(Color.YELLOW);
                found=1;
                break;
            }
        }
    }
    public Boolean isCyclic(String i,Map<String , Boolean> visited ,Map<String , Boolean> recStack){
        if(recStack.get(i)) return true;
        if(visited.get(i)) return false;
        visited.put(i,true);
        recStack.put(i,true);
        ArrayList<String> children = graph.getAdjacencylist().get(i);
        for(String c : children){
            if(isCyclic(c,visited,recStack)) {
                graph.getNodeList().get(c).updateHex(Color.YELLOW);
                graph.getNodeList().get(i).updateHex(Color.YELLOW);
                graph.getEdgeList().get(i+c).updateHex(Color.YELLOW);
                if(type==0)
                    graph.getEdgeList().get(c+i).updateHex(Color.YELLOW);
                return true;
            }
        }
        recStack.put(i,false);
        return  false;
    }
    public int getFound(){
        return found;
    }
}
