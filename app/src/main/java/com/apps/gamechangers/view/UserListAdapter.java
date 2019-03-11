package com.apps.gamechangers.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.gamechangers.R;
import com.apps.gamechangers.model.User;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter {

    interface Callback {
        void onSelected(User user);
    }

    private final Callback callback;
    private List<User> users = new ArrayList<>();

    public UserListAdapter(Callback callback) {
        this.callback = callback;
    }

    void addUser(User user) {
        users.add(user);
        notifyItemInserted(users.size() - 1);
    }

    void setUsers(List<User> users) {
        this.users.clear();
        this.users.addAll(users);
        notifyDataSetChanged();
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView userNameView;
        private View colorView;
        private ImageView userProfileIcon;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameView = itemView.findViewById(R.id.user_name);
            colorView = itemView.findViewById(R.id.user_color);
            userProfileIcon = itemView.findViewById(R.id.user_profile_icon);
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list_view, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final MyViewHolder rowView = (MyViewHolder)viewHolder;
        final User user = users.get(i);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(user.getUpdated());


        Glide.with(rowView.itemView)
                .asDrawable()
                .load(user.getProfile().getProfilePictureThumbnail())
                .placeholder(android.R.drawable.btn_star)
                .circleCrop()
                .into(rowView.userProfileIcon);

        rowView.userNameView.setText(user.getProfile().getRealName());
        rowView.colorView.setBackgroundColor(user.getColor());
        rowView.itemView.findViewById(R.id.user_color_indicator).setBackgroundColor(user.getColor());
        rowView.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onSelected(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
