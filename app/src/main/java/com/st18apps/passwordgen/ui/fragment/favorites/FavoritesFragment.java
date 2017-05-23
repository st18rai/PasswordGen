package com.st18apps.passwordgen.ui.fragment.favorites;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.st18apps.passwordgen.MyAdapter;
import com.st18apps.passwordgen.R;
import com.st18apps.passwordgen.presentation.presenter.favorites.FavoritesPresenter;
import com.st18apps.passwordgen.presentation.view.favorites.FavoritesView;

public class FavoritesFragment extends MvpAppCompatFragment implements FavoritesView {
    public static final String TAG = "FavoritesFragment";
    @InjectPresenter
    FavoritesPresenter mFavoritesPresenter;

    private String[] keyWord = {
            "Papa",
            "Mama",
            "John",
            "Cat"
    };
    private String[] shifrType = {
            "SHA1",
            "SHA1",
            "BASE64",
            "MD5"
    };

    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();
        if (view != null) {

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerFavorites);

            recyclerView.setNestedScrollingEnabled(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            MyAdapter adapter = new MyAdapter(keyWord, shifrType);
            recyclerView.setAdapter(adapter);
            //add divider
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(),
                    DividerItemDecoration.VERTICAL));

        }
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
