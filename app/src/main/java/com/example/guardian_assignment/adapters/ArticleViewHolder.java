package com.example.guardian_assignment.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.example.guardian_assignment.R;
import com.example.guardian_assignment.models.Article;
import org.jsoup.Jsoup;

public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView webTitle, webBody, webPubDate;
    ImageView thumbnail;
    RequestManager requestManager;
    ViewPreloadSizeProvider viewPreloadSizeProvider;

    onArticleListener onArticleListener;


    public ArticleViewHolder(@NonNull View itemView,
                             onArticleListener onArticleListener,
                             RequestManager requestManager,
                             ViewPreloadSizeProvider preloadSizeProvider) {
        super(itemView);

        this.onArticleListener = onArticleListener;
        this.requestManager = requestManager;
        this.viewPreloadSizeProvider = preloadSizeProvider;

        webTitle = itemView.findViewById(R.id.web_title);
        webBody = itemView.findViewById(R.id.web_body);
        webPubDate = itemView.findViewById(R.id.web_pub_date);

        thumbnail = itemView.findViewById(R.id.thumbnail);
        itemView.setOnClickListener(this);
    }

    public void onBind(Article article){
        requestManager
                .load(article.getFields().getThumbnail())
                .into(thumbnail);

        webTitle.setText(article.getWebTitle());
        String normalString = Jsoup.parse(article.getFields().getBody()).text();
        webBody.setText(normalString);
        webPubDate.setText(article.getWebPublicationDate().substring(0,10));

        viewPreloadSizeProvider.setView(thumbnail);
    }

    @Override
    public void onClick(View view) {
        onArticleListener.onArticleClick(getAdapterPosition());
    }
}
