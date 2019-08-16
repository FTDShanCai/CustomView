package com.example.customview.litho;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Recycler;
import com.facebook.litho.widget.RecyclerBinder;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
@LayoutSpec
public class RecyclerItemSpec {

    @OnCreateLayout
    static Component onCreateLayout(ComponentContext context, @Prop RecyclerBinder recyclerBinder) {
        return Recycler.create(context)
                .binder(recyclerBinder)
                .build();
    }
}
