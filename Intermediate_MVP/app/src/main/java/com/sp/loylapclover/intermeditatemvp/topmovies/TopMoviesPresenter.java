package com.sp.loylapclover.intermeditatemvp.topmovies;

import rx.Observer;
import rx.Subscription;
import rx.schedulers.Schedulers;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

/**
 * Created by Daniel on 08/07/2017.
 */

public class TopMoviesPresenter implements TopMoviesActivityMVP.Presenter {

    private TopMoviesActivityMVP.View view;
    private Subscription subscription = null;
    private TopMoviesActivityMVP.Model model;

    public TopMoviesPresenter(TopMoviesActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void loadData() {
        subscription = model.result().subscribeOn(Schedulers.io()).observeOn(mainThread())
                .subscribe(new Observer<ViewModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (view != null) {
                            view.showSnackbar("Error getting movies");
                        }
                    }

                    @Override
                    public void onNext(ViewModel viewModel) {
                        if (view != null) {
                            view.updateData(viewModel);
                        }
                    }
                });
    }

    @Override
    public void rxUnsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void setView(TopMoviesActivityMVP.View view) {
        this.view = view;
    }
}
