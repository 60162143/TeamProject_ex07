package com.example.myapplication;

public class Edge{
    //가리키는 방향의 정점
    public final Vertex target;
    //가중치
    public final int weight;
    public Edge(Vertex target, int weight){
        this.target = target;
        this.weight = weight;
    }
}