package com.cash_hand_see_client.http.util;


import androidx.annotation.NonNull;

import org.reactivestreams.Publisher;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableTransformer;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxScheduler {

    public static <T> FlowableTransformer<T,T> flowableTransformer(){
        return new FlowableTransformer<T, T>() {
            @Override
            public @NonNull
            Publisher<T> apply(@NonNull Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }



    public static <T> ObservableTransformer<T,T> observableTransformer(){
        return new ObservableTransformer<T, T>() {
            @Override
            public @NonNull
            ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}


