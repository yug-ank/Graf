package com.example.graf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectType extends Activity {
    public static final String type="com.example.Graf.MESSAGE";
    Button dg;
    Button udg;
    Intent sourceintent , intent , weightIntent;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selecttype);
        dg=(Button)findViewById(R.id.dg);
        udg=(Button)findViewById(R.id.udg);
        sourceintent=getIntent();
        intent=new Intent(this , Draw.class);
        weightIntent=new Intent(this , DrawWithWeight.class);
        intent.putExtra("algo" , sourceintent.getStringExtra("algo"));
        weightIntent.putExtra("algo" , sourceintent.getStringExtra("algo"));
        if(sourceintent.getStringExtra("algo").equals("ts")){
            udg.setEnabled(false);
        }
    }
    public void directed(View view){
        intent.putExtra(type , "directed");
        weightIntent.putExtra(type , "directed");
        if(sourceintent.getStringExtra("algo").equals("mst")){
                startActivity(weightIntent);
        }
        else {
            startActivity(intent);
        }
    }
    public void undirected(View view){
        intent.putExtra(type , "undirected");
        weightIntent.putExtra(type , "undirected");
        if(sourceintent.getStringExtra("algo").equals("mst")){
                startActivity(weightIntent);
        }
        else {
            startActivity(intent);
        }
    }
}
