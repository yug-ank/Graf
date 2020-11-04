package com.example.graf;

import android.graphics.Color;

import java.util.HashMap;
import java.util.Map;

public class ShortestPath extends Result {
    public void result(){
        Map<String , Boolean> visited=new HashMap<>();
        Map<String , Integer> shortestDistance=new HashMap<>();
        Map<String , String> Parent=new HashMap<>();
        for(String i:graph.getNodeList().keySet()){
            shortestDistance.put(i , Integer.MAX_VALUE);
            visited.put(i , false);
            Parent.put(start , null);
        }
        shortestDistance.put(start , 0);
        Parent.put(start , null);
        for(int i=0;i<graph.getNodeList().size();i++){
            String nearest=null;
            int shortest=Integer.MAX_VALUE;
            for(String j:graph.getNodeList().keySet()){
                if(!visited.get(j) && shortestDistance.get(j)<shortest){
                    nearest=j;
                    shortest=shortestDistance.get(j);
                }
            }
            visited.put(nearest , true);
            for(String j:graph.getAdjacencylist().get(nearest)){
                if(graph.getWeight(nearest , j)+shortest<shortestDistance.get(j)){
                    Parent.put(j , nearest);
                    shortestDistance.put(j , graph.getWeight(nearest , j)+shortest);
                }
            }
        }
        for(Map.Entry<String , String> i:Parent.entrySet()){
            graph.getNodeList().get(i.getKey()).updateHex(Color.YELLOW);
            if(i.getValue()!=null) {
                if (graph.getEdgeList().get(i.getValue() + i.getKey()) != null) {
                        graph.getEdgeList().get(i.getValue()+i.getKey()).updateHex(Color.YELLOW);
                        if(type==0){
                            graph.getEdgeList().get(i.getKey()+i.getValue()).updateHex(Color.YELLOW);
                        }
                        graph.getNodeList().get(i.getValue()).updateHex(Color.YELLOW);
                }
            }
        }
    }
}
