package net.freelance.android.miwok.fragments;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import net.freelance.android.miwok.R;
import net.freelance.android.miwok.model.Word;
import net.freelance.android.miwok.adapter.WordAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    /*private static final String TAG_NUMBERS_PAGE = "NUMBERS_PAGE";

    private int mNumbersPage;

    public static NumbersFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(TAG_NUMBERS_PAGE, page);
        NumbersFragment numbersFragment = new NumbersFragment();
        numbersFragment.setArguments(args);
        return numbersFragment;
    }*/

    public NumbersFragment() {
        // Required empty public constructor
    }

    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNumbersPage = getArguments().getInt(TAG_NUMBERS_PAGE);
    }*/

    /**
     * Handles playback of all the sound files.
     */
    private MediaPlayer mMediaPlayer;

    /**
     * Handles audio focus when playing a sound file.
     */
    private AudioManager mAudioManager;
    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed playing the audio file.
     */

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            //Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device.)
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                //The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a short amount of time.
                //The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that our app is allowed to continue playing around but at a lover volume.
                //We'll treat both cases the same way because our app is playing short sound files.

                //Pause playback and reset player to the start of the file. That way, we can play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                //The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                //The AUDIOFOCUS_LOSS case means we've lost audio focus and Stop playback and clean up resources.
                releaseMediaPlayer();
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        //Create and Setup the {@link AudioManager} to request audio focus.
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        //Create an array of words.
        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word(R.string.number_one, R.string.miwok_number_one, R.drawable.number_one, R.raw.number_one));
        words.add(new Word(R.string.number_two, R.string.miwok_number_two, R.drawable.number_two, R.raw.number_two));
        words.add(new Word(R.string.number_three, R.string.miwok_number_three, R.drawable.number_three, R.raw.number_three));
        words.add(new Word(R.string.number_four, R.string.miwok_number_four, R.drawable.number_four, R.raw.number_four));
        words.add(new Word(R.string.number_five, R.string.miwok_number_five, R.drawable.number_five, R.raw.number_five));
        words.add(new Word(R.string.number_six,R.string.miwok_number_six, R.drawable.number_six, R.raw.number_six));
        words.add(new Word(R.string.number_seven, R.string.miwok_number_seven, R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word(R.string.number_eight, R.string.miwok_number_eight, R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word(R.string.number_nine, R.string.miwok_number_nine, R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word(R.string.number_ten, R.string.miwok_number_ten, R.drawable.number_ten, R.raw.number_ten));

        //Create an ArrayAdapter object.
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);

        //Typecast for listView on with XML
        ListView lv = (ListView) rootView.findViewById(R.id.listView);

        //Set the listView data with ArrayAdapter.
        lv.setAdapter(adapter);

        //Set a click listener to play the audio when the list item is clicked on
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                //Release the media player if it currently exists because we are about to play a different sound file.
                releaseMediaPlayer();

                //Get the {@link Word} object at the given position the user clicked on
                Word eachWordAudio = words.get(position);

                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // We've a audio focus now.

                    //Create and setup the {@link MediaPlayer} for the audio resource associated with the current word
                    mMediaPlayer = MediaPlayer.create(getActivity(), eachWordAudio.getmAudioResourceId());

                    //Start the audio file.
                    mMediaPlayer.start();

                    //Setup a listener on the media player, so that we can stop and release the media player once the sounds has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
        return rootView;
    }

    /**
     * Different Usage between "protected" used to Activity and
     * "public" used to Fragment in onStop() Method Activity Life Cycle.
     */
    @Override
    public void onStop() {
        super.onStop();
        //When the activity is stopped, release the media player resources because we won't be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {

        if (mMediaPlayer != null) {
            //Regardless of the current state of the media player, release its resources because we no longer need it.
            mMediaPlayer.release();

            //Set the media player back to null.For our code, we've decided that setting the media player to null is an easy way to tell that the media player isn't configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
