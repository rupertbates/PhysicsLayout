package com.jawnnypoo.physicslayout.sample;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
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
import java.util.List;

class BubbleAdapter extends BaseAdapter implements View.OnClickListener {
    private static final int MARGIN = 6;
    private final Context context;
    private View.OnClickListener closeButtonClickListener;
    private final LayoutInflater inflater;
    private List<String[]> rows;
    private List<String> selected = new ArrayList<>();

    public BubbleAdapter(Context context, List<String[]> rows, View.OnClickListener closeButtonClickListener) {
        this.context = context;
        this.closeButtonClickListener = closeButtonClickListener;
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
        if (position == 0)
            addCloseButton(wrapper);
        return wrapper;

    }

    private void addCloseButton(LinearLayout wrapper) {
        View closeButton = inflater.inflate(R.layout.close_button, wrapper, true);
        if (closeButtonClickListener != null)
            closeButton.setOnClickListener(closeButtonClickListener);
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
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.setMargins(0, 10, 0, 10);
//        layout.setLayoutParams(params);
        //int padding = 6;
        //layout.setPadding(padding, padding, padding, padding);
        layout.setGravity(Gravity.CENTER);
//        Physics.setPhysicsConfig(
//                layout,
//                new PhysicsConfig.Builder()
//                        .setFriction(0f)
//                        .setRestitution(0.5f)
//                        .setAllowRotation(false)
//                        .build());
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
