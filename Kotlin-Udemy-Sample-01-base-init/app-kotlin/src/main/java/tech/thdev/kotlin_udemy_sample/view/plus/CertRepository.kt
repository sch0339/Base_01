package tech.thdev.kotlin_udemy_sample.view.plus

import android.app.Application

// plus:CertRaonRepository
interface CertRepository {
    fun getMyData(): String
}

class CertRepositoryImpl(
    val app: Application,
    val dataSource: CertRaonDataSource
) : CertRepository {
    override fun getMyData(): String =
        dataSource.getMyData(app)
}