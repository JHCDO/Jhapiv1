package nl.jhcdo.jotihunt.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.FrameLayout;

import nl.jhcdo.jotihunt.Jotihunt;
import nl.jhcdo.jotihunt.net.data.structures.Bericht;
import nl.jhcdo.jotihunt.net.data.structures.Nieuws;
import nl.jhcdo.jotihunt.net.enumeration.FunctionType;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 22-10-2016
 * NieuwsView is build out of two views, a ListView to show the possible Nieuws.Items @see nl.jhcdo.jotihunt.net.data.structures.Nieuws.Item, and a WebView to show the HTML content of a Bericht @see nl.jhcdo.jotihunt.net.data.structures.Bericht.
 */
public class NieuwsView extends FrameLayout implements AdapterView.OnItemClickListener, Callback<Bericht>, WebViewSwipeRefreshLayout.CanChildScrollUpListener {

    private static final String TAG = "NieuwsView";

    /**
     * Value holding the ID of the last item, is null if there's no last item.
     * */
    protected String lastItemId;

    /**
     * Value indicating if a Nieuws.Item has been opened.
     * */
    protected boolean isOpened = false;

    /**
     * The listener for when a refresh has been completed.
     * */
    protected OnRefreshCompletedListener listener;

    /**
     * Initializes a new instance of NieuwsView.
     * */
    public NieuwsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.news_view, this);

        WebView webView = (WebView)view.findViewById(R.id.news_view_web);
        webView.setVisibility(INVISIBLE);

        NieuwsListView list = (NieuwsListView)view.findViewById(R.id.news_view_list);
        list.setOnItemClickListener(this);
        list.refresh();
    }

    /**
     * Gets the value indicating if a Nieuws.Item has been opened.
     * */
    public boolean isOpened() {
        return isOpened;
    }

    /**
     * Sets a listener for when a refresh has been completed.
     * */
    public void setOnRefreshCompletedListener(OnRefreshCompletedListener listener) {
        this.listener = listener;
        getNieuwsListView().setOnRefreshCompletedListener(listener);
    }

    /**
     * Gets the WebView of hte NieuwsView.
     * */
    protected WebView getWebView() {
        return (WebView) findViewById(R.id.news_view_web);
    }

    /**
     * Gets the NieuwsListView of the NieuwsView.
     * */
    protected NieuwsListView getNieuwsListView() {
        return (NieuwsListView)findViewById(R.id.news_view_list);
    }

    /**
     * Refreshes the current page or the items.
     * */
    public void refresh() {
        if(isOpened && lastItemId != null) {
            /**
             * Refresh the current opened item.
             * */
            Jotihunt.getApiInstance().getBericht(FunctionType.NIEUWS, lastItemId).enqueue(this);
        } else {
            /**
             * Refresh the items in the Adapter.
             * */
            getNieuwsListView().refresh();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Nieuws.Item item = (Nieuws.Item)parent.getItemAtPosition(position);
        if(item != null) {
            Jotihunt.getApiInstance().getBericht(FunctionType.NIEUWS, item.getId()).enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<Bericht> call, Response<Bericht> response) {
        Bericht bericht =  response.body();
        if(bericht.hasContent()) {
            Bericht.Content content = bericht.getContents().get(0);
            WebView webView = getWebView();
            webView.setVisibility(VISIBLE);
            webView.loadDataWithBaseURL("http://www.jotihunt.net", content.getContent(), "text/html", "UTF-8", "about:blank");
            lastItemId = content.getId();
            isOpened = true;

            NieuwsListView list = getNieuwsListView();
            list.setVisibility(INVISIBLE);
        }

        if(listener != null) {
            listener.onRefreshCompleted();
        }
    }

    @Override
    public void onFailure(Call<Bericht> call, Throwable t) {
        Log.e(TAG, t.toString(), t);
    }


    /**
     * Closes the currently opened Item.
     * */
    public void close() {
        if(isOpened) {
            NieuwsListView list = getNieuwsListView();
            list.setVisibility(VISIBLE);

            WebView webView = getWebView();
            webView.setVisibility(INVISIBLE);
            isOpened = false;
        }
    }

    /**
     * Determines if the active child of the SwipeRefreshLayout can scroll up.
     * */
    @Override
    public boolean canChildScrollUp() {
        if(isOpened) {
            return getWebView().getScrollY() > 0;
        }
        return false;
    }
}
