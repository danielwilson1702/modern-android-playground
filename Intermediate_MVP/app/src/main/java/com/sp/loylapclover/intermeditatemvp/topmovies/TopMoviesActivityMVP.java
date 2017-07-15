package com.sp.loylapclover.intermeditatemvp.topmovies;

import rx.Observable;

/**
 * Created by Daniel on 08/07/2017.
 */

public interface TopMoviesActivityMVP {
    interface View {

        void updateData(ViewModel viewModel);

        void showSnackbar(String s);
    }

    interface Presenter {

        void loadData();

        void rxUnsubscribe();

        void setView(TopMoviesActivityMVP.View view);
    }

    interface Model {

        Observable<ViewModel> result();
    }
}
