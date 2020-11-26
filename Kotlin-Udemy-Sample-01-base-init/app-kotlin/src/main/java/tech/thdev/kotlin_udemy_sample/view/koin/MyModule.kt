package tech.thdev.kotlin_udemy_sample.view.koin

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import tech.thdev.kotlin_udemy_sample.view.plus.*

// 1. 모듈 생성하기 (Koin DSL) : MyModule.kt
val appModule = module {
    single<Repository> { RepositoryImpl() } // 싱글톤으로 생성
    factory { MyPresenter(get()) } // 의존성 주입때마다 인스턴스 생성, get()을 이용하면 위에서 선언된 적절한 클래스가 주입됩니다.

    // plus랑 비슷하게 구현..
    single<CertRaonDataSource> { CertRaonDataSourceImpl() } // // 싱글톤으로 생성
    single<CertRepository> { CertRepositoryImpl(androidApplication(), get()) } // // 싱글톤으로 생성
    factory { Cert(get()) } // 의존성생성자 주입. 인스턴스생성,

}