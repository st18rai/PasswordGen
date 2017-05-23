package com.st18apps.passwordgen.ui.fragment.generatePassword;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.st18apps.passwordgen.R;
import com.st18apps.passwordgen.Utils;
import com.st18apps.passwordgen.presentation.presenter.generatePassword.GeneratePasswordPresenter;
import com.st18apps.passwordgen.presentation.view.generatePassword.GeneratePasswordView;

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
        return inflater.inflate(R.layout.fragment_generate_password, container, false);

    }

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
            enteredDataSimple = (EditText) view.findViewById(R.id.editTextFragmentGenerateCharNumber);
            generateSimple = (Button) view.findViewById(R.id.buttonFragmentGenerateSimple);
        }

        generateHash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generatePasswordHash();
            }
        });

        generateSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generatePasswordSimple();
            }
        });

        deleteHash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDataHash();
            }
        });

        deleteSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDataSimple();
            }
        });

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyToClipboard();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharePassword();
            }
        });
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void generatePasswordHash() {

        if (!TextUtils.isEmpty(enteredDataHash.getText().toString())) {
            if (md5.isChecked()) {
                type.setText("MD5");
                password.setText(Utils.md5(enteredDataHash.getText().toString()));
            }
            if (sha1.isChecked()) {
                type.setText("SHA1");
                password.setText(Utils.sha1(enteredDataHash.getText().toString()));
            }
            if (base64.isChecked()) {
                type.setText("BASE64");
                password.setText(Utils.base64(enteredDataHash.getText().toString()));
            }
        } else
            Toast.makeText(getActivity().getApplicationContext(), "Enter data!", Toast.LENGTH_SHORT).show();
    }

    public void generatePasswordSimple() {

        if (!TextUtils.isEmpty(enteredDataSimple.getText().toString())) {
            int enteredNumber = Integer.parseInt(enteredDataSimple.getText().toString());
            if (enteredNumber > 1 && enteredNumber < 101) {
                Utils utils = new Utils(enteredNumber);
                String newPassword = utils.randomChar();
                password.setText(newPassword);
                type.setText("Your password");
            } else
                Toast.makeText(getActivity().getApplicationContext(), "Please, use a value between 1 and 100 in a field above", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(getActivity().getApplicationContext(), "Enter data!", Toast.LENGTH_SHORT).show();
    }

    public void deleteDataHash() {
        enteredDataHash.setText("");
        password.setText("Generated password");
    }

    public void deleteDataSimple() {
        enteredDataSimple.setText("");
        password.setText("Generated password");
    }

    public void copyToClipboard() {

        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(null, password.getText());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getActivity().getApplicationContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
    }

    public void sharePassword() {

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, password.getText());
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_text)));
    }
}
