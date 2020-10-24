package com.example.graf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Draw extends Activity {

    private  EditText from , to;
    private int typeflag;
    private drawView dview;
    private Button btn;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw);
        Intent intent=getIntent();
        String type=intent.getStringExtra(SelectType.type);
        if(type.equals("directed")){
            typeflag=1;
        }
        else{
            typeflag=0;
        }
        from=(EditText)findViewById(R.id.start);
        to=(EditText)findViewById(R.id.end);
        dview= new drawView(this);
        btn=(Button)findViewById(R.id.calculate);
        btn.setText("Calculate"+" "+intent.getStringExtra("algo"));
    }
    public void addEdge(View view){
        String u=from.getText().toString();
        String v=to.getText().toString();
        dview.setType(typeflag);
        dview.getData(u , v);
    }
    public void calculate(View view){
            Dfs dfs=new Dfs(this);
            setContentView(R.layout.result);
            dfs.setData(dview.graph , dview.height  , typeflag);
    }
}
