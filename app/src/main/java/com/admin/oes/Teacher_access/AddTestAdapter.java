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

import java.util.List;

public class AddTestAdapter extends RecyclerView.Adapter<AddTestAdapter.ViewHolder> {
    private List<AddTestModel> listData;
    Context context;
    public void setlist(List<AddTestModel> listData) {
        this.listData = listData;
    }
    AddTestAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.addquestion,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final AddTestModel ld=listData.get(position);
        holder.addquestion.setText("Add Question");
//        holder.addquestion.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i=new Intent(context,AddTestQuestions.class);
//                int t = position + 1;
//                i.putExtra("position",t+"");
//                i.putExtra("test name",ld.getTest_name());
//                i.putExtra("type",ld.getType());
//                i.putExtra("no of questions",ld.getNo_of_questions());
//                i.putExtra("max marks",ld.getMax_marks());
//                i.putExtra("weightage",ld.getWeightage());
//                v.getContext().startActivity(i);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView addquestion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            addquestion=itemView.findViewById(R.id.id_add_question);
        }
    }
}
