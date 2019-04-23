package com.assesment.neuroflow.ui.main.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.assesment.neuroflow.R;
import com.assesment.neuroflow.data.Gender;
import com.assesment.neuroflow.data.ResponseData;

import com.assesment.neuroflow.ui.main.MainActivity;
import com.assesment.neuroflow.utils.Common;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vaibhavlakhera.circularprogressview.CircularProgressView;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

import static com.assesment.neuroflow.ui.main.HomeActivity.getResourceId;

public class AllUsersFragment extends Fragment {

    private static final String TAG = "AllUsersFragment";
    private final Handler mHandler = new Handler();

    private static final String ARG_USERS_LIST = "argUsers";
    private RecyclerView recyclerView;
    private SectionedRecyclerViewAdapter sectionAdapter;


    private ArrayList<ResponseData> usersList = new ArrayList<>();

    View view;
    public static AllUsersFragment newInstance(List<ResponseData> users) {
        Log.e(TAG, "onCreateView: "+ users.size() );
        AllUsersFragment fragment = new AllUsersFragment();

        Gson gson = new Gson();
        String usersJson = gson.toJson(users);
        Bundle args = new Bundle();
        args.putSerializable(ARG_USERS_LIST, usersJson);
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.all_users_fragment, container, false);

        recyclerView = view.findViewById(R.id.rvAllsUsers);

        String jsonData= getArguments().getString(ARG_USERS_LIST);


        Gson gson = new Gson();
        Type listType = new TypeToken<List<ResponseData>>(){}.getType();
        ArrayList readFromJason = gson.fromJson(jsonData, listType);

        this.usersList = readFromJason;
        setup(usersList);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void setup(ArrayList<ResponseData> users) {




        UsersSection malesSection = new UsersSection(UsersSection.MALE);
        UsersSection femalesSection = new UsersSection(UsersSection.FEMALE);

        sectionAdapter = new SectionedRecyclerViewAdapter();
        sectionAdapter.addSection(malesSection);
        sectionAdapter.addSection(femalesSection);

        loadUsers(femalesSection, users.get(1).getGender());

        loadUsers(malesSection, users.get(0).getGender());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sectionAdapter);

    }

    private void loadUsers(final UsersSection section, final List<Gender> users) {

        section.setHasFooter(false);

        List<Gender> usersList = new ArrayList<>();
        switch(section.getGender()){
            case UsersSection.FEMALE:
                usersList = users;
                break;
            case UsersSection.MALE:
                usersList = users;
                break;
            default:
                throw new IllegalStateException("Invalid section");

        }
        Collections.sort(usersList);
        section.setList(usersList);
        section.setState(Section.State.LOADED);
        section.setHasFooter(false);
        sectionAdapter.notifyDataSetChanged();

    }


    private class UsersSection extends Section {

        private static final int MALE = 1;
        private static final int FEMALE = 0;

        final int gender;
        String title;
        List<Gender> list;

        /**
         * Create a Section object based on {@link SectionParameters}.
         *
         * @param gender section parameters
         */
        public UsersSection(int gender) {
            super(SectionParameters.builder()
                    .itemResourceId(R.layout.all_user_layout)
                    .headerResourceId(R.layout.group_layout)
                    .footerResourceId(R.layout.section_footer)
                    .failedResourceId(R.layout.section_failed)
                    .loadingResourceId(R.layout.section_loading)
                    .build());
            this.gender = gender;
            this.list = Collections.emptyList();


            switch (gender){
                case FEMALE:
                    this.title = getString(R.string.female_title);
                    break;
                case MALE:
                    this.title = getString(R.string.male_title);
                    break;

            }
        }

        int getGender(){
            return gender;
        }

        void setList(List<Gender> list){
            this.list = list;
        }

        @Override
        public int getContentItemsTotal() {
            return list.size();
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
            final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            Gender user = list.get(position);
            String date;

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Common.DATE_PATTERN);

            date = simpleDateFormat.format(new Date((long) user.getDate_created()));
            itemViewHolder.tvName.setText(Character.toUpperCase(user.getName().charAt(0))+user.getName().substring(1));
            itemViewHolder.tvTimeStamp.setText(date);
            int imageId = getResourceId(getContext(), user.getName(), "drawable", getContext().getPackageName());

            itemViewHolder.profilePic.setImageResource(imageId);
            itemViewHolder.progressView.setProgress(user.getScore(), true);

            itemViewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Gson gson = new Gson();
                    String userJson = gson.toJson(user);
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtra("user", userJson);
                    startActivity(intent);
                }
            });
        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.tvGroupTitle.setText(title);
        }

        @Override
        public RecyclerView.ViewHolder getFooterViewHolder(View view) {
            return new FooterViewHolder(view);
        }

        @Override
        public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;

            footerHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), String.format("Clicked on footer of Section %s", title), Toast.LENGTH_SHORT).show();
                }
            });
        }



        @Override
        public RecyclerView.ViewHolder getFailedViewHolder(View view) {
            return new FailedViewHolder(view);
        }

        @Override
        public void onBindFailedViewHolder(RecyclerView.ViewHolder holder) {
            FailedViewHolder footerHeader = (FailedViewHolder) holder;

            footerHeader.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvGroupTitle;

        HeaderViewHolder(View itemView) {
            super(itemView);

            tvGroupTitle = itemView.findViewById(R.id.tv_groupName);
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;

        FooterViewHolder(View view) {
            super(view);

            rootView = view;
        }
    }

    private class FailedViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;

        FailedViewHolder(View itemView) {
            super(itemView);

            rootView = itemView.findViewById(R.id.iv_profilePic);
        }
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final ImageView profilePic;
        private final TextView tvName;
        private final TextView tvTimeStamp;
        private final CircularProgressView progressView;



        public ItemViewHolder(@NonNull View view) {
            super(view);
            rootView = view;
            profilePic = view.findViewById(R.id.iv_allUsersProfilePic);
            tvName = view.findViewById(R.id.tv_allUsersName);
            tvTimeStamp = view.findViewById(R.id.tv_allUsersTimeStamp);
            progressView = view.findViewById(R.id.allUsersrogressCircle);

        }
    }

}
