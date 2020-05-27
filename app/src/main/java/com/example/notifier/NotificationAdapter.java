package com.example.notifier;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notifier.clicked.CheckNotification;
import com.example.notifier.clicked.DoneNotification;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{
    private Context context;
    private List<Notification> notificationList;
    CheckNotification cdb;

    public NotificationAdapter(Context context, List<Notification> list, CheckNotification cdb) {
        this.context = context;
        this.notificationList = list;
        this.cdb = cdb;
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {
        Notification notification = notificationList.get(position);
        System.out.println("Check : "+notification.getTitle());
        System.out.println("Check : "+notification.getDescription());

        holder.not_title.setText(notification.getTitle());
        holder.not_desc.setText(notification.getDescription());
        //System.out.println("chal");
        String go = String.valueOf(notificationList.get(position).getId());
        if(cdb.isExist(go)){
            holder.linearLayout.setBackgroundColor(Color.WHITE);
        }
        //System.out.println("fir chal");
    }


    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView not_title;
        public TextView not_desc;
        public ImageView iconButton;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.change_background);
            not_title = itemView.findViewById(R.id.item_title);
            not_desc = itemView.findViewById(R.id.item_description);
            iconButton = itemView.findViewById(R.id.image);
            iconButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            //Toast.makeText(context,"The Position is : "+pos,Toast.LENGTH_SHORT).show();
            linearLayout.setBackgroundColor(Color.WHITE);
            Notification notify = notificationList.get(pos);
            if(!cdb.isExist(String.valueOf(notify.getId()))){
                DoneNotification read = new DoneNotification(String.valueOf(notify.getId()));
                cdb.add(read);
            }
            //mainActivity.getNotification();
            String name = notify.getTitle();
            String desc = notify.getDescription();

            Intent it = new Intent(context ,ShowNotification.class);
            it.putExtra("Rname",name);
            it.putExtra("Rdesc",desc);
            context.startActivity(it);
        }
    }
}