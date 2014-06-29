package com.eql.safe.ui.photo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.eql.safe.R;
import com.eql.safe.common.Constants;
import com.eql.safe.view.HorizontalImgSelectedView;
import com.eql.safe.entity.ImageDir;
import com.eql.safe.entity.LocalImgItem;
import com.eql.safe.utils.AsyncImageLoader;
import com.eql.safe.utils.UIUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;

public class ChoosePhotoGridActivity extends Activity implements AdapterView.OnItemClickListener {

    GridView mGridView;

    HorizontalImgSelectedView mHorizontalImgSelectedView;
    PhotoAdapter mAdapter;

    ArrayList<LocalImgItem> mSelectedItemList;
    int maxSelectedCount = 6;
    String mTargetClassName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_photo_grid);

        ImageDir imageDir = getIntent().getParcelableExtra(Constants.INTENT_KEY_IMAGEDIR);
        setTitle(imageDir.dirName);

        mTargetClassName = getIntent().getStringExtra(Constants.INTENT_KEY_CHOOSEIMG_TARGETCLASS);
        maxSelectedCount = getIntent().getIntExtra(Constants.INTENT_KEY_CHOOSEIMG_COUNT,6);

        mSelectedItemList = getIntent().getParcelableArrayListExtra(Constants.INTENT_KEY_LOCALITEMS);
        if (mSelectedItemList == null) {
            mSelectedItemList = new ArrayList<LocalImgItem>();
        }


        mGridView = (GridView) findViewById(R.id.gridView);
        mGridView.setOnItemClickListener(this);
        mAdapter = new PhotoAdapter(imageDir.imgList);
        mGridView.setAdapter(mAdapter);
        mHorizontalImgSelectedView = (HorizontalImgSelectedView) findViewById(R.id.view_horizontal);
        mHorizontalImgSelectedView.setOnItemCountChangedListener(new HorizontalImgSelectedView.OnItemCountChangedListener() {

            @Override
            public void onItemCountChanged(int count, LocalImgItem dataItem, int type) {

                ChoosePhotoGridActivity.this.invalidateOptionsMenu();
                mAdapter.notifyDataSetChanged();

            }
        });
        mHorizontalImgSelectedView.setData(maxSelectedCount, mSelectedItemList);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        String menuSure = getResources().getString(R.string.action_sure, mSelectedItemList.size());

        getMenuInflater().inflate(R.menu.choose_photo_grid, menu);
        menu.findItem(R.id.action_sure).setTitle(menuSure);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_sure) {

            if (mSelectedItemList.size() == 0) {

                UIUtils.showToast(this, "至少选择一张");
                return true;
            }

            try {
                Intent intent = new Intent(this, Class.forName(mTargetClassName));
                intent.putExtra(Constants.INTENT_KEY_LOCALITEMS, mSelectedItemList);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return true;
        }
        if(id == android.R.id.home){

            Intent intent = new Intent();
            intent.putExtra(Constants.INTENT_KEY_LOCALITEMS, mSelectedItemList);
            setResult(RESULT_OK,intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent();
        intent.putExtra(Constants.INTENT_KEY_LOCALITEMS, mSelectedItemList);
        setResult(RESULT_OK,intent);


        super.onBackPressed();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


        LocalImgItem imgItem = (LocalImgItem) mAdapter.getItem(i);
        PhotoAdapter.ViewHolder holder = (PhotoAdapter.ViewHolder) view.getTag();
        if (mSelectedItemList.contains(imgItem)) {

            holder.imgSelect.setVisibility(View.GONE);
            mHorizontalImgSelectedView.removeItem(imgItem);


        } else {//添加

            if (mSelectedItemList.size() == maxSelectedCount) {

                UIUtils.showToast(this, String.format("最多只能选择%d个", maxSelectedCount));
                return;
            }

            holder.imgSelect.setVisibility(View.VISIBLE);
            mHorizontalImgSelectedView.addItem(imgItem);

            this.invalidateOptionsMenu();

        }

    }


    class PhotoAdapter extends BaseAdapter {

        ArrayList<LocalImgItem> items;
        private DisplayImageOptions options;
        private SimpleImageLoadingListener animateFirstListener;

        public PhotoAdapter(ArrayList<LocalImgItem> items) {

            this.items = items;

            options = AsyncImageLoader.getListDisplayImageOptions(R.drawable.pic_thumb_bg).cacheOnDisc(true).cacheInMemory(true).build();
            animateFirstListener = AsyncImageLoader.getListAnimOfSimpleImageLoadingListener();
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

                convertView = getLayoutInflater().inflate(R.layout.item_gridphoto2, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {

                viewHolder = (ViewHolder) convertView.getTag();
            }

            LocalImgItem imgItem = items.get(position);

            String imgUrl = "file://" + (TextUtils.isEmpty(imgItem.thumbnailPath) == false ? imgItem.thumbnailPath : imgItem.path);
            AsyncImageLoader.loadList(imgUrl, viewHolder.icon, options);

            if (mSelectedItemList != null && mSelectedItemList.size() > 0) {

                viewHolder.imgSelect.setVisibility(mSelectedItemList.contains(imgItem) ? View.VISIBLE : View.GONE);
            } else {

                viewHolder.imgSelect.setVisibility(View.GONE);

            }

            return convertView;

        }

        public class ViewHolder {
            public final ImageView icon;
            public final View root;
            public final ImageView imgSelect;

            public ViewHolder(View root) {
                icon = (ImageView) root.findViewById(R.id.iv_photo);
                imgSelect = (ImageView) root.findViewById(R.id.img_selected);
                this.root = root;
            }
        }
    }




}
