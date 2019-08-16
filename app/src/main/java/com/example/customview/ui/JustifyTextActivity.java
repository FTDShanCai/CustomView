package com.example.customview.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;

import com.example.customview.R;
import com.example.customview.view.JustifyTextView;

public class JustifyTextActivity extends AppCompatActivity {

    JustifyTextView tv_text;
    AppCompatTextView tv_text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_justify_text);
        tv_text = findViewById(R.id.tv_text);


        tv_text1 = findViewById(R.id.tv_text1);
        tv_text.setText(Html.fromHtml("《论语》是孔子及其弟子的语录结集，由孔子弟子及再传弟子编写而成，至战国前期成书。全书共20篇492章，以语录体为主，叙事体为辅，主要记录孔子及其弟子的言行，较为集中地体现了孔子的政治主张、伦理思想、道德观念及教育原则等。此书是儒家学派的经典著作之一，与<font color='#DC143C'>《大学》《中庸》《孟子》</font>并称“四书”，再加上《诗经》《尚书》《礼记》《周易》《春秋》，总称“四书五经”。"));
        int length = tv_text.getText().length();
        tv_text.setSelectColor(10, length-2, Color.rgb(220, 20, 60));
        tv_text1.setText(Html.fromHtml("《论语》是孔子及其弟子的语录结集，由孔子弟子及再传弟子编写而成，至战国前期成书。全书共20篇492章，以语录体为主，叙事体为辅，主要记录孔子及其弟子的言行，较为集中地体现了孔子的政治主张、伦理思想、道德观念及教育原则等。此书是儒家学派的经典著作之一，与<font color='#DC143C'>《大学》《中庸》《孟子》</font>并称“四书”，再加上《诗经》《尚书》《礼记》《周易》《春秋》，总称“四书五经”。"));
        Log.d("ftd", "length!!!" + tv_text.getText().toString().length() + "|" + tv_text1.getText().toString().length());
        Log.d("ftd", tv_text1.getText().toString().substring(10, tv_text1.getText().length()));

        tv_text.setText("asdassdasdasdasdassdasdasdasdassdasdasdasdassdasdasdasdassdasdasdasdassdasdasdasdassdasdasdasdassdasdasdasdassdasdasdasdassdasdasdasdassdasdasdasdassdasdasdasdassdasdasdasdassdasdasd");
    }
}
