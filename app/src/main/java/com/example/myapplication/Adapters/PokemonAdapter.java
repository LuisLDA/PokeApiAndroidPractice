package com.example.myapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.myapplication.Activity.DetailActivity;
import com.example.myapplication.Model.Pokemon;
import com.example.myapplication.PokeApi.ItemClickListener;
import com.example.myapplication.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener,View.OnLongClickListener{
    protected ImageView imageView;
    protected TextView textView;
    protected CardView tarjetas;
    private ItemClickListener itemClickListener;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.avatarPokemon);
        textView = itemView.findViewById(R.id.avatarPokemon_text);
        tarjetas = (CardView) itemView.findViewById(R.id.tarjetas);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }
}


public class PokemonAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<Pokemon> results;
    private Context context;
    private Pokemon pokemon;
    private LayoutInflater inflater;


    public PokemonAdapter(Context context) {
        this.context = context;
        results = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        pokemon = results.get(position);
        holder.textView.setText(pokemon.getName());
        Glide.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemon.getNumber() + ".png").
                centerCrop().transition(DrawableTransitionOptions.withCrossFade()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(context,DetailActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }


}



