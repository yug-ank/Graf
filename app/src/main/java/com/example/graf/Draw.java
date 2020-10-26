package com.example.graf;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

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
        btn.setText(intent.getStringExtra("algo"));
    }
    public void onBackPressed(){
        dview.graph=new Graph();
    }
    public void addEdge(View view){
        String u=from.getText().toString();
        String v=to.getText().toString();
        dview.setType(typeflag);
        dview.getData(u , v);
    }
    public void calculate(View view){
            final Dfs dfs=new Dfs(this);
            final Intent dfsintent=new Intent(this , Result.class);
            LayoutInflater inflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            View popup=inflater.inflate(R.layout.startingvertex , null);
            int width= LinearLayout.LayoutParams.WRAP_CONTENT;
            int height=LinearLayout.LayoutParams.WRAP_CONTENT;
            final PopupWindow popupWindow=new PopupWindow(popup , width , height , true);
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                popupWindow.setElevation(20);
            }
            popupWindow.showAtLocation(view , Gravity.CENTER , 0 , 0);
            final EditText input=(EditText)popup.findViewById(R.id.input);
            Button start=(Button)popup.findViewById(R.id.start);
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String x =input.getText().toString();
                    popupWindow.dismiss();
                    dfs.setData(dview.graph , dview.height  , typeflag , x);
                    startActivity(dfsintent);
                    }
            });
    }
}
