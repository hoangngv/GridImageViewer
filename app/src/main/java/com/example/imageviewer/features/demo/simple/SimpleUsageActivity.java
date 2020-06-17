package com.example.imageviewer.features.demo.simple;

import android.os.Bundle;

import com.example.imageviewer.R;
import com.example.imageviewer.features.demo.DemoActivity;
import com.example.imageviewer.utils.AppUtils;
import com.stfalcon.frescoimageviewer.ImageViewer;

public class SimpleUsageActivity extends DemoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppUtils.showInfoSnackbar(findViewById(R.id.coordinator),
                R.string.message_open_viewer, true);
    }

    @Override
    protected void showPicker(int startPosition) {
        new ImageViewer.Builder<>(this, posters)
                .setStartPosition(startPosition)
                .show();
    }
}
