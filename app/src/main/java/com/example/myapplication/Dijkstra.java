package com.example.myapplication;

import android.util.Log;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class Dijkstra {
    int Start = 0;
    int Destination = 0;
    Graph graph;
    public Dijkstra(int start, int dest, Graph g){
        Start = start;
        Destination = dest;
        graph = g;
    }

    public void calculate(){
        Vertex start_source = null;
        Vertex dest_source = null;
        //Log.d("tag", Integer.toString(graph.index.get(1)));
        Log.d("tag", "------------------------------------------------------------------");
        for(int i = 0; i < 111; i++){
            //Log.d("tag3", "몇 번");
            Log.d("tag3", Integer.toString(graph.index.get(i)));
            if(graph.index.get(i) == Start){
                Log.d("tag3", Integer.toString(i));
                start_source = graph.getVertex(i);
                break;
            }
        }

        for(int j = 0; j < 111; j++){
            //Log.d("tag3", "몇 번");
            if(graph.index.get(j) == Destination){
                Log.d("tag3", Integer.toString(j));
                dest_source = graph.getVertex(j);
                break;
            }
        }

        start_source.minDistance = 0;
        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
        queue.add(start_source);
        boolean tf = false;

        while(!queue.isEmpty()){
            Vertex u = queue.poll();
            //Log.d("tag", u.toString());

            for(Edge neighbour : u.neighbours){
                //Log.d("tag", "---------------------------------" + neighbour.target + "  " + neighbour.weight);
                //Log.d("tag", Integer.toString(neighbour.weight));
                //Log.d("tag", neighbour.target.toString());
                int newDist = u.minDistance + neighbour.weight;
                //Log.d("tag", Integer.toString(newDist));
                //Log.d("tag", Integer.toString(neighbour.target.minDistance));
                /*if(neighbour.target == dest_source){
                    tf = true;
                    break;
                }*/
                if(neighbour.target.minDistance > newDist){

                    queue.remove(neighbour.target);
                    neighbour.target.minDistance = newDist;

                    neighbour.target.path = new LinkedList<Vertex>(u.path);
                    neighbour.target.path.add(u);

                    queue.add(neighbour.target);
                    //Log.d("tag", Integer.toString(neighbour.target.minDistance));
                }
            }
            Log.d("tag", "--------------------------------------------");
            if (tf) {
                tf = false;
                break;
            }
        }

    }

}
