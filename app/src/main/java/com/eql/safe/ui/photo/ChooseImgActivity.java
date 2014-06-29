package com.eql.safe.ui.photo;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.eql.safe.R;
import com.eql.safe.entity.ImageDir;
import com.eql.safe.ui.BaseActivity;
import com.eql.safe.utils.LocalImageUtils;

import java.util.ArrayList;

public class ChooseImgActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_img);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment());

        }

    }


    private static class LoadDataTask extends AsyncTask<Void, Void, ArrayList<ImageDir>> {

        Context context;

        public LoadDataTask(Context context) {

            this.context = context;

        }

        @Override
        protected ArrayList<ImageDir> doInBackground(Void... params) {

            return LocalImageUtils.newInstance(context).getLocalImgList();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.choose_img, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_main2, container, false);
//            return rootView;
            return null;
        }
    }
}
