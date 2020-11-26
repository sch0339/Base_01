package tech.thdev.kotlin_udemy_sample.util

/**
 * object
 * 1. 싱글턴 클래스로 만들 때
 * 2. 익명 클래스 객체를 생성할 때
 */
// 1-1
object CarFactory {
    var cars = mutableListOf<Car1>()
    fun makeCar(horsepowers: Int): Car1 {
        var car = Car1(horsepowers)
        cars.add(car)
        return car
    }
}
class Car1(power: Int) {}

// 1-2. companion object 이용해서 Factory 패턴 사용..
class Car2(power: Int) {
    companion object Factory {
        val cars = mutableListOf<Car2>()

        fun makeCar(hosepowers: Int): Car2 {
            var car = Car2(hosepowers)
            cars.add(car)
            return car
        }
    }
}


// 2.익명 클래스
interface Vehicle {
    fun drive(): String
}
fun start(vehicle: Vehicle) = println(vehicle.drive())
