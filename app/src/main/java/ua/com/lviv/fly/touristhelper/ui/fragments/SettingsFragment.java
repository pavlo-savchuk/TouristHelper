package ua.com.lviv.fly.touristhelper.ui.fragments;

import android.location.Location;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import com.ls.util.L;

import java.util.Locale;

import ua.com.lviv.fly.touristhelper.R;
import ua.com.lviv.fly.touristhelper.data.manager.OptionManager;
import ua.com.lviv.fly.touristhelper.model.Model;

public class SettingsFragment extends Fragment implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    private Button btnSpeak;
    private EditText txtText;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        initTypeSpinner(view);
        initRadiusSpinner(view);

        tts = new TextToSpeech(getContext(), this);

         btnSpeak = (Button) view.findViewById(R.id.btnSpeak);

         txtText = (EditText) view.findViewById(R.id.txtText);

        // button on click event
        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                speakOut();
            }

        });



        final OptionManager optionManager = Model.instance().getOptionManager();
        final Location myLocation = optionManager.getMyLocation();

        L.e("My location = " + myLocation.toString());


        CheckBox bank = (CheckBox) view.findViewById(R.id.bank);
        bank.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                optionManager.setType("/nearbysearch/json?location=" + getLocation() + optionManager.getRadius() + "&types=bank&key=");
            }
        });

        CheckBox cafe = (CheckBox) view.findViewById(R.id.cafe);
        cafe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                optionManager.setType("/nearbysearch/json?location=" + getLocation() + optionManager.getRadius() + "&types=food|cafe&keyword=vegetarian&key=");
            }
        });

        CheckBox radius1 = (CheckBox) view.findViewById(R.id.radius1);
        radius1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                optionManager.setRadius("&radius=1000");
            }
        });

        CheckBox radius2 = (CheckBox) view.findViewById(R.id.radius2);
        radius2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                optionManager.setRadius("&radius=2000");
            }
        });

        return view;
    }

    public void initTypeSpinner(View view) {
        final OptionManager optionManager = Model.instance().getOptionManager();
        Spinner spinner = (Spinner) view.findViewById(R.id.type_spinner);
        final String[] types = getResources().getStringArray(R.array.types);

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, types);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                L.e("weeeeee");
                optionManager.setType("/nearbysearch/json?location=" + getLocation() + optionManager.getRadius() + "&types=" + parent.getItemAtPosition(position) + "&key=");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void initRadiusSpinner(View view) {
        final OptionManager optionManager = Model.instance().getOptionManager();
        Spinner spinner = (Spinner) view.findViewById(R.id.radius_spinner);
        final String[] radius = getResources().getStringArray(R.array.radius);

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, radius);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                optionManager.setRadius("&radius=" + parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    private String getLocation() {
        Location location = Model.instance().getOptionManager().getMyLocation();
        return location.getLatitude() + "," + location.getLongitude();
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                L.e( "This Language is not supported");
            } else {
                btnSpeak.setEnabled(true);
                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    private void speakOut() {

        String text = txtText.getText().toString();

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
