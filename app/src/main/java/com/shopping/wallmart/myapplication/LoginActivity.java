package com.shopping.wallmart.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView header;
    private Spinner country;
    private Spinner state;
    private Button applyButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        header = (TextView) findViewById( R.id.header);
        String text = "<font color=#808080>baby</font><font color=#FF1493>pink</font>";
        header.setText(Html.fromHtml(text));

        addListnersOnButton();



    }

    private void addListnersOnButton() {
        country = (Spinner) findViewById(R.id.countryspinner);
        state = (Spinner) findViewById(R.id.statespinner);
        applyButton = (Button) findViewById(R.id.apply);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
                finish();
            }
        });
    }

}
