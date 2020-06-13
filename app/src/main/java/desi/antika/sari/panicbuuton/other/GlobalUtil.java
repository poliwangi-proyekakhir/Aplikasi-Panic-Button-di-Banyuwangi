package desi.antika.sari.panicbuuton.other;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Base64;
import android.util.Base64OutputStream;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class GlobalUtil {

    private static TimeZone timeZone        = TimeZone.getDefault();
    private static Locale locale            = Locale.ENGLISH;
    private static TimeZone timeZoneWIB     = TimeZone.getTimeZone("GMT+7");
    private static Locale localeID          = new Locale("in", "ID");

    public static String toCurrency(Integer nominal) {
        return NumberFormat.getCurrencyInstance(localeID).format(nominal);
    }

    public static String convertFirestoreTime(Date date, String pattern){
        SimpleDateFormat formater = new SimpleDateFormat(pattern, localeID);
        return formater.format(date);
    }

    public static String dateNow(String pattern){
        SimpleDateFormat formater = new SimpleDateFormat(pattern, locale);
        formater.setTimeZone(timeZone);
        Calendar calendar   = Calendar.getInstance(timeZone,locale);
        return formater.format(calendar.getTime());
    }

    public static String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 70, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public static String getStringFile(File f) {
        InputStream inputStream;
        String encodedFile                  = "", lastVal;
        try {
            inputStream                     = new FileInputStream(f.getAbsolutePath());

            byte[] buffer                   = new byte[10240];//specify the size to allow
            int bytesRead;
            ByteArrayOutputStream output    = new ByteArrayOutputStream();
            Base64OutputStream output64     = new Base64OutputStream(output, Base64.DEFAULT);

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output64.write(buffer, 0, bytesRead);
            }
            output64.close();
            encodedFile =  output.toString();
        }
        catch (FileNotFoundException e1 ) {
            e1.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        lastVal = encodedFile;
        return lastVal;
    }

    public static void openFiles(AppCompatActivity activity, int reqCode){
        Intent intent = new Intent();
        intent.setType("application/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent,"Pilih File"), reqCode);
    }


    public static String getFileType(String path){
        File file           = new File(path);
        String fileExt      = MimeTypeMap.getFileExtensionFromUrl(file.toString());
        String type         = "docx";
        switch (fileExt) {
            case "pdf":
                type        = "application/pdf";
                break;
            case "doc":
            case "docx":
                type        = "application/msword";
                break;
            case "ppt":
            case "pptx":
                type        = "application/vnd.ms-powerpoint";
                break;
            case "csv":
            case "xls":
            case "xlsx":
                type        = "application/vnd.ms-excel";
                break;
        }

        return type;
    }

}
