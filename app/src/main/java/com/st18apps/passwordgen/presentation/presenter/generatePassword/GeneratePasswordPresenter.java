package com.st18apps.passwordgen.presentation.presenter.generatePassword;


import com.st18apps.passwordgen.presentation.view.generatePassword.GeneratePasswordView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class GeneratePasswordPresenter extends MvpPresenter<GeneratePasswordView> {

    public void generatePasswordHash() {
        getViewState().generatePasswordHash();
    }

    public void generatePasswordSimple() {
        getViewState().generatePasswordSimple();
    }

    public void deleteDataHash() {
        getViewState().deleteDataHash();
    }

    public void deleteDataSimple() {
        getViewState().deleteDataSimple();
    }

    public void copyToClipboard() {
        getViewState().copyToClipboard();
    }

    public void sharePassword() {
        getViewState().sharePassword();
    }

    public void addToFavorite() {
        getViewState().addToFavorite();
    }

}
