package com.admin.oes.Statistics;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.oes.QuestionModel;
import com.admin.oes.R;
import com.admin.oes.Tests_Details.TestDetails;

import java.util.ArrayList;
import java.util.List;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.ViewHolder> {
    private List<StatisticsModel> listData;
    Context context;

    public void setlist(List<StatisticsModel> listData) {
        this.listData = listData;
    }

    StatisticsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.stats,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final StatisticsModel ld=listData.get(position);

        final String Name = ld.getName();
        final String TotalQ = ld.getTotalQ();
        final String Correctans = ld.getCorrectans();
        final String wrongans = ld.getWrongans();
        final String Time = ld.getTime();
        final String role=ld.getRole();
        final String Teacher = ld.getTeacher();
        final String ID = ld.getID();
        final ArrayList<QuestionModel> ques=ld.getQuestion();

        holder.test_name.setText(ld.getTest_Name());
        holder.test_date.setText(ld.getTime());
            holder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, TestDetails.class);
                    intent.putExtra("test_name", ld.getTest_Name());
                    v.getContext().startActivity(intent);
                }
            });
        }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView test_name,test_date;
        LinearLayout ll;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            test_name=itemView.findViewById(R.id.id_user_test_name);
            test_date=itemView.findViewById(R.id.did_user_test_date);
            ll=itemView.findViewById(R.id.id_test_details_linear_layout);
        }
    }
}
