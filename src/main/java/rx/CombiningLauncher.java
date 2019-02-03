package rx;


import io.reactivex.Observable;
import org.springframework.boot.CommandLineRunner;

import java.util.concurrent.TimeUnit;

public class CombiningLauncher {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("hello","world");
        source.flatMap(s -> Observable.fromArray(s.split("")),(s, s2) -> s + ":" + s2).subscribe(System.out::println);
        Runner runner = new Runner();
        runner.run();
    }
}
class Runner implements CommandLineRunner{

    @Override
    public void run(String... strings) {
        Observable<String> sourceA = Observable.interval(1, TimeUnit.SECONDS)
        .take(2)
        .map(l -> l + 1)
        .map(l -> "SourceA: " + l + " seconds");

        Observable<String> sourceB = Observable.interval(250, TimeUnit.MILLISECONDS).take(10).map(aLong -> (aLong + 1) *250 )
                .map(aLong -> "sourceB:" + aLong + " milliseconds");

        Observable.concat(sourceA,sourceB).subscribe(s -> System.out.println("TOOK:" + s));
        sleep(6000);


    }
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}