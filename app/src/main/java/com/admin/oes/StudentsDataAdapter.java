package com.admin.oes;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentsDataAdapter extends RecyclerView.Adapter<StudentsDataAdapter.ViewHolder> {
    private List<StudentsDataMOdel> listData;
    Context context;

    public void setlist(List<StudentsDataMOdel> listData) {
        this.listData = listData;
    }

    StudentsDataAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.studentsdata,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final StudentsDataMOdel ld=listData.get(position);
        holder.txtid.setText(ld.getName());
//        holder.txtname.setText(ld.getTest());
        Log.i("name1234",ld.getName());
        holder.txtid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StudentsStatistics.class);
                intent.putExtra("Student_uid",ld.getUid());
                intent.putExtra("Student_name" , ld.getName());
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
            txtid=itemView.findViewById(R.id.id_student_name);
        }
    }
}
