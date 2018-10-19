package br.com.vhbsolution.modules.vhbutils.interfaces;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Victor Bitencourt - vtbitencourt on 6/2/2018.
 */
public interface IVhbTypes {


    int IMAGE_CONTENT = 1;
    int DOC_CONTENT = 2;


    @IntDef({IMAGE_CONTENT, DOC_CONTENT})
    @Retention(RetentionPolicy.SOURCE)
    @interface FileContentType { }

}
