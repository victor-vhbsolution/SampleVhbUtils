package br.com.vhbsolution.modules.vhbutils.custom_objects.custom_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.vhbsolution.modules.vhbutils.interfaces.IVhbBaseViewHolder;

/**
 * Created by Victor Bitencourt - vtbitencourt on 8/26/2018.
 */
public abstract class VhbBaseViewHolder<T> extends RecyclerView.ViewHolder implements IVhbBaseViewHolder<T> {

    protected View mItemView;

    public VhbBaseViewHolder(View itemView) {
        super(itemView);
        mItemView = itemView;
    }
}


