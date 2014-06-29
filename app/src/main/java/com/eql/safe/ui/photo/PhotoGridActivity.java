package com.eql.safe.ui.photo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.eql.safe.R;
import com.eql.safe.ui.BaseActivity;

public class PhotoGridActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {

            PhotoFragment photoFragment = new PhotoFragment();
            getFragmentManager().beginTransaction().add(android.R.id.content, photoFragment).commit();

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.photo_grid, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_import) {//导入照片

            Intent intent = new Intent(this,ChoosePhotoListActivity.class);
            startActivity(intent);
            return true;

        }else if(id == R.id.action_camera){//相册


        }
        return super.onOptionsItemSelected(item);
    }
}
