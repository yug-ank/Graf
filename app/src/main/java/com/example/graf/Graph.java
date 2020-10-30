package com.example.graf;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Graph {
    private Map<String , Node> nodeList;
    private Map<String , Edges> edgeList;
    private Map<String , ArrayList<String>> adjacencyList;
    private Map<String , Stroke> strokeList;

    /**
     * nodeList contains the value of a node mapped to its co-ordinate on the view
     * edgeList contains edges created as a string with its ending node value appened to its starting node value mapped to
     * co-ordinate required to draw an edge
     * adjacencyList contains the graph in list format
     * strokeList contains the co-ordinate to draw a stroke in a directed graph mapped to same format as edges
     */
    public Graph() {
        nodeList=new HashMap<>();
        edgeList=new HashMap<>();
        adjacencyList=new HashMap<>();
        strokeList=new HashMap<>();
    }

    public Map<String, Node> getNodeList() {
        //returns the nodeList
        return nodeList;
    }

    public Map<String, Edges> getEdgeList() {
        //returns the edgeList
        return edgeList;
    }

    public Map<String, Stroke> getStrokelist() {
        //returns the strokeList
        return strokeList;
    }
    public Map<String, ArrayList<String>> getAdjacencylist() {
        //returns the adjacencyList
        return adjacencyList;
    }

    public void addVertex(String s){
        //add a node to graph if not present
        adjacencyList.put(s , new ArrayList<String>());
    }

    public void addUndirectedEdge(String s , String d){
        //add an edge to the if not already present
        if(!adjacencyList.containsKey(s)){
            addVertex(s);
        }
        if(!adjacencyList.containsKey(d)){
            addVertex(d);
        }
        adjacencyList.get(s).add(d);
        adjacencyList.get(d).add(s);
    }

    public void addDirectedEdge(String s , String d){
        //add an edge to the if not already present
        if(!adjacencyList.containsKey(s)){
            addVertex(s);
        }
        if(!adjacencyList.containsKey(d)){
            addVertex(d);
        }
        adjacencyList.get(s).add(d);
    }

    public void createNode(String u , Node n){
        //add a node with its co-ordinate if not present in nodeList
        nodeList.put(u , n);
    }

    public void createEdge(String s , Edges e){
        //add an edge between two node with its co-ordinate if not present in edgeList
        edgeList.put(s , e);
    }
    public void createStroke(String s , Stroke stroke){
        //add a stroke to the map strokeList if not present
        strokeList.put(s , stroke);
    }

    public boolean hasVertex(int Vertex){
        /**
         *checks if the given vertex is already present in the graph
         *if present returns true
         * if not present returns false
         */
        if(adjacencyList.containsKey(Vertex)){
            return true;
        }
        return false;
    }

    public int getSize(){
        //returns the number of nodes in a given Graph
        return adjacencyList.keySet().size();
    }

    public Node getNode(String vertex){
        //retruns the co-ordinate required to draw a node on the canvas
        return nodeList.get(vertex);
    }

    public boolean containsUndirectedEdge(String u , String v){
        /**
         * function to check if a given edge starting from node u and ending at node v is present in the edgeList or not
         * returns true if present
         * returns false if not
         */
        if(edgeList.containsKey(""+u+v) || edgeList.containsKey(""+v+u)){
            return true;
        }
        return false;
    }

    public boolean containDirectedEdge(String u , String v){
        if(edgeList.containsKey(""+u+v)){
            return true;
        }
        else
            return false;
    }

    public Edges getEdge(String u , String v){

        return edgeList.get(""+u+v);
    }

    public Stroke getStroke(String u , String v){
        return strokeList.get(""+u+v);
    }
    public int optimalCircle(float x  , float y , String n , String m , float radius){
        /**
         * startx represents the x co-ordinate of u for edge drawn from u to v
         * starty represnts the y co-ordinate of u for edge drawn from u to v
         */
        float startx = 0 , starty=0;
        /**
         * function to check if the new node overlaps any existing node
         * checks if the distance between two nodes is less then the radius or not
         * return 1 if overlapping
         */
        for(Map.Entry<String , Node> i:nodeList.entrySet()){
            float dx = (x - i.getValue().getCenterx()) * (x - i.getValue().getCenterx());
            float dy = (y - i.getValue().getCentery()) * (y - i.getValue().getCentery());
            float fd = (float) Math.sqrt(dx + dy);
            if (fd <=(2*i.getValue().getRadius())){
                return 1;
            }
            if(i.getKey()==n || i.getKey()==m){
                startx=i.getValue().getCenterx();
                starty=i.getValue().getCentery();
            }
        }
        /**
         * function to check if a node being created at the co-ordinates overlaps any existing edge
         * if the perpendicular distacne of given node from the line is less than radius then it overlaps
         * return -1 if overlap
         */
        float a , b , c , pd , md , dfs , efs;
        for(Map.Entry<String , Edges> i:edgeList.entrySet()){
            b=-1;
            a=((i.getValue().getEndingy()-i.getValue().getStartingy())/(i.getValue().getEndingx()-i.getValue().getStartingx()));
            c=i.getValue().getStartingy()-(a*i.getValue().getStartingx());
            pd=Math.abs((a*x)+(b*y)+c);
            pd= (float) (pd/(Math.sqrt((Math.pow(a , 2))+(Math.pow(b , 2)))));
            md= (float) Math.sqrt(Math.pow(i.getValue().getEndingx()-i.getValue().getStartingx() , 2)-Math.pow(i.getValue().getStartingy()-i.getValue().getEndingy() , 2));
            dfs= (float) Math.sqrt(Math.pow(x-i.getValue().getStartingx()+5 , 2)-Math.pow(y-i.getValue().getStartingy()+5 , 2));
            efs= (float) Math.sqrt(Math.pow(i.getValue().getEndingx()-x+5 , 2)-Math.pow(y-i.getValue().getEndingy()+5 , 2));
            if(pd<=radius && dfs+efs<=(1.2*md)){
                return -1;
            }
        }
        //if the node to draw is optimal satisfying the above criteria then return 0
        return 0;
    }

    /**
     * changes the value of radius and repositions every edge on the basis of new scaled radius
     * @param radius
     */
    public void onNewScale(float radius ,int type , float screenwidth){
        for(Node i:nodeList.values()){
            i.updateRadius(radius);
        }
        edgeList.clear();
        strokeList.clear();
        for(Map.Entry<String , ArrayList<String>> i:adjacencyList.entrySet()){
            float startx=nodeList.get(i.getKey()).getCenterx();
            float starty=nodeList.get(i.getKey()).getCentery();
            for(String x:i.getValue()){
                float endx=nodeList.get(x).getCenterx();
                float endy=nodeList.get(x).getCentery();
                //Log.i("rectify" , " u:"+i.getValue()+" v:"+x);
                Edges e=new Edges();
                e.generateEdge(startx , starty , endx , endy , radius);
                String s=""+i.getKey()+x;
                createEdge(s , e);
                if(type==1){
                    Stroke strk=new Stroke();
                    strk.generateStroke(e.getStartingx() , e.getStartingy() , e.getEndingx() , e.getEndingy() , screenwidth);
                    createStroke(s , strk);
                }
            }
        }
    }
    public void updateHeight(float ratio , int type , float screenwidth) {

        for (Node i : nodeList.values()) {
            float y = i.getCentery() * ratio;
            i.updatey(y);
        }
        Map<String , Integer> edgeHexValue=new HashMap<>();
        for(Map.Entry<String  , Edges> i:edgeList.entrySet()){
            edgeHexValue.put(i.getKey() , i.getValue().getHex());
        }
        edgeList.clear();
        strokeList.clear();
        for (Map.Entry<String, ArrayList<String>> i : adjacencyList.entrySet()) {
            float startx = nodeList.get(i.getKey()).getCenterx();
            float starty = nodeList.get(i.getKey()).getCentery();
            for (String x : i.getValue()) {
                float endx = nodeList.get(x).getCenterx();
                float endy = nodeList.get(x).getCentery();
                //Log.i("rectify" , " u:"+i.getValue()+" v:"+x);
                Edges e = new Edges();
                e.generateEdge(startx, starty, endx, endy, nodeList.get(i.getKey()).getRadius());
                String s = "" + i.getKey() + x;
                createEdge(s, e);
                if(type==1){
                    Stroke strk=new Stroke();
                    strk.generateStroke(e.getStartingx() , e.getStartingy() , e.getEndingx() , e.getEndingy() , screenwidth);
                    createStroke(s , strk);
                }
            }
        }
        for(Map.Entry<String  , Integer> i:edgeHexValue.entrySet()){
            edgeList.get(i.getKey()).updateHex(i.getValue());
        }
    }
}
