package com.example.ssendhil.myGallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

// modified from https://medium.com/@crossphd/android-image-loading-from-a-string-url-6c8290b82c5e
public class LoadPic extends AsyncTask<String, Void, Bitmap> {
    // using bitmap to load images from url
    private ImageView bmImage;
    public LoadPic (ImageView i){
        bmImage = i;
    }
    protected Bitmap doInBackground(String... urls){
        String myUrl = urls[0];
        Bitmap b = null;
        try {
            InputStream in = new java.net.URL(myUrl).openStream();
            b = BitmapFactory.decodeStream(in);
        }
        catch (Exception e) {
            Log.e("Cannot Load from URL", e.getMessage());
        }
        return b;
    }
    protected void onPostExecute(Bitmap b){
        bmImage.setImageBitmap(b);
    }
}
