package com.st18apps.passwordgen.ui.fragment.aboutApp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.st18apps.passwordgen.BuildConfig;
import com.st18apps.passwordgen.R;
import com.st18apps.passwordgen.presentation.view.aboutApp.AboutAppView;
import com.st18apps.passwordgen.presentation.presenter.aboutApp.AboutAppPresenter;

import com.arellomobile.mvp.presenter.InjectPresenter;

public class AboutAppFragment extends MvpAppCompatFragment implements AboutAppView {
    public static final String TAG = "AboutAppFragment";
    @InjectPresenter
    AboutAppPresenter mAboutAppPresenter;

    private TextView mVersionTv;
    private TextView mAboutApp;

    public static AboutAppFragment newInstance() {
        AboutAppFragment fragment = new AboutAppFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_app, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();
        if (view != null) {
            mVersionTv = (TextView) view.findViewById(R.id.version_tv);
            mAboutApp = (TextView) view.findViewById(R.id.about_app);

            String mVersionName = BuildConfig.VERSION_NAME;
            mVersionTv.setText(mVersionTv.getText() + " " + mVersionName);
        }
    }


    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
