package com.st18apps.passwordgen;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by st18rai on 22.05.17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    //Предоставляет ссылку на представления, используемые в RecyclerView
    private String[] keyWord;
    private String[] shifrType;

    static class ViewHolder extends RecyclerView.ViewHolder {
        //Определение класса ViewHolder
        private RelativeLayout relativeLayout;

        ViewHolder(RelativeLayout v) {
            super(v);
            relativeLayout = v;
        }
    }

    public MyAdapter(String[] keyWord, String[] shifrType) {
        this.keyWord = keyWord;
        this.shifrType = shifrType;
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        //Создание нового представления
        RelativeLayout relative = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(relative);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Заполнение заданного представления данными
        final RelativeLayout relativeLayout = holder.relativeLayout;

        final TextView word = (TextView) relativeLayout.findViewById(R.id.textViewFavoriteWord);
        final TextView type = (TextView) relativeLayout.findViewById(R.id.textViewFavoriteType);
        ImageButton favorite = (ImageButton) relativeLayout.findViewById(R.id.imageButtonFavorite);
        ImageButton copy = (ImageButton) relativeLayout.findViewById(R.id.imageButtonFavoriteCopy);
        ImageButton share = (ImageButton) relativeLayout.findViewById(R.id.imageButtonFavoriteShare);

        word.setText(keyWord[position]);
        type.setText(shifrType[position]);

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(null, word.getText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(v.getContext().getApplicationContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
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
        return keyWord.length;
    }
}
