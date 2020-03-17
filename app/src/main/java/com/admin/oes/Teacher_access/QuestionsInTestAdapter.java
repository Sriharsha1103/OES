package com.admin.oes.Teacher_access;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.oes.R;
import com.admin.oes.Tests_Details.TestDetailsModel;
import com.admin.oes.Topics.TopicModel;

import java.util.List;

public class QuestionsInTestAdapter extends RecyclerView.Adapter<QuestionsInTestAdapter.ViewHolder> {
    private List<TestDetailsModel> listData;
    Context context;
    public void setlist(List<TestDetailsModel> listData) {
        this.listData = listData;
    }
    QuestionsInTestAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lidt_data,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final TestDetailsModel ld=listData.get(position);
        holder.addquestion.setText(ld.getQues());
        holder.addquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,QuestionDetails.class);
                i.putExtra("question_Name",ld.getQues());
                i.putExtra("position",position);
                Log.i("pos", String.valueOf(position));
                i.putExtra("ques",ld.getCans());
                i.putExtra("Test_name",ld.getGans());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView addquestion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            addquestion=itemView.findViewById(R.id.one);
        }
    }
}
