package com.example.customview.litho;

import android.widget.ImageView;

import com.example.customview.R;
import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Card;
import com.facebook.litho.widget.Image;
import com.facebook.yoga.YogaEdge;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
@LayoutSpec
public class ListItemSpec {

    @OnCreateLayout
    static Component onCreateLayout(
            ComponentContext c,
            @Prop int color,
            @Prop int mheight) {

        return Card.create(c).cardBackgroundColor(color)
                .paddingDip(YogaEdge.ALL, 15)
                .marginDip(YogaEdge.ALL, 15)
                .cornerRadiusDip(8)
                .heightDip(mheight)
                .content(Column.create(c)
                        .child(Image.create(c).drawableRes(R.mipmap.ic_launcher).scaleType(ImageView.ScaleType.CENTER_CROP)).build())
                .build();
    }
}