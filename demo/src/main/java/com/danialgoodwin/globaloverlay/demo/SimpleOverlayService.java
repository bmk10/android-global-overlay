/**
 * Created by Danial on 3/16/2015.
 */
package com.danialgoodwin.globaloverlay.demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.danialgoodwin.globaloverlay.GlobalOverlay;
import com.danialgoodwin.globaloverlay.GlobalOverlayService;

/** Simple demo for creating an interactable global floating view. */
public class SimpleOverlayService extends Service {

    private GlobalOverlay mGlobalOverlay;

    @Override
    public void onCreate() {
        super.onCreate();

        mGlobalOverlay = new GlobalOverlay(this);

        ImageView view = new ImageView(this);
        view.setImageResource(R.mipmap.ic_launcher);
        mGlobalOverlay.addOverlayView(view, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("onClick");
            }
        }, new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                toast("onLongClick not implemented yet");
                return false;
            }
        }, new GlobalOverlay.OnRemoveOverlayListener() {
            @Override
            public void onRemoveOverlay(View view, boolean isRemovedByUser) {
                toast("onRemoveOverlay");
                stopSelf();
            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
