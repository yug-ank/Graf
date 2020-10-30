package com.example.graf;

import android.app.Activity;
import android.content.Intent;
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

    }
    public void setData(Graph graph , float prevheight ,int type , String start){
        this.graph=graph;
        this.prevheight=prevheight;
        this.type=type;
        this.start=start;
    }
}
