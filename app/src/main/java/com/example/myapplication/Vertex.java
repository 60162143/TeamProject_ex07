package com.example.myapplication;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.LinkedList;

public class Vertex implements Comparable<Vertex>{
    public final String name;
    //이웃한 edge들을 저장
    public ArrayList<Edge> neighbours;
    //경로 저장
    public LinkedList<Vertex> path;
    //최소 거리 저장
    public int minDistance = 123456890;
    //신경 ㄴㄴ
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    //비교하는 함수인데 이것도 신경 ㄴㄴ
    //이게 있어야 우선순위큐로 서로 비교한다는데
    //인터넷에 잘 나와있음 ^^
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
