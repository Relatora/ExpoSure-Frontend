package com.example.exposure.Adapter;

import androidx.appcompat.widget.AppCompatImageView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.exposure.R;
import com.squareup.picasso.Picasso;

import java.awt.font.TextAttribute;
import java.util.List;

import com.example.exposure.Model.ExpoEvent;

/**
 * Event adapter class to populate RecyclerView with REST data coming from NodeJS backend
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder>{

    List<ExpoEvent> expoEventList;

    /**
     * Adapter Constructor
     * @param expoEventList ExpoEvent List
     */
    public EventAdapter(List<ExpoEvent> expoEventList) {
        this.expoEventList = expoEventList;
    }

    /**
     * Inflates layout with data received from backend
     * @param parent context
     * @param viewType viewType
     * @return new MyViewHolder(itemView) View
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_layout,parent, false);
        // create a new view
        // View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.feedholder, parent, false);
        // set the view's size, margins, paddings and layout parameters
        // ViewHolder vh = new ViewHolder(v);
        // set the Context here
        //context = parent.getContext();
        //return vh;
        return new MyViewHolder(itemView);
    }

    /**
     * Binds holder with data received from backend based on position of object in thee REST list received from backend
     * @param holder Custom made container for data plugged into controls
     * @param position position of object in REST list returned from backend
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(expoEventList.get(position).getName());
        holder.location.setText(expoEventList.get(position).getLocation());
        holder.contact_email.setText(expoEventList.get(position).getcontact_email());
        holder.contact_phone.setText(expoEventList.get(position).getcontact_phone());
        holder.event_date.setText(expoEventList.get(position).getdate().toString());

        // Load Images - Picasso library - takes care of async, resizing,
        // cropping and rendering an image into the holder
        Picasso.get()
                .load(expoEventList.get(position).getPhoto())
                .resize(400, 150)
                .centerCrop()
                .into(holder.event_photo);

        // Alternating colors of holder between white nad light grey to improve readability
        if( position % 2 == 0)
            holder.root_view.setCardBackgroundColor(Color.parseColor("#E1E1E1"));
    }

    /**
     * Gets List Size for proper iteration
     * @return expoEventList.size()
     */
    @Override
    public int getItemCount() {
        return expoEventList.size();
    }

    /**
     * Create a custom view holder that ties the holder with the control objects
     */
    public class MyViewHolder extends RecyclerView.ViewHolder{
        CardView root_view;
        TextView title, contact_phone, contact_email, location, event_date;
        AppCompatImageView event_photo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            root_view = itemView.findViewById(R.id.root_view);
            title = itemView.findViewById(R.id.txt_event_title);
            contact_phone = itemView.findViewById(R.id.txt_event_contact_phone);
            contact_email= itemView.findViewById(R.id.txt_event_contact_email);
            location = itemView.findViewById(R.id.txt_event_contact_location);
            event_date = itemView.findViewById(R.id.txt_event_date);
            event_photo = itemView.findViewById(R.id.img_Event);
        }
    }
}
