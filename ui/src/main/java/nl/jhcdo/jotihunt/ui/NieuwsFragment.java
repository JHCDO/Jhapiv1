package nl.jhcdo.jotihunt.ui;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 22-10-2016
 * Description...
 */

public class NieuwsFragment extends Fragment {

    public static final String TAG = "NieuwsFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container, false);
        NieuwsView nieuwsView = (NieuwsView) view.findViewById(R.id.fragment_news_view);
        nieuwsView.setOnRefreshCompletedListener(new OnRefreshCompletedListener() {
            @Override
            public void onRefreshCompleted() {
                View base = getView();
                if(base != null) {
                    SwipeRefreshLayout layout = (SwipeRefreshLayout) base.findViewById(R.id.fragment_news_main);
                    layout.setRefreshing(false);
                }
            }
        });

        /**
         * Get the custom SwipeRefreshLayout and set the OnRefreshListener.
         * */
        WebViewSwipeRefreshLayout layout = (WebViewSwipeRefreshLayout) view.findViewById(R.id.fragment_news_main);
        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                View base = getView();
                if(base != null) {
                    NieuwsView nieuwsView = (NieuwsView) base.findViewById(R.id.fragment_news_view);
                    nieuwsView.refresh();
                }
            }
        });
        layout.setCanChildScrollUpListener(nieuwsView);
        layout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);

        return view;
    }

    public NieuwsView getNieuwsView() {
        View view = getView();
        if(view != null) {
            return (NieuwsView)view.findViewById(R.id.fragment_news_view);
        }
        return null;
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        View view = getView();
        if(view != null) {
            return (SwipeRefreshLayout)view.findViewById(R.id.fragment_news_main);
        }
        return null;
    }
}
