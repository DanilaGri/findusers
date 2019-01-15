package com.master.findusers.search.presentation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.master.findusers.R;
import com.master.findusers.search.domain.model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchUserAdapter extends RecyclerView.Adapter<SearchUserAdapter.ViewHolder> {

    private final OnListFragmentInteractionListener mListener;
    private List<User> mUserList;

    public SearchUserAdapter(List<User> items, OnListFragmentInteractionListener listener) {
        mUserList = items;
        mListener = listener;
    }

    public List<User> getUserList() {
        return mUserList;
    }

    public void setUserList(List<User> items) {
        mUserList = items;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        User mUser = mUserList.get(position);
        Context context = holder.iulName.getContext();
        holder.iulName.setText(mUser.getName() == null ? context.getText(R.string.name) + ": -" : context.getText(R.string.name) + ": " + mUser.getName());
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iul_name)
        TextView iulName;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onListFragmentInteraction(getAdapterPosition());
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + iulName.getText() + "'";
        }
    }
}
