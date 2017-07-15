package com.sp.loylapclover.intermeditatemvp.topmovies;

import com.sp.loylapclover.intermeditatemvp.http.MoreInfoApiService;
import com.sp.loylapclover.intermeditatemvp.http.MovieApiService;
import com.sp.loylapclover.intermeditatemvp.http.apimodel.OmdbApi;
import com.sp.loylapclover.intermeditatemvp.http.apimodel.Result;
import com.sp.loylapclover.intermeditatemvp.http.apimodel.TopRated;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Daniel on 08/07/2017.
 */

public class TopMoviesRepository implements Repository {

    private static final long STALE_MS = 20 * 1000;
    private MovieApiService movieApiService;
    private MoreInfoApiService moreInfoApiService;
    private List<String> countries;
    private List<Result> results;
    private long timestamp;

    public TopMoviesRepository(MovieApiService movieApiService, MoreInfoApiService moreInfoApiService) {
        this.movieApiService = movieApiService;
        this.timestamp = System.currentTimeMillis();
        this.moreInfoApiService = moreInfoApiService;
        this.countries = new ArrayList<>();
        this.results = new ArrayList<>();
    }

    public boolean isUpToDate() {
        return System.currentTimeMillis() - timestamp < STALE_MS;
    }

    @Override
    public Observable<Result> getResultsFromMemory() {
        if (isUpToDate()) {
            return Observable.from(results);
        } else {
            timestamp = System.currentTimeMillis();
            results.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<Result> getResultsFromNetwork() {
        Observable<TopRated> topRatedObservable = movieApiService.getTopRatedMovies(1)
                .concatWith(movieApiService.getTopRatedMovies(2))
                .concatWith(movieApiService.getTopRatedMovies(3));

        return topRatedObservable.concatMap(new Func1<TopRated, Observable<? extends Result>>() {
            @Override
            public Observable<? extends Result> call(TopRated topRated) {
                return Observable.from(topRated.results);
            }
        }).doOnNext(new Action1<Result>() {
            @Override
            public void call(Result result) {
                results.add(result);
            }
        });
    }

    @Override
    public Observable<String> getCountriesFromMemory() {
        if (isUpToDate()) {
            return Observable.from(countries);
        } else {
            timestamp = System.currentTimeMillis();
            countries.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<String> getCountriesFromNetwork() {

        //Returns all countries for all movies from the network
        return getResultsFromNetwork().concatMap(new Func1<Result, Observable<OmdbApi>>() {
            @Override
            public Observable<OmdbApi> call(Result result) {
                return moreInfoApiService.getCountry(result.title);
            }
        }).concatMap(new Func1<OmdbApi, Observable<String>>() {
            @Override
            public Observable<String> call(OmdbApi omdbApi) {
                return Observable.just(omdbApi.getCountry());
            }
        }).doOnNext(new Action1<String>() {
            @Override
            public void call(String s) {
                countries.add(s);
            }
        });
    }

    //Facade methods
    @Override
    public Observable<String> getCountryData() {
        return getCountriesFromMemory().switchIfEmpty(getCountriesFromNetwork());
    }

    @Override
    public Observable<Result> getResultData() {
        return getResultsFromMemory().switchIfEmpty(getResultsFromNetwork());
    }
}
