package m.novikov.io.github.themihabyte.kievtourguide;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class PlaceAdapter extends ArrayAdapter<Place> {
    private MediaPlayer mMediaPlayer;
    private Context mContext;

    public PlaceAdapter(@NonNull Context context, ArrayList<Place> places) {
        super(context, 0, places);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from((getContext()))
                    .inflate(R.layout.list_item, parent, false);
        }

        final Place currentPlace = getItem(position);

        TextView placeName = listItemView.findViewById(R.id.place_name);
        placeName.setText(currentPlace.getName());

        TextView placeAddress = listItemView.findViewById(R.id.place_address);
        placeAddress.setText(currentPlace.getAddress());

        ImageView placePhoto = listItemView.findViewById(R.id.place_photo);
        if (currentPlace.isHavePhoto()) {
            placePhoto.setImageResource(currentPlace.getPhoto());
        } else {
            placePhoto.setImageResource(R.drawable.no_image);
        }

        Button buttonVoice = listItemView.findViewById(R.id.place_button_voice);
        if (currentPlace.isHaveVoice()) {
            buttonVoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    releaseMediaPlayer();
                    mMediaPlayer = MediaPlayer.create(mContext, currentPlace.getVoiceResourceID());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseMediaPlayer();
                        }
                    });
                }
            });
        } else {
            buttonVoice.setVisibility(View.GONE);
        }

        final TextView placeDescription = listItemView.findViewById(R.id.place_description);
        placeDescription.setText(currentPlace.getDescription());
        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeDescription.getVisibility() == ViewPager.VISIBLE) {
                    placeDescription.setVisibility(View.GONE);
                } else {
                    placeDescription.setVisibility(View.VISIBLE);
                }
            }
        });

        return listItemView;
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener() {
        return new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                switch (focusChange) {
                    case AudioManager.AUDIOFOCUS_GAIN:
                        mMediaPlayer.start();
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS:
                        releaseMediaPlayer();
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                        break;
                }
            }
        };
    }
}

