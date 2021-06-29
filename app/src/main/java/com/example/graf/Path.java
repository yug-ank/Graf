package com.example.graf;

import android.graphics.Color;
import android.util.Log;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Path extends ResultWithOutput {
    int found=-1;
    public void result(){
        Map<String , String> parent=new HashMap<>();
        Map<String , Boolean>visited=new HashMap<>();
        Map<String , Integer>depth=new HashMap<>();
        for (String i : graph.getNodeList().keySet()) {
            visited.put(i, false);
        }
        Stack<String> stk = new Stack<>();
        stk.push(start);
        while (!stk.empty()) {
            String u = stk.pop();
            if(u.equals(end)){
                found=0;
                break;
            }
            visited.put(u, true);
            for (String v : graph.getAdjacencylist().get(u)) {
                if (!visited.get(v)) {
                    stk.push(v);
                    visited.put(v, true);
                }
            }
        }
        if(found==0){
            for(String i:graph.getNodeList().keySet()){
                visited.put(i , false);
                parent.put(i , null);
            }
            Queue<String> que=new LinkedList<>();
            for(String i:graph.getNodeList().keySet()){
                if(visited.get(i)==false){
                    que.add(i);
                    depth.put(i , 0);
                    while(que.peek()!=null){
                        String u=que.poll();
                        visited.put(u , true);
                        for(String v:graph.getAdjacencylist().get(u)){
                            if(!visited.get(v)){
                                que.add(v);
                                parent.put(v , u);
                                depth.put(v , depth.get(u)+1);
                            }
                        }
                    }
                }
            }
            while(depth.get(start)!=depth.get(end)){
                if(depth.get(start)>depth.get(end)){
                    if(parent.get(start)!=null){
                        graph.getNodeList().get(parent.get(start)).updateHex(Color.parseColor("#93A2DB"));
                        graph.getNodeList().get(start).updateHex(Color.parseColor("#93A2DB"));
                        graph.getEdgeList().get(parent.get(start)+start).updateHex(Color.parseColor("#93A2DB"));
                        if(type==0){
                          graph.getEdgeList().get(start+parent.get(start)).updateHex(Color.parseColor("#93A2DB"));
                        }
                        start=parent.get(start);
                    }
                }
                else{
                    if(parent.get(end)!=null){
                        graph.getNodeList().get(parent.get(end)).updateHex(Color.parseColor("#93A2DB"));
                        graph.getNodeList().get(end).updateHex(Color.parseColor("#93A2DB"));
                        graph.getEdgeList().get(parent.get(end)+end).updateHex(Color.parseColor("#93A2DB"));
                        if(type==0){
                            graph.getEdgeList().get(end+parent.get(end)).updateHex(Color.parseColor("#93A2DB"));
                        }
                        end=parent.get(end);
                    }
                }
            }
            if(depth.get(start)==depth.get(end) &&parent.get(start)!=null && parent.get(end)!=null &&
                                    parent.get(start).equals(parent.get(end))) {
                if (parent.get(start) != null) {
                    graph.getNodeList().get(parent.get(start)).updateHex(Color.parseColor("#93A2DB"));
                    if (graph.getEdgeList().get(parent.get(start) + start) != null) {
                        graph.getEdgeList().get(parent.get(start) + start).updateHex(Color.parseColor("#93A2DB"));
                        if (type == 0) {
                            graph.getEdgeList().get(start + parent.get(start)).updateHex(Color.parseColor("#93A2DB"));
                        }
                    }
                    if (parent.get(end) != null) {
                        if (graph.getEdgeList().get(parent.get(end) + end) != null) {
                            graph.getEdgeList().get(parent.get(end) + end).updateHex(Color.parseColor("#93A2DB"));
                            if (type == 0) {
                                graph.getEdgeList().get(end + parent.get(end)).updateHex(Color.parseColor("#93A2DB"));
                            }
                        }
                    }
                }
            }
        }
    }
    public int getFound(){
        return found;
    }
}
