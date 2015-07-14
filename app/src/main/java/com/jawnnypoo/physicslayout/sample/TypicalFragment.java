package com.jawnnypoo.physicslayout.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * This may seem familiar....
 * Created by Jawn on 5/5/2015.
 */
public class TypicalFragment extends Fragment {
    String[] values = new String[]{"Art"
            , "Culture"
            , "Performance art"
            , "Art and design"
            , "Women"
            , "Photography"
            , "Superhero movies"
            , "Culture"
            , "Film"
            , "Marvel"
            , "DC Comics"
            , "Science fiction and fantasy"
            , "Ant-Man"
            , "X-Men Origins: Wolverine"
            , "Architecture"
            , "Art and design"
            , "London"
            , "Culture"
            , "Cities"};

    public static TypicalFragment newInstance() {
        TypicalFragment fragment = new TypicalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    PhysicsListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_typical, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (PhysicsListView) view;
        listView.setAdapter(new BubbleAdapter(getActivity(), values));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    static class BubbleAdapter extends BaseAdapter {
        private final Context context;
        private String[] values;

        public BubbleAdapter(Context context, String[] values) {
            this.context = context;
            this.values = values;
        }

        @Override
        public int getCount() {
            return values.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView child = new SquareTextView(context);
            child.setText(values[position]);
            child.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.circle));
            int max = 300;
            int padding = 20;
            child.setPadding(padding, padding, padding, padding);
            child.setMaxWidth(max);
            child.setMaxHeight(max);
            child.setGravity(Gravity.CENTER);
            child.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return child;
        }
    }
}
