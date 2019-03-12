package com.gurbanidarshan.activities.nitnem;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gurbanidarshan.R;
import com.gurbanidarshan.activities.nitnem.constants.Constants;
import com.gurbanidarshan.activities.nitnem.utils.ReadFileModel;


public class NitnemBani extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nitnem_bani);
        ReadFileModel readFileModel = new ReadFileModel(this);
        Constants.data = readFileModel.readFile();
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new NitnemBani.CustomPagerAdapter(getSupportFragmentManager()));
        viewPager.setPageTransformer(false, new NitnemBani.PageCurlPageTransformer());

    }

    public class CustomPagerAdapter extends FragmentPagerAdapter {
        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TestFragment.getInstance(position);
        }

        @Override
        public int getCount() {
            return Constants.page;
        }
    }

    public static class TestFragment extends Fragment {
        TextView textView;
        LayoutInflater inflater;
        View v;
        private int mPage;

        public static TestFragment getInstance(int page) {
            TestFragment testFragment = new TestFragment();
            Bundle args = new Bundle();
            args.putInt("page", page);
            testFragment.setArguments(args);
            return testFragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mPage = getArguments().getInt("page");
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.layout_blue, container, false);
            TextView textView = (TextView) view.findViewById(R.id.detail);
            Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/amrlipi_.ttf");
            textView.setText(Constants.paragraph.get(mPage));
            textView.setTypeface(custom_font);
            view.setTag(R.id.viewpager, mPage);
            return view;
        }

        private String getSampleText() {

            String str = "Page " + mPage + " ";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 300; i++) {
                sb.append(str);
            }
            return sb.toString();
        }
    }

    public static class PageCurlPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            if (page instanceof PageCurl) {
                if (position > -1.0F && position < 1.0F) {
                    // hold the page steady and let the views do the work
                    page.setTranslationX(-position * page.getWidth());
                } else {
                    page.setTranslationX(0.0F);
                }
                if (position <= 1.0F && position >= -1.0F) {
                    ((PageCurl) page).setCurlFactor(position);
                }
            }
        }
    }
}
