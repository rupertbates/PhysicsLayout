package com.jawnnypoo.physicslayout.sample;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Adapter;

import com.jawnnypoo.physicslayout.PhysicsLinearLayout;

public class PhysicsListView extends PhysicsLinearLayout {


    public PhysicsListView(Context context) {
        super(context);
    }

    public PhysicsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PhysicsListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PhysicsListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setAdapter(Adapter adapter) {

        for (int i = 0; i < adapter.getCount(); i++) {
            addView(adapter.getView(i, null, this));
        }
        getPhysics().createWorld();
    }

}
