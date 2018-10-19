package br.com.vhbsolution.modules.vhbutils.interfaces;

import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Victor Bitencourt - vtbitencourt on 7/1/2018.
 */
public interface IVhbBaseActivity {

    boolean performCustomAnimationIn();
    boolean performCustomAnimationOut();

    void performNextActivity(Intent intent, @Nullable Integer requestCode);

    void hideSoftKeyboard();
}
