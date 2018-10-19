package br.com.vhbsolution.modules.vhbutils.custom_objects.custom_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.vhbsolution.modules.vhbutils.R;

/**
 * Created by Victor Bitencourt on 8/1/2017.
 */

public class VhbCustomEmptyRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvMessage;
    private Button  mBtnAction;

    public VhbCustomEmptyRecyclerViewHolder(View itemView) {
        super(itemView);

        mTvMessage = itemView.findViewById(R.id.vhbutils_item_recycler_view_empty_tv_message);
        mBtnAction = itemView.findViewById(R.id.vhbutils_item_recycler_view_empty_btn_action);

    }

    public TextView getTextViewAlertMessage() {
        return mTvMessage;
    }

    public Button getBtnAction() {
        return mBtnAction;
    }
}
