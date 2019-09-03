package com.example.customview.ui.opengl;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customview.R;
import com.example.customview.ui.opengl.renderer.GL1Render;

public class OpenGl1Activity extends AppCompatActivity {
    GLSurfaceView gl_surface_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gl1);
        gl_surface_view = findViewById(R.id.gl_surface_view);
        gl_surface_view.setEGLContextClientVersion(2);
        gl_surface_view.setRenderer(new GL1Render(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (gl_surface_view != null) {
            gl_surface_view.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (gl_surface_view != null) {
            gl_surface_view.onPause();
        }
    }


}
