package com.dicoding.moviecatalogue.ui.favorite.movie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.dicoding.moviecatalogue.BuildConfig;
import com.dicoding.moviecatalogue.R;
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity;
import com.dicoding.moviecatalogue.ui.detail.movie.DetailMovieActivity;
import com.dicoding.moviecatalogue.utils.GlideApp;

public class FavMovieAdapter extends PagedListAdapter<MovieEntity, FavMovieAdapter.FavMovViewHolder> {
    private static DiffUtil.ItemCallback<MovieEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<MovieEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
            return oldItem.getMvId() == newItem.getMvId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
            return oldItem.equals(newItem);
        }
    };

    FavMovieAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public FavMovieAdapter.FavMovViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new FavMovViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavMovViewHolder holder, int position) {
        final MovieEntity movieEntity = getItem(position);
        if (movieEntity != null) {
            holder.txtName.setText(movieEntity.getMvName());
            holder.txtRate.setText(movieEntity.getMvRate());
            holder.txtDate.setText(movieEntity.getMvDate());
            holder.txtDirector.setText(movieEntity.getMvOriLang());
            holder.txtDescription.setText(movieEntity.getMvDescription());
            holder.itemView.setOnClickListener(v -> {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movieEntity.getMvId());
                context.startActivity(intent);
            });
            GlideApp.with(holder.itemView.getContext())
                    .load(BuildConfig.API_URL_PHOTO + movieEntity.getMvPhoto())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_image_loading).error(R.drawable.ic_image_error))
                    .into(holder.imgPhoto);
        }
    }


    class FavMovViewHolder extends RecyclerView.ViewHolder {
        final TextView txtName;
        final TextView txtDescription;
        final TextView txtDirector;
        final TextView txtDate;
        final TextView txtRate;
        final ImageView imgPhoto;

        FavMovViewHolder(@NonNull View itemView) {
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
