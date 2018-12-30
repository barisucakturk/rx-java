package rx;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.observables.ConnectableObservable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Application {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");


        Observer<Integer> myObserver = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("h:" + integer);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
                System.out.println("Finished");

            }
        };
        Observable<String> observable = Observable.just("Alpha", "Beta", "Gamma", "Delta",
                "Epsilon");
        observable.map(String::length).filter(integer -> integer > 4)
                .subscribe(myObserver);

        Consumer<Integer> consumer = integer -> System.out.println(integer);

        Action action = () -> System.out.println("done!");


        Observable<String> source =
                Observable.just("Alpha", "Beta", "Gamma", "Delta",
                        "Epsilon");
        source.map(String::length).filter(i -> i >= 5)
                .subscribe(i -> System.out.println("RECEIVED: " + i),
                        Throwable::printStackTrace,
                        () -> System.out.println("Done!"));


        ConnectableObservable<String> connectableObservable = Observable.just("Alpha", "Beta", "Gamma", "Delta",
                "Epsilon").publish();

        connectableObservable.subscribe(s -> System.out.println("o1:" + s));
        connectableObservable.map(String::length).filter(s -> s > 4).subscribe(s -> System.out.println("o2:" + s),Throwable::printStackTrace);

        connectableObservable.connect();
    }
}
