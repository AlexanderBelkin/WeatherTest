package com.dev.weathertest.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.weathertest.R;
import com.dev.weathertest.base.BaseFragment;
import com.dev.weathertest.model.WeatherResp;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends BaseFragment implements DetailMvpView {
    private static final String ARG_LAT_LNG = "ARG_LAT_LNG";
    public static final String TAG = "DetailFragment";

    @Nullable
    private Callback callback;

    @BindView(R.id.weather_text_view)
    TextView weatherTextView;

    @Inject
    DetailPresenter<DetailMvpView> presenter;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(LatLng latLng) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_LAT_LNG, latLng);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        getFragmentComponent().inject(this);
        presenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        view.setClickable(true);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter.onAttachView(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            callback = (Callback) context;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDetachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public Double[] getLocation() {
        if (getArguments() != null) {
            LatLng latLng = getArguments().getParcelable(ARG_LAT_LNG);
            if (latLng != null) {
                return new Double[]{latLng.latitude, latLng.longitude};
            }
        }
        return null;
    }

    @Override
    public void setData(WeatherResp weatherResp) {
        weatherTextView.setText(weatherResp.toString());
    }

    public interface Callback {
    }
}
