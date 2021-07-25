package com.example.guardian_assignment.requests;

import com.example.guardian_assignment.utils.Constants;
import com.example.guardian_assignment.utils.LiveAdapterCallFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.guardian_assignment.utils.Constants.CONNECTION_TIMEOUT;
import static com.example.guardian_assignment.utils.Constants.READ_TIMEOUT;
import static com.example.guardian_assignment.utils.Constants.WRITE_TIMEOUT;

public class ServiceGenerator {

    private static OkHttpClient client = new OkHttpClient.Builder()

            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .build();



    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(new LiveAdapterCallFactory())
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();
    private static GuardianApi guardianApi = retrofit.create(GuardianApi.class);
    public static GuardianApi getGuardianApi(){
        return guardianApi;
    }

}
