package com.st18apps.passwordgen.ui.fragment.generatePassword;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.st18apps.passwordgen.R;
import com.st18apps.passwordgen.Utils;
import com.st18apps.passwordgen.model.WordsDB;
import com.st18apps.passwordgen.presentation.presenter.generatePassword.GeneratePasswordPresenter;
import com.st18apps.passwordgen.presentation.view.generatePassword.GeneratePasswordView;

import static android.R.attr.fragment;

public class GeneratePasswordFragment extends MvpAppCompatFragment implements GeneratePasswordView {
    public static final String TAG = "GeneratePasswordFragment";
    @InjectPresenter
    GeneratePasswordPresenter mGeneratePasswordPresenter;

    private EditText enteredDataHash;
    private RadioButton md5;
    private RadioButton sha1;
    private RadioButton base64;
    private ImageButton deleteHash;
    private ImageButton deleteSimple;
    private Button generateHash;
    private TextView type;
    private TextView password;
    private ImageButton copy;
    private ImageButton share;
    private ImageButton favorite;
    private EditText enteredDataSimple;
    private Button generateSimple;

    public static GeneratePasswordFragment newInstance() {
        GeneratePasswordFragment fragment = new GeneratePasswordFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_generate_password, container, false);

        if (view != null) {
            enteredDataHash = (EditText) view.findViewById(R.id.editTextFragmentGenerate);
            md5 = (RadioButton) view.findViewById(R.id.radioButtonFragmentGenerateMD5);
            sha1 = (RadioButton) view.findViewById(R.id.radioButtonFragmentGenerateSHA1);
            base64 = (RadioButton) view.findViewById(R.id.radioButtonFragmentGenerateBASE64);
            deleteHash = (ImageButton) view.findViewById(R.id.imageButtonFragmentGenerateDeleteHash);
            deleteSimple = (ImageButton) view.findViewById(R.id.imageButtonFragmentGenerateDeleteSimple);
            generateHash = (Button) view.findViewById(R.id.buttonFragmentGenerateHash);
            type = (TextView) view.findViewById(R.id.textViewFragmentGenerateType);
            password = (TextView) view.findViewById(R.id.textViewFragmentGeneratedPassword);
            copy = (ImageButton) view.findViewById(R.id.imageButtonFragmentGenerateCopy);
            share = (ImageButton) view.findViewById(R.id.imageButtonFragmentGenerateShare);
            favorite = (ImageButton) view.findViewById(R.id.imageButtonFragmentGenerateAddFavorite);
            enteredDataSimple = (EditText) view.findViewById(R.id.editTextFragmentGenerateCharNumber);
            generateSimple = (Button) view.findViewById(R.id.buttonFragmentGenerateSimple);
        }
        generateHash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGeneratePasswordPresenter.generatePasswordHash();
            }
        });

        generateSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGeneratePasswordPresenter.generatePasswordSimple();
            }
        });

        deleteHash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGeneratePasswordPresenter.deleteDataHash();
            }
        });

        deleteSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGeneratePasswordPresenter.deleteDataSimple();
            }
        });

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGeneratePasswordPresenter.copyToClipboard();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGeneratePasswordPresenter.sharePassword();
            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGeneratePasswordPresenter.addToFavorite();
            }
        });
        return view;

    }
/*
    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            enteredDataHash = (EditText) view.findViewById(R.id.editTextFragmentGenerate);
            md5 = (RadioButton) view.findViewById(R.id.radioButtonFragmentGenerateMD5);
            sha1 = (RadioButton) view.findViewById(R.id.radioButtonFragmentGenerateSHA1);
            base64 = (RadioButton) view.findViewById(R.id.radioButtonFragmentGenerateBASE64);
            deleteHash = (ImageButton) view.findViewById(R.id.imageButtonFragmentGenerateDeleteHash);
            deleteSimple = (ImageButton) view.findViewById(R.id.imageButtonFragmentGenerateDeleteSimple);
            generateHash = (Button) view.findViewById(R.id.buttonFragmentGenerateHash);
            type = (TextView) view.findViewById(R.id.textViewFragmentGenerateType);
            password = (TextView) view.findViewById(R.id.textViewFragmentGeneratedPassword);
            copy = (ImageButton) view.findViewById(R.id.imageButtonFragmentGenerateCopy);
            share = (ImageButton) view.findViewById(R.id.imageButtonFragmentGenerateShare);
            favorite = (ImageButton) view.findViewById(R.id.imageButtonFragmentGenerateAddFavorite);
            enteredDataSimple = (EditText) view.findViewById(R.id.editTextFragmentGenerateCharNumber);
            generateSimple = (Button) view.findViewById(R.id.buttonFragmentGenerateSimple);
        }

        generateHash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGeneratePasswordPresenter.generatePasswordHash();
            }
        });

        generateSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGeneratePasswordPresenter.generatePasswordSimple();
            }
        });

        deleteHash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGeneratePasswordPresenter.deleteDataHash();
            }
        });

        deleteSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGeneratePasswordPresenter.deleteDataSimple();
            }
        });

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGeneratePasswordPresenter.copyToClipboard();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGeneratePasswordPresenter.sharePassword();
            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGeneratePasswordPresenter.addToFavorite();
            }
        });
    }
*/
    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    /*
        if (savedInstanceState != null) {
            enteredDataHash.setText(savedInstanceState.getString("enteredDataHash", ""));
            enteredDataSimple.setText(savedInstanceState.getString("enteredDataSimple", ""));
            type.setText(savedInstanceState.getString("type", ""));
            password.setText(savedInstanceState.getString("password", getResources().getString(R.string.generated_password_text)));
        }
    */

    @Override
    public void generatePasswordHash() {

        if (!TextUtils.isEmpty(enteredDataHash.getText().toString())) {
            if (md5.isChecked()) {
                type.setText(R.string.md5);
                password.setText(Utils.md5(enteredDataHash.getText().toString()));
            }
            if (sha1.isChecked()) {
                type.setText(R.string.sha1);
                password.setText(Utils.sha1(enteredDataHash.getText().toString()));
            }
            if (base64.isChecked()) {
                type.setText(R.string.base64);
                password.setText(Utils.base64(enteredDataHash.getText().toString()));
            }
        } else
            //Toast.makeText(getActivity().getApplicationContext(), "Enter data!", Toast.LENGTH_SHORT).show();
            Snackbar.make(password, R.string.if_hash_field_empty, Snackbar.LENGTH_LONG).show();

    }

    @Override
    public void generatePasswordSimple() {

        if (!TextUtils.isEmpty(enteredDataSimple.getText().toString())) {
            int enteredNumber = Integer.parseInt(enteredDataSimple.getText().toString());
            if (enteredNumber > 1 && enteredNumber < 101) {
                Utils utils = new Utils(enteredNumber);
                String newPassword = utils.randomChar();
                password.setText(newPassword);
                type.setText(R.string.your_password);
            } else
                //Toast.makeText(getActivity().getApplicationContext(), "Please, use a value between 1 and 100 in a field above", Toast.LENGTH_LONG).show();
                Snackbar.make(password, R.string.if_simple_field_empty, Snackbar.LENGTH_LONG).show();
        } else
            //Toast.makeText(getActivity().getApplicationContext(), "Enter data!", Toast.LENGTH_SHORT).show();
            Snackbar.make(password, R.string.if_hash_field_empty, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void deleteDataHash() {
        enteredDataHash.setText("");
        type.setText("");
        password.setText(R.string.generated_password_text);
    }

    @Override
    public void deleteDataSimple() {
        enteredDataSimple.setText("");
        password.setText(R.string.generated_password_text);
    }

    @Override
    public void copyToClipboard() {

        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(null, password.getText());
        clipboard.setPrimaryClip(clip);
        //Toast.makeText(getActivity().getApplicationContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
        Snackbar.make(password, R.string.clipboard_text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void sharePassword() {

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, password.getText());
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_text)));
    }

    @Override
    public void addToFavorite() {

        if (!TextUtils.isEmpty(enteredDataHash.getText().toString()) && !TextUtils.isEmpty(type.getText())) {
            String currentWord = enteredDataHash.getText().toString();
            String currentType = type.getText().toString();

            WordsDB wordsDB = new WordsDB(currentWord, currentType);
            wordsDB.save();

            //Toast.makeText(getActivity().getApplicationContext(), "Added to favorites: " + currentWord, Toast.LENGTH_SHORT).show();
            Snackbar.make(password, getString(R.string.added_to_favorite) + " " + currentWord, Snackbar.LENGTH_LONG).show();
        } else
            Snackbar.make(password, R.string.if_hash_field_empty, Snackbar.LENGTH_LONG).show();

    }

    /*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("enteredDataHash", enteredDataHash.getText().toString());
        outState.putString("enteredDataSimple", enteredDataSimple.getText().toString());
        outState.putString("type", type.getText().toString());
        outState.putString("password", password.getText().toString());
    }
    */
}
