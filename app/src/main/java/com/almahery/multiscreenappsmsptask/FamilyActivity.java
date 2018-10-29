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

public class FamilyActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_family);


        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        final ArrayList<Word> Words = new ArrayList<Word>();

        Words.add(new Word ("father","әpә",R.drawable.family_father, R.raw.family_father));
        Words.add(new Word ("mother","әṭa",R.drawable.family_mother, R.raw.family_mother));
        Words.add(new Word ("son","angsi",R.drawable.family_son, R.raw.family_son));
        Words.add(new Word ("daughter","tune",R.drawable.family_daughter, R.raw.family_daughter));
        Words.add(new Word ("older brother","taachi",R.drawable.family_older_brother, R.raw.family_older_brother));
        Words.add(new Word ("younger brother","chalitti",R.drawable.family_younger_brother, R.raw.family_younger_brother));
        Words.add(new Word ("older sister","teṭe",R.drawable.family_older_sister, R.raw.family_older_sister));
        Words.add(new Word ("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        Words.add(new Word ("grandmother","ama",R.drawable.family_grandmother, R.raw.family_grandmother));
        Words.add(new Word ("grandfather","paapa",R.drawable.family_grandfather, R.raw.family_grandfather));



        LinearLayout rootView = (LinearLayout)findViewById(R.id.Familyac);

        WordAdapter adapter= new WordAdapter (this,Words,R.color.category_family);

        ListView listview = (ListView) findViewById(R.id.listfam);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = Words.get(position);
                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                    mMediaPlayer = MediaPlayer.create(FamilyActivity.this,word.getmAudioResourcedId());
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
