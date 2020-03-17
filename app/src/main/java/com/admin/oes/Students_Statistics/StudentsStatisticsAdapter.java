package com.admin.oes.Students_Statistics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.oes.R;
import com.admin.oes.Statistics.StatisticsModel;

import java.util.List;

public class StudentsStatisticsAdapter extends RecyclerView.Adapter<StudentsStatisticsAdapter.viewHolder> {
    private List<StatisticsModel> listData;
    Context context;

    public void setlist(List<StatisticsModel> listData) {
        this.listData = listData;
    }

    StudentsStatisticsAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.stats,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final StatisticsModel ld=listData.get(position);
        holder.test_name.setText(ld.getTest_Name());
        holder.test_date.setText(ld.getTime());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView test_name,test_date;
        LinearLayout ll;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            test_name=itemView.findViewById(R.id.id_user_test_name);
            test_date=itemView.findViewById(R.id.did_user_test_date);
            ll=itemView.findViewById(R.id.id_test_details_linear_layout);
        }
    }
}
