package com.example.guardian_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.example.guardian_assignment.adapters.ArticleRecyclerView;
import com.example.guardian_assignment.adapters.onArticleListener;
import com.example.guardian_assignment.models.Article;
import com.example.guardian_assignment.requests.GuardianApi;
import com.example.guardian_assignment.requests.ServiceGenerator;
import com.example.guardian_assignment.requests.responses.Root;
import com.example.guardian_assignment.utils.Constants;
import com.example.guardian_assignment.utils.Resource;
import com.example.guardian_assignment.utils.Testing;
import com.example.guardian_assignment.viewmodels.NewsListViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsListActivity extends AppCompatActivity implements onArticleListener {

    private NewsListViewModel newsListViewModel;
    private RecyclerView recyclerView;
    private ArticleRecyclerView articleRecyclerAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsListViewModel = new ViewModelProvider(this).get(NewsListViewModel.class);
        recyclerView = findViewById(R.id.news_list);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);

        initRecyclerView();         // for articles list
        searchArticlesApi(1);   // starts with loading first page
        subscribeObservers();       // Observe LiveData from ViewModel

        // Used for Pull to Refresh
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initRecyclerView();
                searchArticlesApi(1);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }
    private RequestManager initGlide(){
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.white_background)
                .error(R.drawable.white_background);
        return Glide.with(this)
                .setDefaultRequestOptions(options);
    }

    private void subscribeObservers(){
        newsListViewModel.getArticles().observe(this, new Observer<Resource<List<Article>>>() {
            @Override
            public void onChanged(Resource<List<Article>> listResource) {
                if(listResource != null){
                    Log.v("Status",listResource.status.toString());
                    if(listResource.status == Resource.Status.ERROR)
                        displayToast();
                    if(listResource.data != null)
                    {
                        //Testing.print(listResource.data,"data");
                        articleRecyclerAdapter.setmArticles(listResource.data);
                    }
                }
            }
        });
    }

    private void displayToast() {
        Toast.makeText(this, "Please Connect to Internet",
                Toast.LENGTH_LONG).show();
    }

    public void searchArticlesApi(int page){
        recyclerView.smoothScrollToPosition(0);         // scroll to top of page
        newsListViewModel.searchArticlesApi(page);
    }

    private void initRecyclerView(){
        ViewPreloadSizeProvider<String> viewPreloader = new ViewPreloadSizeProvider<>();
        articleRecyclerAdapter = new ArticleRecyclerView(this, initGlide(), viewPreloader);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Used to load images in advance
        RecyclerViewPreloader<String> preloader = new RecyclerViewPreloader<String>(
                Glide.with(this),
                articleRecyclerAdapter,
                viewPreloader,
                30);
        recyclerView.addOnScrollListener(preloader);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(!recyclerView.canScrollVertically(1)){
                    Log.v("Down", String.valueOf(newState));
                    if (newState != RecyclerView.SCROLL_STATE_IDLE) {
                        return;
                    }
                    newsListViewModel.searchNextPage();     // once we reach end of list
                }
            }
        });
        recyclerView.setAdapter(articleRecyclerAdapter);
    }

    // Sending data to NewsDetails Page when article is clicked
    @Override
    public void onArticleClick(int position) {
        Log.v("Click", String.valueOf(position));
        Intent intent = new Intent(this,NewsDetailsActivity.class);
        intent.putExtra("apiUrl",articleRecyclerAdapter.getSelectedArticle(position));
        startActivity(intent);
    }
}