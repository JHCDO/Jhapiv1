package nl.jhcdo.jotihunt.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import nl.jhcdo.jotihunt.Jotihunt;
import nl.jhcdo.jotihunt.net.model.Nieuws;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 22-10-2016
 * A ListView for the Nieuws.Items
 */
public class NieuwsListView extends ListView {

    /**
     * Defines a tag for this class.
     * */
    private static final String TAG = "NieuwsListView";

    /**
     * The Adapter for the ListView, that can show the Nieuws.Item.
     * */
    protected Adapter adapter;

    /**
     * Initializes a new instance of NieuwsListView.
     * */
    public NieuwsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.adapter = new Adapter(context, new ArrayList<Nieuws>());
        setAdapter(adapter);
    }

    /**
     * Sets the OnRefreshCompletedListener for the NieuwsListView.
     * */
    public void setOnRefreshCompletedListener(OnRefreshCompletedListener listener) {
        adapter.listener = listener;
    }

    /**
     * Refreshes the data in the adapter.
     * */
    public void refresh() {
        Jotihunt.getApiInstance().getNieuws().enqueue(adapter);
    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 21-10-2016
     * Adapter for the Nieuws.Item @see nl.jhcdo.jotihunt.net.data.structures.Nieuws.Item
     */
    public class Adapter extends ArrayAdapter<Nieuws> implements Callback<Nieuws.Container> {

        protected OnRefreshCompletedListener listener;

        /**
         * Initializes a new instance of the Adapter.
         * */
        public Adapter(Context context, ArrayList<Nieuws> data) {
            super(context, 0, data);
        }

        public void setOnRefreshCompletedListener(OnRefreshCompletedListener listener) {
            this.listener = listener;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Nieuws item = getItem(position);
            if(item != null) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item, parent, false);
                }

                TextView title = (TextView) convertView.findViewById(R.id.title);
                TextView date = (TextView) convertView.findViewById(R.id.date);
                title.setText(item.getTitle());
                date.setText(item.getDate().toString());
            }
            return convertView;
        }

        @Override
        public void onResponse(Call<Nieuws.Container> call, Response<Nieuws.Container> response) {
            Nieuws.Container nieuws = response.body();
            if(nieuws != null) {
                this.clear();
                addAll(nieuws.getItems());
            }
            if(listener != null) {
                listener.onRefreshCompleted();
            }
        }

        @Override
        public void onFailure(Call<Nieuws.Container> call, Throwable t) {
            Log.e(TAG, t.toString(), t);
        }

    }
}
