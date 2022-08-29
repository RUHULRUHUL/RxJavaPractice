import io.reactivex.rxjava3.core.Emitter;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import javafx.animation.PauseTransition;

import javax.jws.Oneway;
import java.util.ArrayList;
import java.util.List;

public class RxTest {
    public static void main(String[] args) {
        // ObservableCreateJust();
        // ObsevableWithITarator();
        //ObserableCreateEmiter();
        // couldObservable();
        // ConnectableHotObservable();
        throExeption();
        throExeptionUsingCallable();


    }

    private static void throExeptionUsingCallable() {
        Observable observable = Observable.error(new Exception("Error"));
        observable.subscribe(System.out::println, error -> System.out.println("error1 " + error.hashCode()));
        observable.subscribe(System.out::println, error -> System.out.println("error2 " + error.hashCode()));

    }

    private static void throExeption() {
        Observable observable = Observable.error(() -> new Exception("Error"));
        observable.subscribe(System.out::println, error -> System.out.println("error1 " + error.hashCode()));
        observable.subscribe(System.out::println, error -> System.out.println("error2 " + error.hashCode()));
    }

    private static void ConnectableHotObservable() {
        ConnectableObservable<Integer> observable = Observable.just(1, 2, 3, 4, 5).publish();
        observable.subscribe(item -> System.out.println("hot Observable 1 : " + item));
        observable.connect();
        observable.subscribe(item1 -> System.out.println("hot Observable 2 : " + item1));

    }

    private static void couldObservable() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        observable.subscribe(item -> System.out.println(item));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        observable.subscribe(item -> System.out.println(item));


    }

    private static void ObserableCreateEmiter() {
        Observable<Integer> observable = Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onNext(4);
            emitter.onNext(5);
            emitter.onComplete();
        });

        observable.subscribe(
                System.out::println,
                error -> System.out.println(error.getMessage()),
                () -> System.out.println("The Task Has Been  Completed")
        );
    }

    private static void ObsevableWithITarator() {

        List<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        arrayList.add("d");
        arrayList.add("e");
        Observable<String> iteratorObservable = Observable.fromIterable(arrayList);
        Observable<Integer> iteratorObservable1 = Observable.fromArray(1, 2, 3, 4, 5);
        iteratorObservable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                System.out.println(s);
            }
        });

    }

    private static void ObservableCreateJust() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);

        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println("Emitter" + integer);
            }
        });

    }


}
