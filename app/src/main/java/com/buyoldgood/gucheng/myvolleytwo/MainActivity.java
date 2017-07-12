package com.buyoldgood.gucheng.myvolleytwo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

public class MainActivity extends Activity {
    private ListView listView;
    private List<Goods> goodsList;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        listView = (ListView) findViewById(R.id.list_view);

        //volley
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest = new StringRequest("http://118.113.9.65:8080/PlayTogether/servlet/MyServlet?func=3",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("顾程", "onResponse: " + response);
//                        Toast.makeText(MainActivity.this,response,Toast.LENGTH_SHORT).show();
                        goodsList = Goods.arrayGoodsFromData(response);
                        //适配器
                        MyAdapter myAdapter = new MyAdapter();
                        listView.setAdapter(myAdapter);

                        for (Goods goods : goodsList) {
                            Log.d("顾程", "--->" + goodsList);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        imageLoader = new ImageLoader(requestQueue, new MyBitMapCache());
        //加载
        requestQueue.add(stringRequest);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return goodsList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = View.inflate(MainActivity.this, R.layout.item, null);
            TextView textView = view1.findViewById(R.id.goods_name);
            ImageView imageView = view1.findViewById(R.id.show_pic);

            ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView,
                    R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher);

            imageLoader.get(goodsList.get(0).getThing_picture(),listener);

            textView.setText(goodsList.get(i).getThing_name());

            return view1;
        }
    }

    //又写一个类
    class MyBitMapCache implements ImageLoader.ImageCache {
        private LruCache<String, Bitmap> mCache;

        public MyBitMapCache() {
            int maxSize = 10 * 1024 * 1024;
            mCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }
    }
}
