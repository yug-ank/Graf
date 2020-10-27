package com.example.graf;

import android.graphics.Color;

/**
 * This class represents a node
 */
class Node {
    private float centerx , centery;
    private float radius;
    private float textSize;
    private int hex;
    /**
     * @param centerx holds the x co-ordinate of center of circle
     * @param centery holds the y co-cordinate of center of circle
     * @param radius holds the value of radius
     * @param  textSize represents the size of text present inside a node
     */
    public Node(float centerx, float centery , float radius , float textSize) {
        this.centerx = centerx;
        this.centery = centery;
        this.radius=radius;
        this.textSize=textSize;
        hex=Color.GRAY;
    }
    public float getCenterx() {
        return centerx;
    }

    public float getCentery() {
        return centery;
    }
    public float getRadius() {
        return radius;
    }
    public float getTextSize(){
        return textSize;
    }

    public int getHex(){
        return hex;
    }
    public void updateRadius(float radius){
        //updates the value of radius and textSize
        this.radius=radius;
        this.textSize=radius;
    }
    public void updatey(float centery){
        //updates the value of y co-ordinate
        this.centery=centery;
    }
    public void updateHex(int hex){
        this.hex=hex;
    }
}