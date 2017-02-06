package com.example.tacademy.fragmentproject.ui.frag;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tacademy.fragmentproject.R;

public class CallSearchFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    GridView gridView;
    MyAdapter listMyAdapter, gridMyAdapter;

    LayoutInflater inflater;

    // 임의 데이터
    String[] name = {"가나다1","가나다2","라나다3","가나다4","가나다라5","라나다6",
            "가나다7","가나다라8","라나다9","가나다0","가나다라9","라나다8",
            "가나다77","가나다6라","라5나다","가나4다","가3나다라","2라나다"};


    private OnFragmentInteractionListener mListener;

    public CallSearchFragment() {

    }


    public static CallSearchFragment newInstance(String param1, String param2) {
        CallSearchFragment fragment = new CallSearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        View view = inflater.inflate(R.layout.fragment_call_search, container, false);
        gridView = (GridView)view.findViewById(R.id.gridView);
        gridMyAdapter = new MyAdapter();
        listMyAdapter = new MyAdapter();
        gridView.setAdapter(gridMyAdapter);

        // 버튼이 토글화 되엇 뷰의 생김새를 변화시킨다!!!
        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 한번 누르면 리스트뷰, 또 누르면 그리드 뷰
                if(gridView.getNumColumns() == 1){
                    gridView.setNumColumns(3);
                    gridView.setAdapter(gridMyAdapter);
                }
                else{
                    gridView.setNumColumns(1);
                    gridView.setAdapter(listMyAdapter);
                }
            }
        });
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            if(name == null) return 0;
            return name.length;
        }

        @Override
        public String getItem(int position) {
            return name[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                // 리스트뷰와 그리뷰 분기 -> 재사용하는 View에서 혼선이 안생긴다.( 각각 아답터 객체를 만들어서 사용함.)
                if (gridView.getNumColumns() == 1) {  //리스트뷰
                    // 리스트뷰
                    convertView = inflater.inflate(R.layout.cell_list_layout, parent, false);
                } else {  // 그리드뷰
                    convertView = inflater.inflate(R.layout.cell_grid_layout, parent, false);
                }
            }
            ImageView profile = (ImageView)convertView.findViewById(R.id.profile);
            TextView nickname = (TextView)convertView.findViewById(R.id.nickname);

            // 이름 세팅
            nickname.setText(name[position]);

            return convertView;
        }
    }
}
