package tech.thdev.java_udemy_sample.view.main;

import android.annotation.SuppressLint;
import android.log.Log;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import tech.thdev.java_udemy_sample.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private FloatingActionButton fab;
    long ttt = System.currentTimeMillis();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ButterKnife 사용
        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // static Util을 이용하여 replace 처리
        // ActivityUtil
        //         .replaceFragmentToActivity(
        //                 getSupportFragmentManager(),
        //                 MainFragment.getInstance(),
        //                 R.id.frame_layout);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        // http://gaemi.github.io/rxjava/2016/12/02/async-task-with-rxjava.html

        /**
         * RXJava
         *
         * 0. 알림이벤트
         * doOnNext() , doOnComplete(), doOnError() 라는 세 함수는 Observable의 알림 이벤트에 해당합니다.
         *
         * 1. create
         * onNext(), onError(), onCompleted()를 적절히 호출하여야 합니다
         * onComplete를 호출하여 최종적으로 Observable의 데이터 방출 작업은 끝났음을 알립니다.
         * 연산자에서 유의해야 할 사항은 Observable가 데이터를 방출(emit)하기 전에 구독자가 구독취소를 하게 되면 UndeliverableException이 발생
         *
         * 2. just
         * just는 굉장히 심플하지만 조심해서 사용해야 하는 연산자입니다. 데이터를 바로 방출할 때 사용됩니다.
         * onNext나 onCompleted() 등과 같이 별도의 메서드를 호출할 필요가 없고, just()로 데이터만 넘겨주면 옵저버의 역할이 끝이 납니다.
         * 내부적으로 ScalarDisposable를 사용하는데, onNext를 통하여 데이터를 방출하고, onCompleted까지 호출하고 있습니다.
         *
         * 3. defer
         * Observable은 create나 just와 다르게 옵져버가 구독하기전까지는 Observable를 생성하지 않습니다. 즉 defer는 subscribe가 호출될 때 할당
         *
         * 4. fromCallable
         * defer와 마찬가지로 스트림 생성을 지연하는 효과를 가지고 있습니다. 하나의 차이점이 존재하는데 이는 Observable에서 데이터를 방출(emit) 할 때
         * 추가로 Observable을 생성하지 않고 바로 데이터를 스트림으로 전달할 수 있다는 점입니다.
         *
         * Observable, Single, Completable
         * Single(onSuccess(item) 과 onError(throwable)만을 가진다)
         * 비동기작업에 적절함.작업이 종료됨과 동시에 1개의 Item 만을 전파하는 Single.
         *
         * Completable(onCompleted() 와 onError(throwable))
         * 비동기작업에 적절함.발행하는 Item 은 없이 작업의 종료만을 전파하는 Completable
         */
        // RXJava
//        observable00();
        observable01(); // create
        // observable02(); // just
        // observable03(); // defer
        // observable04(); // fromCallable
//        Single01(); // Single
//        Completable01(); // Completable


    }

    void observable00() {
        Integer[] orgs = {10, 5, 0};
        Observable<Integer> source = Observable.fromArray(orgs);
        source
                .map(data -> 1000 / data)
                .doOnNext(data -> Log.d("doOnNext1 " + data))
                .doOnComplete(() -> Log.d("doOnComplete"))
                .doOnError(e -> Log.d("doOnError"))
//                .subscribe(Log::i);
                .subscribe(n -> Log.d("next >" + n), e -> e.printStackTrace());

        System.out.println("-----------------------------------------------------");

        Observable<Integer> source1 = Observable.just(0)
                .doOnSubscribe(s -> Log.d("doOnSubscribe..."));
        source1.subscribe(n -> Log.d("Next >" + n)
                , e -> Log.d("Error" + e)
                , () -> Log.d("Complete"));


    }

    @SuppressLint("CheckResult")
    void observable01() {
        System.out.println("start 01 : " + (System.currentTimeMillis() - ttt));

        Observable.create(
                subscriber -> {
                    try {
                        String[] list = {"1", "3"};
                        subscriber.onNext("Hello");
                        subscriber.onNext("My name is");
                        subscriber.onNext(getHeavyData());
                        subscriber.onNext(delayTime(3000));
                        subscriber.onNext(list[4]);
                        subscriber.onNext("Gaemi");
                        subscriber.onComplete(); // 호출안하면 대기상태, Single, Completable 사용하면 호출필요없음.
                    } catch (Exception e) {
                        e.printStackTrace();
                        subscriber.onError(e);
                    }
                })
                // .map(ss -> ss + "_map")
                .subscribeOn(Schedulers.io())
                .subscribe(s -> {
                    System.out.println("subscribe > " + s);
                }, throwable -> {
                    throwable.printStackTrace();
                });
        System.out.println("end 01 : " + (System.currentTimeMillis() - ttt));

        System.out.println("-----------------------------------------------------");

        Observer<String> observable = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.d("s=" + s);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d("onComplete");
            }
        };
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("Hello world");
                e.onComplete();
            }
        }).subscribe(observable);

        System.out.println("-----------------------------------------------------");

        Iterable<String> samples = Arrays.asList("banana","orange","apple","apple mango","melon","watermelon");
        Observable.fromIterable(samples)
                .skipWhile(s -> !s.contains("apple"))
                .first("Not found")
                .subscribe(s -> Log.d("s="+s));
    }

    @SuppressLint("CheckResult")
    void observable02() {
        Observable.just("just")
                .map(ss -> ss + "-seo")
                .subscribe(ss -> System.out.println(ss));

        System.out.println("-----------------------------------------------------");

        Observable.just("just")
                .map(ss -> ss + "-seo")
                .map(ss -> ss.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(ss -> System.out.println(ss));

        System.out.println("-----------------------------------------------------");

        Observable source = Observable.just(1).doOnSubscribe(s -> System.out.println("doOnSubscribe"));
        source.subscribe(ss -> System.out.println(ss + "subscribe"));
        System.out.println("-----------------------------------------------------");
        Observable.just(2).doOnSubscribe(s -> System.out.println("doOnSubscribe")).subscribe(ss -> System.out.println(ss + "subscribe"));
        System.out.println("-----------------------------------------------------");
        Observable.just(3).subscribe(ss -> System.out.println(ss + "subscribe"));
    }

    @SuppressLint("CheckResult")
    void observable03() {
        System.out.println("start 03 : " + (System.currentTimeMillis() - ttt));
        Observable.defer(() -> Observable.just(delayTime(3000)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ss -> System.out.println(ss + " Completed!!"));
        System.out.println("end 03 : " + (System.currentTimeMillis() - ttt));
    }

    @SuppressLint("CheckResult")
    void observable04() {
        System.out.println("start 04 : " + (System.currentTimeMillis() - ttt));
        Observable.fromCallable(() -> delayTime(5000))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ss -> System.out.println(ss + ".. Completed!!"));
        System.out.println("end 04 : " + (System.currentTimeMillis() - ttt));
    }

    @SuppressLint("CheckResult")
    void Single01() {
        System.out.println("start Single01 : " + (System.currentTimeMillis() - ttt));
        Single.fromCallable(() -> delayTime(5000))
                .subscribeOn(Schedulers.io())
                .subscribe(str -> {
                    System.out.println("str=" + str + " Single Completed!!");
                }, it -> {
                    it.printStackTrace();
                });
        System.out.println("end Single01 : " + (System.currentTimeMillis() - ttt));

//        Single
//                .do(() ->delayTime(5000))
//                .subscribeOn(Schedulers.io())
//                .subscribe();
    }

    void Completable01() {
        System.out.println("start Completable01 : " + (System.currentTimeMillis() - ttt));
        Completable.fromCallable(() -> getHeavyData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    System.out.println((System.currentTimeMillis() - ttt) + " Completable01 Completed!!");
                }, throwable -> {
                    throwable.printStackTrace();
                });
        System.out.println("end Completable01 : " + (System.currentTimeMillis() - ttt));
    }

    private String getHeavyData() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Just Item";
    }

    String delayTime(int delayTime) {
        long saveTime = System.currentTimeMillis();
        long currTime = 0;
        while (currTime - saveTime < delayTime) {
            currTime = System.currentTimeMillis();
        }
        return "delayTime=" + delayTime;
    }
}
