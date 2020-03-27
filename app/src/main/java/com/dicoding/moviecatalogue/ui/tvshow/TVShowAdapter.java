package com.dicoding.moviecatalogue.ui.tvshow;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
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
import com.dicoding.moviecatalogue.data.source.local.entity.TVShowEntity;
import com.dicoding.moviecatalogue.ui.detail.tvshow.DetailTVActivity;
import com.dicoding.moviecatalogue.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder> {
    private final Activity activity;
    private List<TVShowEntity> mTVShow = new ArrayList<>();

    TVShowAdapter(Activity activity) {
        this.activity = activity;
    }

    void setListTVShow(List<TVShowEntity> listTVSHows) {
        this.mTVShow = listTVSHows;
    }

    @NonNull
    @Override
    public TVShowAdapter.TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_show_item, parent, false);
        return new TVShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowAdapter.TVShowViewHolder holder, int position) {
        holder.txtNameTV.setText(mTVShow.get(position).getTvName());
        holder.txtRateTV.setText(mTVShow.get(position).getTvRate());
        holder.txtDateTV.setText(mTVShow.get(position).getTvDate());
        holder.txtDirectorTV.setText(mTVShow.get(position).getTvOriLang());
        holder.txtDescriptionTV.setText(mTVShow.get(position).getTvDescription());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailTVActivity.class);
            intent.putExtra(DetailTVActivity.EXTRA_TVSHOW, mTVShow.get(position).getId());
            Log.i("onBindViewHolder: ", String.valueOf(mTVShow.get(position).getId()));
            activity.startActivity(intent);
        });

        GlideApp.with(holder.itemView.getContext())
                .load(BuildConfig.API_URL_PHOTO + mTVShow.get(position).getTvPhoto())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_image_loading).error(R.drawable.ic_image_error))
                .into(holder.imgPhotoTV);

    }

    @Override
    public int getItemCount() {
        return mTVShow.size();
    }

    class TVShowViewHolder extends RecyclerView.ViewHolder {
        final TextView txtNameTV;
        final TextView txtDescriptionTV;
        final TextView txtDirectorTV;
        final TextView txtDateTV;
        final TextView txtRateTV;
        final ImageView imgPhotoTV;

        TVShowViewHolder(View itemView) {
            super(itemView);
            imgPhotoTV = itemView.findViewById(R.id.tvshow_photo);
            txtNameTV = itemView.findViewById(R.id.tvshow_name);
            txtDescriptionTV = itemView.findViewById(R.id.tvshow_description);
            txtDirectorTV = itemView.findViewById(R.id.tvshow_director);
            txtDateTV = itemView.findViewById(R.id.tvshow_date);
            txtRateTV = itemView.findViewById(R.id.tvshow_rate);
        }
    }
}
