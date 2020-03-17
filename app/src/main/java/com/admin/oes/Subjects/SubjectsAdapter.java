package com.admin.oes.Subjects;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.oes.R;
import com.admin.oes.Topics.Topics;

import java.util.List;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.ViewHolder> {
    private List<SubjectsModel> listData;
    Context context;
    public void setlist(List<SubjectsModel> listData) {
        this.listData = listData;
    }
    SubjectsAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.subjects,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final SubjectsModel ld=listData.get(position);
                holder.txtid.setText(ld.getKey());
//        holder.txtname.setText(ld.getTest());
        holder.txtid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Topics.class);
                intent.putExtra("subject_name" , ld.getKey());
                v.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtid;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtid=itemView.findViewById(R.id.id_subject_name);
        }
    }
}
