package com.example.graf;

import android.app.Activity;
import android.os.Bundle;

public class Result extends Activity {

    FinalResult finalResult;
    static Graph graph;
    static float prevheight;
    static int type=0;
    static String start;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        finalResult=new FinalResult(this);
        finalResult=new FinalResult(this);
        finalResult.setData(graph , prevheight , type , start);
        setContentView(R.layout.finalresult);
    }
    public void setData(Graph graph , float prevheight ,int type , String start){
        this.graph=graph;
        this.prevheight=prevheight;
        this.type=type;
        this.start=start;
    }
}
