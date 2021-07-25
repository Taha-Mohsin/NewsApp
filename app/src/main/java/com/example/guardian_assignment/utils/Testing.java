package com.example.guardian_assignment.utils;

import android.util.Log;

import com.example.guardian_assignment.models.Article;

import java.util.List;

public class Testing {

    public static void print(List<Article>list, String tag){
        for(Article article: list){
            Log.d(tag, "onChanged: " + article.getWebTitle());
        }
    }
}

// For logging