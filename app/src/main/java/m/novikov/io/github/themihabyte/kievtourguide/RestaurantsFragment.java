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

public class RestaurantsFragment extends Fragment {
    private AudioManager mAudioManager;
    private AudioFocusRequest mAudioFocusRequest;
    private PlaceAdapter mPlaceAdapter;
    public RestaurantsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.place_list, container, false);
        final ArrayList<Place> places = new ArrayList<>(Arrays.asList(
                new Place("Meatbusters",
                        getString(R.string.restaurant_meatbusters_description),
                        "Yuriya Shums'koho St, 3"),
                new Place("Shoti Restaurant",
                        getString(R.string.restaurant_shoti_restaurant),
                        "Mechnykova St, 9",
                        R.drawable.restaurant_shoti_restaurant),
                new Place("BAO • Modern Chinese Cuisine",
                        getString(R.string.restaurant_bao_description),
                        "Mechnykova St, 14/1",
                        R.drawable.restaurant_bao),
                new Place("BEEF meat & wine",
                        getString(R.string.restaurant_beef_description),
                        "Shota Rustaveli St, 11",
                        R.drawable.restaurant_beef)
        ));
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
