package br.com.vhbsolution.modules.vhbutils.custom_objects.custom_adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import br.com.vhbsolution.modules.vhbutils.R;
import br.com.vhbsolution.modules.vhbutils.custom_objects.custom_holders.VhbCustomEmptyRecyclerViewHolder;
import br.com.vhbsolution.modules.vhbutils.custom_objects.custom_holders.VhbCustomLoadingViewHolder;


/**
 * Created by Victor Bitencourt on 1/13/2018.
 */


public abstract class VhbCustomRecyclerViewListAdapter<T extends RecyclerView.ViewHolder, I> extends RecyclerView.Adapter<T> {
    /**
     * @param T RecyclerView.ViewHolder Type
     * @param I Item Type
     *
     */

    protected final int VIEW_TYPE_EMPTY = -1;
    protected final int VIEW_TYPE_LOADING = -2;

    private String mEmptyAletMessage;

    protected List<I> mList;
    protected Context mContext;
    protected boolean mIsEmpty;
    protected boolean mIsLoading;
    protected boolean mWaiting = false;

    protected VhbCustomRecyclerViewListAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        int typeView = 0;

        if (isEnableEmptyView() && mList.get(position) == null && mIsEmpty)
            typeView = VIEW_TYPE_EMPTY;
        else if (mList.get(position) == null)
            typeView = VIEW_TYPE_LOADING;

        return typeView;
    }

    @Override
    public T onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = null;

        switch (viewType) {
            case VIEW_TYPE_EMPTY:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vhbutils_item_recycler_view_empty_list, viewGroup, false);
                return (T) new VhbCustomEmptyRecyclerViewHolder(view);

            case VIEW_TYPE_LOADING:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vhbutils_item_loader_list, viewGroup, false);
                return (T) new VhbCustomLoadingViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof VhbCustomEmptyRecyclerViewHolder) {
            VhbCustomEmptyRecyclerViewHolder emptyRecyclerViewHolder = (VhbCustomEmptyRecyclerViewHolder) viewHolder;
            emptyRecyclerViewHolder.getTextViewAlertMessage().setText(mEmptyAletMessage);
        } else if (viewHolder instanceof VhbCustomLoadingViewHolder) {
            VhbCustomLoadingViewHolder loadingViewHolder = (VhbCustomLoadingViewHolder) viewHolder;
            loadingViewHolder.getProgressBar().setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setWatingRequest(boolean isWating){mWaiting = isWating;}

    protected boolean isEnableEmptyView() {
        return true;
    }

    protected boolean isEnableLoadingView() {
        return true;
    }

    public void setEmptyAlertMessage(String message) {
        mEmptyAletMessage = message;
    }

    public boolean isLoading() {
        return mIsLoading;
    }

    public boolean isIsEmpty() {
        return mIsEmpty;
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

    public void addListItem(I item, int position) {
        mList.add(position, item);
        notifyItemInserted(position);
    }

    public void addItem(I item) {
        mList.add(item);
        notifyItemInserted(mList.size() - 1);
        isEmptyList();
    }

    public void removeListItem(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
        isEmptyList();
    }

    public void addAll(List<I> list) {
        mList.addAll(list);
        notifyDataSetChanged();
        isEmptyList();
    }

    public void removeItem(int position) {
        mList.remove(position);
        notifyDataSetChanged();
        isEmptyList();
    }

    public void addNewList(List<I> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
        isEmptyList();
    }

    public void isEmptyList() {
        if (isEnableEmptyView()) {
            if (mList.size() == 0) {
                mIsEmpty = true;
                mList.add(null);
            } else {
                mIsEmpty = false;
            }
        }
    }

    public void setLoading(boolean isLoading) {
        if (isLoading) {
            mIsLoading = true;
            mList.add(null);
            notifyItemInserted(mList.size() - 1);

        } else {
            mIsLoading = false;
            removeItem(getItemCount() - 1);
            notifyItemRemoved(mList.size() - 1);
        }
    }

    public void setEmptyList() {
        if (isEnableEmptyView()) {
            clear();
            mIsEmpty = true;
            mList.add(null);
        }
    }
}
