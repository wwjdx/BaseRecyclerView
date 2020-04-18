package com.diit.recyclerviewdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvData;
    private List<String> strList;
    private RecyclerAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvData=findViewById(R.id.rv_data);
        strList=new ArrayList<>();
        for (int i=0;i<100;i++){
            strList.add(i+"条");
        }
        recyclerAdapter=new RecyclerAdapter(this,strList);
        rvData.setAdapter(recyclerAdapter);
        //ListView效果
//        rvData.setLayoutManager(new LinearLayoutManager(this));
//        rvData.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST));
        //GrideView效果
        rvData.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
        rvData.addItemDecoration(new DividerGrideItemDecoration(this));
        recyclerAdapter.setOnItemClickLis(new RecyclerAdapter.OnItemClickLis() {
            @Override
            public void onItemClickLis(View view, int pos) {
                Toast.makeText(MainActivity.this,"点击了"+pos,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClickLis(View view, final int pos) {
                new AlertDialog.Builder(MainActivity.this)
                .setTitle("系统提示")
                .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        recyclerAdapter.removeData(pos);
                    }
                }).show();
            }
        });
    }
}
