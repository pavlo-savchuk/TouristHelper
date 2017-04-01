package ua.com.lviv.fly.touristhelper.ui.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.preference.EditTextPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ls.util.L;

import java.util.Locale;

import ua.com.lviv.fly.touristhelper.R;
import ua.com.lviv.fly.touristhelper.TemplateApplication;

public class OptionsFragment extends Fragment implements TextToSpeech.OnInitListener {
    TextToSpeech textToSpeech;
    Button button;
    EditText editText;
    private boolean ready = false;

    public static OptionsFragment newInstance() {
        OptionsFragment fragment = new OptionsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textToSpeech = new TextToSpeech(getActivity(), OptionsFragment.this);

        button = (Button) view.findViewById(R.id.button);

        editText = (EditText) view.findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                TextToSpeechFunction();
            }

        });
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(TemplateApplication.getSharedContext());
        String string = defaultSharedPreferences.getString(getString(R.string.user_first_name_key), getString(R.string.user_first_name_default_value));
        Toast.makeText(getContext(), string, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInit(int status) {
        L.e("onInit" + status);
        if (status == TextToSpeech.LANG_MISSING_DATA) {
            Intent installIntent = new Intent();
            installIntent.setAction(
                    TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
            startActivity(installIntent);
        }
        if (status == TextToSpeech.SUCCESS) {
            // Change this to match your
            // locale
            textToSpeech.setLanguage(Locale.US);
            ready = true;
            textToSpeech.speak("Test", TextToSpeech.QUEUE_FLUSH, null);
        } else {
            ready = false;
        }
    }

    @Override
    public void onDestroy() {
        textToSpeech.shutdown();
        super.onDestroy();
    }

    public void TextToSpeechFunction() {

        String textholder = editText.getText().toString();

        textToSpeech.speak(textholder, TextToSpeech.QUEUE_FLUSH, null);

        Toast.makeText(getActivity(), textholder, Toast.LENGTH_LONG).show();
    }
}
