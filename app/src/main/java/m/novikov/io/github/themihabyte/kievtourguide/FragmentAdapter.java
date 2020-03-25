package m.novikov.io.github.themihabyte.kievtourguide;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {
    private final static int NUMBER_OF_FRAGMENTS = 4;

    private Context mContext;

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior, Context context) {
        super(fm, behavior);
        this.mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RestaurantsFragment();
            case 1:
                return new HotelsFragment();
            case 2:
                return new ParksFragment();
            case 3:
                return new SightseeingsFragment();
            default:
                return new SightseeingsFragment();
        }
    }

    @Override
    public int getCount() {
        return NUMBER_OF_FRAGMENTS;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.category_restaurants);
            case 1:
                return mContext.getString(R.string.category_hotels);
            case 2:
                return mContext.getString(R.string.category_parks);
            case 3:
                return mContext.getString(R.string.category_sightseeings);
            default:
                return mContext.getString(R.string.category_sightseeings);
        }
    }
}
