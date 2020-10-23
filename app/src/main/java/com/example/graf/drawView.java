package com.example.graf;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import androidx.annotation.Nullable;

/**
 * class to draw the graph on the convas
 */
public class drawView extends View {
    private float scaling=1;
    static Graph graph=new Graph();
    Paint circlePaint , linePaint , textPaint;
    static String u , v;
    static int flagu, flagv , edge;
    static int prevSize=0;
    static float radius;
    static int flagr=0;
    static float height;
    static float screenwidth;

    /**
     * scaling used to reduce the scale of radius of node when number of nodes increase a particular value
     * graph represents the object of graph class
     * circlePaint , textPaint and LinePaint represents the property of circlePaint of the mentioned figures
     * u and v represents the starting and ending edge
     * flagu represents if node u is already present or not(0 if present 1 if not)
     * flagv to check if node v is already present or not(0 if present 1 if not)
     * edge to check if given edge is already present or not(0 if present 1 if not)
     * prevSize represents the size nodeList every time the value of scaling is changed
     * radius presents the value of value radius to draw with
     * flagr to check if value of radius is set or not(1 if set else 0)
     * screenwidth stores the value of width of view
     */
    public drawView(Context context) {
        super(context);
        init(null);
    }
    public drawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }
    public drawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public drawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    public void init(@Nullable AttributeSet atrs){
        circlePaint=new Paint();
        textPaint=new Paint(Paint.UNDERLINE_TEXT_FLAG);
        linePaint=new Paint();
        circlePaint.setAntiAlias(true);
        textPaint.setColor(Color.RED);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.BLACK);
        screenwidth=this.getResources().getDisplayMetrics().widthPixels;
        float stroke=screenwidth*0.0035f;
        linePaint.setStrokeWidth(stroke);
        linePaint.setStyle(Paint.Style.STROKE);
        this.setFocusableInTouchMode(true);
    }
    public void getData(String u , String v){
        /**
         * gets the starting node u  and and ending node v to draw an edge between and sets the value of flagu
         * flagv and edge
         */
        this.u=u;
        this.v=v;
        if(!graph.getAdjacencylist().containsKey(u)){
            flagu=1;
        }
        if(!graph.getAdjacencylist().containsKey(v)){
            flagv=1;
        }
        if(!graph.containsEdge(u  , v)) {
            edge = 1;
        }
        //if the node are already present we only need to draw the edge
        if(edge==1 && flagu==0 && flagv==0){
            graph.addEdge(u , v);
            Node nu=graph.getNode(u);
            Node nv=graph.getNode(v);
            Edges e=new Edges();
            e.generateEdge(nu.getCenterx() , nu.getCentery() , nv.getCenterx() , nv.getCentery() , nu.getRadius());
            String s=""+u+v;
            graph.createEdge(s , e);
            edge=0;
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //find the value of radius on the basis of height of the view
        if(flagr==0) {
            height=this.getHeight();
            radius = this.getHeight() / 8;
            flagr=1;
        }
        //change the value of scaling if the condition is matcched
        if(graph.getSize()==prevSize+5){
            scaling-=(scaling*0.1);
            radius=radius*scaling;
            Log.i("rectify" , "graphsize:"+graph.getSize()+"radius:"+radius);
            graph.onNewScale(radius);
            prevSize+=5;
        }
        canvas.drawColor(Color.WHITE);
        for(Map.Entry<String , Node> i:graph.getNodeList().entrySet()){
            float x=i.getValue().getCenterx();
            float y=i.getValue().getCentery();
            float radius=i.getValue().getRadius();
            float textsize=i.getValue().getTextSize();
            canvas.drawCircle(x , y , radius , circlePaint);
            String s=""+i.getKey();
            Rect bound=new Rect();
            textPaint.setTextSize(textsize);
            textPaint.setUnderlineText(false);
            textPaint.getTextBounds(s , 0 , s.length() , bound);
            canvas.drawText(s , x , y+(bound.height()/2) , textPaint);
        }
        for(Map.Entry<String  , Edges> i:graph.getEdgeList().entrySet()) {
            float sx=i.getValue().getStartingx();
            float sy=i.getValue().getStartingy();
            float ex=i.getValue().getEndingx();
            float ey=i.getValue().getEndingy();
            canvas.drawLine(sx , sy , ex , ey , linePaint);
        }
       /* for(stroke i:graph.getStrokelist().values()){
            Paint pathpaint=new Paint();
            pathpaint.setAntiAlias(true);
            pathpaint.setStyle(Paint.Style.FILL_AND_STROKE);
            pathpaint.setColor(Color.BLUE);
            Path path=new Path();
            path.moveTo(i.getStartx() , i.getStarty());
            path.lineTo(i.getEndx1() , i.getEndy1());
            //path.moveTo(i.getEndx1() , i.getEndy1());
            path.lineTo(i.getEndx2() , i.getEndy2());
            //path.moveTo(i.getEndx2() , i.getEndy2());
            path.lineTo(i.getStartx() , i.getStarty());
            path.close();
            canvas.drawPath(path , pathpaint);
        }*/
        invalidate();
    }

    /**
     * create nodes and edges at the given touch on the screen
     * @param event
     * @return
     */
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();
        if((event.getAction()==MotionEvent.ACTION_DOWN ) && flagu==1 ) {
            int i=graph.optimalCircle(x , y , u , v , radius);
            if(i==0){
                Node n=new Node(x , y , radius , radius);
                graph.addVertex(u);
                graph.createNode(u , n);
                flagu=0;
                if(edge==1 && flagu==0 && flagv==0){
                    graph.addEdge(u , v);
                    Node nu=graph.getNode(u);
                    Node nv=graph.getNode(v);
                    Edges e=new Edges();
                    e.generateEdge(nu.getCenterx() , nu.getCentery() , nv.getCenterx() , nv.getCentery() , nu.getRadius());
                    String s=""+u+v;
                    graph.createEdge(s , e);
                    edge=0;
                }
            }
            else if(i==1){
                Toast.makeText(getContext() , "Vertex will overlap another vertex" , Toast.LENGTH_SHORT).show();
            }
            else if(i==-1){
                Toast.makeText(getContext() , "edge will overlap another vertex" , Toast.LENGTH_SHORT).show();
            }
        }
        else if((event.getAction()==MotionEvent.ACTION_DOWN) && flagv==1 ) {
            int i=graph.optimalCircle(x , y , u , v , radius);
            if(i==0){
                Node n=new Node(x , y , radius , radius);
                graph.addVertex(v);
                graph.createNode(v , n);
                flagv=0;
                if(edge==1 && flagu==0 && flagv==0){
                    graph.addEdge(u , v);
                    Node nu=graph.getNode(u);
                    Node nv=graph.getNode(v);
                    Edges e=new Edges();
                    e.generateEdge(nu.getCenterx() , nu.getCentery() , nv.getCenterx() , nv.getCentery() , nu.getRadius());
                    String s=""+u+v;
                    graph.createEdge(s , e);
                    edge=0;
                }
            }
            else if(i==1){
                Toast.makeText(getContext() , "Vertex will overlap another vertex" , Toast.LENGTH_SHORT).show();
            }
            else if(i==-1){
                Toast.makeText(getContext() , "edge will overlap another edge" , Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }
}
