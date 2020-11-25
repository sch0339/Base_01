package tech.thdev.kotlin_udemy_sample.di

import org.koin.dsl.module

/**
 * 우선 Koin DSL을 이용하여 모듈을 설치하여야 하는데 DSL은 무엇인까요? DSL(Domain Specific Language)으로 번역을 하자면
 * 도메인 특화 언어로 위키피디아에는 "특정한 도메인을 적용하는데 특화된 언어" 라고 정의되어 있습니다.
 *
    Koin DSL은 아래와 같이 있습니다.
    module { } - 코인 모듈 또는 하위 모듈 생성 (모듈 내부)
    applicationContext : Koin 모듈 생성
    factory : 매번 inject 할때마다 인스턴스 생성.항상 새로운 인스턴스를 생성하도록 해줌.
    single : 싱글톤 생성
    bind : 종속시킬 class나 interface를 주입
    get : 컴포넌트내에서 알맞은 의존성을 주입함.컴포넌트 종속성을 해결해줌 (필요한 컴포넌트를 주입받음)
 */
val appModule = module {
    // 1.
//    single<Repository> { RepositoryImpl() } // 싱글톤으로 생성
//    factory { MyPresenter(get(), "hyun") }

    // 2.
    factory { MyPresenter(RepositoryImpl(), "seo") }
}