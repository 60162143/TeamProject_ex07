package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;
import java.io.IOException;

import cz.msebera.android.httpclient.Header;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class MainActivity extends AppCompatActivity {
    AsyncHttpClient client;
    Workbook workbook;

    public Graph g_time = new Graph(140);
    public Graph g_distance = new Graph(140);
    public Graph g_cost = new Graph(140);

    String url = "https://github.com/60162143/ExcelExam/blob/main/stations1.xls?raw=true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new AsyncHttpClient();
        client.get(url, new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Toast.makeText(MainActivity.this, "Download Failed", Toast.LENGTH_SHORT);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                Toast.makeText(MainActivity.this, "File Download", Toast.LENGTH_SHORT);
                WorkbookSettings ws = new WorkbookSettings();
                ws.setGCDisabled(true);
                Log.d("tag", "---------------------------11111111111111111111--------------------------------");
                if(file != null){
                    Log.d("tag", "----------------------------222222222222222222-------------------------------");
                    try {
                        workbook = workbook.getWorkbook(file);
                        Sheet sheet = workbook.getSheet(0);
                        Log.d("tag", "--------------------------------333333333333333333---------------------------");
                        Log.d("tag", Integer.toString(sheet.getRows()));
                        for(int i = 0; i < sheet.getRows(); i++){
                            if(i == 0){

                            }else{
                                Cell[] row = sheet.getRow(i);
                                g_time.addEdge(row[0].getContents(), row[1].getContents(), row[2].getContents());
                                g_distance.addEdge(row[0].getContents(), row[1].getContents(), row[3].getContents());
                                g_cost.addEdge(row[0].getContents(), row[1].getContents(), row[4].getContents());

                                //Log.d("TAG", row[0].getContents());

                            }

                        }
                    } catch (IOException e) {
                        Log.d("tag", "--------------------------------44444444444444444---------------------------");
                        e.printStackTrace();
                    } catch (BiffException e) {
                        Log.d("tag", "--------------------------------5555555555555555---------------------------");
                        e.printStackTrace();
                    }
                }
                Log.d("tag2", "여기는 오니?");
                g_time.print();
                //g_distance.print();
                //g_cost.print();

                //입력을 받으면
                int ex_start = 101;
                int ex_end = 701;
                //type_1은 시간 type_2는 거리 type_3은 비용
                int type = 2;

                if(type == 1){
                    Dijkstra dij = new Dijkstra(ex_start, ex_end, g_time);
                    dij.calculate();
                }else if(type == 2){
                    Dijkstra dij = new Dijkstra(ex_start, ex_end, g_distance);
                    dij.calculate();
                }else{
                    Dijkstra dij = new Dijkstra(ex_start, ex_end, g_cost);
                    dij.calculate();
                }

                /*for(Vertex v : g_distance.getVertices()){
                    System.out.print("Vertex - " + v + " , Dist - " + v.minDistance + " , Path - ");
                    for(Vertex pathvert : v.path){
                        System.out.print(pathvert + " ");
                    }
                    System.out.println("" + v);
                }*/
            }
        });




    }
}