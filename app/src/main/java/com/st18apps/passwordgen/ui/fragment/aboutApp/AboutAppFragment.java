package com.st18apps.passwordgen.ui.fragment.aboutApp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
    private ImageButton mPlayStore;
    private ImageButton mMail;
    private ImageButton mRate;

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
            mPlayStore = (ImageButton) view.findViewById(R.id.ib_play_store);
            mMail = (ImageButton) view.findViewById(R.id.ib_mail);
            mRate = (ImageButton) view.findViewById(R.id.ib_rate);

            mAboutAppPresenter.setAnimation();
            mAboutAppPresenter.setVersionName();

            mPlayStore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAboutAppPresenter.onPlayStoreClick();
                }
            });
            mMail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAboutAppPresenter.onMailClick();
                }
            });
            mRate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAboutAppPresenter.onRateClick();
                }
            });

        }
    }


    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void setAnimation() {
        Animation anim;
        anim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.myscale);
        mAboutApp.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.myalpha);
        mVersionTv.startAnimation(anim);
    }

    @Override
    public void setVersionText() {
        String mVersionName = BuildConfig.VERSION_NAME;
        mVersionTv.setText(getResources().getString(R.string.about) + " " + mVersionName);
    }

    @Override
    public void onPlayStoreClick() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://search?q=pub:Aleksey+Rudenko"));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getActivity().getApplicationContext(), " Sorry, Not able to open!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMailClick() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"rudenko.al.iv@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "About app");

        startActivity(Intent.createChooser(intent, "Email via..."));
    }

    @Override
    public void onRateClick() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=com.st18apps.passwordgen"));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getActivity().getApplicationContext(), " Sorry, Not able to open!", Toast.LENGTH_SHORT).show();
        }
    }
}
