package com.startandroid.biimka.discount_card_aggregator;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Card> cards = Collections.emptyList();
    private Listener listener;

    public void setCards(List<Card> cards) {
        this.cards = cards;
        notifyDataSetChanged();
    }

    void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {
        void onItemClicked(Card card);

        void onItemClickedLong(Card card);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewCardName;
        private ImageView imageFront;

        private MyViewHolder(View v) {
            super(v);
            textViewCardName = v.findViewById(R.id.textViewCardName);
            imageFront = v.findViewById(R.id.frontImage);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(cards.get(getAdapterPosition()));
                }
            });
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClickedLong(cards.get(getAdapterPosition()));
                }
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Bitmap bitmap = new DBHelper(App.context).getBitmap(cards.get(position).getImageFrontBytes());
        myViewHolder.textViewCardName.setText(cards.get(position).getName());
        myViewHolder.imageFront.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
