package com.example.customview.hilt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.customview.R;
import com.example.customview.hilt.bean.Tools;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class Hilt2Activity extends AppCompatActivity {

    @Inject
    Tools tools;
    @Inject
    Tools tools2;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hilt2);
//        if (tools != null) {
//            Log.d("ftd", "tools : " + tools.getName() + " hasCode:" + tools.hashCode());
//            Log.d("ftd", "tools2 : " + tools2.getName() + " hasCode:" + tools2.hashCode());
//        } else {
//            Log.d("ftd", "tools2 inject failed.");
//        }

        MyPresenter presenter = new MyPresenter();
        presenter.init();
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(v -> {
            AlertDialog alertDialog = new AlertDialog.Builder(Hilt2Activity.this)
                    .setMessage("aasdasdad")
                    .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).create();
            alertDialog.show();
        });
    }
}