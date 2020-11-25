package tech.thdev.kotlin_udemy_sample.view.sample2.presenter

import tech.thdev.kotlin_udemy_sample.view.sample.presenter.PresenterSampleContract

class Presenter2 : Contract2.Presenter {
    override var view: Contract2.View? = null

    override fun getItems(size: Int) {
        if (size < 50) {
            val tempSize = size + 1
            val tempCount = (tempSize / 10) + 1

            for (index in tempSize..(tempCount * 10)) {
                view?.addItem(index)
            }
            view?.adapterNotify()
        }
    }
}