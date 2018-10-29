package com.almahery.multiscreenappsmsptask;

import android.app.Activity;
import android.provider.UserDictionary;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words,int colorResourcedId) {

        super(context, 0, words);
        mColorResourceId = colorResourcedId;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Word currentWord = getItem(position);

        TextView miwoktextview = (TextView) listItemView.findViewById(R.id.miwok_text_text_view);
        miwoktextview.setText(currentWord.getmMiwokeTranslation());

        TextView defaulttextview = (TextView) listItemView.findViewById(R.id.default_text_text_view);
        defaulttextview.setText(currentWord.getmDefaultTranslation());

        ImageView iconView = (ImageView) listItemView.findViewById(R.id.ImageIcon);

        if (currentWord.hasImage()) {
            iconView.setImageResource(currentWord.getmImageResoursesres());
            iconView.setVisibility(View.VISIBLE);
        } else {
                iconView.setVisibility(View.GONE);
        }
        View textContoiner = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContoiner.setBackgroundColor(color);
        return listItemView;

    }    }
