
/** Dummy class. Was used for getting remote data prior to Data persistence*/

//package com.example.guardian_assignment.requests;
//
//import android.util.Log;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//
//import com.example.guardian_assignment.AppExecutors;
//import com.example.guardian_assignment.models.Article;
//import com.example.guardian_assignment.requests.responses.NewsListResponse;
//import com.example.guardian_assignment.requests.responses.Root;
//import com.example.guardian_assignment.utils.Constants;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;
//
//import retrofit2.Call;
//import retrofit2.Response;
//
//public class GuardianApiClient {
//
//    private MutableLiveData<List<Article>> mArticles;
//    private static GuardianApiClient instance;
//    private RetrieveArticlesRunnable retrieveArticlesRunnable;
//
//
//    public static GuardianApiClient getInstance(){
//        if(instance == null)
//            instance = new GuardianApiClient();
//        return instance;
//    }
//
//    private GuardianApiClient(){
//        mArticles = new MutableLiveData<>();
//    }
//
//
//    public  LiveData<List<Article>> getArticles() {
//        return mArticles;
//    }
//
//    public void searchArticlesApi(int page) {
//        if(retrieveArticlesRunnable != null)
//            retrieveArticlesRunnable = null;
//        Log.v("flow3","api call");
//
//
//        retrieveArticlesRunnable = new RetrieveArticlesRunnable(page);
//
//        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveArticlesRunnable);
//
//        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
//            @Override
//            public void run() {
//                myHandler.cancel(true);
//            }
//        }, Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS);
//    }
//
//    private class RetrieveArticlesRunnable implements Runnable {
//        boolean cancelRequest;
//        private int page;
//
//        public RetrieveArticlesRunnable(int page){
//            this.page = page;
//            cancelRequest = false;
//        }
//
//        @Override
//        public void run() {
//            try {
//                Log.v("flow4","run call");
//                Response response = getArticles(page).execute();
//
//                if(cancelRequest){
//                    return;
//                }
//                if(response.code() == 200){
//                    Log.v("data","call");
//                    List<Article> list = new ArrayList<>(((Root)response.body()).getResponse().getResults());
//                    mArticles.postValue(list);
//                    Log.v("inhere", String.valueOf(page));
//
//                    // INSERT into DB from here
//                    // repository.insert(response.body());
//                }
//                else
//                {
//                    String error = response.errorBody().toString();
//                    Log.v("Error",error);
//                    mArticles.postValue(null);
//                }
//            } catch (IOException e) {
//                Log.v("Catch","Caughthere");
//                e.printStackTrace();
//            }
//        }
//
//
////        private Call<Root> getArticles(int page) {
////            Log.v("flow5","final call");
////            return ServiceGenerator.getGuardianApi()
////                    .searchNews(Constants.Fields, Constants.Order_By,page, Constants.Home_Page, Constants.API_KEY);
////        }
//        private void cancelRequest(){
//            Log.v("Cancel","RequestCancelled");
//            cancelRequest = true;
//        }
//    }
//}
//
