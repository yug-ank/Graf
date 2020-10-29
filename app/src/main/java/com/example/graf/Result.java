package com.example.graf;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

public class Result extends Activity {
    static FinalResult finalResult;
    static Graph graph;
    static float prevheight;
    static int type=0;
    static String start;
    private Intent intent;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        intent=getIntent();
        finalResult=new FinalResult(this);
        setContentView(R.layout.finalresult);
        if(intent.getStringExtra("algo").equals("dfs")){
            Dfs dfs=new Dfs();
            dfs.result();
        }
        if(intent.getStringExtra("algo").equals("ts")){
            Ts ts=new Ts();
            ts.result();
        }
    }
    public void setData(Graph graph , float prevheight ,int type , String start){
        this.graph=graph;
        this.prevheight=prevheight;
        this.type=type;
        this.start=start;
    }
}
