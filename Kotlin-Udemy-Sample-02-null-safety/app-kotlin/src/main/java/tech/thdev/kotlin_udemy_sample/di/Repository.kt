package tech.thdev.kotlin_udemy_sample.di

// 모듈에서 사용하는 인터페이스입니다.
interface Repository {
    fun getMyData(): String
}