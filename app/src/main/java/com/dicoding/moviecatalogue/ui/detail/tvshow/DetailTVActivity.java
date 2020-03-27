package com.dicoding.moviecatalogue.ui.detail.tvshow;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.request.RequestOptions;
import com.dicoding.moviecatalogue.BuildConfig;
import com.dicoding.moviecatalogue.R;
import com.dicoding.moviecatalogue.data.source.local.entity.TVShowEntity;
import com.dicoding.moviecatalogue.utils.GlideApp;
import com.dicoding.moviecatalogue.viewmodel.ViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailTVActivity extends AppCompatActivity {
    public static final String EXTRA_TVSHOW = "extra_tvshow";
    private TextView txtNameTV;
    private TextView txtDescriptionTV;
    private ImageView imgPhotoTV;
    private TextView txtOriLang;
    private TextView txtDateTV;
    private TextView txtRateTV;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);
        Toolbar toolbar = findViewById(R.id.toolbarDetailTvShow);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        DetailTvViewModel viewModel = obtainViewModel(this);
        txtNameTV = findViewById(R.id.tvshow_name);
        txtDescriptionTV = findViewById(R.id.tvshow_description);
        txtOriLang = findViewById(R.id.tvshow_director);
        txtDateTV = findViewById(R.id.tvshow_date);
        txtRateTV = findViewById(R.id.tvshow_rate);
        imgPhotoTV = findViewById(R.id.tvshow_photo);
        floatingActionButton = findViewById(R.id.floatingButtonTVDetail);
        floatingActionButton.setOnClickListener(view -> viewModel.setFavoriteTVShow());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            long idTvShow = extras.getLong(EXTRA_TVSHOW);
            viewModel.setDetailTV(idTvShow);
        }

        viewModel.getTVDetail().observe(this, detailTvShow -> {
            if (detailTvShow != null) {
                switch (detailTvShow.status) {
                    case SUCCESS:
                        assert detailTvShow.data != null;
                        boolean state = detailTvShow.data.isFavorite();
                        setFloatingButton(state);
                        populateTVShow(detailTvShow.data);
                        break;
                    case LOADING:
                        break;
                    case ERROR:
                        Toast.makeText(this, R.string.errorToast, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @NonNull
    private static DetailTvViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(DetailTvViewModel.class);
    }

    private void populateTVShow(TVShowEntity tvShowEntity) {
        txtNameTV.setText(tvShowEntity.getTvName());
        txtDescriptionTV.setText(tvShowEntity.getTvDescription());
        txtOriLang.setText(tvShowEntity.getTvOriLang());
        txtDateTV.setText(tvShowEntity.getTvDate());
        txtRateTV.setText(tvShowEntity.getTvRate());
        GlideApp.with(getApplicationContext())
                .load(BuildConfig.API_URL_PHOTO + tvShowEntity.getTvPhoto())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_image_loading))
                .error(R.drawable.ic_image_error)
                .into(imgPhotoTV);
    }

    private void setFloatingButton(boolean state) {
        if (state) {
            floatingActionButton.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else {
            floatingActionButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
    }
}
