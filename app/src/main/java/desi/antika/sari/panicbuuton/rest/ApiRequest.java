package desi.antika.sari.panicbuuton.rest;

import desi.antika.sari.panicbuuton.model.*;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Field;
import retrofit2.http.Query;

public interface ApiRequest {
    @GET("artikel/list")
    Call<Getartikel> getArtikel();

    @FormUrlEncoded
    @POST("authentication/login")
    Call<Login>  postLogin(@Field("username")String username,
                       @Field("password")String password);
    

    @FormUrlEncoded
    @POST("authentication/register")
    Call<Hasil>  postRegister(@Field("username")String username,
                              @Field("password")String password,
                              @Field("password_confirm") String konfirmasi,
                              @Field("nik")String nik,
                              @Field("nama")String nama,
                              @Field("alamat")String alamat,
                              @Field("telpon")String telpon,
                              @Field("tempat")String tempat,
                              @Field("tanggal")String tanggal,
                              @Field("foto")String foto);
}
