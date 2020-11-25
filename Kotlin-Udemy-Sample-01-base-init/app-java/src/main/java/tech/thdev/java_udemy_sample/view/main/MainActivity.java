package tech.thdev.java_udemy_sample.view.main;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import tech.thdev.java_udemy_sample.R;
import tech.thdev.java_udemy_sample.util.ActivityUtil;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private FloatingActionButton fab;

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
        ActivityUtil
                .replaceFragmentToActivity(
                        getSupportFragmentManager(),
                        MainFragment.getInstance(),
                        R.id.frame_layout);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        CustomDialog d = new CustomDialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
//        CustomDialog d = new CustomDialog(this);
        d.show();

        movable0.move("movable0");
        movable1.move("movable1");
        movable2.move("movable2");

        Movable movable3 = str -> System.out.println("gogo move move" + str); // * 람다식
        movable3.move("movable3");

        List<Fruit> fruits = Arrays.asList(new Fruit("apple", "red"), new Fruit("melon", "green"), new Fruit("banana", "yellow"));

        List<Fruit> list = extractFruitList(fruits, new Predicate<Fruit>() {
            @Override
            public boolean test(Fruit fruit) {
                return "apple".equals(fruit.getName());
            }
        });

        // http://gaemi.github.io/rxjava/2016/12/02/async-task-with-rxjava.html

        // 람다식(이름이 없는 함수를 정의할 수 있어야 한다.)
        List<Fruit> list1 = extractFruitList(fruits, fruit -> "apple".equals(fruit.getName()));
        List<Fruit> list2 = extractFruitList(fruits, fruit -> "yellow".equals(fruit.getColor()));

        for (Fruit f : list2) {
            Log.d("TAG", "onCreate: f=" + f.toString());
        }

        Runnable r = () -> System.out.println("onCreate hello functional"); // 스레드를 생성할때 주로사용하였으며 가장 기본적인 함수형 인터페이스다.
        r.run();

        Supplier<String> s = () -> "hello supplier"; // 인자는 받지않으며 리턴타입만 존재하는 메서드를 갖고있다.
        String str = s.get();
        Log.d("TAG", "onCreate: Supplier str="+str);

        Consumer<String> c = str1 -> System.out.println(str1); // 리턴을 하지않고(void), 인자를 받는 메서드를 갖고있다.
        c.accept("onCreate test");

        // RXJava
        Observable.just("Hello")
                .map(ss -> ss+"-seo")
                .subscribe(ss -> System.out.println(ss));

        Observable.just("Hello")
                .map(ss -> ss+"-seo")
                .map(ss -> ss.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(ss -> System.out.println(ss));
    }

    // 람다..
    interface Movable {
        void move(String str);
    }

    class Car implements Movable {
        private int speed; // 람다 표현식으로 구현할때 객체는 상태를 가질 수 없다. 이는 추후에 알아볼 스트림(Stream)을 이용한 병렬(Parallel)적 프로그래밍시

        @Override
        public void move(String str) {
            System.out.println("gogo car move" + str);
        }
    }

    // 인터페이스를 익명클래스로 구현해서 메소드를 호출
    Movable movable0 = new Movable() {
        @Override
        public void move(String str) {
            System.out.println("movable0 gogo move move" + str);
        }
    };

    Movable movable1 = (str) -> { // * 람다식
        System.out.println("movable1 gogo move move" + str);
    };

    Movable movable2 = str -> System.out.println("movable2 gogo move move" + str); // * 람다식


    // 행위 파라미터화(Behavior Parameterize)
    class Fruit {
        private String name;
        private String color;

        Fruit(String name, String color) {
            this.name = name;
            this.color = color;
        }

        String getName() {
            return this.name;
        }

        String getColor() {
            return this.color;
        }

        @Override
        public String toString() {
            return "Fruit{" + "name='" + name + '\'' + ", color='" + color + '\'' + '}';
        }
    }

    List<Fruit> extractApple(List<Fruit> fruits) {
        List<Fruit> resultList = new ArrayList<>();
        for (Fruit fruit : fruits) {
            if ("apple".equals(fruit.getName())) {
                resultList.add(fruit);
            }
        }
        return resultList;
    }

    List<Fruit> extractRed(List<Fruit> fruits) {
        List<Fruit> resultList = new ArrayList<>();
        for (Fruit fruit : fruits) {
            if ("yellow".equals(fruit.getColor())) {
                resultList.add(fruit);
            }
        }
        return resultList;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    List<Fruit> extractFruitList(List<Fruit> fruits, Predicate<Fruit> predicate) {
        List<Fruit> resultList = new ArrayList<>();
        for (Fruit fruit : fruits) {
            if (predicate.test(fruit)) {
                resultList.add(fruit);
            }
        }
        return resultList;
    }



//    Action1<String> onNextAction = new Action1<String>() {
//        @Override
//        public void call(String s) {
//            System.out.println(s);
//        }
//    };

}
