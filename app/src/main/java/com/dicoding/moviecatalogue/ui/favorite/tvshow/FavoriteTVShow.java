package com.dicoding.moviecatalogue.ui.favorite.tvshow;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.moviecatalogue.R;
import com.dicoding.moviecatalogue.viewmodel.ViewModelFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTVShow extends Fragment {
    private FavTVAdapter tvAdapter;
    private RecyclerView rvTVShow;
    private ProgressBar progressBar;

    public FavoriteTVShow() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTVShow = view.findViewById(R.id.rv_tv_show_favorite);
        progressBar = view.findViewById(R.id.tvshow_progressBar);
        rvTVShow.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            progressBar.setVisibility(View.VISIBLE);
            FavTVViewModel viewModel = obtainVieModel(getActivity(), this);
            tvAdapter = new FavTVAdapter();
            viewModel.getFavTVShow().observe(this, pagedListResource -> {
                if (pagedListResource != null) {
                    switch (pagedListResource.status) {
                        case LOADING:
                            rvTVShow.setVisibility(View.GONE);
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            rvTVShow.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            tvAdapter.submitList(pagedListResource.data);
                            tvAdapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), R.string.errorToast, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
            rvTVShow.setAdapter(tvAdapter);
        }
    }

    private FavTVViewModel obtainVieModel(FragmentActivity activity, Fragment fragment) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(fragment, factory).get(FavTVViewModel.class);
    }
}
