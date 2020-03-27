package m.novikov.io.github.themihabyte.kievtourguide;

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
public class SightseeingsFragment extends Fragment {

    public SightseeingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.place_list, container, false);
        final ArrayList<Place> places = new ArrayList<>(Arrays.asList(
                new Place("Kiev Pechersk Lavra",
                        getString(R.string.sightseeing_kiev_pechersk_lavra_description),
                        "Lavrska St, 15",
                        R.drawable.sightseeing_kiev_pechersk_lavra),
                new Place("The Motherland Monument",
                        getString(R.string.sightseeing_the_motherland_monument_description),
                        "Zapecherna St",
                        R.drawable.sightseeing_the_motherland_monument,
                        R.raw.sightseeing_the_motherland_monument),
                new Place("Golden Gate",
                        getString(R.string.sightseeing_golden_gate_description),
                        "Volodymyrska St, 40А",
                        R.drawable.sightseeing_golden_gate,
                        R.raw.sightseeing_golden_gate),
                new Place("Gorodetsky House",
                        getString(R.string.sightseeing_gorodetsky_house_description),
                        "Bankova St, 10",
                        R.drawable.sightseeing_gorodetsky_house,
                        R.raw.sightseeing_gorodetsky_house)
        ));
        PlaceAdapter placeAdapter = new PlaceAdapter(getActivity(), places);
        ListView listView = rootView.findViewById(R.id.place_list);
        listView.setAdapter(placeAdapter);
        return rootView;
    }
}
