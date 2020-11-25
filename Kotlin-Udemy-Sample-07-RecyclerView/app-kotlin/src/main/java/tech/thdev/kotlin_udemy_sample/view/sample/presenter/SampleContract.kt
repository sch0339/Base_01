package tech.thdev.kotlin_udemy_sample.view.sample.presenter

import tech.thdev.kotlin_udemy_sample.adapter.sample_one.model.SampleOneModel
import tech.thdev.kotlin_udemy_sample.adapter.sample_two.model.SampleTwoModel

/**
 * Created by tae-hwan on 10/23/16.
 */

interface SampleContract {
    interface View {
        fun adapterOneNotify() // RecyclerView One 갱신한다
        fun adapterTwoNotify() // RecyclerView two 갱신한다

        fun onSuccessAddItem(position: Int) // RecyclerView에 item add
        fun onSuccessRemoveItem()           // RecyclerView에 item remove
        fun onSuccessImageSample(position: Int) // image item add
    }

    interface Presenter {
        var view: View?
        var sampleOneModel: SampleOneModel? // Adapter one의 model
        var sampleTwoModel: SampleTwoModel? // Adapter two의 model

        fun loadDefaultItems() // 첫 번째 RecyclerView에 값을 정의
        fun adapterOneAddItem() // Sample에 item을 하나씩 추가한다
        fun adapterOneItemClick(position: Int) // Adapter One에 클릭을 처리한다
        fun adapterTwoItemClick(position: Int) // Adapter Two의 클릭을 처리한다
        fun adapterTwoRemoveItem() // Adapter Two의 아이템을 삭제한다
        fun addSampleImageItem() // Sample image을 추가한다

    }
}