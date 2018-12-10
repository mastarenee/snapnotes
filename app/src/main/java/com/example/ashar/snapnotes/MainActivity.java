package com.example.ashar.snapnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView bg;
    private ImageView logo;
    private Button btnwrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bg = (ImageView) findViewById(R.id.bg);
        logo = (ImageView) findViewById(R.id.logo);
        btnwrite = (Button) findViewById(R.id.btnwrite);

        btnwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,getString(com.example.ashar.snapnotes.R.string.snapping),Toast.LENGTH_SHORT).show();
                openwritepage();
            }
        });

    }

    private void openwritepage() {
        Intent intent = new Intent(MainActivity.this,Writepage.class);
        startActivity(intent);
    }
}
