package com.example.graf;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent=new Intent(this , SelectType.class);
    }
    public void bfs(View view){
        intent.putExtra("algo" , "bfs");
        startActivity(intent);
    }
    public void dfs(View view){
        intent.putExtra("algo" , "dfs");
        startActivity(intent);
    }
    public void ts(View view){
        intent.putExtra("algo" , "ts");
        startActivity(intent);
    }
}