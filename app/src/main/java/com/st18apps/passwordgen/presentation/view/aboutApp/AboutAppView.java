package com.st18apps.passwordgen.presentation.view.aboutApp;

import com.arellomobile.mvp.MvpView;

public interface AboutAppView extends MvpView {

    void setAnimation();

    void setVersionText();

    void onPlayStoreClick();

    void onMailClick();

    void onRateClick();
}
