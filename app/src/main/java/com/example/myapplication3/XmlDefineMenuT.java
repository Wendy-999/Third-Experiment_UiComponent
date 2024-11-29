package com.example.myapplication3;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class XmlDefineMenuT extends Activity {
    TextView tv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xml_define_menu); // 确保这个布局文件存在
        tv_test = findViewById(R.id.tv_test); // 获取 TextView 控件
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_settings, menu); // 加载菜单
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 处理操作栏项目点击事件
        if (item.getItemId() == R.id.menu_font_small) {
            tv_test.setTextSize(10 * 2); // 设置小字体
        } else if (item.getItemId() == R.id.menu_font_middle) {
            tv_test.setTextSize(16 * 2); // 设置中字体
        } else if (item.getItemId() == R.id.menu_font_big) {
            tv_test.setTextSize(20 * 2); // 设置大字体
        } else if (item.getItemId() == R.id.menu_normal) {
            Toast.makeText(XmlDefineMenuT.this, "这是普通菜单项", Toast.LENGTH_SHORT).show(); // 显示普通菜单项的 Toast
        } else if (item.getItemId() == R.id.menu_color_red) {
            tv_test.setTextColor(Color.RED); // 设置文本颜色为红色
        } else if (item.getItemId() == R.id.menu_color_black) {
            tv_test.setTextColor(Color.BLACK); // 设置文本颜色为黑色
        } else {
            return super.onOptionsItemSelected(item); // 处理其他菜单项
        }
        return true; // 返回 true 表示处理成功
    }
}