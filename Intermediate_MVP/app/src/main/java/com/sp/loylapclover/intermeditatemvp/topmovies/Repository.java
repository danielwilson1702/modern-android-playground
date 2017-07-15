package com.sp.loylapclover.intermeditatemvp.topmovies;

import com.sp.loylapclover.intermeditatemvp.http.apimodel.Result;

import rx.Observable;

/**
 * Created by Daniel on 08/07/2017.
 */

public interface Repository {

    Observable<Result> getResultsFromMemory();

    Observable<Result> getResultsFromNetwork();

    Observable<String> getCountriesFromMemory();

    Observable<String> getCountriesFromNetwork();

    Observable<String> getCountryData();

    Observable<Result> getResultData();
}
