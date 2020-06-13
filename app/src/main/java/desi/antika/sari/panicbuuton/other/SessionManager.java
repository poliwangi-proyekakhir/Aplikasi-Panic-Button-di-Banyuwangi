package desi.antika.sari.panicbuuton.other;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    private static final String pref_name = "panicbutton";
    private static final String is_login = "islogin";
    public static final String key_id= "keyid";
    public static final String key_nama= "keynama";
    public static final String key_status= "keystatus";

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int mode = 0;

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(pref_name, mode);
        editor = pref.edit();
    }

    public void createSession(String id, String nama,String status){
        editor.putBoolean(is_login, true);
        editor.putString(key_id, id);
        editor.putString(key_nama, nama);
        editor.putString(key_status, status);
        editor.commit();
    }

    private boolean is_login() {
        return pref.getBoolean(is_login, false);
    }

    public void logout(){
        editor.putBoolean(is_login, false);
        editor.putString(key_id, null);
        editor.putString(key_nama, null);
        editor.putString(key_status, null);
        editor.commit();
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(pref_name, pref.getString(pref_name, null));
        user.put(key_id, pref.getString(key_id, null));
        user.put(key_nama, pref.getString(key_nama, null));
        user.put(key_status, pref.getString(key_status, null));
        return user;
    }

}
