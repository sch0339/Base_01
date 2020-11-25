package tech.thdev.kotlin_udemy_sample.di

import org.greenrobot.eventbus.EventBus
import tech.thdev.kotlin_udemy_sample.view.main.TestEventBus

/**
 * Repository를 사용하는 Presenter 입니다.
 * 생성자에서 repository의 인스턴스가 의존성 주입이 되는데, 모듈에서의 get()을 통해서 자동으로 주입이 됩니다.
 */
class MyPresenter(private val repository: Repository, val name: String) {
    init {
        System.out.println("init..")
        EventBus.getDefault().post(TestEventBus("hyun"))
    }

    fun sayHello() = "${repository.getMyData()} from $name, $this"
}