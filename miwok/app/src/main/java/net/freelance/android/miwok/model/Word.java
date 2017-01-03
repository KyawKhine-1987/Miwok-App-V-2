package net.freelance.android.miwok.model;

/**
 * Created by Kyaw Khine on 12/05/2016.
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a Miwok translation for that word.
 */
public class Word {

    /*
     *Create a STATE
     */
    //Default Translation for the word.
//    private String mDefaultTranslation;
    private int mDefaultTranslationId;

    //Miwok Translation for the word.
//    private String mMiwokTranslation;
    private int mMiwokTranslationId;

    //Audio resource ID for the word
    private int mAudioResourceId;

    //Image Resource ID for the word.
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    //Constant value that represents no image was provided for this word.
    private static final int NO_IMAGE_PROVIDED = -1;

    /*
     *Create a new Word object.
     */
    //Create a constructor of Word two strings
    /*public Word(String defaultTranslation, String miwokTranslation, int audioResourceId) {
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;
        this.mAudioResourceId = audioResourceId;
    }*/
    public Word(int defaultTranslationId, int miwokTranslationId, int audioResourceId) {
        this.mDefaultTranslationId = defaultTranslationId;
        this.mMiwokTranslationId = miwokTranslationId;
        this.mAudioResourceId = audioResourceId;
    }

    //Create a constructor of Word two strings and one image
   /* public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int audioResourceId) {
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;
        this.mImageResourceId = imageResourceId;
        this.mAudioResourceId = audioResourceId;
    }*/
    public Word(int defaultTranslationId, int miwokTranslationId, int imageResourceId, int audioResourceId) {
        this.mDefaultTranslationId = defaultTranslationId;
        this.mMiwokTranslationId = miwokTranslationId;
        this.mImageResourceId = imageResourceId;
        this.mAudioResourceId = audioResourceId;
    }

    /*
     * Create a relevant METHOD of State
     */
    //Get the default translation of the word.
    /*public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }*/
    public int getmDefaultTranslation() {
        return mDefaultTranslationId;
    }

    //Get the miwok translation of the word.
    /*public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }*/
    public int getmMiwokTranslation() {
        return mMiwokTranslationId;
    }

    //Get the image resource of the word.
    public int getmImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Return whether or not there is an image for this word.
     * mImageResourceId is not equal to -1, then return true(Showed the ImageView).
     * Otherwise return false for hasImage method.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getmAudioResourceId() {
        return mAudioResourceId;
    }
}
