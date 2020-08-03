package com.example.customview.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.SharedElementCallback;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.customview.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SharedElement1Activity extends AppCompatActivity {

    RecyclerView recycler_view;

    ArrayList<String> list = new ArrayList<>();

    private MyImgsAdapter adapter;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element1);
        EventBus.getDefault().register(this);
        recycler_view = findViewById(R.id.recycler_view);
        initImgs();
        adapter = new MyImgsAdapter();
        gridLayoutManager = new GridLayoutManager(this, 3);
        recycler_view.setLayoutManager(gridLayoutManager);
        recycler_view.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String url) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions
                            options = ActivityOptions.makeSceneTransitionAnimation(SharedElement1Activity.this, view.findViewById(R.id.iv_img), url);
                    Intent intent = new Intent(SharedElement1Activity.this, SharedElement2Activity.class);
                    intent.putExtra("current", position);
                    intent.putStringArrayListExtra("urls", list);
                    startActivity(intent, options.toBundle());
                }
            }
        });
        setExitSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                if (bundle != null) {
                    int i = bundle.getInt("index", 0);
                    View itemView = gridLayoutManager.findViewByPosition(i);
                    if (itemView != null) {
                        sharedElements.clear();
                        names.clear();
                        names.add(list.get(i));
                        ImageView iv_img = itemView.findViewById(R.id.iv_img);
                        sharedElements.put(list.get(i), iv_img);
                    }
                    bundle = null;
                }
            }
        });
    }

    private void initImgs() {
        list.add("http://img5.imgtn.bdimg.com/it/u=2275242580,1037844037&fm=26&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=3934604995,1580427243&fm=26&gp=0.jpg");
        list.add("http://img5.imgtn.bdimg.com/it/u=4244486386,2791378985&fm=26&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=1755706322,691772951&fm=26&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=1657339080,551953815&fm=26&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=3619111418,3430283103&fm=26&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=1050410812,3277857031&fm=26&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=581792687,3927970653&fm=26&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=3839901355,367818887&fm=26&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=4156719081,631307045&fm=26&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=330773756,775849355&fm=26&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=3995274232,304718323&fm=26&gp=0.jpg");
        list.add("http://img5.imgtn.bdimg.com/it/u=1403504802,1338955697&fm=26&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=3988009988,2917667097&fm=26&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=103673839,2063549303&fm=26&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=3042129278,2095572296&fm=26&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=2111617701,2428817927&fm=26&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=2342861551,2637732696&fm=26&gp=0.jpg");
        list.add("http://img5.imgtn.bdimg.com/it/u=2943848099,675149868&fm=26&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=1190184472,2874731474&fm=26&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=2383279386,1707440388&fm=26&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=171478551,2549164993&fm=26&gp=0.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=3314475456,934125860&fm=26&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=1683772857,577592773&fm=26&gp=0.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=2486728720,415922278&fm=26&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=2042454559,1212319284&fm=26&gp=0.jpg");
    }

    Bundle bundle;

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        bundle = new Bundle(data.getExtras());
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, String url);
    }

    public class MyImgsAdapter extends RecyclerView.Adapter<MyImgsAdapter.ViewHolder> {
        private OnItemClickListener onItemClickListener;

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_img, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            final String url = list.get(position);
            Glide.with(SharedElement1Activity.this).load(url).into(holder.iv_img);
            holder.itemView.setTag("position:" + position);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null)
                        onItemClickListener.onItemClick(holder.itemView, position, url);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iv_img;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                iv_img = itemView.findViewById(R.id.iv_img);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPageScroll(HashMap<String, Integer> map) {
        recycler_view.smoothScrollToPosition(map.get("index"));
    }
}
