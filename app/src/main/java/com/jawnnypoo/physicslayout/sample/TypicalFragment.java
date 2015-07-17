package com.jawnnypoo.physicslayout.sample;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jawnnypoo.physicslayout.Physics;
import com.jawnnypoo.physicslayout.PhysicsConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This may seem familiar....
 * Created by Jawn on 5/5/2015.
 */
public class TypicalFragment extends Fragment {
    List<String> values = new ArrayList<>(Arrays.asList(new String[]{
            "Art"
            , "Culture"
            , "Performance art"
            , "Women"
            , "Photography"
            , "Superhero movies"
            , "Culture"
            , "Science fiction and fantasy"
            , "Film"
            , "Marvel"
            , "DC Comics"
            , "Ant-Man"}));

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
        listView = (PhysicsListView) view.findViewById(R.id.list);
        listView.setAdapter(new BubbleAdapter(getActivity(), new BubbleRowSorter(32).getRows(values)));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    static class BubbleAdapter extends BaseAdapter implements View.OnClickListener {
        private static final int MARGIN = 6;
        private final Context context;
        private final LayoutInflater inflater;
        private List<String[]> rows;
        private List<String> selected = new ArrayList<>();

        public BubbleAdapter(Context context, List<String[]> rows) {
            this.context = context;
            this.inflater = LayoutInflater.from(context);
            this.rows = rows;
        }

        @Override
        public int getCount() {
            return rows.size();
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
            LinearLayout wrapper = getWrapper();
            for (String row : rows.get(position)) {
                wrapper.addView(getChild(row, parent));
            }
            return wrapper;

        }

        private View getChild(String text, ViewGroup parent) {
            TextView child = (TextView) inflater.inflate(R.layout.bubble, parent, false);
            child.setTag(text);
            child.setText(text);
            child.setOnClickListener(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);
            child.setLayoutParams(params);
            return child;
        }

        private LinearLayout getWrapper() {
            LinearLayout layout = new LinearLayout(context);
            //int padding = 6;
            //layout.setPadding(padding, padding, padding, padding);
            layout.setGravity(Gravity.CENTER);
            Physics.setPhysicsConfig(
                    layout,
                    new PhysicsConfig.Builder()
                            .setDensity(9f)
                            .setFriction(0f)
                            .setRestitution(0.5f)
                            .setAllowRotation(false)
                            .build());
            return layout;
        }

        @Override
        public void onClick(View v) {
            GradientDrawable gradientDrawable = (GradientDrawable) v.getBackground().mutate();
            String text = (String) v.getTag();
            if (selected.contains(text)) {
                gradientDrawable.setColor(context.getResources().getColor(R.color.background));
                selected.remove(text);
            } else {
                gradientDrawable.setColor(context.getResources().getColor(R.color.highlight));
                selected.add(text);
            }

        }
    }
}
