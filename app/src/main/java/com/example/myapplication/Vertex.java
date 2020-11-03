package com.example.myapplication;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.LinkedList;

public class Vertex implements Comparable<Vertex>{
    public final String name;
    public ArrayList<Edge> neighbours;
    public LinkedList<Vertex> path;
    public int minDistance = 123456890;
    public Vertex previous;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public int compareTo(Vertex other){
        return Integer.compare(minDistance, other.minDistance);
    }
    public Vertex(String name) {
        this.name = name;
        neighbours = new ArrayList<Edge>();
        path = new LinkedList<Vertex>();
    }
    public String toString(){
        return name;
    }
}
