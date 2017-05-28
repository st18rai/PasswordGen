package com.st18apps.passwordgen.presentation.presenter.aboutApp;


import com.st18apps.passwordgen.presentation.view.aboutApp.AboutAppView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class AboutAppPresenter extends MvpPresenter<AboutAppView> {

    public void setAnimation() {
        getViewState().setAnimation();
    }

    public void setVersionName() {
        getViewState().setVersionText();
    }

    public void onPlayStoreClick() {
        getViewState().onPlayStoreClick();
    }

    public void onMailClick() {
        getViewState().onMailClick();
    }

    public void onRateClick() {
        getViewState().onRateClick();
    }

}
