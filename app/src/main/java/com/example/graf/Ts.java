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

public class Ts extends Result{

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void result() {
        finalResult.setData(graph , prevheight , type , start);
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
            graph.getNodeList().get(u).updateHex(Color.YELLOW);
            visited.put(u , true);
            for(String v:graph.getAdjacencylist().get(u)){
                if(!visited.get(v)){
                    l.add(new Pair <Integer,String> (postOrder, v));
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
       String ans=null;
        for(Pair<Integer,String> i: l)
            ans+=i.second;

       /* //Dialog Box
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popup = inflater.inflate(R.layout.dialog, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popup, width, height, true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(20);
        }
        View view = null;
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        final TextView answer = (TextView) popup.findViewById(R.id.ans);
        Button ok = (Button) popup.findViewById(R.id.ok);
        answer.setText(ans);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
*/
    }
}

