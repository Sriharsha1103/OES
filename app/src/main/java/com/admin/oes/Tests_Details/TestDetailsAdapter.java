package com.admin.oes.Tests_Details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.oes.R;

import java.util.List;

public class TestDetailsAdapter extends RecyclerView.Adapter<TestDetailsAdapter.ViewHolder> {
    private List<TestDetailsModel> listData;
    Context context;

    public void setlist(List<TestDetailsModel> listData) {
        this.listData = listData;
    }

    TestDetailsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.test_questions,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TestDetailsModel ld=listData.get(position);
        holder.ques.setText(ld.getQues());
        holder.cans.setText(ld.getCans());
        holder.gans.setText(ld.getGans());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ques,cans,gans;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ques=itemView.findViewById(R.id.id_user_test_question);
            cans=itemView.findViewById(R.id.id_user_test_correct_ans);
            gans=itemView.findViewById(R.id.id_user_test_given_ans);
        }
    }
}
