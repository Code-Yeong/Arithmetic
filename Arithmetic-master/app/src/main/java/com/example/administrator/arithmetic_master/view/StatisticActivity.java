package com.example.administrator.arithmetic_master.view;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.arithmetic_master.R;
import com.example.administrator.arithmetic_master.http.HttpUtil;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.example.administrator.arithmetic_master.Model.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StatisticActivity extends AppCompatActivity {

    private LineChart lineChart;
    private int[] scores = null;//答题成绩
    private int total = 0;//答题总数
    private int right = 0;//答对的题目数量
    private int max = 0;//最高成绩

    private TextView sta_rate, sta_score, sta_count;
    private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        lineChart = (LineChart) findViewById(R.id.lineChart);
        sta_rate = (TextView) findViewById(R.id.sta_rate);
        sta_score = (TextView) findViewById(R.id.sta_score);
        sta_count = (TextView) findViewById(R.id.sta_count);
        img_back = (ImageView) findViewById(R.id.sta_back);

        new HttpUtil(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                getData(msg.obj.toString());
            }
        }, "recordsAction_getRecordsByUser.action?userid=" + User.getInstance().getUserId()
        ).start();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatisticActivity.this.finish();
            }
        });
    }

    public LineData getLineData(LineChart lineChart) {
        Description des = new Description();
        des.setText("LineChart");
        des.setPosition(0f, 0f);
        lineChart.setDescription(des);
        ArrayList<Entry> val = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            val.add(new Entry(i, i, 0));
        }
        for (int j = 0; j < 7; j++) {
            if (j < scores.length) {
                val.get(j).setY(scores[j]);
            } else {
                val.get(j).setY(0);
            }
        }
        LineData lineData = null;
        LineDataSet set = new LineDataSet(val, "近7次得分曲线图");
        set.enableDashedLine(0f, 0f, 0f);
        set.enableDashedHighlightLine(0f, 0f, 0f);
        set.setCircleColor(Color.RED);
        set.setLineWidth(2f);
        set.setCircleHoleRadius(1f);
        set.setDrawFilled(true);
        set.setDrawCircleHole(true);
        set.setValueTextSize(7f);
        lineData = new LineData(set);
        return lineData;
    }

    public void showLineChart(LineChart lineChart, LineData lineData) {
        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.setDrawBorders(true);

        lineChart.animateXY(2000, 2000);//从XY轴一起进入的动画 

        XAxis x = lineChart.getXAxis();
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setLabelCount(0);
        YAxis y = lineChart.getAxisLeft();
        y.removeAllLimitLines();
        y.setAxisMinimum(0f);
        y.setCenterAxisLabels(true);
        y.setYOffset(0f);
        y.setLabelCount(0);
        y.enableGridDashedLine(0f, 0f, 0f);
        y.setDrawZeroLine(true);
        y.setDrawLimitLinesBehindData(true);
        y = lineChart.getAxisRight();
        y.setEnabled(false);
        lineChart.setData(lineData);
        Legend l = lineChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setForm(Legend.LegendForm.LINE);
        l.setFormLineWidth(2f);
        l.setFormSize(30f);
    }

    public void setData() {
        if (total != 0) {
            sta_score.setText("历史最高分:" + max + "分");
            sta_rate.setText("正确率:" + ((right * 10000 / total) / 100 + "%"));
            sta_count.setText("总答题数:" + total + "题");
        }
    }

    public void getData(String data) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            scores = new int[jsonArray.length()];
            JSONObject jsonObject;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                scores[i] = Integer.parseInt(jsonObject.getString("score"));
                if (max < scores[i]) {
                    max = scores[i];
                }
                total += Integer.parseInt(jsonObject.getString("totalcount"));
                right += Integer.parseInt(jsonObject.getString("rightcount"));
                Log.i("data", total + "---" + right);
            }
            setData();
            showLineChart(lineChart, getLineData(lineChart));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
