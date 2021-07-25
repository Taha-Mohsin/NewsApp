package com.example.guardian_assignment.adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.example.guardian_assignment.R;
import com.example.guardian_assignment.models.Article;

import org.jetbrains.annotations.NotNull;
import java.util.Collections;
import java.util.List;

public class ArticleRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements
        ListPreloader.PreloadModelProvider<String> {

    private onArticleListener onArticleListener;
    private RequestManager requestManager;
    private ViewPreloadSizeProvider<String> preloadSizeProvider;

    public void setmArticles(List<Article> mArticles) {
        this.mArticles = mArticles;
        notifyDataSetChanged();
    }

    private List<Article> mArticles;

    public ArticleRecyclerView(onArticleListener onArticleListener,
                               RequestManager requestManager,
                               ViewPreloadSizeProvider<String> viewPreloadSizeProvider) {
        this.onArticleListener = onArticleListener;
        this.requestManager = requestManager;
        this.preloadSizeProvider = viewPreloadSizeProvider;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item,parent,false);
        return new ArticleViewHolder(view,onArticleListener,requestManager,preloadSizeProvider);
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder holder, int position) {
        ((ArticleViewHolder) holder).onBind(mArticles.get(position));
    }

    @Override
    public int getItemCount() {
        if(mArticles!=null)
            return mArticles.size();
        else
            return 0;
    }

    public Article getSelectedArticle(int position){
        if(mArticles != null)
        {
            if(mArticles.size()>0)
                return mArticles.get(position);
        }
        return null;
    }


    @NonNull
    @NotNull
    @Override
    public List<String> getPreloadItems(int position) {
        // handling IndexOutOfBounds
        if(position < mArticles.size()){
            String url = mArticles.get(position).getFields().getThumbnail();
            if(TextUtils.isEmpty(url)){
                return Collections.emptyList();
            }
            return Collections.singletonList(url);
        }
        return Collections.emptyList();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public RequestBuilder<?> getPreloadRequestBuilder(@NonNull @NotNull String item) {
        return requestManager.load(item);
    }
}
