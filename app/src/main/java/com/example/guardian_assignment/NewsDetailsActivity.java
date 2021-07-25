package com.example.guardian_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.guardian_assignment.models.Article;
import org.jsoup.Jsoup;

public class NewsDetailsActivity extends AppCompatActivity {

    private ImageView thumbnail;
    private TextView webTitle;
    private TextView webPubdate;
    private TextView webBody;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        thumbnail= findViewById(R.id.thumbnail2);
        webBody = findViewById(R.id.web_body2);
        webTitle = findViewById(R.id.web_title2);
        webPubdate = findViewById(R.id.web_pub_date2);

        getDataFromIntent();
    }

    // Get data from NewsList activity
    private void getDataFromIntent() {
        if(getIntent().hasExtra("apiUrl")){
            Article article = getIntent().getParcelableExtra("apiUrl");
            Log.v("incomingUrl",article.getApiUrl());
            load(article);
        }
    }

    private void load(Article article) {
        // load thumbnail
        Glide.with(this).load(article.getFields().getThumbnail()).into(thumbnail);

        webTitle.setText(article.getWebTitle());
        webPubdate.setText(article.getWebPublicationDate().substring(0,10));
        String normailString = Jsoup.parse(article.getFields().getBody()).text();
        webBody.setText(normailString);
        webBody.setMovementMethod(new ScrollingMovementMethod());

        TextView txt= (TextView) findViewById(R.id.link);
        txt.setText(article.getWebUrl());
        txt.setMovementMethod(LinkMovementMethod.getInstance());

        // Open browser onClick
        txt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(article.getWebUrl()));
                startActivity(browserIntent);
            }
        });
    }
}