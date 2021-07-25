package com.example.guardian_assignment.repositories;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.guardian_assignment.AppExecutors;
import com.example.guardian_assignment.models.Article;
import com.example.guardian_assignment.persistence.ArticleDao;
import com.example.guardian_assignment.persistence.ArticleDatabase;
import com.example.guardian_assignment.requests.ServiceGenerator;
import com.example.guardian_assignment.requests.responses.ApiResponse;
import com.example.guardian_assignment.requests.responses.Root;
import com.example.guardian_assignment.utils.Constants;
import com.example.guardian_assignment.utils.NetworkBoundResource;
import com.example.guardian_assignment.utils.Resource;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArticleRepository {

    private static ArticleRepository instance;
    private ArticleDao articleDao;

    // Singleton
    public static ArticleRepository getInstance(Context context){
        if(instance == null)
            instance = new ArticleRepository(context);
        return instance;
    }

    private ArticleRepository (Context context)
    {
        articleDao = ArticleDatabase.getInstance(context).getArticleDao();
    }

    // Single Source of Truth
    public LiveData<Resource<List<Article>>> searchArticlesApi(int pageNumber){
        return new NetworkBoundResource<List<Article>,Root>(AppExecutors.getInstance()){

            @Override
            protected void saveCallResult(@NonNull @NotNull Root item) {
                Log.v("DB","Data inserted");
                // 4) if New data available insert it into DB
                if(item.getResponse().getResults() != null){
                    Article [] articles = new Article[item.getResponse().getResults().size()];
                    articleDao.insert((Article[])item.getResponse().getResults().toArray(articles));
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Article> data) {
                Log.v("DB","Should Fetch");
                // 2) Data to be fetched
                return true;
            }

            @NonNull
            @NotNull
            @Override
            protected LiveData<List<Article>> loadFromDb() {
                Log.v("DB","Data load from DB");
                // 1) Load data from DB at first
                // 5) Load data from DB once inserted
                return articleDao.getArticles();
            }

            @NonNull
            @NotNull
            @Override
            protected LiveData<ApiResponse<Root>> createCall() {
                Log.v("DB","APIcall");
                // 3) API call if new data available
                return ServiceGenerator.getGuardianApi()
                        .searchNews(Constants.Fields,
                                Constants.Order_By,pageNumber, Constants.Home_Page, Constants.API_KEY);
            }
        }.getAsLiveData();
    }


}
