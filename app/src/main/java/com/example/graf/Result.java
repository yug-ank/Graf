package com.example.graf;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

public class Result extends Activity {
    static FinalResult finalResult;
    static Graph graph;
    static float prevheight;
    static int type=0;
    static String start;
    private Intent intent;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        intent=getIntent();
        finalResult=new FinalResult(this);
        if(intent.getStringExtra("algo").equals("sp")){
            ShortestPath sp=new ShortestPath();
            sp.result();
            finalResult.setData(graph , prevheight , type , start);
        }
    }
    public void setData(Graph graph , float prevheight ,int type , String start){
        this.graph=graph;
        this.prevheight=prevheight;
        this.type=type;
        this.start=start;
    }
    public void onBackPressed(){
        for(Node i:graph.getNodeList().values()){
            i.updateHex(Color.GRAY);
        }
        for(Edges i:graph.getEdgeList().values()){
            i.updateHex(Color.GRAY);
        }
        super.onBackPressed();
    }
}
