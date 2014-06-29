package com.eql.safe.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.eql.safe.R;
import com.eql.safe.entity.LocalImgItem;
import com.eql.safe.utils.AsyncImageLoader;
import com.eql.safe.utils.ImageUtils;
import com.eql.safe.utils.UIUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by hanxingke on 14-4-3.
 */
public class HorizontalImgSelectedView extends LinearLayout {


    LinearLayout mViewGroup;
    GridView gridView;

    int mItemWidth;
    int mSpaicing;

    View view;

    private DisplayImageOptions options;
    private SimpleImageLoadingListener animateFirstListener;

    public HorizontalImgSelectedView(Context context) {
        super(context);
    }

    public HorizontalImgSelectedView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    int mScreenWidth;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();


        gridView = (GridView) findViewById(R.id.gallery);
        mItemWidth = getResources().getDimensionPixelSize(R.dimen.horizontal_grid_img_selected_width);
        mSpaicing = getResources().getDimensionPixelSize(R.dimen.horizontal_grid_img_spacing);

        options = AsyncImageLoader.getListDisplayImageOptions().cacheOnDisc(true).cacheInMemory(true).build();
        animateFirstListener = AsyncImageLoader.getListAnimOfSimpleImageLoadingListener();


        view = findViewById(R.id.layout_gallery);

        mScreenWidth = UIUtils.getScreenSize(getContext())[0];

        view.setLayoutParams(new FrameLayout.LayoutParams(mScreenWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                LocalImgItem item = (LocalImgItem) mAdapter.getItem(i);
                removeItem(item);
            }
        });

    }


    PhotoAdapter mAdapter;
    int mMaxCount;

    public void setData(int maxCount, ArrayList<LocalImgItem> imgItems) {


        if (mItemWidth * imgItems.size() >= gridView.getWidth()) {//修正gridview的大小

            initWidth(imgItems.size());

        }

        mMaxCount = maxCount;
        mAdapter = new PhotoAdapter(imgItems);
        gridView.setAdapter(mAdapter);

        if(imgItems.size() > 0)
             this.setVisibility(View.VISIBLE);
    }

    private void initWidth(int count) {

        int rightWidth = getRightWidth(count);
        view.setLayoutParams(new FrameLayout.LayoutParams(rightWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    private int getRightWidth(int count) {

        int rightWidth = count * mItemWidth + mSpaicing * (count - 1);
        return rightWidth;

    }

    public void removeItem(LocalImgItem item) {

        mAdapter.removeItem(item);
        if (mItemWidth * mAdapter.getCount() >= gridView.getWidth()) {

            initWidth(mAdapter.getCount());
        }
        mAdapter.notifyDataSetChanged();
        if (onItemCountChangedListener != null) {


            onItemCountChangedListener.onItemCountChanged(mAdapter.getCount(), item, 0);

        }

        if(mAdapter.getCount() == 0){
            this.setVisibility(View.INVISIBLE);
        }

    }

    public void addItem(LocalImgItem item) {

        mAdapter.addItem(item);
        if (mItemWidth * mAdapter.getCount() >= gridView.getWidth()) {

            initWidth(mAdapter.getCount());
        }
        mAdapter.notifyDataSetChanged();
//        if (onItemCountChangedListener != null) {
//
//            onItemCountChangedListener.onItemCountChanged(mAdapter.getCount(), item, 1);
//
//        }

        if(this.getVisibility() != View.VISIBLE){

            this.setVisibility(View.VISIBLE);
        }

    }


    class PhotoAdapter extends BaseAdapter {

        ArrayList<LocalImgItem> items;
        private DisplayImageOptions options;
        private SimpleImageLoadingListener animateFirstListener;

        public PhotoAdapter(ArrayList<LocalImgItem> items) {

            this.items = items;

            options = AsyncImageLoader.getListDisplayImageOptions().cacheOnDisc(false).cacheInMemory(true).build();
            animateFirstListener = AsyncImageLoader.getListAnimOfSimpleImageLoadingListener();
        }

        public void addItem(LocalImgItem item) {

            items.add(item);
        }


        public void removeItem(LocalImgItem item) {

            items.remove(item);

        }

        public void removeItem(int itemIndex) {

            items.remove(itemIndex);

        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            ViewHolder viewHolder;
            if (convertView == null) {

                convertView = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_gridphoto_selected, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {

                viewHolder = (ViewHolder) convertView.getTag();
            }

            LocalImgItem imgItem = items.get(position);

            String imgUrl = (TextUtils.isEmpty(imgItem.thumbnailPath) == false ? imgItem.thumbnailPath : imgItem.path);
//            AsyncImageLoader.loadList(imgUrl, viewHolder.icon, options, animateFirstListener);


            Bitmap bitmap = ImageUtils.getBitmapFromPath(imgUrl, mItemWidth, mItemWidth);
            viewHolder.icon.setImageBitmap(bitmap);

            return convertView;

        }

        public class ViewHolder {
            public final ImageView icon;
            public final View root;

            public ViewHolder(View root) {
                icon = (ImageView) root.findViewById(R.id.iv_photo);
                this.root = root;
            }
        }
    }

    private ImageView generateItem(LocalImgItem imgItem, boolean isSpacing) {

        ImageView imageView = new ImageView(getContext());
        LayoutParams params = new LayoutParams(mItemWidth, mItemWidth);
        if (isSpacing) {


        }
        imageView.setLayoutParams(params);

        String imgUrl = (TextUtils.isEmpty(imgItem.thumbnailPath) == false ? imgItem.thumbnailPath : imgItem.path);
//        AsyncImageLoader.loadList(imgUrl, imageView, options);

        Bitmap bitmap = ImageUtils.getBitmapFromPath(imgUrl, mItemWidth, mItemWidth);
        imageView.setImageBitmap(bitmap);

        return imageView;
    }


    OnItemCountChangedListener onItemCountChangedListener;

    public void setOnItemCountChangedListener(OnItemCountChangedListener onItemCountChangedListener) {
        this.onItemCountChangedListener = onItemCountChangedListener;
    }

    public interface OnItemCountChangedListener {

        /**
         * @param count    删除/添加后大小
         * @param dataItem 被删除/添加的item
         * @param type     类型：0删除 1添加
         */
        void onItemCountChanged(int count, LocalImgItem dataItem, int type);


    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {

        gridView.setOnItemClickListener(onItemClickListener);
    }


}
