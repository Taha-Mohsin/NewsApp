package com.example.guardian_assignment.requests;

import androidx.lifecycle.LiveData;

import com.example.guardian_assignment.requests.responses.ApiResponse;
import com.example.guardian_assignment.requests.responses.NewsListResponse;
import com.example.guardian_assignment.requests.responses.Root;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface GuardianApi {
    //URL:  https://content.guardianapis.com/search?q=Olympics&api-key=62dd7008-ae2b-439c-8889-3f1bab9848aa

    @GET("search")
    LiveData<ApiResponse<Root>> searchNews(
            @Query("show-fields") String field,
            @Query("order-by") String sort,
            @Query("page") int page,
            @Query("q") String val,
            @Query("api-key") String key
            );

    // Single Api call as data required for 2nd Page will already be persisted.
}
