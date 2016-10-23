package com.example.administrator.arithmetic_master.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.arithmetic_master.Model.User;
import com.example.administrator.arithmetic_master.R;
import com.example.administrator.arithmetic_master.http.HttpUtil;
import com.example.administrator.arithmetic_master.utils.DisplayMsg;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoWrongActivity extends AppCompatActivity {
    private ImageView img_back;
    private ListView list_wrong;

    private JSONArray j;
    private JSONObject o;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_wrong);

        img_back = (ImageView) findViewById(R.id.wrong_back);
        list_wrong = (ListView) findViewById(R.id.list_wrong);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoWrongActivity.this.finish();
            }
        });

        list_wrong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showNoticeDialog(position);
            }
        });

        init();
    }

    public void show(String obj) {
//        adapter = new SimpleAdapter(this, null, R.layout.wrong_row_item, new String[]{"id", "dscp", "time"}, new int[]{R.id.wrong_id, R.id.wrong_dscp, R.id.wrong_time});
//        list_wrong.setAdapter(adapter);
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map;
        try {
            j = new JSONArray(obj);
            for (int i = 0; i < j.length(); i++) {
                o = j.getJSONObject(i);
                map = new HashMap<>();
                map.put("id", (i + 1) + "");
                map.put("dscp", o.getString("dscp"));
                map.put("time", o.getString("time"));
                list.add(map);
            }
            adapter = new SimpleAdapter(this, list, R.layout.wrong_row_item, new String[]{"id", "dscp", "time"}, new int[]{R.id.wrong_id, R.id.wrong_dscp, R.id.wrong_time});
            list_wrong.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showNoticeDialog(final int option) {
        try {
            o = j.getJSONObject(option);
            final String errorid = o.getString("id");
            final String content = o.getString("content");
            // Get the layout inflater:获取自定义的Layout
            LayoutInflater inflater = getLayoutInflater();
            View layout_casSetting = inflater.inflate(R.layout.wrongsetting, null);
            new AlertDialog.Builder(this)
                    .setView(layout_casSetting)
                    .setPositiveButton("重做", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Intent intent = new Intent(DoWrongActivity.this, DoWrong2ACtivity.class);
                            intent.putExtra("errorid", errorid);
                            intent.putExtra("content", content);
                            startActivity(intent);
                        }
                    })
                    .setNeutralButton("关闭", null)
                    .setNegativeButton("移除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new HttpUtil(new Handler() {
                                @Override
                                public void handleMessage(Message msg) {
                                    super.handleMessage(msg);
                                    if (msg.obj.toString().equals("0x01")) {
                                        init();
                                        DisplayMsg.Show(DoWrongActivity.this, "删除成功");
                                    }
                                }
                            }, "errorsAction_deleteErrors.action?userid=" + User.getInstance().getUserId() + "&errorid=" + errorid).start();
                            dialog.dismiss();
                        }
                    }).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        new HttpUtil(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                show(msg.obj.toString());
            }
        }, "errorsAction_getErrorsByUser.action?userid=" + User.getInstance().getUserId()
        ).start();
    }
}
