package com.example.graf;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class  DrawWithWeight extends Activity {
    private EditText from, to , weight;
    private int typeflag;
    private drawView dview;
    private Button btn;
    private Intent intent;
    Result result;
    Intent resultintent;
    ResultWithOutput resultWithOutput;
    Intent resultWithOutputIntent;
    boolean doubleBackToExitPressedOnce=false;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawwithweight);
        intent = getIntent();
        String type = intent.getStringExtra(SelectType.type);
        if (type.equals("directed")) {
            typeflag = 1;
        } else {
            typeflag = 0;
        }
        from = (EditText) findViewById(R.id.start);
        to = (EditText) findViewById(R.id.end);
        weight=(EditText)findViewById(R.id.weight);
        dview = new drawView(this);
        btn = (Button) findViewById(R.id.calculate);
        btn.setText(intent.getStringExtra("algo"));
        result = new Result();
        resultWithOutput=new ResultWithOutput();
        resultintent = new Intent(this, Result.class);
        resultWithOutputIntent=new Intent(this , ResultWithOutput.class);
        resultWithOutputIntent.putExtra("algo" , intent.getStringExtra("algo"));
        resultintent.putExtra("algo", intent.getStringExtra("algo"));
        dview.graph.setWeightFlag(1);
    }

    public void onBackPressed() {
        dview.graph = new Graph();
        if(doubleBackToExitPressedOnce){
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce=true;
        Toast.makeText(this , "Please Click back again to Change Graph type" , Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        } , 2000);
    }

    public void addEdge(View view) {
        String u = from.getText().toString();
        String v = to.getText().toString();
        int w=Integer.parseInt(weight.getText().toString());
        dview.setType(typeflag);
        if(typeflag==1 && dview.graph.getWeightList().containsKey(v+u)==true){
            Toast.makeText(this , "edge is already directed" , Toast.LENGTH_SHORT).show();
        }
        dview.graph.addWeight(u , v , w);
        if(typeflag==0){
            dview.graph.addWeight(v , u , w);
        }
        dview.getData(u, v);
    }

    public void calculate(View view) {
            if(intent.getStringExtra("algo").equals("mst")){
                resultWithOutput.setData(dview.graph, dview.height, typeflag);
                startActivity(resultWithOutputIntent);
            }
            if(intent.getStringExtra("algo").equals("sp")){
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popup = inflater.inflate(R.layout.startingvertex, null);
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                final PopupWindow popupWindow = new PopupWindow(popup, width, height, true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    popupWindow.setElevation(20);
                }
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                final EditText input = (EditText) popup.findViewById(R.id.input);
                Button start = (Button) popup.findViewById(R.id.start);
                start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String x = input.getText().toString();
                        popupWindow.dismiss();
                        result.setData(dview.graph, dview.height, typeflag, x);
                        startActivity(resultintent);
                    }
                });
            }
    }
}
