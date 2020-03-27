package com.dicoding.moviecatalogue.ui.movie;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.dicoding.moviecatalogue.BuildConfig;
import com.dicoding.moviecatalogue.R;
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity;
import com.dicoding.moviecatalogue.ui.detail.movie.DetailMovieActivity;
import com.dicoding.moviecatalogue.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final Activity activity;
    private List<MovieEntity> mMovie = new ArrayList<>();


    MovieAdapter(Activity activity) {
        this.activity = activity;
    }

    void setListMovies(List<MovieEntity> listMovies) {
        this.mMovie = listMovies;
    }


    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, final int position) {
        holder.txtName.setText(mMovie.get(position).getMvName());
        holder.txtRate.setText(mMovie.get(position).getMvRate());
        holder.txtDate.setText(mMovie.get(position).getMvDate());
        holder.txtDirector.setText(mMovie.get(position).getMvOriLang());
        holder.txtDescription.setText(mMovie.get(position).getMvDescription());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailMovieActivity.class);
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, mMovie.get(position).getMvId());
            activity.startActivity(intent);
        });
        GlideApp.with(holder.itemView.getContext())
                .load(BuildConfig.API_URL_PHOTO + mMovie.get(position).getMvPhoto())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_image_loading).error(R.drawable.ic_image_error))
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return mMovie.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        final TextView txtName;
        final TextView txtDescription;
        final TextView txtDirector;
        final TextView txtDate;
        final TextView txtRate;
        final ImageView imgPhoto;

        MovieViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo);
            txtName = itemView.findViewById(R.id.txt_name);
            txtDescription = itemView.findViewById(R.id.txt_description);
            txtDirector = itemView.findViewById(R.id.txt_director);
            txtDate = itemView.findViewById(R.id.txt_date);
            txtRate = itemView.findViewById(R.id.txt_rate);
        }
    }
}