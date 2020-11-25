package tech.thdev.kotlin_udemy_sample.view.sample2.presenter

interface Contract2 {
    interface View {
        fun addItem(index: Int)
        fun adapterNotify()
    }

    interface Presenter {
        var view: View?

        fun getItems(size: Int)
    }
}