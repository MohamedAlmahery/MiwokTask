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

public class PhrasesActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_phrases);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        final ArrayList<Word> Words = new ArrayList<Word>();

        Words.add(new Word ("Where are you going?","minto wuksus",R.drawable.ic_launcher_background, R.raw.phrase_where_are_you_going));
        Words.add(new Word ("What is your name?","tinnә oyaase'nә",R.drawable.ic_launcher_background,R.raw.phrase_what_is_your_name));
        Words.add(new Word ("My name is...","oyaaset...",R.drawable.ic_launcher_background, R.raw.phrase_my_name_is));
        Words.add(new Word ("How are you feeling?","michәksәs?",R.drawable.ic_launcher_background, R.raw.phrase_how_are_you_feeling));
        Words.add(new Word ("I’m feeling good.","kuchi achit",R.drawable.ic_launcher_background,R.raw.phrase_im_feeling_good));
        Words.add(new Word ("Are you coming?","әәnәs'aa?",R.drawable.ic_launcher_background, R.raw.phrase_are_you_coming));
        Words.add(new Word ("Yes, I’m coming.","hәә’ әәnәm",R.drawable.ic_launcher_background, R.raw.phrase_yes_im_coming));
        Words.add(new Word ("I’m coming.","әәnәm",R.drawable.ic_launcher_background,R.raw.phrase_im_coming));
        Words.add(new Word ("Come here.","әnni'nem",R.drawable.ic_launcher_background, R.raw.phrase_come_here));



        LinearLayout rootView = (LinearLayout)findViewById(R.id.pharesac);

        WordAdapter adapter= new WordAdapter (this,Words,R.color.category_phrases);

        ListView listview = (ListView) findViewById(R.id.listpharses);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = Words.get(position);
                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                    mMediaPlayer = MediaPlayer.create(PhrasesActivity.this,word.getmAudioResourcedId());
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

    }}
