package com.dicoding.moviecatalogue.ui.tvshow;


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
public class TVShowFragment extends Fragment {
    private TVShowAdapter tvShowAdapter;
    private RecyclerView rvTVShow;
    private ProgressBar progressBar;

    public TVShowFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new TVShowFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTVShow = view.findViewById(R.id.rv_fragment_tv_show);
        progressBar = view.findViewById(R.id.tvshow_progressBar);
        rvTVShow.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            progressBar.setVisibility(View.VISIBLE);
            TVShowViewModel viewModel = obtainViewModel(getActivity());
            tvShowAdapter = new TVShowAdapter(getActivity());
            viewModel.getTVShowList().observe(this, tvShowEntities -> {
                if (tvShowEntities != null) {
                    switch (tvShowEntities.status) {
                        case LOADING:
                            rvTVShow.setVisibility(View.GONE);
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            rvTVShow.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            tvShowAdapter.setListTVShow(tvShowEntities.data);
                            tvShowAdapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), R.string.errorToast, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
            rvTVShow.setAdapter(tvShowAdapter);
        }
    }

    @NonNull
    private static TVShowViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(TVShowViewModel.class);
    }
}
