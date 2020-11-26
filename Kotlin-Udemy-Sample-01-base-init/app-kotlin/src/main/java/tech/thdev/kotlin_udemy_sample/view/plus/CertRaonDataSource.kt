package tech.thdev.kotlin_udemy_sample.view.plus

import android.content.Context

// plus:CertRaonDataSource
interface CertRaonDataSource {
    fun getMyData(context: Context): String
}

class CertRaonDataSourceImpl : CertRaonDataSource {
    override fun getMyData(context: Context): String { // 실제로직 구현..
        return "Hello plus"
    }
}