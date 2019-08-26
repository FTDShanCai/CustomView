package com.example.customview.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import com.example.customview.R;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class ConstraintLayoutActivity extends AppCompatActivity {
    TextView lable_1;
    Group group;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_constraint_layout_4);
        lable_1 = findViewById(R.id.lable_1);
        group = findViewById(R.id.group);


        lable_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (group.getVisibility() == View.VISIBLE) {
                    group.setVisibility(View.GONE);
                } else {
                    group.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
