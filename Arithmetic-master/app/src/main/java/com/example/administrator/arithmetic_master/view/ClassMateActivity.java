package com.example.administrator.arithmetic_master.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.arithmetic_master.Model.User;
import com.example.administrator.arithmetic_master.R;
import com.example.administrator.arithmetic_master.http.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassMateActivity extends AppCompatActivity {

    private ImageView img_back;
    private ListView list_mate;

    private JSONArray j = null;
    private JSONObject o = null;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_mate);

        img_back = (ImageView) findViewById(R.id.mate_back);
        list_mate = (ListView) findViewById(R.id.list_mate);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClassMateActivity.this.finish();
            }
        });

        list_mate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    o = j.getJSONObject(position);
                    Intent intent = new Intent(ClassMateActivity.this, StatisticActivity.class);
                    intent.putExtra("userid", o.getString("userid"));
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        new HttpUtil(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                showList(msg.obj.toString());
            }
        }, "userAction_getClassMates.action?grade=" + User.getInstance().getUserGrade()
        ).start();
    }

    private void showList(String obj) {
        List<Map<String, String>> list = new ArrayList<>();
        try {
            j = new JSONArray(obj);
            for (int i = 0; i < j.length(); i++) {
                o = j.getJSONObject(i);
                Map<String, String> map = new HashMap<>();
                map.put("id", (i + 1) + "");
                map.put("loginname", o.getString("loginname"));
                map.put("name", o.getString("name"));
                map.put("score", o.getString("score"));
                list.add(map);
            }
            adapter = new SimpleAdapter(this, list, R.layout.classmate_row_item,
                    new String[]{"id", "loginname", "name", "score"},
                    new int[]{R.id.mate_id, R.id.mate_loginname, R.id.mate_name, R.id.mate_score});
            list_mate.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
