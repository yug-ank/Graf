package com.example.graf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class ResultWithOutput extends Activity {
    static FinalResult finalResult;
    static Graph graph;
    static float prevheight;
    static int type=0;
    static String start;
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
        if(intent.getStringExtra("algo").equals("ts")){
            Ts ts=new Ts();
            ts.result();
            output.setText(ts.getRes());
            finalResult.setData(graph, prevheight, type, start);
        }
    }
    public void setData(Graph graph , float prevheight ,int type , String start){
        this.graph=graph;
        this.prevheight=prevheight;
        this.type=type;
        this.start=start;
    }
}
