package com.xiongwo.huaweiappstore.banner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xiongwo.huaweiappstore.R;
import com.xiongwo.huaweiappstore.util.UIUtil;
import com.xiongwo.huaweiappstore.view.widget.AutoGallery;
import com.xiongwo.huaweiappstore.view.widget.FlowIndicator;


import java.util.List;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class RecommendController {

    private Context mContext ;
    private View contentView ;
    private AutoGallery headline_image_gallery ;
    private FlowIndicator headline_circle_indicator ;

//    private List<String> urls = new ArrayList<>() ;

    private int gallerySelectedPositin = 0 ;//Gallery索引
    private int circleSelectedPosition = 0 ;//默认指示器

    public RecommendController(Context context){
        this.mContext = context ;
        init();
    }

    private void init(){
        contentView = UIUtil.inflate(R.layout.gallery_indicator_layout);
        headline_image_gallery = (AutoGallery) contentView.findViewById(R.id.headline_image_gallery);
        headline_circle_indicator = (FlowIndicator) contentView.findViewById(R.id.headline_circle_indicator);

    }

    public void setData(List<String> datas){
        setIndicator(datas);
    }

    public View getContentView() {
        return contentView;
    }

    private void setIndicator(final List<String> urls){
        //指示器
        headline_circle_indicator.setCount(urls.size());
        headline_circle_indicator.setSeletion(circleSelectedPosition);

        headline_image_gallery.setLength(urls.size());
        gallerySelectedPositin = urls.size() * 50 + circleSelectedPosition ;
        headline_image_gallery.setSelection(gallerySelectedPositin);
        headline_image_gallery.setDelayMillis(4000);
        headline_image_gallery.start();
        headline_image_gallery.setAdapter(new GalleryAdapter(urls));
        headline_image_gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                circleSelectedPosition = position ;
                gallerySelectedPositin = circleSelectedPosition % urls.size();
                headline_circle_indicator.setSeletion(gallerySelectedPositin);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    class GalleryAdapter extends BaseAdapter{

        private List<String> urls ;
        public GalleryAdapter(List<String> urls){
            this.urls = urls ;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object getItem(int position) {
            return urls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null ;
            if(convertView == null){
                holder = new Holder() ;
                convertView = UIUtil.inflate(R.layout.item_gallery_layout);
                holder.item_head_gallery_img = (ImageView) convertView.findViewById(R.id.item_head_gallery_img);
                convertView.setTag(holder);
            }else {
                holder = (Holder) convertView.getTag();
            }
            Glide.with(UIUtil.getContext()).load(urls.get(position % urls.size())).into(holder.item_head_gallery_img);

            return convertView;
        }
    }
    private class Holder {
        ImageView item_head_gallery_img ;
    }
}
