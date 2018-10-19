package br.com.vhbsolution.modules.vhbutils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.vhbsolution.modules.vhbutils.interfaces.IVhbTypes;

import static br.com.vhbsolution.modules.vhbutils.interfaces.IVhbTypes.DOC_CONTENT;
import static br.com.vhbsolution.modules.vhbutils.interfaces.IVhbTypes.IMAGE_CONTENT;

/**
 * Created by Victor Bitencourt - vtbitencourt on 6/2/2018.
 */
public class VhbFileUtils {

    public static File getExternalFile(Context context, @IVhbTypes.FileContentType int contentType, String mimeType) {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = new File(context.getFilesDir(), getContentType(contentType));

        if (storageDir.exists()) {
            if (!storageDir.isDirectory()) {
                storageDir.delete();
                storageDir.mkdirs();
            }
        } else
            storageDir.mkdirs();


        File file = null;
        try {
            file = File.createTempFile(timeStamp, mimeType, storageDir);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public static Uri getContentUriFromFile(Context context, File file) {

        if (file == null)
            return null;

        Uri uri = FileProvider.getUriForFile(context, context.getPackageName()+".fileprovider" , file);

        return uri;
    }


    private static String getContentType(@IVhbTypes.FileContentType int contentType) {

        String content = null;

        switch (contentType) {

            case IMAGE_CONTENT:

                content = "images";

                break;

            case DOC_CONTENT:

                content = "docs";

                break;
        }

        return content;
    }

    public static String getRealPathFromURI(Activity activity, Uri contentUri) {

        try {
            String realPath = null;

            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = activity.getContentResolver().query(contentUri, filePathColumn, null, null, null);
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                realPath = cursor.getString(columnIndex);
            }
            cursor.close();
            return realPath;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * @param context The context .
     * @param uri     The Uri to check.
     * @return return the temporary file
     */
    public static File getFileFromUri(Context context, Uri uri) {

        InputStream input = null;
        File file = null;
        try {
            file = new File(context.getCacheDir(), "cacheFileAppeal.srl");
            OutputStream output = new FileOutputStream(file);

            input = context.getContentResolver().openInputStream(uri);

            try {
                byte[] buffer = new byte[4 * 1024]; // or other buffer size
                int read;

                while ((read = input.read(buffer)) != -1) {
                    output.write(buffer, 0, read);
                }

                output.flush();
            } finally {
                output.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null)
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return file;
    }
}


