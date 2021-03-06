package com.example.wiam2_m13.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wiam2_m13.HomeActivity;
import com.example.wiam2_m13.R;
import com.example.wiam2_m13.objetos.Plato;
import com.example.wiam2_m13.placeholder.PlaceholderContent;

import java.util.List;

public class Miniature2Adapter extends RecyclerView.Adapter<Miniature2Adapter.ViewHolder> {

    private List<Plato> listaPlatos;
    private LayoutInflater mInflater;
    private Miniature2Adapter.ItemClickListener mClickListener;
    private PlatoRatingChangeListener mPlatoRatingChangeListener;

    public Miniature2Adapter(HomeActivity context, List<Plato> platoList) {
        this.mInflater = LayoutInflater.from(context);
        this.listaPlatos = platoList;
    }

    @NonNull
    @Override
    public Miniature2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.viewholder_home_data, parent, false);
        return new Miniature2Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Plato plato = listaPlatos.get(position);
        Log.d("pruebas", "onBindViewHolder: "+plato );
        holder.myImageView.setImageResource(plato.imageUrl);
        holder.myRatingBar.setRating(plato.rating);

        holder.myRatingBar.setOnRatingBarChangeListener((ratingBar, newRating, fromUser) -> {
            if (fromUser && mPlatoRatingChangeListener != null) {
                mPlatoRatingChangeListener.onRatingChange(plato, newRating);
            }
        });
    }

    @Override
    public int getItemCount() {return listaPlatos.size();}

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RatingBar myRatingBar;
        ImageView myImageView;

        ViewHolder(View itemView) {
            super(itemView);
            myImageView = itemView.findViewById(R.id.imgVHomeData);
            myRatingBar = itemView.findViewById(R.id.rating);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (mClickListener != null) mClickListener.onItemClickmin2(view, getAdapterPosition());
        }
    }
    public Plato getItem(int id) {
        return listaPlatos.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(Miniature2Adapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public  void setRatingChangeListener(PlatoRatingChangeListener listener) {
        this.mPlatoRatingChangeListener = listener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClickmin2(View view, int position);
    }

    public interface PlatoRatingChangeListener {
        void onRatingChange(Plato plato, float newRating);
    }
}
