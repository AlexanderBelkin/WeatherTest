package com.dev.weathertest.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.dev.weathertest.MyApplication;
import com.dev.weathertest.R;
import com.dev.weathertest.di.DaggerFragmentComponent;
import com.dev.weathertest.di.FragmentComponent;
import com.dev.weathertest.di.FragmentModule;

public abstract class BaseFragment extends Fragment implements BaseMvpView {

    private FragmentComponent fragmentComponent;

    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent = DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(this))
                .appComponent(MyApplication.appComponent)
                .build();
    }

    @Override
    public void showLoading() {
        hideLoading();
        progressDialog = showLoadingDialog(getContext());
    }

    public ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_layout);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
    }

    public FragmentComponent getFragmentComponent() {
        return fragmentComponent;
    }
}
