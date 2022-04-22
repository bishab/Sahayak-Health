package com.sabkokura.sahayekhelath.APIs;

import com.google.gson.JsonObject;
import com.sabkokura.sahayekhelath.ModelClasses.FAQsModelClass;
import com.sabkokura.sahayekhelath.Requests.LoginRequest;
import com.sabkokura.sahayekhelath.Requests.RegisterRequest;
import com.sabkokura.sahayekhelath.Responses.LoginResponses;
import com.sabkokura.sahayekhelath.Responses.RegisterResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebApi {
    @POST("userlogin/")
    Call<LoginResponses> loginUser(@Body LoginRequest loginRequest);

//    @FormUrlEncoded
//    @POST("userlongin/")
//    Call<LoginResponses> loginUser(@Field("email") String email, @Field("password") String pass);

    @POST("patient/postreg/")
    Call<RegisterResponse> registerUser(@Body RegisterRequest request);




}
