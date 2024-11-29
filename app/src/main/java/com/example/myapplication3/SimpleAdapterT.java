package com.example.myapplication3;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class SimpleAdapterT extends Activity {

    // 初始化视图
    ListView simpleListView;
    String[] animalName = {"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"}; // 动物名称数组
    int[] animalImages = {R.drawable.lion, R.drawable.tiger, R.drawable.monkey, R.drawable.dog, R.drawable.cat, R.drawable.elephant}; // 动物图片数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_adapter); // 设置活动的布局文件
        simpleListView = findViewById(R.id.simpleListView); // 获取 ListView 控件

        // 创建一个 ArrayList 用于存储动物名称和图片
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        for (int i = 0; i < animalName.length; i++) {
            HashMap<String, String> hashMap = new HashMap<>(); // 创建一个 HashMap 来存储数据
            hashMap.put("name", animalName[i]); // 将动物名称放入 HashMap
            hashMap.put("image", animalImages[i] + ""); // 将动物图片的资源 ID 转换为字符串并放入 HashMap
            arrayList.add(hashMap); // 将 HashMap 添加到 ArrayList 中
        }

        // 定义从 HashMap 中获取数据的键
        String[] from = {"name", "image"}; // 键数组
        int[] to = {R.id.textView, R.id.imageView}; // 对应的视图 ID 数组

        // 创建 SimpleAdapter 对象并设置参数
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, arrayList, R.layout.list_view_items, from, to) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                // 获取 ImageView 并设置图片资源
                ImageView imageView = view.findViewById(R.id.imageView);
                imageView.setImageResource(animalImages[position]); // 设置动物图片
                return view; // 返回视图
            }
        };

        simpleListView.setAdapter(simpleAdapter); // 设置适配器到 ListView

        // 处理 ListView 项目点击事件
        simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 显示选中的动物名称
                Toast.makeText(getApplicationContext(), animalName[i], Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 加载菜单; 这会将项目添加到操作栏（如果存在）
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 处理操作栏项目点击事件
        int id = item.getItemId();

        // 处理设置菜单项的点击事件
        if (id == R.id.action_settings) {
            // 在这里可以添加设置的逻辑
            return true;
        }

        return super.onOptionsItemSelected(item); // 处理其他菜单项的点击事件
    }
}