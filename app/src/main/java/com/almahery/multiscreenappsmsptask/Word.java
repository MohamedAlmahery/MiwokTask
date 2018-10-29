package com.almahery.multiscreenappsmsptask;


public class Word{

    private String mDefaultTranslation;
    private String mMiwokeTranslation;
    private int mImageResoursesres = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mAudioResourcedId;


    public Word(String defaultTranslation, String miwokeTranslation, int ImageResoursesres, int audioResourcedId){
        mDefaultTranslation = defaultTranslation;
        mMiwokeTranslation = miwokeTranslation;
        mImageResoursesres = ImageResoursesres;
        mAudioResourcedId = audioResourcedId;
    }


    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmMiwokeTranslation() {
        return mMiwokeTranslation;
    }

    public int getmImageResoursesres() {
        return mImageResoursesres;
    }

    public boolean hasImage(){
        return mImageResoursesres != NO_IMAGE_PROVIDED;
    }

    public int getmAudioResourcedId(){return mAudioResourcedId; }

}
