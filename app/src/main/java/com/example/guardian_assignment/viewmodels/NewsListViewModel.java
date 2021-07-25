package com.example.guardian_assignment.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.example.guardian_assignment.models.Article;
import com.example.guardian_assignment.repositories.ArticleRepository;
import com.example.guardian_assignment.utils.Resource;
import java.util.List;


public class NewsListViewModel extends AndroidViewModel {

    private static final String TAG = "ArticleListViewModel";
    public static final String QUERY_EXHAUSTED = "No more results.";

    // Used to react to onChange events
    private MediatorLiveData<Resource<List<Article>>> articles = new MediatorLiveData<>();
    private ArticleRepository articleRepository;

    private boolean isQueryExhausted;
    private boolean isPerformingQuery;
    private int pageNumber;


    public NewsListViewModel(@NonNull Application application){
        super(application);
        articleRepository = ArticleRepository.getInstance(application);
    }

    public LiveData<Resource<List<Article>>> getArticles(){
        return articles;
    }

    public void searchArticlesApi(int pageNumber) {
        if (!isPerformingQuery) {
            if (pageNumber == 0) {
                pageNumber = 1;
            }
            this.pageNumber = pageNumber;
            isQueryExhausted = false;
            executeSearch();
        }
    }
    public void searchNextPage(){
        //Search only if query is not active
        if(!isQueryExhausted && !isPerformingQuery){
            pageNumber++;
            executeSearch();
        }
    }


    private void executeSearch(){
        isPerformingQuery = true;
        final LiveData<Resource<List<Article>>> repositorySource = articleRepository.searchArticlesApi(pageNumber);
        articles.addSource(repositorySource, new Observer<Resource<List<Article>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<Article>> listResource) {
                    if(listResource != null){
                        if(listResource.status == Resource.Status.SUCCESS){
                            Log.d(TAG, "onChanged: page number: " + pageNumber);
                            Log.d(TAG, "onChanged: " + listResource.data);

                            isPerformingQuery = false;
                            if(listResource.data != null){
                                if(listResource.data.size() == 0 ){
                                    Log.d(TAG, "onChanged: query is exhausted...");
                                    articles.setValue(
                                            new Resource<List<Article>>(
                                                    Resource.Status.ERROR,
                                                    listResource.data,
                                                    QUERY_EXHAUSTED
                                            )
                                    );
                                    isQueryExhausted = true;
                                }
                            }
                            articles.removeSource(repositorySource);
                        }
                        else if(listResource.status == Resource.Status.ERROR){
                            isPerformingQuery = false;
                            if(listResource.message.equals(QUERY_EXHAUSTED)){
                                isQueryExhausted = true;
                            }
                            articles.removeSource(repositorySource);
                        }
                        articles.setValue(listResource);
                    }
                    else{
                        articles.removeSource(repositorySource);
                    }
                }
        });
    }
}