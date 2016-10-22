package nl.jhcdo.jotihunt.ui;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 22-10-2016
 * Custom SwipeRefreshLayout for supporting WebView scrolling.
 */
public class WebViewSwipeRefreshLayout extends SwipeRefreshLayout {

    /**
     * Initializes an new instance of WebViewSwipeRefreshLayout.
     * */
    public WebViewSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     *
     * */
    protected CanChildScrollUpListener mCanChildScrollUpListener;

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 22-10-2016
     * Listener for determining if the child can scroll up.
     */
    public interface CanChildScrollUpListener {
        boolean canChildScrollUp();
    }

    /**
     * Sets the listener that determines if the child can scroll up.
     * */
    public void setCanChildScrollUpListener(CanChildScrollUpListener canChildScrollUpListener) {
        mCanChildScrollUpListener = canChildScrollUpListener;
    }

    @Override
    public boolean canChildScrollUp() {
        if (mCanChildScrollUpListener != null) {
            return mCanChildScrollUpListener.canChildScrollUp();
        }
        return super.canChildScrollUp();
    }

}
