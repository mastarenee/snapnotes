package com.example.ashar.snapnotes;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
//import android.app.Activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot {
    public static Bitmap takescreenshot(View v){
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        Bitmap B = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return B;

    }

    public static Bitmap takescreenshotofrootview(View v){
        return takescreenshot(v.getRootView());
    }

    public static Uri savescreenshot(Bitmap B){
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/screenshot";
        File cachedir = new File(path);
        if (!cachedir.exists()){
            cachedir.mkdirs();
        }
        String name = new SimpleDateFormat("yyyymmdd").format(new Date()).concat(".png");
        File file = new File(cachedir,name);
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
            B.compress(Bitmap.CompressFormat.PNG,90,fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

}
