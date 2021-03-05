package world.thecoder.agecalculator.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.FileOutputStream;

import world.thecoder.agecalculator.R;
import world.thecoder.agecalculator.fragment.DatePickerFragment;
import world.thecoder.agecalculator.fragment.InfoFragment;
import world.thecoder.agecalculator.fragment.InputFragment;
import world.thecoder.agecalculator.fragment.WelcomeFragment;


public class MainActivity extends AppCompatActivity {

    TextView date;

    Bundle bundle = new Bundle();

    public MainActivity(){
        super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (savedInstanceState == null){

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_welcome, WelcomeFragment.class,null)
                    .commit();

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_input, InputFragment.class,null)
                    .commit();
        }

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);
    }


    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment(this,date);
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    public void loadJson(View view){
        String month ="";
        String day ="";
        String url = "https://history.muffinlabs.com/date/" + month + "/" + day;

        String filename = "dates.json";
        String fileContents = "Your config content..";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}