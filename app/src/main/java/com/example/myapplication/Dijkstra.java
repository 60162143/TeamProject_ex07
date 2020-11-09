package com.example.myapplication;

import android.util.Log;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class Dijkstra {
    int Start = 0;
    int Destination = 0;
    Graph graph;
    //시작점과 도착점, 그래프를 저장
    public Dijkstra(int start, int dest, Graph g){
        Start = start;
        Destination = dest;
        graph = g;
    }

    //계산하는 함수
    public void calculate(){
        //시작 정점을 기준으로 모든 정점으로 까지의 경로를 계산
        Vertex start_source = null;

        //Graph클래스의 인덱스에서 시작 정점값을 찾아 저장
        for(int i = 0; i < 111; i++){
            if(graph.index.get(i) == Start){
                start_source = graph.getVertex(i);
                break;
            }
        }

        //시작 정점의 최소 거리를 0으로 초기화
        start_source.minDistance = 0;

        //우선순위 큐를 사용하여 계산
        //이부분은 인터넷 우선순위 큐 치면 자세히 설명 나올거야
        //나도 설명은 못하겠음...
        //코드 그대로 사용했으니까 이해하기 쉬울거야
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        queue.add(start_source);

        while(!queue.isEmpty()){

            Vertex u = queue.poll();

            for(Edge neighbour : u.neighbours){

                int newDist = u.minDistance + neighbour.weight;

                if(neighbour.target.minDistance > newDist){
                    queue.remove(neighbour.target);

                    neighbour.target.minDistance = newDist;
                    neighbour.target.path = new LinkedList<Vertex>(u.path);

                    neighbour.target.path.add(u);
                    queue.add(neighbour.target);
                }
            }
        }
    }

    //결과물을 출력하는 함수
    public void print(){
        Vertex dest_source = null;
        int start = 0;
        int destination = 0;

        //Graph클래스의 인덱스에서 도착 정점값을 찾아 저장
        for(int j = 0; j < 111; j++){
            if(graph.index.get(j) == Destination){
                //도착 정점의 이름값 저장
                destination = graph.index.get(j);
                //도착 정점값 저장
                dest_source = graph.getVertex(j);
                break;
            }
        }

        //경로 출력
        System.out.print("Vertex - " + start + " to " + "Vertex - " + destination + " , Dist - " + dest_source.minDistance + " , Path - ");
        //도착 정점의 경로 정점들을 하나씩 꺼내서 이름을 출력
        for(Vertex pathvert : dest_source.path){
            //Graph클래스의 인덱스에서 경로 정점 이름을 찾아 출력
            for(int k = 0; k < 111; k++){
                if(graph.Vertexs.get(k) == pathvert){
                    System.out.print(graph.index.get(k) + " ");
                    break;
                }
            }
        }

        //Graph클래스의 인덱스에서 도착 정점 이름을 찾아 출력
        //도착 정점은 위에서 출력이 안되니 따로 빼줘서 출력
        for(int i = 0; i < 111; i++){
            if(graph.Vertexs.get(i) == dest_source){
                System.out.println("" + graph.index.get(i));
                break;
            }
        }
    }

}
