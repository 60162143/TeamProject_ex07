package com.example.myapplication;

import android.util.Log;

import java.util.ArrayList;

public class Graph {
    private ArrayList<Vertex> vertices;
    public ArrayList<Vertex> Vertexs;
    public ArrayList<Integer> index = null;
    private int count = 0;
    int src_index = 0;
    int dest_index = 0;
    public final int MAX_INDEX = 111;
    public Graph(int numberVertices){
        vertices = new ArrayList<Vertex>(numberVertices);
        Vertexs = new ArrayList<Vertex>(MAX_INDEX);
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
                    /*for(int j = 0; j<index.size(); j++){

                    }*/
                    Log.d("tag", "111111111111111111111111111111111111111111111111111111111111111111");
                    Edge new_edge = new Edge(vertices.get(dest_index),Integer.parseInt(weight));
                    Vertexs.get(i).neighbours.add(new_edge);
                    //Log.d("tag", "---------------------------------" + new_edge.target.toString() + "   " + new_edge.weight);
                    tf = true;
                    break;
                }
            }
            if(!tf){
                Log.d("tag", "5555555555555555555555555555555555555555555555555555555555555");
                index.add(Integer.parseInt(dest));
                //Log.d("tag", "index 크기는 " + index.size());
                dest_index = count++;
                Vertex s = vertices.get(dest_index);
                Edge new_edge = new Edge(vertices.get(dest_index),Integer.parseInt(weight));
                s.neighbours.add(new_edge);
                Vertexs.add(s);
            }else {
                tf = false;
            }
        }else{
            Log.d("tag", "444444444444444444444444444444444444444444444444444");
            index.add(Integer.parseInt(dest));
            dest_index = count++;
            Vertex s = vertices.get(dest_index);
            //Log.d("tag", "---------------------------------" + s.toString());
            Edge new_edge = new Edge(vertices.get(dest_index),Integer.parseInt(weight));
            s.neighbours.add(new_edge);
            Vertexs.add(s);
        }

        for(int j=0; j < index.size();j++){
            if(index.get(j) == Integer.parseInt(src)) {
                Log.d("tag", "77777777777777777777777777777777777777777777777777777777777");
                Edge new_edge = new Edge(vertices.get(dest_index),Integer.parseInt(weight));
                Vertexs.get(j).neighbours.add(new_edge);
                tf = true;
                src_index = j;
                break;
            }
        }

        if(!tf){
            Log.d("tag", "8888888888888888888888888888888888888888888888888");
            index.add(Integer.parseInt(src));
            src_index = count++;
            Vertex s = vertices.get(src_index);
            Edge new_edge = new Edge(vertices.get(dest_index),Integer.parseInt(weight));
            s.neighbours.add(new_edge);
            Vertexs.add(s);
        }

        /*Vertex s = vertices.get(src_index);
        Edge new_edge = new Edge(vertices.get(dest_index),Double.parseDouble(weight));
        s.neighbours.add(new_edge);*/
    }

    public void print(){
        Log.d("tag2", "-------------------------------------------------------------------------");
        for(int i = 0; i<Vertexs.size(); i++){
            Log.d("tag3", Vertexs.get(i).toString() + " " + Integer.toString(index.get(i)));
            //Log.d("tag3", Vertexs.get(i).toString());
        }
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getVertex(int vert){
        return vertices.get(vert);
    }
}
