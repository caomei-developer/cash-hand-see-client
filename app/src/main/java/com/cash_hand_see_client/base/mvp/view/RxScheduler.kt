package com.cash_hand_see_client.base.mvp.view

import com.cash_hand_see_client.http.bean.Response
import com.cash_hand_see_client.http.exception.ApiException
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers

class RxScheduler {


    fun <T> observableTransformer(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> flowableTransformer(): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }


    /**
     *compose判断结果,统一返回结果处理,支持背压
     * @param <T>
     * @return
    </T> */
    fun <T> handleObservableResult(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.flatMap(Function<T, Observable<T>> { bean ->
                val response = bean as Response<T>
                if (response.code == 0 && bean.list != null) {//{"code":200,"msg":"成功!","data":{"appId":"com.chat.peakchao","appkey":"00d91e8e0cca2b76f515926a36db68f5"}}
                    createObservable(
                        bean
                    )
                } else {
                    Observable.error(
                        ApiException(
                            response.code,
                            response.message
                        )
                    )
                }
            }).observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     *compose判断结果,统一返回结果处理,支持背压
     * @param <T>
     * @return
    </T> */
    fun <T> handleFlowableResult(): FlowableTransformer<T, T> {
        return FlowableTransformer { flowable ->
            flowable.flatMap(Function<T, Flowable<T>> { bean ->
                val response = bean as Response<T>
                if (response.code == 200) {
                    createFlowable(
                        bean
                    )
                } else {
                    Flowable.error(
                        ApiException(
                            response.code,
                            response.message
                        )
                    )
                }
            }).observeOn(AndroidSchedulers.mainThread())
        }
    }


    private fun <T> createObservable(t: T): Observable<T> {
        return Observable.create { emitter ->
            try {
                emitter.onNext(t)
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    /**
     * 生成Flowable,支持背压
     * @param <
     * @return
    </T> */
    private fun <T> createFlowable(t: T): Flowable<T> {
        return Flowable.create({ emitter ->
            try {
                emitter.onNext(t)
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }, BackpressureStrategy.BUFFER)
    }
}