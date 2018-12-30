package rx;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

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
        Observable.range(1,120).skip(100).subscribe(s -> System.out.println("Baris: " + s));

        //takeWhile

        Disposable strings = Observable.range(1,100)
                .takeWhile(i -> i < 5)
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        strings.dispose();

    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
