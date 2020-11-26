package tech.thdev.kotlin_udemy_sample.view.plus

import tech.thdev.kotlin_udemy_sample.view.koin.Repository

class Cert(private val repository: CertRepository) {
    fun sayCert() = "${repository.getMyData()} from ${this}"
}