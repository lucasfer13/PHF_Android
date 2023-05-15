package com.example.phf_android.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.phf_android.Clases.Rating;
import com.example.phf_android.R;

import java.util.ArrayList;

public class AdapterRatings extends RecyclerView.Adapter<AdapterRatings.ViewHolder> {
    ArrayList<Rating> ratings;
    Context context;

    public AdapterRatings(ArrayList<Rating> ratings, Context context)  {
        this.ratings =ratings;
        this.context=context;
    }

    @Override
    public AdapterRatings.ViewHolder onCreateViewHolder(ViewGroup ViewGroup, int viewType) {
        View view = LayoutInflater.from(ViewGroup.getContext())
                .inflate(R.layout.item_rating,ViewGroup,false);
        return new AdapterRatings.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterRatings.ViewHolder ViewHolder, int position) {
        ViewHolder.bind(ratings.get(position));
        //ViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {return ratings.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView user;
        RatingBar rb;

        public ViewHolder(View view) {
            super(view);
            user = view.findViewById(R.id.lblViewRatingNomUsuari);
            rb = view.findViewById(R.id.rtgViewRatingRating);
            // Ús del mètode findViewById per associar els atributs del layout (R.layout....) amb els atributs de la classe
        }
        public void bind(Rating rating){
            user.setText(rating.getUser());
            rb.setEnabled(false);
            rb.setRating(rating.getRating());
            // Assignació dels valors del dataset[position] als atributs de la classe
        }

    }

}
