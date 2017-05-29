package com.st18apps.passwordgen;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.st18apps.passwordgen.model.WordsDB;

import java.util.List;

/**
 * Created by st18rai on 22.05.17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    //Предоставляет ссылку на представления, используемые в RecyclerView
    private List<WordsDB> keyWord;

    static class ViewHolder extends RecyclerView.ViewHolder {
        //Определение класса ViewHolder
        private CardView cardView;

        ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public MyAdapter(List<WordsDB> keyWord) {
        this.keyWord = keyWord;
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        //Создание нового представления
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Заполнение заданного представления данными
        final CardView card = holder.cardView;

        final TextView word = (TextView) card.findViewById(R.id.textViewFavoriteWord);
        final TextView type = (TextView) card.findViewById(R.id.textViewFavoriteType);
        ImageButton copy = (ImageButton) card.findViewById(R.id.imageButtonFavoriteCopy);
        ImageButton share = (ImageButton) card.findViewById(R.id.imageButtonFavoriteShare);

        word.setText(keyWord.get(position).getWord());
        type.setText(keyWord.get(position).getType());

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(null, word.getText());
                clipboard.setPrimaryClip(clip);
                //Toast.makeText(v.getContext().getApplicationContext(), R.string.clipboard_text, Toast.LENGTH_SHORT).show();
                Snackbar.make(word, R.string.clipboard_text, Snackbar.LENGTH_LONG).show();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, word.getText());
                v.getContext().startActivity(Intent.createChooser(shareIntent, v.getContext().getString(R.string.share_text)));
            }
        });
    }

    @Override
    public int getItemCount() {
        //Возвращает количество вариантов в наборе данных
        return keyWord.size();
    }
}
