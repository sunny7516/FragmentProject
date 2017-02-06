package com.example.tacademy.fragmentproject.ui.act;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.tacademy.fragmentproject.R;
import com.example.tacademy.fragmentproject.ui.frag.CallSearchFragment;
import com.example.tacademy.fragmentproject.ui.frag.FaceBookFragment;
import com.example.tacademy.fragmentproject.ui.frag.WebSearchFragment;

public class CallSearchActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_search);

        tabLayout = (TabLayout)findViewById(R.id.maintablayout);
        viewPager = (ViewPager)findViewById(R.id.viewpager);

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    class FragmentAdapter extends FragmentPagerAdapter{

        // 프레그먼트 화면 개수 및 정의
        Fragment[] frags = new Fragment[]{
                new CallSearchFragment(),
                new FaceBookFragment(),
                new WebSearchFragment()
        };

        // 프레그먼트 화면 제목 설정
        String[] titles = new String[]{
                "가나다 출력", "프로필", "산책후기"
        };

        // 생성자
        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return frags[position];
        }

        @Override
        public int getCount() {
            return frags.length;
        }

        // 오버라이드 getPageTitle
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
