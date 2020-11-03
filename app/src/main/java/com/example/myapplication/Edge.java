package com.example.myapplication;

public class Edge{
    public final Vertex target;
    public final int weight;
    public Edge(Vertex target, int weight){
        this.target = target;
        this.weight = weight;
    }
}