package com.example.myapplication3;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Set;

public class ContextualActionBarT extends ListActivity {

    private String[] data = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"}; // 数据数组

    private SelectionAdapter mAdapter; // 自定义适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contextual_action_bar); // 设置布局文件

        // 初始化自定义适配器
        mAdapter = new SelectionAdapter(this, R.layout.row_list_item, R.id.textView1, data);
        setListAdapter(mAdapter); // 设置适配器到 ListView
        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL); // 设置多选模式

        // 设置多选模式监听器
        getListView().setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            private int nr = 0; // 选中的项数

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // 准备操作模式时的回调
                return false; // 返回 false 表示不做任何操作
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // 销毁操作模式时的回调
                mAdapter.clearSelection(); // 清除选中的项
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // 创建操作模式时的回调
                nr = 0; // 重置选中的项数
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.contextual_menu, menu); // 加载上下文菜单
                return true; // 返回 true 表示创建成功
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // 处理操作模式中的菜单项点击事件
                if (item.getItemId() == R.id.item_delete) {
                    nr = 0; // 重置选中的项数
                    mAdapter.clearSelection(); // 清除选中的项
                    mode.finish(); // 结束操作模式
                    return true; // 返回 true 表示处理成功
                } else {
                    return false; // 返回 false 表示未处理
                }
            }

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                // 处理选中状态改变的回调
                if (checked) {
                    nr++; // 增加选中的项数
                    mAdapter.setNewSelection(position, checked); // 设置新选中的项
                } else {
                    nr--; // 减少选中的项数
                    mAdapter.removeSelection(position); // 移除选中的项
                }
                mode.setTitle(nr + " selected"); // 更新操作模式标题
            }
        });

        // 设置长按监听器
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                // 处理长按事件
                getListView().setItemChecked(position, !mAdapter.isPositionChecked(position)); // 切换选中状态
                return false; // 返回 false 以允许其他事件处理
            }
        });
    }

    // 自定义适配器类
    private class SelectionAdapter extends ArrayAdapter<String> {

        private HashMap<Integer, Boolean> mSelection = new HashMap<>(); // 存储选中状态的 HashMap

        public SelectionAdapter(Context context, int resource, int textViewResourceId, String[] objects) {
            super(context, resource, textViewResourceId, objects); // 调用父类构造函数
        }

        public void setNewSelection(int position, boolean value) {
            mSelection.put(position, value); // 设置新选中状态
            notifyDataSetChanged(); // 通知数据集已更改
        }

        public boolean isPositionChecked(int position) {
            Boolean result = mSelection.get(position); // 获取选中状态
            return result == null ? false : result; // 返回选中状态
        }

        public Set<Integer> getCurrentCheckedPosition() {
            return mSelection.keySet(); // 返回当前选中的位置集合
        }

        public void removeSelection(int position) {
            mSelection.remove(position); // 移除选中状态
            notifyDataSetChanged(); // 通知数据集已更改
        }

        public void clearSelection() {
            mSelection = new HashMap<>(); // 清空选中状态
            notifyDataSetChanged(); // 通知数据集已更改
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = super.getView(position, convertView, parent); // 让适配器处理行视图的设置
            v.setBackgroundColor(getResources().getColor(android.R.color.background_light)); // 默认背景颜色

            // 如果该位置被选中，设置背景颜色
            if (mSelection.get(position) != null) {
                v.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light)); // 选中时的背景颜色
            }
            return v; // 返回视图
        }
    }
}