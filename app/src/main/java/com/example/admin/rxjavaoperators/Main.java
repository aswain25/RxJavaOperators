package com.example.admin.rxjavaoperators;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class Main {
    public static void main(String[] args)
    {
        Observable<Integer> intergerObservable1 = Observable.just(1, 2, 3, 4, 5, 6);
        Observable<Integer> intergerObservable2 = Observable.range(0, 10);
        Observable<Integer> intergerObservable3 = Observable.fromArray(new Integer[]{1, 3, 3, 3, 5});

        Observer<Integer> Observer1 = new TestObservable();
        Observer<Integer> Observer2 = new TestObservable();
        Observer<Integer> Observer3 = new TestObservable();

        intergerObservable1.map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Exception {
                return integer * integer;
            }
        }).takeLast(3).startWith(4).delay(3, TimeUnit.SECONDS).subscribe(Observer1);
        intergerObservable2.window(8).take(5).mergeWith(new ObservableSource<Observable<Integer>>() {
            @Override
            public void subscribe(Observer<? super Observable<Integer>> observer) {

            }
        }).timestamp().subscribe();
        intergerObservable3.distinct().skip(1).subscribe(Observer3);


    }
    public static class TestObservable implements Observer<Integer>
    {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Integer integer) {
            System.out.println(integer);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
