package com.example.myapplication;

import java.util.ArrayList;
import java.util.LinkedList;

public class Vertex {
    public final String name;
    public ArrayList<Edge> neighbours;
    public LinkedList<Vertex> path;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;
    public Vertex(String name) {
        this.name = name;
        neighbours = new ArrayList<Edge>();
        path = new LinkedList<Vertex>();
    }
    public String toString(){
        return name;
    }
}
