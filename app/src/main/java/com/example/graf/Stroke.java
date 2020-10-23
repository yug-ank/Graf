package com.example.graf;

/**
 * this class represents the strokes drawn in directed graph
 */
class Stroke{
            private float startx , starty , endx1 , endy1 , endx2 , endy2;

            /**
             *
             * startx represents the x co-ordinate of origin of stroke
             * starty represents the y co-ordinate of origin of stroke
             * endx1 represents the x co-ordinate of one of the end of stroke
             * endy1 represents the y co-ordinate of one of the end of stroke
             * endx2 represents the x co-ordinate of other end of stroke
             * endy2 represents the y co-ordinate of other end of stroke
             */
            public Stroke(){

            }
            public float getStartx(){
                    return startx;
            }

            public float getStarty(){
                    return starty;
            }

            public float getEndx1(){
                    return endx1;
            }

            public float getEndy1(){
                    return endy1;
            }

            public float getEndx2(){
                    return endx2;
            }

            public float getEndy2(){
                    return endy2;
            }

            public void generateStroke(float startx , float starty , float endx , float endy , float screenwidth){
                /**
                 * function to create strokes for directed graph at an angle of 45 degree with the edge
                 * startx and starty represents the starting of an edge
                 * endx and endy represents the ending of an edge
                 * screenwidth represents the width of screen to find an optimal value of length for stroke
                 * endx1 , endx2 , endy1 and endy2 represents the end of the stroke on both side of edge
                 * midx and midy represents the co-ordinate of mid point between the two ends of the stroke
                 * m represents the slope
                 * angle is tha value of slope in radian
                 * midx = endx +- cos(angle)*d
                 * midy=  endy+- sin(angle)*d
                 * l90 represents the slope of line perpendicular to given edge
                 * l45 represents the slope of line at 45 degree to given edge
                 * c90 represents the value of c for line perpendicular in the equation y=m*x+c
                 * c45 represents the value of c for line at 45 degree in the equation y=m*x+c
                 */
                float endx1 = 0, endx2=0 , endy1=0 , endy2=0 , midx=0 , midy=0;
                    float m=((starty-endy)/(startx-endx));
                    float d=screenwidth*0.013f;
                    double angle=  Math.atan(m);
                    float sinvalue= (float) Math.sin(angle);
                    float cosvalue=(float) Math.cos(angle);
                    sinvalue=(d*sinvalue);
                    cosvalue=(d*cosvalue);
                    midx = endx>startx?endx-cosvalue:endx+cosvalue;
                    if(starty<endy){
                         midy = startx > endx ? endy + sinvalue : endy - sinvalue;
                    }
                    else{
                        midy=startx<endx?endy-sinvalue:endy+sinvalue;
                    }
                    float l90=(-1/m);
                    float l45=((1+m)/(1-m));
                    float c45=(endy-(l45*endx));
                    float c90=(midy-(l90*midx));
                    endx1=((c45-c90)/(l90-l45));
                    endy1=((l45*endx1)+c45);
                    endx2=(2*midx)-endx1;
                    endy2=(2*midy)-endy1;
                    this.startx=startx;
                    this.starty=starty;
                    this.endx1=endx1;
                    this.endy1=endy1;
                    this.endx2=endx2;
                    this.endy2=endy2;
            }
}