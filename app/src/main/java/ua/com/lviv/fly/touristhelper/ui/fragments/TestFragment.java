package ua.com.lviv.fly.touristhelper.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ls.util.L;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ua.com.lviv.fly.touristhelper.R;
import ua.com.lviv.fly.touristhelper.data.JsonVO;
import ua.com.lviv.fly.touristhelper.model.Model;

public class TestFragment extends Fragment implements TextToSpeech.OnInitListener {
    TextToSpeech textToSpeech;
    Button button;
    EditText editText;
    private boolean ready = false;

    public static TestFragment newInstance() {
        TestFragment fragment = new TestFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<JsonVO> jsonVOs = parseJson("dental_clinics.json");
        ArrayList<JsonVO> test1 = parseJson("museums.json");
        L.e("Address = " +  jsonVOs.get(5).getAddress());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        textToSpeech = new TextToSpeech(getActivity(), TestFragment.this);

        button = (Button) view.findViewById(R.id.button);

        editText = (EditText) view.findViewById(R.id.editText);
        editText.setText(Model.instance().getProfileManager().fetchUserDescription());
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                TextToSpeechFunction();
            }

        });


        Locale[] locales = Locale.getAvailableLocales();
        List<Locale> localeList = new ArrayList<Locale>();
        for (Locale locale : locales) {
            int res = textToSpeech.isLanguageAvailable(locale);
            if (res == TextToSpeech.LANG_COUNTRY_AVAILABLE) {
                localeList.add(locale);
            }
        }
    }

    @Override
    public void onInit(int status) {
        L.e("onInit" + status);
        if (status == TextToSpeech.LANG_MISSING_DATA) {
            Intent installIntent = new Intent();
            installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
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

    public String loadJSONFromAsset(String jsonName) {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open(jsonName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private  ArrayList<JsonVO> parseJson(String jsonName){
        Gson gson = Model.instance().getGson();
        ArrayList<JsonVO> list= gson.fromJson(loadJSONFromAsset(jsonName), new TypeToken<ArrayList<JsonVO>>(){}.getType());
        L.e("Json  " + jsonName + " = " + list.toString());
        return list;
    }
}
