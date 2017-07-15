package com.sp.loylapclover.intermeditatemvp.http;

import com.sp.loylapclover.intermeditatemvp.http.apimodel.OmdbApi;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Daniel on 08/07/2017.
 */

public interface MoreInfoApiService {
    @GET("/")
    Observable<OmdbApi> getCountry(@Query("t") String title);
}
