package tech.thdev.kotlin_udemy_sample.view.koin

class MyPresenter(private val repository: Repository) {
    fun sayHello() = "${repository.getMyData()} from ${this}"
}