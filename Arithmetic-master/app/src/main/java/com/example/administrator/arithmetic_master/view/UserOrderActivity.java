package com.example.administrator.arithmetic_master.view;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.arithmetic_master.Model.Leaderboard_RowItem;
import com.example.administrator.arithmetic_master.Model.User;
import com.example.administrator.arithmetic_master.R;
import com.example.administrator.arithmetic_master.adapter.LeaderboardAdapter;
import com.example.administrator.arithmetic_master.http.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserOrderActivity extends AppCompatActivity {

    private ImageView back;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView order_compete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order);

        back = (ImageView) findViewById(R.id.leaderboard_return);
        order_compete = (ImageView) findViewById(R.id.order_compete);

        order_compete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserOrderActivity.this, CalculateActivity.class));
                UserOrderActivity.this.finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        new HttpUtil(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                ShowTheRank(msg.obj.toString());
            }
        }, "rankingAction_getRankListForWebPage.action?grade=" + User.getInstance().getUserGrade()).start();
    }

    public ArrayList<Leaderboard_RowItem> parsingData(String data) {
        ArrayList<Leaderboard_RowItem> itemlist = new ArrayList<>();
        try {
            JSONArray jsonarray = new JSONArray(data);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonObject = jsonarray.getJSONObject(i);
                Leaderboard_RowItem item = new Leaderboard_RowItem();
                item.userName = jsonObject.getString("name");
                String rightCount = jsonObject.getString("rightcount");
                String totalCount = jsonObject.getString("totalcount");
                item.accuracy = rightCount + "/" + totalCount;
                item.useTime = jsonObject.getString("time");
                item.totalScore = jsonObject.getString("score");
                item.order = i + 1;
                itemlist.add(item);
            }
        } catch (JSONException e) {
            System.out.println("JsonsArray parse error !");
        }
        return itemlist;
    }

    private void ShowTheRank(String data) {
        Log.i("data", data);
        ArrayList<Leaderboard_RowItem> itemlist = parsingData(data);
        mRecyclerView = (RecyclerView) findViewById(R.id.leaderboard);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new LeaderboardAdapter(itemlist);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(5));
    }


    class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            if (parent.getChildPosition(view) != 0)
                outRect.top = space;
        }
    }
}
