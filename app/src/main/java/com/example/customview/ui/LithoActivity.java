package com.example.customview.ui;

import android.graphics.Color;
import android.os.Bundle;

import com.example.customview.litho.ListItem;
import com.example.customview.litho.RecyclerItem;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.widget.LithoViewFactory;
import com.facebook.litho.widget.RecyclerBinder;
import com.facebook.litho.widget.StaggeredGridLayoutInfo;
import com.facebook.litho.widget.Text;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class LithoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ComponentContext context = new ComponentContext(this);
//        Component component = ListItem.create(context).title("Title").color(Color.YELLOW).build();

        final RecyclerBinder recyclerBinder = new RecyclerBinder.Builder()
                .layoutInfo(new StaggeredGridLayoutInfo(2, RecyclerView.VERTICAL, false, 0))
//                .layoutInfo(new LinearLayoutInfo(new LinearLayoutManager(this, RecyclerView.VERTICAL, false)))
                .build(context);
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            recyclerBinder.insertItemAt(i * 2, ListItem.create(context).contentDescription("123123123123").color(Color.YELLOW).mheight(random.nextInt(300)).build());
            recyclerBinder.insertItemAt(i * 2 + 1, ListItem.create(context).color(Color.BLUE).mheight(random.nextInt(300)).build());
        }
        setContentView(LithoView.create(context,  Text.create(context).text("你好啊").textSizeSp(20).build()));
//        setContentView(LithoView.create(context, RecyclerItem.create(context).recyclerBinder(recyclerBinder).build()));
    }
}
