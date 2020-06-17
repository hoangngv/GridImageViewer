package com.example.imageviewer.features.demo.formatter;

import android.os.Bundle;

import com.example.imageviewer.R;
import com.example.imageviewer.common.data.Demo;
import com.example.imageviewer.common.data.models.CustomImage;
import com.example.imageviewer.common.views.ImageOverlayView;
import com.example.imageviewer.features.demo.DemoActivity;
import com.example.imageviewer.utils.AppUtils;
import com.stfalcon.frescoimageviewer.ImageViewer;
import java.util.List;

/*
 * Created by troy379 on 06.03.17.
 */
public class CustomObjectsActivity extends DemoActivity {

    private List<CustomImage> images;
    private ImageOverlayView overlayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        images = Demo.getCustomImages();
        AppUtils.showGotItSnackbar(findViewById(R.id.coordinator), R.string.custom_objects_hint);
    }

    @Override
    protected void showPicker(int startPosition) {
        overlayView = new ImageOverlayView(this);
        new ImageViewer.Builder<>(this, images)
                .setFormatter(getCustomFormatter())
                .setImageChangeListener(getImageChangeListener())
                .setOverlayView(overlayView)
                .show();
    }

    private ImageViewer.Formatter<CustomImage> getCustomFormatter() {
        return new ImageViewer.Formatter<CustomImage>() {
            @Override
            public String format(CustomImage customImage) {
                return customImage.getUrl();
            }
        };
    }

    private ImageViewer.OnImageChangeListener getImageChangeListener() {
        return new ImageViewer.OnImageChangeListener() {
            @Override
            public void onImageChange(int position) {
                CustomImage image = images.get(position);
                overlayView.setShareText(image.getUrl());
                overlayView.setDescription(image.getDescription());
            }
        };
    }
}
