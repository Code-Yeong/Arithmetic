package com.example.administrator.arithmetic_master.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.arithmetic_master.Model.Leaderboard_RowItem;
import com.example.administrator.arithmetic_master.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/21.
 */
public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {

    private ArrayList<Leaderboard_RowItem> data_list=new ArrayList<>();

    public LeaderboardAdapter(ArrayList<Leaderboard_RowItem> data)
    {
        this.data_list=data;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data_list.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.leaderboard_row_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Leaderboard_RowItem currentItem=data_list.get(position);
        //根据排名决定是否显示奖杯
        if(currentItem.order<=3)
        {
            switch (position)
            {
                case 0:
                    holder.order.setImageResource(R.drawable.leaderboard_gold);
                    break;
                case 1:
                    holder.order.setImageResource(R.drawable.leaderboard_silver);
                    break;
                case 2:
                    holder.order.setImageResource(R.drawable.leaderboard_copper);
                    break;
                default:
                    break;
            }
        }
        else
        {
            holder.order.setVisibility(View.INVISIBLE);
            holder.othersorder.setVisibility(View.VISIBLE);
            holder.othersorder.setText(currentItem.order+"");
        }
        holder.userName.setText(currentItem.userName);
        holder.accuracy.setText(currentItem.accuracy);
        holder.useTime.setText(currentItem.useTime);
        holder.totalScore.setText(currentItem.totalScore);
    }


    class  ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView order;
        private TextView othersorder;
        private TextView userName;
        private TextView accuracy;
        private TextView useTime;
        private TextView totalScore;
        public ViewHolder(View itemView) {
            super(itemView);
            order=(ImageView)itemView.findViewById(R.id.item_order);
            othersorder=(TextView)itemView.findViewById(R.id.item_othersorder);
            userName=(TextView)itemView.findViewById(R.id.item_username);
            accuracy=(TextView)itemView.findViewById(R.id.item_accuracy);
            useTime=(TextView)itemView.findViewById(R.id.item_time);
            totalScore=(TextView)itemView.findViewById(R.id.item_totalscore);
        }
    }
}


