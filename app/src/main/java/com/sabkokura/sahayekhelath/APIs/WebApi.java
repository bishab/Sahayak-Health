package com.sabkokura.sahayekhelath.APIs;

import com.sabkokura.sahayekhelath.Requests.LoginRequest;
import com.sabkokura.sahayekhelath.Responses.LoginResponses;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface WebApi {
    @POST("userlogin/")
    Call<LoginResponses> loginUser(@Body LoginRequest loginRequest);

//    @FormUrlEncoded
//    @POST("userlongin/")
//    Call<LoginResponses> loginUser(@Field("email") String email, @Field("password") String pass);


}
