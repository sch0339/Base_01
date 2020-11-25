package tech.thdev.kotlin_udemy_sample.null_safety_02

import org.junit.Test

/**
 * Created by tae-hwan on 10/7/16.
 * return에 null을 배제하는 방법
 */
class _02_ReturnTest {

    @Test
    @Throws(InterruptedException::class)
    fun test01() {
        var temp: String? = ""
        val size: Int = if (temp != null) temp.length else 0
        print(size)
    }

    @Test
    @Throws(InterruptedException::class)
    fun test02() {
        var temp: String? = null
        val size: Int? = temp?.length
        print(size)
    }

    /**
     * Elvis operator을 사용하여 if / else를 사용한다
     * ?:(앞의 값이 널이면 세팅)
     */
    @Test
    @Throws(InterruptedException::class)
    fun testElvis() {
        var temp: String? = null
        val size: Int = temp?.length ?: 0
        print(size)
    }
}