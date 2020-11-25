package tech.thdev.kotlin_udemy_sample.di

class RepositoryImpl : Repository {
    override fun getMyData(): String {
        return "Hello Koin"
    }

}