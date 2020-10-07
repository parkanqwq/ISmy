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

public class AdapterAllChat extends RecyclerView.Adapter<AdapterAllChat.ViewHolder> {

    public static  final int MSG_TYPE_LEFT = 0;
    public static  final int MSG_TYPE_RIGHT = 1;

    private Context mContext;
    private List<Chat> mChat;

    public AdapterAllChat(Context mContext, List<Chat> mChat){
        this.mChat = mChat;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public AdapterAllChat.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_chat, parent, false);
        return new AdapterAllChat.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterAllChat.ViewHolder holder, final int position) {

        Chat chat = mChat.get(position);

        holder.id.setText(chat.getId());
        holder.MyMSG.setText(chat.getName());
        holder.vopros.setText(chat.getVopros());
        holder.otvet.setText(chat.getOtvet());

    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView id, MyMSG, vopros, otvet;

        public ViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            MyMSG = itemView.findViewById(R.id.MyMSG);
            vopros = itemView.findViewById(R.id.vopros);
            otvet = itemView.findViewById(R.id.otvet);
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