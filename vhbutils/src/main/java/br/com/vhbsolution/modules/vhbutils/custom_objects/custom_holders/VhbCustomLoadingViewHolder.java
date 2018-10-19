package br.com.vhbsolution.modules.vhbutils.custom_objects.custom_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import br.com.vhbsolution.modules.vhbutils.R;


/**
 * Created by Victor Bitencourt on 03/07/2017.
 */

public class VhbCustomLoadingViewHolder extends RecyclerView.ViewHolder{

    private ProgressBar mProgressBar;

    public VhbCustomLoadingViewHolder(View view) {
        super(view);
        mProgressBar = view.findViewById(R.id.vhbutils_item_loader_prgbr_load);
    }

    public ProgressBar getProgressBar() {
        return mProgressBar;
    }

}
