package com.app.firebasechatdemo.ui.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.firebasechatdemo.R;
import com.app.firebasechatdemo.data.model.ChatModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private List<ChatModel> items;

    public MainAdapter(Context context, List<ChatModel> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ChatModel model = items.get(position);
        if (position % 2 == 0)
            holder.tvData.setGravity(Gravity.END|Gravity.CENTER_VERTICAL);
        else
            holder.tvData.setGravity(Gravity.START|Gravity.CENTER_VERTICAL);


        holder.tvData.setText(model.getText());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView)
        TextView tvData;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                ButterKnife.bind(this, itemView);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
