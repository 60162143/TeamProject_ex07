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

    //최단 시간, 최단 거리, 최소 비용 그래프 만들기
    public Graph g_time = new Graph(140);
    public Graph g_distance = new Graph(140);
    public Graph g_cost = new Graph(140);

    //엑셀파일이 있는 주소
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
                //엑셀 파일이 비어있지 않으면 파일을 엑셀의 워크시트처럼 임시 저장 시킨다.
                if(file != null){
                    try {
                        workbook = workbook.getWorkbook(file);
                        Sheet sheet = workbook.getSheet(0);
                        //엑셀파일을 행단위로 읽는다
                        for(int i = 0; i < sheet.getRows(); i++){
                            //첫행은 필드 이름이므로 읽지 않는다
                            if(i == 0){

                            }else{  //row에 행을 한줄씩 임시 저장 시킴
                                //row[0]은 시작정점, row[1]은 도착정점, row[2, 3, 4]는 각각 시간, 거리, 비용
                                Cell[] row = sheet.getRow(i);
                                g_time.addEdge(row[0].getContents(), row[1].getContents(), row[2].getContents());
                                g_distance.addEdge(row[0].getContents(), row[1].getContents(), row[3].getContents());
                                g_cost.addEdge(row[0].getContents(), row[1].getContents(), row[4].getContents());
                            }

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (BiffException e) {
                        e.printStackTrace();
                    }
                }

                //입력을 받으면(입력 예시)
                //여기 부분에서 이제 입력값 받는거 추가해야 해
                int ex_start = 101;
                int ex_end = 701;
                //type_1은 시간 type_2는 거리 type_3은 비용
                int type = 2;

                //최단 시간일때
                if(type == 1){
                    Dijkstra dij = new Dijkstra(ex_start, ex_end, g_time);
                    //계산
                    dij.calculate();
                    //경로 출력
                    //아래도 같음
                    dij.print();
                }
                //최단 거리일때
                else if(type == 2){
                    Dijkstra dij = new Dijkstra(ex_start, ex_end, g_distance);
                    dij.calculate();
                    dij.print();
                }
                //최소 비용일때
                else{
                    Dijkstra dij = new Dijkstra(ex_start, ex_end, g_cost);
                    dij.calculate();
                    dij.print();
                }

            }
        });




    }
}