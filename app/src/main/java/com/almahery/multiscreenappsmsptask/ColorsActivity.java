package com.almahery.multiscreenappsmsptask;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){


                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mMediaPlayer.start();
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
        }
    };
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        final ArrayList<Word> Words = new ArrayList<Word>();

        Words.add(new Word ("red","weṭeṭṭi",R.drawable.color_red, R.raw.color_red));
        Words.add(new Word ("green","chokokki",R.drawable.color_green, R.raw.color_green));
        Words.add(new Word ("brown","ṭakaakki",R.drawable.color_brown, R.raw.color_brown));
        Words.add(new Word ("gray","ṭopoppi",R.drawable.color_gray, R.raw.color_gray));
        Words.add(new Word ("black","kululli",R.drawable.color_black, R.raw.color_black));
        Words.add(new Word ("white","kelelli",R.drawable.color_white, R.raw.color_white));
        Words.add(new Word ("dusty yellow","ṭopiisә",R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        Words.add(new Word ("mustard yellow","chiwiiṭә",R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));



        LinearLayout rootView = (LinearLayout)findViewById(R.id.Colorsac);

        ListView listview = (ListView) findViewById(R.id.listcolor);

        WordAdapter adapter= new WordAdapter (this,Words,R.color.category_colors);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = Words.get(position);
                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this,word.getmAudioResourcedId());
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);

                }}
        });
    }
    @Override
    protected void onStop(){
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        if (mMediaPlayer != null){
            mMediaPlayer.release();
        }
        mMediaPlayer = null;

        mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);

    }
}
