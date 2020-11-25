package tech.thdev.kotlin_udemy_sample.view.image.presenter

import tech.thdev.kotlin_udemy_sample.data.model.PhotoDataSource

/**
 * Created by tae-hwan on 10/23/16.
 */

interface ImageContract {

    interface View {
        fun showLoadSuccess() // 로딩완료.
        fun showLoadFail() // 로딩실패.
        fun showLoadFailMessage(message: String) // 실패메세지.
    }

    interface Presenter {
        var view: View?
        var photoDataSample: PhotoDataSource? // Model
        fun getRecentImageSample() // Flickr image을 불러온다
    }
}