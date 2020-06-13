package desi.antika.sari.panicbuuton.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retroserver {
    private  static  final String base_url = "http://192.168.1.15/panicbutton-api/";
    public   static  final String url_img = "http://192.168.1.15/panicbutton-api/assets/images/";
    private static Retrofit retrofit;
    private static Gson gson;

    public static Retrofit getClient()
    {

        if(retrofit == null)
        {
            gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return  retrofit;
    }
}
