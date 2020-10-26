package com.example.graf;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import androidx.annotation.Nullable;

public class Dfs extends View implements Serializable {
    static Graph graph;
    static float prevheight , newheight , flag=0 ;
    static int type=0;
    static String start;
    Paint circlePaint , linePaint , textPaint;
    public Dfs(Context context) {
        super(context);
        init(null);
    }

    public Dfs(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Dfs(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public Dfs(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    public void init(@Nullable AttributeSet attrs){
        circlePaint=new Paint();
        textPaint=new Paint(Paint.UNDERLINE_TEXT_FLAG);
        linePaint=new Paint();
        circlePaint.setColor(Color.GRAY);
        circlePaint.setAntiAlias(true);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.GRAY);
        float screenwidth=this.getResources().getDisplayMetrics().widthPixels;
        float stroke=screenwidth*0.0035f;
        linePaint.setStrokeWidth(stroke);
        linePaint.setStyle(Paint.Style.STROKE);
    }
    public void setData(Graph graph , float prevheight ,int type , String start){
        this.graph=graph;
        this.prevheight=prevheight;
        this.type=type;
        this.start=start;

        this.invalidate();
    }
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        newheight=this.getHeight();
        if(flag==0) {
            float ratio = newheight / prevheight;
            graph.updateHeight(ratio , type , this.getResources().getDisplayMetrics().widthPixels);
            flag=1;
        }
        Log.i("rectify" , start);
        for(Map.Entry<String , ArrayList<String>> i:graph.getAdjacencylist().entrySet()){
            Node nu=graph.getNode(i.getKey());
            Rect bound=new Rect();
            textPaint.setUnderlineText(false);
            float textsize=nu.getTextSize();
            textPaint.setTextSize(textsize);
            canvas.drawCircle(nu.getCenterx() , nu.getCentery() , nu.getRadius() , circlePaint);
            String u=i.getKey();
            textPaint.getTextBounds(u , 0 , u.length() , bound);
            canvas.drawText(u , nu.getCenterx() , nu.getCentery()+(bound.height()/2) , textPaint);
            for(String v:i.getValue()){
                Node nv=graph.getNode(v);
                canvas.drawCircle(nv.getCenterx() , nv.getCentery() , nv.getRadius() , circlePaint);
                textPaint.getTextBounds(v , 0 , v.length() , bound);
                canvas.drawText(v , nv.getCenterx() , nv.getCentery()+(bound.height()/2) , textPaint);
                Edges e=graph.getEdge(u , v);
                canvas.drawLine(e.getStartingx() , e.getStartingy() , e.getEndingx() , e.getEndingy() , linePaint);
                if(type==1){
                    Stroke s=graph.getStroke(u , v);
                    canvas.drawLine(s.getStartx() , s.getStarty() , s.getEndx1() , s.getEndy1() , linePaint);
                    canvas.drawLine(s.getStartx() , s.getStarty() , s.getEndx2() , s.getEndy2() , linePaint);
                }
            }
        }
    }
}
