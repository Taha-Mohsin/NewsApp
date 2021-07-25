package com.example.guardian_assignment.persistence;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.guardian_assignment.models.Article;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ArticleDao {

    @Insert(onConflict = REPLACE)
    long[] insert(Article... articles);

    // order by data to latest in cache
    @Query("SELECT DISTINCT * FROM article ORDER BY webPubDate DESC")
    LiveData<List<Article>> getArticles();

    @Query("DELETE FROM article")
    void deleteAll();
}
