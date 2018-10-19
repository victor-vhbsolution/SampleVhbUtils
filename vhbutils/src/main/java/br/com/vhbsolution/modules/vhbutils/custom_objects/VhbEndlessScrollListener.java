package br.com.vhbsolution.modules.vhbutils.custom_objects;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE;

/**
 * Created by Victor Bitencourt - vtbitencourt on 7/1/2018.
 */

public abstract class VhbEndlessScrollListener extends RecyclerView.OnScrollListener {


    public VhbEndlessScrollListener() {
    }

    // Defines the process for actually loading more data based on page
    // Returns true if more data is being loaded; returns false if there is no more data to load.
    public abstract void onLoadMore(int offset);

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState); // Don't take any action on changed

        LinearLayoutManager manager = ((LinearLayoutManager) recyclerView.getLayoutManager());

        int totalItemCount = recyclerView.getLayoutManager().getItemCount();
        int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();

        //onScroll(recyclerView, firstVisibleItem, lastVisibleItem, totalItemCount);
        if (newState == SCROLL_STATE_IDLE && totalItemCount > 0 && ((lastVisibleItem + 1) == totalItemCount))
            onLoadMore(totalItemCount);

    }

}
