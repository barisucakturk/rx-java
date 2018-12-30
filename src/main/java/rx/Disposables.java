package rx;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class Disposables {

    private static CompositeDisposable compositeDisposable = new CompositeDisposable();

    public static void main(String [] args){

        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);


        Disposable disposable = seconds.subscribe(s -> System.out.println("o1: "+ s));
        Disposable disposable2 = seconds.subscribe(s -> System.out.println("o2: "+ s));

        compositeDisposable.addAll(disposable,disposable2);

        sleep(5000);

        compositeDisposable.dispose();
        System.out.println("disposed");
        sleep(5000);
    }


    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
