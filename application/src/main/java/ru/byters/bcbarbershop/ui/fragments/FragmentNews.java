package ru.byters.bcbarbershop.ui.fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.controllers.Controller;
import ru.byters.bcbarbershop.controllers.adapters.AdapterNews;

public class FragmentNews extends FragmentBase implements OnClickListener {

    public FragmentNews() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        view.findViewById(R.id.btEnroll).setOnClickListener(this);

        RecyclerView rvNews = (RecyclerView) view.findViewById(R.id.rvNews);
        rvNews.setHasFixedSize(true);
        rvNews.setLayoutManager(new LinearLayoutManager(container.getContext()));
        AdapterNews adapter = ((Controller) container.getContext().getApplicationContext()).adapterNews;
        adapter.setViews(rvNews, view.findViewById(R.id.tvNoData));
        rvNews.setAdapter(adapter);
        rvNews.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int margin = (int) getResources().getDimension(R.dimen.item_news_margin);
                outRect.set(margin, margin, margin, 0);
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        //todo implement
        //startActivity(new Intent(getActivity(), ActivityEnroll.class));
    }
}