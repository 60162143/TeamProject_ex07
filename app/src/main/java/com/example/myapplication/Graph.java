package com.example.myapplication;

import android.util.Log;

import java.util.ArrayList;

public class Graph {
    //정점의 이름을 저장하기 위해 만든 arraylist
    //예를 들어 v0 v1 등 정점을 그래프 느낌으로 만들기 위해 이름 만듦
    //딱히 신경 안써도 됨
    private ArrayList<Vertex> vertices;
    //아래 arraylist가 정점들을 저장하기 위해 만듦
    public ArrayList<Vertex> Vertexs;
    //정점들의 이름이 101, 102같이 정수이므로 integer형으로 저장
    //위에 Vertexs와 index는 한묶음으로 저장되기 때문에
    //다른곳에서 찾기 편하게 하도록 만듦
    public ArrayList<Integer> index = null;
    //정점이 저장될때마다 늘어나는 값
    private int count = 0;
    //정점의 개수가 111개이기 때문에 임의로 만듦
    public final int MAX_INDEX = 111;
    //초기화 신경 딱히 안써도 되는 부분
    public Graph(int numberVertices){
        vertices = new ArrayList<Vertex>(numberVertices);
        Vertexs = new ArrayList<Vertex>(MAX_INDEX);
        index = new ArrayList<Integer>();
        //이름 저장
        for(int i=0;i<numberVertices;i++){
            vertices.add(new Vertex("v"+Integer.toString(i)));
        }
    }

    //정점과 이웃한 정점과 가중치 저장하는 함수
    public void addEdge(String src, String dest, String weight){
        //시작정점의 값이 인덱스에 저장되어 있지 않으면 false
        boolean tf_src = false;
        //도착정점의 값이 인덱스에 저장되어 있지 않으면 false
        boolean tf_dest = false;
        //정점의 이름이 정수형이므로
        int src_index = 0;
        int dest_index = 0;
        //시작점과 도착점이 저장되어 있는지 확인

        //시작정점이 저장되어 있으면
        for(int i = 0; i < index.size(); i++){
            if(index.get(i) == Integer.parseInt(src)){
                tf_src = true;
                src_index = i;
                break;
            }
        }

        //도착정점이 저장되어 있으면
        for(int i = 0; i < index.size(); i++){
            if(index.get(i) == Integer.parseInt(dest)){
                tf_dest = true;
                dest_index = i;
                break;
            }
        }

        //시작점과 도착점이 저장되어 있을때
        if(tf_src && tf_dest){
            //시작점에서 도착점까지의 edge를 시작정점의 neighbours에 저장
            //아래도 똑같음
            Edge new_edge1 = new Edge(vertices.get(dest_index),Integer.parseInt(weight));
            Vertexs.get(src_index).neighbours.add(new_edge1);
            //도착점에서 시작점까지의 edge를 도착정점의 neighbours에 저장
            //아래도 똑같음
            Edge new_edge2 = new Edge(vertices.get(src_index),Integer.parseInt(weight));
            Vertexs.get(dest_index).neighbours.add(new_edge2);
        }
        //도착점만 저장되어 있을때
        else if(!tf_src && tf_dest){
            index.add(Integer.parseInt(src));
            src_index = count;
            Vertexs.add(vertices.get(count++));
            Edge new_edge1 = new Edge(vertices.get(dest_index),Integer.parseInt(weight));
            Vertexs.get(src_index).neighbours.add(new_edge1);
            Edge new_edge2 = new Edge(vertices.get(src_index),Integer.parseInt(weight));
            Vertexs.get(dest_index).neighbours.add(new_edge2);
        }
        //시작점만 저장되어 있을때
        else if(tf_src && !tf_dest){
            index.add(Integer.parseInt(dest));
            dest_index = count;
            Vertexs.add(vertices.get(count++));
            Edge new_edge1 = new Edge(vertices.get(dest_index),Integer.parseInt(weight));
            Vertexs.get(src_index).neighbours.add(new_edge1);
            Edge new_edge2 = new Edge(vertices.get(src_index),Integer.parseInt(weight));
            Vertexs.get(dest_index).neighbours.add(new_edge2);
        }
        //시작점과 도착점이 저장되어 있지 않을 때
        else{
            index.add(Integer.parseInt(src));
            src_index = count;
            Vertexs.add(vertices.get(count++));
            index.add(Integer.parseInt(dest));
            dest_index = count;
            Vertexs.add(vertices.get(count++));
            Edge new_edge1 = new Edge(vertices.get(dest_index),Integer.parseInt(weight));
            Vertexs.get(src_index).neighbours.add(new_edge1);
            Edge new_edge2 = new Edge(vertices.get(src_index),Integer.parseInt(weight));
            Vertexs.get(dest_index).neighbours.add(new_edge2);
        }

    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getVertex(int vert){
        return Vertexs.get(vert);
    }
}
