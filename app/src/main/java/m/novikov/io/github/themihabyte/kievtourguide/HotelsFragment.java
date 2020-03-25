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

/**
 * A simple {@link Fragment} subclass.
 */
public class HotelsFragment extends Fragment {
    private AudioManager mAudioManager;
    private AudioFocusRequest mAudioFocusRequest;

    public HotelsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.place_list, container, false);
        final ArrayList<Place> places = new ArrayList<>(Arrays.asList(
                new Place("Hotel Ukraine", "The hotel " +
                        "was built in 1961 as the Hotel" +
                        " \"Moscow\" in a location which originally" +
                        " was occupied by Kiev's first skyscraper, the Ginzburg House.",
                        "Vulytsya Instytutsʹka, 4", R.drawable.hotel_ukraine, R.raw.hotel_ukraine),
                new Place("Hilton", "The Hilton Kyiv hotel boasts a convenient" +
                        " location within walking distance of many major Kiev" +
                        " attractions such as Shevchenko Boulevard and the Old" +
                        " Botanical Gardens.", "Tarasa Shevchenko Blvd, 30",
                        R.drawable.hotel_hilton, R.raw.hotel_hilton),
                new Place("Hyatt Regency", "Located in the heart of the" +
                        " political and business capital of Ukraine",
                        "Ally Tarasovoi St, 5", R.drawable.hotel_hyatt_regency,
                        R.raw.hotel_hyatt_regency)));
        PlaceAdapter placeAdapter = new PlaceAdapter(getActivity(), places);
        ListView listView = rootView.findViewById(R.id.place_list);
        listView.setAdapter(placeAdapter);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();
        mAudioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                .setAudioAttributes(audioAttributes)
                .setAcceptsDelayedFocusGain(true)
                .setOnAudioFocusChangeListener(placeAdapter.onAudioFocusChangeListener()).build();
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        mAudioManager.abandonAudioFocusRequest(mAudioFocusRequest);
    }
}

