package com.example.myapplication;

import android.util.Log;

import java.util.ArrayList;

public class Graph {
    private ArrayList<Vertex> vertices;
    public ArrayList<Integer> index = null;
    private int count = 0;
    public Graph(int numberVertices){
        vertices = new ArrayList<Vertex>(numberVertices);
        index = new ArrayList<Integer>();
        for(int i=0;i<numberVertices;i++){
            vertices.add(new Vertex("v"+Integer.toString(i)));
        }
    }

    public void addEdge(String src, String dest, String weight){
        boolean tf = false;
        if(index != null){
            for(int i = 0;i < index.size();i++){
                if(index.get(i) == Integer.parseInt(dest)){
                    tf = true;
                    break;
                }
            }
            if(tf == false){
                index.add(Integer.parseInt(dest));
                count++;
            }else {
                tf = false;
            }
        }else{
            index.add(Integer.parseInt(dest));
            count++;
        }
        Vertex s = vertices.get(count-1);
        Edge new_edge = new Edge(vertices.get(count-1),Double.parseDouble(weight));
        s.neighbours.add(new_edge);
    }

    public void print(){
        Log.d("tag2", "여기까지 오나");
        for(int i = 0; i<index.size(); i++){
            Log.d("tag3", Integer.toString(index.get(i)));
            //Log.d("tag3", vertices.get(i));
        }
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getVertex(int vert){
        return vertices.get(vert);
    }
}
