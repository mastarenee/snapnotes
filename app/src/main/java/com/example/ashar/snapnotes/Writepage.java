package com.example.ashar.snapnotes;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.Html;
import android.support.design.internal.BottomNavigationItemView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import android.os.CountDownTimer;
import android.view.Menu;
import android.app.ActionBar;
import android.content.ActivityNotFoundException;

public class Writepage extends AppCompatActivity implements Animation.AnimationListener{
    //private ImageView writebg;
    private ImageView writelogo;
    private EditText notetxt;
    private BottomNavigationItemView btnhighlight;
    private BottomNavigationItemView btnbold;
    private BottomNavigationItemView btnunderline;
    private BottomNavigationItemView micicon;
    private ImageView refresh;
    private ImageView note;
    private ImageView screen;
    private BottomNavigationItemView checkText;

    private Toolbar mTopToolbar;

    public boolean set_bold = false;
    public boolean set_underline = false;
    public boolean set_highlight = false;

    public int counter;
    TextView countingTo;
    public TextView intro;

    Animation animSlidedown;
    Animation animSlideup;

    public ImageView ballons;
    Animation slideUpAnimation, slideDownAnimation;

    private static final int speechinput=100;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writepage);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
        final String name = sharedPreferences.getString("example_text","");

        //Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

        slideUpAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_up_animation);

        // Animation Up
        animSlideup = AnimationUtils.loadAnimation(this,
                R.anim.slide_up_animation);
        animSlideup.setAnimationListener(this);

        //writebg = (ImageView) findViewById(R.id.writebg);
        writelogo = (ImageView) findViewById(R.id.writelogo);
        notetxt=(EditText) findViewById(R.id.notetxt);
        btnhighlight = (BottomNavigationItemView) findViewById(R.id.btnhighlight);
        btnbold = (BottomNavigationItemView) findViewById(R.id.btnbold);
        btnunderline = (BottomNavigationItemView) findViewById(R.id.btnunderline);
        micicon = (BottomNavigationItemView) findViewById(R.id.micicon);
        refresh = (ImageView) findViewById(R.id.refresh);
        note = (ImageView) findViewById(R.id.note);
        screen = (ImageView) findViewById(R.id.screen);
        checkText = (BottomNavigationItemView) findViewById(R.id.checkText);

        countingTo = (TextView) findViewById(R.id.countingTo);
        intro = (TextView) findViewById(R.id.intro);
        ballons = (ImageView) findViewById(R.id.ballons);

        intro.setText("What are you snapping today " + name);

        writelogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                final String name = sharedPreferences.getString("example_text","");
                intro.setText("What are you snapping today " + name);

            }
        });

        final MediaPlayer mp = MediaPlayer.create(Writepage.this,R.raw.flyby );

        // Scan all text
        // Check for special keyworks
        // Show something fansy
        checkText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String note = notetxt.getText().toString();

                for (String word : note.split("\\s+")){
                    if (word.contains("congrats")){
                        //ballons.startAnimation(animSlidedown);
                        Toast.makeText(Writepage.this,"Woahh",Toast.LENGTH_SHORT).show();

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        final String name = sharedPreferences.getString("example_text","");
                        intro.setText(name + ". You mentioned our keyword congrats!!");

                    }
                }

                new CountDownTimer(30000, 1000){
                    public void onTick(long millisUntilFinished){
                        countingTo.setText(String.valueOf(counter));
                        counter++;
                    }
                    public void onFinish(){
                        notetxt.setText("");
                        countingTo.setText("Gone");

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        final String name = sharedPreferences.getString("example_text","");
                        intro.setText("Hey " + name + ", snap again!");

                    }
                }.start();

            }
        });

        btnhighlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /** String txthlight = String.valueOf(notetxt.getText());
                SpannableString spanned = new SpannableString(txthlight);
                BackgroundColorSpan bgyellow = new BackgroundColorSpan(Color.YELLOW);
                spanned.setSpan(bgyellow,);
                //notetxt.setHighlightColor();
                //notetxt.setBackgroundTintList();**/

               if( set_highlight ) {
                   notetxt.setBackgroundColor(Color.YELLOW);
               }else{
                   notetxt.setBackgroundColor(Color.WHITE);
               }
                set_highlight = !set_highlight;
            }
        });
        btnbold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //notetxt.s

                if( set_bold ) {
                    notetxt.setTypeface(null, Typeface.NORMAL);
                }else{
                    notetxt.setTypeface(null, Typeface.BOLD);
                }
                set_bold = !set_bold;

            }
        });
        btnunderline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SpannableString content = new SpannableString(notetxt.getText());
                //notetxt.setTypeface(null,Typeface.);

                if( set_underline ) {
                    notetxt.setPaintFlags(notetxt.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
                }else{
                    notetxt.setPaintFlags(notetxt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                }
                set_underline = !set_underline;

            }
        });

        micicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notetxt.setText("");
            }
        });

        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                AlertDialog.Builder builder = new AlertDialog.Builder(Writepage.this);
                builder.setMessage(com.example.ashar.snapnotes.R.string.would_you_like_to_take_a_screenshot).setPositiveButton(com.example.ashar.snapnotes.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bitmap B = Screenshot.takescreenshotofrootview(screen);
                        screen.setImageBitmap(B);
                        Screenshot.savescreenshot(B);
                        Toast.makeText(Writepage.this,getString(com.example.ashar.snapnotes.R.string.screenshot_saved),Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton(com.example.ashar.snapnotes.R.string.no, null);

                //AlertDialog alert = builder.create();
                //alert.show();
                //MAKE TEXT UNEDITTABLE
                // +notetxt.edi
                // +notetxt.e
            }
        });
    }

    private void shareTextUrl() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "My Notes");
        share.putExtra(Intent.EXTRA_TEXT, notetxt.getText().toString());

        startActivity(Intent.createChooser(share, "Share link!"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.top_menu, menu);
        //getMenuInflater().inflate(R.menu.menu_time, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.share) {
            //Toast.makeText(Writepage.this, "Share Action clicked", Toast.LENGTH_LONG).show();
            //sharescreenshot();
            shareTextUrl();
        }else if( id == R.id.settings ){
            //Toast.makeText(Writepage.this, "Settings Action clicked", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void sharescreenshot() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        String imgpath = Environment.getExternalStorageDirectory()+ "/screenshot.png";
        File imgfile = new File(imgpath);
        Uri uri = Uri.fromFile(new File(imgpath));
        intent.putExtra(Intent.EXTRA_STREAM,uri);
        startActivity(Intent.createChooser(intent,getString(com.example.ashar.snapnotes.R.string.sharetitle)));
    }

    public void startSlideUpAnimation(View view) {
        ballons.startAnimation(slideUpAnimation);
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        ballons.setVisibility(View.GONE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    /*private void tosavescreenshot() {
        //File cachedir = new File(android.os.Environment.getExternalStorageDirectory(),"Snapnotes");
        //creates folder to store screen shots
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/screenshot";
        File cachedir = new File(path);
        if (!cachedir.exists()){
            cachedir.mkdirs();
        }
        String name = new SimpleDateFormat("yyyymmdd").format(new Date()).concat(".png");
        File file = new File(cachedir,name);
        //Screenshot.savescreenshot(B);

        //String path = new File(android.os.Environment.getExternalStorageDirectory(),"Snapnotes") + "/screenshot.jpg";
        //Screenshot.savescreenshot(Screenshot.takescreenshotofrootview());
    }*/

    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.vocal_snap));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Speech not supported",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    notetxt.setText(result.get(0));
                }
                break;
            }

        }
    }

}
