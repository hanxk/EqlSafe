package com.eql.safe.ui.photo;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;
import com.eql.safe.R;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


/**
 * 相册
 * Activities that contain this fragment must implement the
 * // * to handle interaction events.
 * create an instance of this fragment.
 */
public class PhotoFragment extends Fragment implements AdapterView.OnItemClickListener {

    //    {@link PhotoFragment.OnFragmentInteractionListener} interface
    GridView mGvPhoto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_photo, container, false);
        mGvPhoto = (GridView) rootView.findViewById(R.id.gridView);
        mGvPhoto.setOnItemClickListener(this);
        mGvPhoto.setAdapter(new PhotoAdapter());
//        setHasOptionsMenu(true);
        return rootView;

    }



    public static PhotoFragment newInstance(int menuPos) {
        PhotoFragment fragment = new PhotoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getActivity(), PhotoViewerActivity.class);
        startActivity(intent);
    }


    class PhotoAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            if (convertView == null) {

                convertView = getActivity().getLayoutInflater().inflate(R.layout.item_gridphoto, null);

            }

            return convertView;

        }
    }




}
