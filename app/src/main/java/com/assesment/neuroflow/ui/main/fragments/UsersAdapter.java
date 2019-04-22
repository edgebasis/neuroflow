package com.assesment.neuroflow.ui.main.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.assesment.neuroflow.R;
import com.assesment.neuroflow.data.User;
import com.assesment.neuroflow.utils.Common;
import com.vaibhavlakhera.circularprogressview.CircularProgressView;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class UsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<User> usersList;
    private SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if(viewType == Common.VIEW_TYPE_GROUP ){
            ViewGroup group = (ViewGroup) inflater.inflate(R.layout.group_layout, viewGroup,false);
            GroupViewHolder groupViewHolder = new GroupViewHolder(group);
            return groupViewHolder;
        }else if(viewType == Common.VIEW_TYPE_PERSON ){
            ViewGroup group = (ViewGroup) inflater.inflate(R.layout.group_layout, viewGroup,false);
            PersonViewHolder personViewHolder = new PersonViewHolder(group);
            return personViewHolder;

        }
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof GroupViewHolder){

        }
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    private class GroupViewHolder extends RecyclerView.ViewHolder{
        TextView tv_groupTitle;


        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_groupTitle = itemView.findViewById(R.id.tv_groupName);
        }
    }

    private class PersonViewHolder extends RecyclerView.ViewHolder{
        TextView tv_Name;
        TextView tv_TimeStamp;
        CircularProgressView progressView;


        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Name = itemView.findViewById(R.id.tv_groupName);
            tv_TimeStamp = itemView.findViewById(R.id.tvTimeStamp);
            progressView = itemView.findViewById(R.id.progressCircle);

        }
    }
}
