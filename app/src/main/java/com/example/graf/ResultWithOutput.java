package com.example.graf;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class ResultWithOutput extends Activity {
    static FinalResult finalResult;
    static Graph graph;
    static float prevheight;
    static int type=0;
    static String start , end;
    private Intent intent;
    private TextView output;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultwithoutput);
        intent=getIntent();
        output=(TextView) findViewById(R.id.resultText);
        finalResult=new FinalResult(this);
        if(intent.getStringExtra("algo").equals("dfs")){
            Dfs dfs=new Dfs();
            dfs.result();
            output.setText(dfs.getRes());
            finalResult.setData(graph, prevheight, type, start);
        }
        if(intent.getStringExtra("algo").equals("bfs")){
            Bfs bfs=new Bfs();
            bfs.result();
            output.setText(bfs.getRes());
            finalResult.setData(graph, prevheight, type, start);
        }
        if(intent.getStringExtra("algo").equals("ts")){
            Ts ts=new Ts();
            ts.result();
            output.setText(ts.getRes());
            finalResult.setData(graph, prevheight, type, start);
        }
        if(intent.getStringExtra("algo").equals("path")){
            Path path=new Path();
            path.result();
            if(path.getFound()==0){
                output.setText("Path is Highlighted");
            }
            else{
                output.setText("Path not found");
            }
            finalResult.setData(graph , prevheight , type , start);
        }
        if(intent.getStringExtra("algo").equals("mst")){
            MinimumSpanningTree mst=new MinimumSpanningTree();
            mst.result();
            output.setText("Minimum Weight of Graph will be"+mst.getTotalWeight());
            finalResult.setData(graph , prevheight , type , start);
        }
        if(intent.getStringExtra("algo").equals("cd")){
            CycleDetection cd=new CycleDetection();
            cd.result();
            if(cd.getFound()==1){
                output.setText("Cycle found");
            }
            else{
                output.setText("No cycle found");
            }
            finalResult.setData(graph , prevheight , type , start);
        }
    }
    public void setData(Graph graph , float prevheight ,int type , String start){
        this.graph=graph;
        this.prevheight=prevheight;
        this.type=type;
        this.start=start;
    }
    public void setData(Graph graph , float prevheight ,int type , String start , String end){
        this.graph=graph;
        this.prevheight=prevheight;
        this.type=type;
        this.start=start;
        this.end=end;
    }
    public void setData(Graph graph , float prevheight ,int type){
        this.graph=graph;
        this.prevheight=prevheight;
        this.type=type;
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
