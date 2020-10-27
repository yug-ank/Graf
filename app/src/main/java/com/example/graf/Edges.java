package com.example.graf;

import android.graphics.Color;

/**
 * this class represents an edge drawn between two nodes
 */
class Edges {
        private float startingx , startingy;
        private float endingx , endingy;
        private int hex;
        public Edges() {
            hex= Color.GRAY;
        }
        /**
          *
          * startingx represents the starting x co-ordinate of an edge
          * startingy represents the starting y co-ordinate of an edge
          * endingx represents the ending x co-ordinate of an edge
          * endingy represents the ending y co-ordinate of an edge
         */
        public float getStartingx() {
            return startingx;
        }

        public float getStartingy() {
            return startingy;
        }

        public float getEndingx() {
            return endingx;
        }

        public float getEndingy() {
            return endingy;
        }

        public void generateEdge(float startx , float starty , float endx , float endy , float radius){
            /**
             * function to find the starting and ending co-ordintate mathematicallly to generate a given edge
             * m represent the slope of line
             * angle represents the value of slope in radian
             * co-ordinates are found using the formulas
             * new x= old x +- radius*sin(angle)
             * new y= old y +- radius*cos(angle)
             * values are added or subtracted on the basis of positions of both the node to create a optimal edge between them
             */
            float m=((starty-endy)/(startx-endx));
            double angle=  Math.atan(m);
            float sinvalue= (float) Math.sin(angle);
            float cosvalue=(float) Math.cos(angle);
            sinvalue=(radius*sinvalue);
            cosvalue=(radius*cosvalue);
            float startingy=0 , endingy=0 , startingx=0 , endingx=0;
            startingx = startx>endx?startx - cosvalue:startx+cosvalue;
            endingx = endx>startx?endx-cosvalue:endx+cosvalue;
            if(starty<endy){
                startingy = startx>endx?starty-sinvalue:starty+sinvalue;
                endingy = startx > endx ? endy + sinvalue : endy - sinvalue;
            }
            else{
                startingy=startx<endx?starty+sinvalue:starty-sinvalue;
                endingy=startx<endx?endy-sinvalue:endy+sinvalue;
            }
            this.startingx = startingx;
            this.startingy = startingy;
            this.endingx = endingx;
            this.endingy = endingy;
        }
    public void updateHex(int hex) {
        this.hex=hex;
    }
    public int getHex(){
            return hex;
    }
}
