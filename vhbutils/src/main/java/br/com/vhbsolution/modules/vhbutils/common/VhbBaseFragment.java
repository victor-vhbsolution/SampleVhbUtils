package br.com.vhbsolution.modules.vhbutils.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

/**
 * Created by Victor Bitencourt - vtbitencourt on 7/1/2018.
 */
public abstract class VhbBaseFragment extends Fragment {

    protected FragmentManager mFragmentManager;

    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: initViews()
     * DateUtils: 06/17/2017
     * About: This function have to be overwrite in the child classes.
     * it helps initialize the components/fields that will be used in the class
     * Parameters: View view
     * return: void
     *************************************************************************/
    abstract protected void initViews(View view);

    abstract protected void performFragmentTransition();


}