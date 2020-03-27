package m.novikov.io.github.themihabyte.kievtourguide;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParksFragment extends Fragment {

    public ParksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.place_list, container, false);
        final ArrayList<Place> places = new ArrayList<>(Arrays.asList(
                new Place("Taras Shevchenko Park",
                        getString(R.string.park_taras_shevchenko_park_description),
                        "Tarasa Shevchenko Blvd",
                        R.drawable.park_taras_shevchenko_park,
                        R.raw.park_taras_shevchenko_park),
                new Place("Park of Eternal Glory",
                        getString(R.string.park_park_of_eternal_glory),
                        "Lavrska St, 15",
                        R.drawable.park_park_of_eternal_glory,
                        R.raw.park_park_of_eternal_glory),
                new Place("Park Kioto",
                        getString(R.string.park_park_kioto_description),
                        "Lisova underground station, Vulytsya Kioto",
                        R.drawable.park_park_kioto,
                        R.raw.park_park_kioto),
                new Place("Feofaniya Park",
                        getString(R.string.park_feofaniya_park_description),
                        "32 Academic Lebedev`s str.",
                        R.drawable.park_feofaniya_park,
                        R.raw.park_feofaniya_park)
        ));
        PlaceAdapter placeAdapter = new PlaceAdapter(getActivity(), places);
        ListView listView = rootView.findViewById(R.id.place_list);
        listView.setAdapter(placeAdapter);
        return rootView;
    }
}
