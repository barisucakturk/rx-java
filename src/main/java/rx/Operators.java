package rx;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Operators {

    public static void main(String[] args) {
        //take
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon").take(3).subscribe(s -> System.out.println(s));
        sleep(2000);

        Observable<Long> observable = Observable.interval(300, TimeUnit.MILLISECONDS);
        observable.take(4).subscribe(i -> System.out.println("Received: " + i));

        sleep(2000);
        //skip
        Observable.range(1, 120).skip(100).subscribe(s -> System.out.println("Baris: " + s));

        //takeWhile

        Disposable strings = Observable.range(1, 100)
                .takeWhile(i -> i < 5)
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        strings.dispose();

        //distinct

        Observable.just("Ozgur", "Baris", "Deniz", "Ayse", "Fatma").map(String::length).distinct().count().subscribe(c -> System.out.println(c));
        Observable.just("Ozgur", "Baris", "Deniz", "Ayse", "Fatma").map(String::length).distinct().subscribe(s -> System.out.println("Distinct count : " + s));

        Observable.just("Ozgur", "Baris", "Deniz", "Ayse", "Fatma", "Joe")
                .map(String::length)
                .distinctUntilChanged()
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        //elementAt
        Observable.just("Alpha", "Beta", "Zeta", "Eta", "Gamma",
                "Delta")
                .elementAt(4)
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        //cast
        Observable<Object> items =
                Observable.just("Alpha", "Beta", "Gamma").cast(Object.class);


        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon").filter(s -> s.startsWith("Z"))
                .defaultIfEmpty("None")
                .subscribe(System.out::println);
        //sorted
        Observable.just(1, 2, 4, 6, 7, 87, 88, 6).sorted(Comparator.reverseOrder()).subscribe(s -> System.out.println("Received: " + s));

        //scan
        Observable.just(5, 3, 7, 10, 2, 14)
                .scan((accumulator, next) -> accumulator * next)
                .subscribe(s -> System.out.println("Received: " + s));

        //reduce
        Observable.just(5, 5, 4, 2, 7, 8, 8, 9).reduce("", (total, next) -> total + (total.equals("") ? "" : ":") + next).subscribe(s -> System.out.println(s));

        //all
        Observable.just(5, 3, 7, 11, 2, 14)
                .all(i -> i < 10)
                .subscribe(s -> System.out.println("Received: " + s));

        //toMap
        Observable.just("Alpha", "Beta", "Gamma", "Delta",
                "Epsilon")
                .toMap(s -> s.charAt(0), String::length)
                .subscribe(s -> System.out.println("Received: " + s));

        //toMultiMap
        Observable.just("xy", "yz","tz","tyz","z").toMultimap(String::length).subscribe(s -> System.out.println(s));

    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
