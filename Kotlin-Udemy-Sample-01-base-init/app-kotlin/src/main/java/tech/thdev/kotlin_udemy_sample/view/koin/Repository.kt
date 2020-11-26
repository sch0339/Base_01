package tech.thdev.kotlin_udemy_sample.view.koin

interface Repository {
    fun getMyData(): String
}

// 주거래..
class RepositoryImpl: Repository {
    override fun getMyData(): String {
        return "Hello koin"
    }
}