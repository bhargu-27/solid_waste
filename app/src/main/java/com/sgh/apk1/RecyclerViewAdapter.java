package com.sgh.apk1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    List<Notify> MainImageUploadInfoList;

    public RecyclerViewAdapter(Context context, List<Notify> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Notify studentDetails = MainImageUploadInfoList.get(position);



        holder.StudentNumberTextView.setText(studentDetails.getArea());
        holder.txt_status.setText(studentDetails.getStatus());

    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView StudentNameTextView;
        public TextView StudentNumberTextView;
        public TextView txt_status;
        public ViewHolder(View itemView) {

            super(itemView);


            StudentNumberTextView = (TextView) itemView.findViewById(R.id.area);
            txt_status=(TextView) itemView.findViewById(R.id.status);
        }
    }
}