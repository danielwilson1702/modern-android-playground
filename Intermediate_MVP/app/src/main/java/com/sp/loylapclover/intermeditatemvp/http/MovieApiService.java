package com.sp.loylapclover.intermeditatemvp.http;

import com.sp.loylapclover.intermeditatemvp.http.apimodel.TopRated;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Daniel on 08/07/2017.
 */

public interface MovieApiService {
    @GET("top_rated")
    Observable<TopRated> getTopRatedMovies(@Query("page") Integer page);
}
