package com.st18apps.passwordgen.presentation.view.generatePassword;

import com.arellomobile.mvp.MvpView;

public interface GeneratePasswordView extends MvpView {

    void generatePasswordHash();

    void generatePasswordSimple();

    void deleteDataHash();

    void deleteDataSimple();

    void copyToClipboard();

    void sharePassword();

    void addToFavorite();
}
