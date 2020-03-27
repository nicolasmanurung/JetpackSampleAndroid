package com.dicoding.moviecatalogue.ui.favorite.tvshow;

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
import com.dicoding.moviecatalogue.data.source.local.entity.TVShowEntity;
import com.dicoding.moviecatalogue.ui.detail.tvshow.DetailTVActivity;
import com.dicoding.moviecatalogue.utils.GlideApp;

public class FavTVAdapter extends PagedListAdapter<TVShowEntity, FavTVAdapter.FavTVViewHolder> {
    private static DiffUtil.ItemCallback<TVShowEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<TVShowEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull TVShowEntity oldItem, @NonNull TVShowEntity newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull TVShowEntity oldItem, @NonNull TVShowEntity newItem) {
            return oldItem.equals(newItem);
        }
    };

    FavTVAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public FavTVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_show_item, parent, false);
        return new FavTVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavTVViewHolder holder, int position) {
        final TVShowEntity tvShowEntity = getItem(position);
        if (tvShowEntity != null) {
            holder.txtName.setText(tvShowEntity.getTvName());
            holder.txtDescription.setText(tvShowEntity.getTvDescription());
            holder.txtDate.setText(tvShowEntity.getTvDate());
            holder.txtDirector.setText(tvShowEntity.getTvOriLang());
            holder.txtRate.setText(tvShowEntity.getTvRate());
            holder.itemView.setOnClickListener(v -> {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, DetailTVActivity.class);
                intent.putExtra(DetailTVActivity.EXTRA_TVSHOW, tvShowEntity.getId());
                context.startActivity(intent);
            });
            GlideApp.with(holder.itemView.getContext())
                    .load(BuildConfig.API_URL_PHOTO + tvShowEntity.getTvPhoto())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_image_loading).error(R.drawable.ic_image_error))
                    .into(holder.imgPhoto);
        }
    }

    class FavTVViewHolder extends RecyclerView.ViewHolder {
        final TextView txtName;
        final TextView txtDescription;
        final TextView txtDirector;
        final TextView txtDate;
        final TextView txtRate;
        final ImageView imgPhoto;

        FavTVViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tvshow_name);
            txtDescription = itemView.findViewById(R.id.tvshow_description);
            txtDirector = itemView.findViewById(R.id.tvshow_director);
            txtDate = itemView.findViewById(R.id.tvshow_date);
            txtRate = itemView.findViewById(R.id.tvshow_rate);
            imgPhoto = itemView.findViewById(R.id.tvshow_photo);
        }
    }
}
