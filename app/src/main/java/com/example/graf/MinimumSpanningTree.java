package com.example.graf;

import android.graphics.Color;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MinimumSpanningTree extends ResultWithOutput {
    Map<String , String> Parent;
    Map<String , Integer> key;
    Map<String , Boolean> Visited;
    int TotalWeight;
    public String getMinimum(){
        int min=Integer.MAX_VALUE;
        String minVertex=null;
        for(Map.Entry<String , Integer> i:key.entrySet()){
            if(!Visited.get(i.getKey()) && i.getValue()<min){
                min=i.getValue();
                minVertex=i.getKey();
            }
        }
        return minVertex;
    }
    public void result(){
        Parent=new HashMap<>();
        key=new HashMap<>();
        Visited=new HashMap<>();
        int flag=0;
        TotalWeight=0;
        for(String i:graph.getNodeList().keySet()){
            Parent.put(i , null);
            Visited.put(i , false);
            if(flag==0){
                key.put(i , 0);
                flag=1;
            }
            else{
                key.put(i , Integer.MAX_VALUE);
            }
        }
        for(int i=0;i<graph.getNodeList().size();i++){
            String u=getMinimum();
            Visited.put(u , true);
            for(String v:graph.getAdjacencylist().get(u)){
                if(!Visited.get(v) && graph.getWeight(u , v)<key.get(v)){
                    Parent.put(v , u);
                    key.put(v , graph.getWeight(u , v));
                }
            }
        }
        for(Map.Entry<String , String> i:Parent.entrySet()){
            if(i.getValue()!=null){
                graph.getNodeList().get(i.getKey()).updateHex(Color.parseColor("#93A2DB"));
                if(graph.getEdgeList().get(i.getValue()+i.getKey())!=null){
                    graph.getEdgeList().get(i.getValue()+i.getKey()).updateHex(Color.parseColor("#93A2DB"));
                    if(type==0){
                        graph.getEdgeList().get(i.getKey()+i.getValue()).updateHex(Color.parseColor("#93A2DB"));
                    }
                }
            }
            if(key.get(i.getKey())!=Integer.MAX_VALUE){
                TotalWeight+=key.get(i.getKey());
            }
        }
    }
    public int getTotalWeight(){
        return TotalWeight;
    }
}
