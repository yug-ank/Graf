package com.example.graf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SelectType extends Activity {
    public static final String type="com.example.Graf.MESSAGE";

    Intent sourceintent , intent;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selecttype);
        sourceintent=getIntent();
        intent=new Intent(this , Draw.class);
        intent.putExtra("algo" , sourceintent.getStringExtra("algo"));
    }
    public void directed(View view){
        intent.putExtra(type , "directed");
        startActivity(intent);
    }
    public void undirected(View view){
        intent.putExtra(type , "undirected");
        startActivity(intent);
    }
}
