package tech.thdev.kotlin_udemy_sample.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.android.ext.android.inject
import tech.thdev.kotlin_udemy_sample.R
import tech.thdev.kotlin_udemy_sample.di.MyPresenter
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val toolbar by lazy {
        findViewById<Toolbar>(R.id.toolbar)
    }

    private val myPresenter1: MyPresenter by inject() // 이 부분을 통해서 의존성 주입이 일어납니다.
    private val myPresenter2: MyPresenter by inject()
    private val myPresenter3: MyPresenter by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EventBus.getDefault().register(this)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()

            EventBus.getDefault().post(TestEventBus("fab click..."))
        }

        System.out.println("MainActivity=== "+myPresenter1.sayHello())
        System.out.println("MainActivity### "+myPresenter2.sayHello())
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().post(TestEventBus("seo"))
    }

    // 백그라운드 쓰레드건, 서비스건 어디서 보내도 상관없다. 위에서 ThreadMode가 main으로 설정되어 있기 떄문에, 이벤트를 받은 후의 작업은 main쓰레드에서 진행된다.
    // @Subscribe 어노테이션 아래에 connectEvent1 이름의 함수를 구독합니다. (connectEvent1299 의 이름은 아무거나 적으셔도 됩니다.)
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: TestEventBus) {
        System.out.println("event str="+event.helloEventBus)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }



}
