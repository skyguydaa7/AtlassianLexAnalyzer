package com.atlassian.lbbento.atlassianlexicalanalyzer.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atlassian.lbbento.atlassianlexicalanalyzer.R;
import com.atlassian.lbbento.atlassianlexicalanalyzer.databinding.FragmentHomeBinding;

import org.parceler.Parcels;

/**
 * Created by lbbento on 2/05/16.
 *
 * Home Content Fragment
 */

public class HomeFragment extends Fragment {
    protected final String VIEWMODEL_PARCELABLE = "viewmodel";
    private HomeFragmentViewModel viewModel;
    private FragmentHomeBinding binding;

    /**
     * Creates instance of the Fragment - If need to pass any params to the Fragment - Use bundle
     * @return Fragment created
     */
    public static Fragment newInstance() {
        Fragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(VIEWMODEL_PARCELABLE, Parcels.wrap(viewModel));
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Lbbento - Manage Configuration changes
        if (savedInstanceState != null)
            viewModel = Parcels.unwrap(savedInstanceState.getParcelable(VIEWMODEL_PARCELABLE));
        else
            viewModel = new HomeFragmentViewModel(getActivity());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //View binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setViewModel(viewModel);

        View rootView = binding.getRoot();

        return rootView;
    }


}
