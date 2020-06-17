package com.example.imageviewer.features.demo.rotation;

import android.os.Bundle;

import com.example.imageviewer.R;
import com.example.imageviewer.common.data.Demo;
import com.example.imageviewer.features.demo.DemoActivity;
import com.example.imageviewer.utils.AppUtils;
import com.stfalcon.frescoimageviewer.ImageViewer;

public class DialogRotationExampleActivity extends DemoActivity {

    private static final String KEY_IS_DIALOG_SHOWN = "IS_DIALOG_SHOWN";
    private static final String KEY_CURRENT_POSITION = "CURRENT_POSITION";

    private boolean isDialogShown;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppUtils.showInfoSnackbar(findViewById(R.id.coordinator),
                R.string.message_rotate, true);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            isDialogShown = savedInstanceState.getBoolean(KEY_IS_DIALOG_SHOWN);
            currentPosition = savedInstanceState.getInt(KEY_CURRENT_POSITION);
        }

        if (isDialogShown) {
            showPicker(currentPosition);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(KEY_IS_DIALOG_SHOWN, isDialogShown);
        outState.putInt(KEY_CURRENT_POSITION, currentPosition);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void showPicker(int startPosition) {
        isDialogShown = true;
        currentPosition = startPosition;

        new ImageViewer.Builder<>(this, Demo.getPosters())
                .setStartPosition(startPosition)
                .setImageChangeListener(getImageChangeListener())
                .setOnDismissListener(getDismissListener())
                .show();
    }

    private ImageViewer.OnImageChangeListener getImageChangeListener() {
        return new ImageViewer.OnImageChangeListener() {
            @Override
            public void onImageChange(int position) {
                currentPosition = position;
            }
        };
    }

    private ImageViewer.OnDismissListener getDismissListener() {
        return new ImageViewer.OnDismissListener() {
            @Override
            public void onDismiss() {
                isDialogShown = false;
            }
        };
    }
}
