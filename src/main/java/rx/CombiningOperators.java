package rx;

import io.reactivex.Observable;

public class CombiningOperators {

    public static void main(String[] args) {

        //merge
        Observable<String> source1 =
                Observable.just("Alpha", "Beta");
        Observable<String> source2 =
                Observable.just("Gamma", "Delta");
        Observable<String> source3 =
                Observable.just("Epsilon", "Zeta");
        Observable<String> source4 =
                Observable.just("Eta", "Theta");
        Observable<String> source5 =
                Observable.just("Iota", "Kappa");

        Observable.mergeArray(source1, source2, source3, source4,
                source5).subscribe(i -> System.out.println(i));

        //flatMap
        Observable<String> source =
                Observable.just("521934/2342/FOXTROT", "21962/12112/78886/TANGO",
                        "283242/4542/WHISKEY/2348562");

        source.flatMap(s -> Observable.fromArray(s.split("/"))).filter(s -> s.matches("[0-9]+")).map(Integer::valueOf).subscribe(System.out::println);

    }


}
