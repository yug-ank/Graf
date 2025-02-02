package com.example.graf;

import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.logging.LogRecord;

public class Ts extends ResultWithOutput{
    StringBuilder ans=new StringBuilder();
    public void result() {
        Map<String , Boolean> visited = new HashMap<String , Boolean>();
        ArrayList <Pair<Integer,String>> l = new ArrayList<Pair <Integer,String> >();
        for(String i:graph.getNodeList().keySet()){
            visited.put(i , false);
        }
        Stack<String> stk=new Stack<>();
        int postOrder=1;
        stk.push(start);
        l.add(new Pair <Integer,String> (postOrder, start));
        while(!stk.empty()){
            String u=stk.pop();
            postOrder++;
            graph.getNodeList().get(u).updateHex(Color.parseColor("#93A2DB"));
            visited.put(u , true);
            for(String v:graph.getAdjacencylist().get(u)){
                if(!visited.get(v)){
                    l.add(new Pair <Integer,String> (postOrder, v));
                    stk.push(v);
                    visited.put(v , true);
                    graph.getEdgeList().get(u+v).updateHex(Color.parseColor("#93A2DB"));
                }
            }

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            l.sort(new Comparator<Pair<Integer,String>>() {
                public int compare(Pair<Integer,String> o1, Pair<Integer,String> o2) {
                    if (o1.first > o2.first) {
                        return -1;
                    } else if (o1.first.equals(o2.first)) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });
        }
        for(Pair<Integer,String> i: l)
            ans.append(i.second+" ");
    }
    public String getRes(){
        return ans.toString();
    }
}

