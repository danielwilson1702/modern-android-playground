package com.sp.loylapclover.intermeditatemvp.topmovies;

import com.sp.loylapclover.intermeditatemvp.http.MoreInfoApiService;
import com.sp.loylapclover.intermeditatemvp.http.MovieApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Daniel on 08/07/2017.
 */

@Module
public class TopMoviesModule {

    @Provides
    public TopMoviesActivityMVP.Presenter provideTopMoviesActivityPresennter(TopMoviesActivityMVP.Model topMoviesModel) {
        return new TopMoviesPresenter(topMoviesModel);
    }

    @Provides
    public TopMoviesActivityMVP.Model provideTopMoviesAcitivtyModel(Repository repository) {
        return new TopMoviesModel(repository);
    }

    @Singleton
    @Provides
    public Repository provideRepo(MovieApiService movieApiService, MoreInfoApiService moreInfoApiService) {
        return new TopMoviesRepository(movieApiService, moreInfoApiService);
    }
}
