package com.jawnnypoo.physicslayout.sample;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This may seem familiar....
 * Created by Jawn on 5/5/2015.
 */
public class TypicalFragment extends Fragment implements View.OnClickListener, ListAnimator.AnimationEndListener {
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
    private View button;
    private LinearLayout listContainer;
    private View title;
    private ListAnimator animator;

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
        button = view.findViewById(R.id.button);
        button.setOnClickListener(this);
        listContainer = (LinearLayout) view.findViewById(R.id.list_container);
        title = view.findViewById(R.id.title);
        listView = (PhysicsListView) view.findViewById(R.id.list);
        listView.setAdapter(new BubbleAdapter(getActivity(), new BubbleRowSorter(32).getRows(values), this));
        animator = new ListAnimator(getActivity(), button, listContainer, listView, title, this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button)
            animator.runOpenListAnimation();
        else
            animator.runCloseListAnimation();
    }

    @Override
    public void OnAnimationEnd() {
        listView.setAdapter(new BubbleAdapter(getActivity(), new BubbleRowSorter(32).getRows(values), this));
    }
}
