package com.st18apps.passwordgen.ui.fragment.favorites;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.st18apps.passwordgen.MyAdapter;
import com.st18apps.passwordgen.R;
import com.st18apps.passwordgen.model.WordsDB;
import com.st18apps.passwordgen.presentation.presenter.favorites.FavoritesPresenter;
import com.st18apps.passwordgen.presentation.view.favorites.FavoritesView;

import java.util.List;

public class FavoritesFragment extends MvpAppCompatFragment implements FavoritesView {
    public static final String TAG = "FavoritesFragment";
    @InjectPresenter
    FavoritesPresenter mFavoritesPresenter;

    private long initialCount;

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

            final List<WordsDB> keyWord = WordsDB.listAll(WordsDB.class);

            initialCount = WordsDB.count(WordsDB.class);

            final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerFavorites);

            if (initialCount >= 0) {

                if (keyWord.isEmpty())
                    Snackbar.make(recyclerView, R.string.no_words, Snackbar.LENGTH_LONG).show();

            }

            recyclerView.setNestedScrollingEnabled(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            final MyAdapter adapter = new MyAdapter(keyWord);
            recyclerView.setAdapter(adapter);

            // Handling swipe to delete
            ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    //Remove swiped item from list and notify the RecyclerView

                    final int position = viewHolder.getAdapterPosition();
                    final WordsDB wordsDB = keyWord.get(viewHolder.getAdapterPosition());
                    keyWord.remove(viewHolder.getAdapterPosition());
                    adapter.notifyItemRemoved(position);

                    wordsDB.delete();
                    initialCount -= 1;

                    Snackbar.make(recyclerView, R.string.word_deleted, Snackbar.LENGTH_SHORT)
                            .setAction(R.string.undo, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    wordsDB.save();
                                    keyWord.add(position, wordsDB);
                                    adapter.notifyItemInserted(position);
                                    initialCount += 1;

                                }
                            })
                            .show();
                }

            };

            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
            itemTouchHelper.attachToRecyclerView(recyclerView);

        }
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
