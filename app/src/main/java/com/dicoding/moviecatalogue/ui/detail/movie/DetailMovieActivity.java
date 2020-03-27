package com.dicoding.moviecatalogue.ui.detail.movie;

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
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity;
import com.dicoding.moviecatalogue.utils.GlideApp;
import com.dicoding.moviecatalogue.viewmodel.ViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    private TextView txtName;
    private TextView txtDescription;
    private ImageView imgPhoto;
    private TextView txtOriLang;
    private TextView txtDate;
    private TextView txtRate;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        DetailViewModel viewModel = obtainViewModel(this);
        Toolbar toolbar = findViewById(R.id.toolbarDetailMovie);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        txtName = findViewById(R.id.txt_name);
        txtDescription = findViewById(R.id.txt_description);
        txtOriLang = findViewById(R.id.txt_director);
        txtDate = findViewById(R.id.txt_date);
        txtRate = findViewById(R.id.txt_rate);
        imgPhoto = findViewById(R.id.img_photo);
        floatingActionButton = findViewById(R.id.floatingButtonMovieDetail);
        floatingActionButton.setOnClickListener(view -> viewModel.setFavoriteMovie());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            long idMovie = extras.getLong(EXTRA_MOVIE);
            viewModel.setDetailMovie(idMovie);
        }
        viewModel.getMovieDetail().observe(this, detailEntity -> {
            if (detailEntity != null) {
                switch (detailEntity.status) {
                    case SUCCESS:
                        assert detailEntity.data != null;
                        boolean state = detailEntity.data.isFavorite();
                        setFloatingButton(state);
                        populateMovie(detailEntity.data);
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
    private static DetailViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(DetailViewModel.class);
    }

    private void populateMovie(MovieEntity movieEntity) {
        txtName.setText(movieEntity.getMvName());
        txtDescription.setText(movieEntity.getMvDescription());
        txtOriLang.setText(movieEntity.getMvOriLang());
        txtDate.setText(movieEntity.getMvDate());
        txtRate.setText(movieEntity.getMvRate());
        GlideApp.with(getApplicationContext())
                .load(BuildConfig.API_URL_PHOTO + movieEntity.getMvPhoto())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_image_loading))
                .error(R.drawable.ic_image_error)
                .into(imgPhoto);
    }

    private void setFloatingButton(boolean state) {
        if (state) {
            floatingActionButton.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else {
            floatingActionButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
    }
}
