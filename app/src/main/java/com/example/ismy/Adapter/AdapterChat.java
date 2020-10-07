package com.example.ismy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ismy.Model.Chat;
import com.example.ismy.R;

import java.util.List;

public class AdapterChat extends RecyclerView.Adapter<AdapterChat.ViewHolder> {

    public static  final int MSG_TYPE_LEFT = 0;
    public static  final int MSG_TYPE_RIGHT = 1;

    private Context mContext;
    private List<Chat> mChat;

    public AdapterChat(Context mContext, List<Chat> mChat){
        this.mChat = mChat;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public AdapterChat.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_right, parent, false);
            return new AdapterChat.ViewHolder(view);
        }  else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_left, parent, false);
            return new AdapterChat.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterChat.ViewHolder holder, final int position) {

        Chat chat = mChat.get(position);

        try {
            if (chat.getName().equals("MyMSG")) {
                holder.show_message.setText(chat.getVopros());
            }
        } catch (Exception e) {}

        try {
            if (chat.getName().equals("BOT")) {
                holder.show_message2.setText(chat.getOtvet());
            }
        } catch (Exception e) {}

    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView show_message, show_message2;

        public ViewHolder(View itemView) {
            super(itemView);

            show_message = itemView.findViewById(R.id.show_message);
            show_message2 = itemView.findViewById(R.id.show_message2);
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (mChat.get(position).getName().equals("MyMSG")){
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }

    }
}