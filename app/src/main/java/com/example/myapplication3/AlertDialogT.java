package com.example.myapplication3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AlertDialogT extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialog); // 设置活动的布局文件

        // 获取自定义对话框按钮
        Button btn_custom_dialog = findViewById(R.id.custom_dialog_btn);
        btn_custom_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog(); // 点击按钮时创建对话框
            }
        });
    }

    public void createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this); // 创建对话框构建器
        // 获取布局填充器
        LayoutInflater inflater = getLayoutInflater();

        // 填充并设置对话框的布局
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        builder.setView(dialogView);

        // 获取对话框中的输入框
        EditText editTextUsername = dialogView.findViewById(R.id.username);
        EditText editTextPassword = dialogView.findViewById(R.id.password);

        // 添加操作按钮
        builder.setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // 获取用户输入的用户名和密码
                        String username = editTextUsername.getText().toString();
                        String password = editTextPassword.getText().toString();

                        // 在这里处理用户点击“登录”按钮后的逻辑
                        if (username.isEmpty() || password.isEmpty()) {
                            Toast.makeText(AlertDialogT.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            // 这里可以添加实际的登录逻辑
                            Toast.makeText(AlertDialogT.this, "登录成功: " + username, Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // 用户点击“取消”按钮时的逻辑
                        dialog.cancel(); // 取消对话框
                    }
                });

        // 创建对话框并显示
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}