package com.eql.safe.ui.photo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.eql.safe.R;
import com.eql.safe.view.HorizontalImgSelectedView;
import com.eql.safe.common.Constants;
import com.eql.safe.entity.ImageDir;
import com.eql.safe.entity.LocalImgItem;
import com.eql.safe.ui.BaseActivity;
import com.eql.safe.utils.AsyncImageLoader;
import com.eql.safe.utils.LocalImageUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ChoosePhotoListActivity extends BaseActivity {

    ListView mListView;

    static ArrayList<LocalImgItem> mSelectedItemList;
    HorizontalImgSelectedView mHorizontalImgSelectedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_photo_list);

        mListView = (ListView) findViewById(R.id.listview);
        mSelectedItemList = getIntent().getParcelableArrayListExtra(Constants.INTENT_KEY_LOCALITEMS);
        new LoadPhotoTask(this, mListView).execute();


        mHorizontalImgSelectedView = (HorizontalImgSelectedView) findViewById(R.id.view_horizontal);

    }


    private class LoadPhotoTask extends AsyncTask<Void, Void, ArrayList<ImageDir>> {

        WeakReference<ListView> weakReference;
        WeakReference<Context> contextWeakReference;


        public LoadPhotoTask(Context context, ListView mListView) {

            this.contextWeakReference = new WeakReference<Context>(context);
            weakReference = new WeakReference<ListView>(mListView);

        }

        @Override
        protected ArrayList<ImageDir> doInBackground(Void... voids) {

            return LocalImageUtils.newInstance(contextWeakReference.get()).getLocalImgList();

        }

        @Override
        protected void onPostExecute(final ArrayList<ImageDir> imageDirs) {
            super.onPostExecute(imageDirs);

            final Context context = contextWeakReference.get();
            if (context == null)
                return;

            ListView listView = weakReference.get();
            if (listView != null) {


                listView.setAdapter(new PhotoDirAdapter(context, imageDirs));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        ImageDir imageDir = imageDirs.get(i);
                        Intent intent = getIntent();
                        intent.setClass(context, ChoosePhotoGridActivity.class);
                        intent.putExtra(Constants.INTENT_KEY_IMAGEDIR, imageDir);
                        intent.putExtra(Constants.INTENT_KEY_LOCALITEMS, mSelectedItemList);
                        startActivityForResult(intent, 0);

                    }
                });

            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            mSelectedItemList = data.getParcelableArrayListExtra(Constants.INTENT_KEY_LOCALITEMS);
//            if(mSelectedItemList != null || mSelectedItemList.size() > 0){
//
//                mHorizontalImgSelectedView.setData(mSelectedItemList.size(),mSelectedItemList);
//
//            }

        }


    }

    private static class PhotoDirAdapter extends BaseAdapter {

        ArrayList<ImageDir> imageDirs;
        Context context;

        private DisplayImageOptions options;
        private SimpleImageLoadingListener animateFirstListener;

        public PhotoDirAdapter(Context context, ArrayList<ImageDir> imageDirs) {

            this.context = context;
            this.imageDirs = imageDirs;

            options = AsyncImageLoader.getListDisplayImageOptions().cacheOnDisc(false).cacheInMemory(true).build();
            animateFirstListener = AsyncImageLoader.getListAnimOfSimpleImageLoadingListener();

        }

        @Override
        public int getCount() {
            return imageDirs.size();
        }

        @Override
        public Object getItem(int i) {

            return imageDirs.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder holder;
            if (view == null) {

                view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_photo_dir, null);
                holder = new ViewHolder(view);
                view.setTag(holder);

            } else {

                holder = (ViewHolder) view.getTag();

            }

            ImageDir imageDir = imageDirs.get(i);
            holder.tvdirname.setText(imageDir.dirName);
            holder.tvdirphotocount.setText(String.valueOf(imageDir.imgList != null ? imageDir.imgList.size() : 0));

            LocalImgItem imgItem = imageDir.imgList.get(0);


            String imgUrl = "file://" + (TextUtils.isEmpty(imgItem.thumbnailPath) == false ? imgItem.thumbnailPath : imgItem.path);
            AsyncImageLoader.loadList(imgUrl, holder.img, options, animateFirstListener);
            return view;
        }


        private class ViewHolder {
            public final ImageView img;
            public final TextView tvdirname;
            public final TextView tvdirphotocount;
            public final View root;

            public ViewHolder(View root) {
                img = (ImageView) root.findViewById(R.id.img);
                tvdirname = (TextView) root.findViewById(R.id.tv_dir_name);
                tvdirphotocount = (TextView) root.findViewById(R.id.tv_dir_photo_count);
                this.root = root;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.choose_photo_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
