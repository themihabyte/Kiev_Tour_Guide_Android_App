package m.novikov.io.github.themihabyte.kievtourguide;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class HotelsFragment extends Fragment {
    private AudioManager mAudioManager;
    private AudioFocusRequest mAudioFocusRequest;
    private PlaceAdapter mPlaceAdapter;

    public HotelsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.place_list, container, false);
        final ArrayList<Place> places = new ArrayList<>(Arrays.asList(
                new Place("Hotel Ukraine",
                        getString(R.string.hotel_ukraine_description),
                        "Vulytsya Instytutsʹka, 4",
                        R.drawable.hotel_ukraine, R.raw.hotel_ukraine),
                new Place("Hilton",
                        getString(R.string.hotel_hilton_description),
                        "Tarasa Shevchenko Blvd, 30",
                        R.drawable.hotel_hilton, R.raw.hotel_hilton),
                new Place("Hyatt Regency",
                        getString(R.string.hotel_hyatt_regency_description),
                        "Ally Tarasovoi St, 5", R.drawable.hotel_hyatt_regency,
                        R.raw.hotel_hyatt_regency),
                new Place("Premier Palace",
                        getString(R.string.hotel_premier_palace_description),
                        "5-7/29 T. Shevchenka Blvd, Pushkinska St",
                        R.drawable.hotel_premier_palace,
                        R.raw.hotel_premier_palace)));
        mPlaceAdapter = new PlaceAdapter(getActivity(), places);
        ListView listView = rootView.findViewById(R.id.place_list);
        listView.setAdapter(mPlaceAdapter);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();
        mAudioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                .setAudioAttributes(audioAttributes)
                .setAcceptsDelayedFocusGain(true)
                .setOnAudioFocusChangeListener(mPlaceAdapter.onAudioFocusChangeListener()).build();
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        mPlaceAdapter.releaseMediaPlayer();
        mAudioManager.abandonAudioFocusRequest(mAudioFocusRequest);
    }
}

