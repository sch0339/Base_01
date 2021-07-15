package tech.thdev.kotlin_udemy_sample.view.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import org.koin.android.ext.android.inject
import tech.thdev.kotlin_udemy_sample.R
import tech.thdev.kotlin_udemy_sample.util.MyFunction
import tech.thdev.kotlin_udemy_sample.util.PapagoDTO
import tech.thdev.kotlin_udemy_sample.util.replaceFragmentToActivity
import tech.thdev.kotlin_udemy_sample.view.koin.MyPresenter
import tech.thdev.kotlin_udemy_sample.view.plus.Cert
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.net.Socket
import java.util.*
import kotlin.coroutines.resume

val Context.kotlinValue: String
    get() = "kotlinHello"

class MainActivity : AppCompatActivity() {
    val time = System.currentTimeMillis()

    private val toolbar by lazy {
        findViewById<Toolbar>(R.id.toolbar)
    }

    private val myPresenter: MyPresenter by inject() // 3. 의존성 주입, 비슷한 방법으로 by viewModel() 태그를 이용해 viewModel을 생성해 사용하면 된다

    private val cert: Cert by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseCrashlytics.getInstance().log("MainActivity.onCreate")

        FirebaseCrashlytics.getInstance().setUserId("seoch1216")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

//        supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.frame_layout, MainFragment.getInstance())
//                .commit()
        replaceFragmentToActivity(MainFragment.getInstance(), R.id.frame_layout)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        // 코루틴.coroutine
//        main01()
//        main02()
//        withTimeout01()

//        launch01()
//        withContext01()
//        launch02()
//        async01()

//        global01()
//        global02()

//        exception01()
//        exception02()
//        exception03()

//        CoroutineScope()
//        runBlocking{
//            launch(Dispatchers.Default) {
//                Logger.d("launch")
//                Logger.d("test1===${test1()}")
//            }
//        }


//        coroutineScope()
//        letTest()


//        runCatching01()
//        runCatching02()
//        runCatching03()
//        runObservable() // Observable(통보를 하는 "어떤 클래스"), Observer(통보를 받는 "다른 클래스")

//        for01()
//        null01()

//        when01()

//        timer01()
//        asIsTest()
//        useTest()

        // LiveData vs ObservableField
        liveDataTest()

        /**
         * object
         * 1. 싱글턴 클래스로 만들 때
         * 2. 익명 클래스 객체를 생성할 때(익명객체는 이름이 없는 객체로, 한번만 사용되고 재사용되지 않을 때 사용합니다. )
         */
//        var car = CarFactory.makeCar(150)
//        Logger.d("car1.size=${CarFactory.cars.size}") // static 메소드를 호출하는 것처럼 보입니다.
//
//        var car2_1 = Car2.makeCar(220) // Car 클래스 안에 Factory 패턴을 정의하고 싶을 수 있습니다.(companion object)
//        var car2_2 = Car2.Factory.makeCar(240)
//        Logger.d("car2.size=${Car2.cars.size}")
//
//        // 익명 클래스 객체를 생성할 때
//        start(object : Vehicle {
//            override fun drive(): String {
//                return "Driving really fast"
//            }
//        })

        /**
         * Lamda : 람다식은 우리가 마치 value처럼 다룰수 있는 익명함수이다.
         * 1. 메소드의 파라메타로 넘겨줄수있다. fun maxBy(lamda: ~~)
         * 2. return 값으로 사용할수 있다.
         */
//        lamda01()
//        lamda02()

        // 줄임사용(kotain interface가 아닌 자바인터페이스이고, 그인터페이스가 딱하나의 메소드를 가질때)
//        fab.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                Logger.d("setOnClickListener")
//            }
//        })
//        fab.setOnClickListener { Logger.d("setOnClickListener") }

//        okhttp()

        /**
         * DI 장점.
         * boilerplate code를 줄여주므로 유연한 프로그래밍이 가능하다
         * 재사용성을 높아지고 유지보수에 용이하다
         *
         * koin은 Android에서 kotlin을 위해 제공하는 DI 라이브러리이다
         * 단계
         * 1. 모듈 생성하기 (Koin DSL) : MyModule.kt
         *    applicationContext(Koin 모듈 생성), factory(매번 inject 할때마다 인스턴스 생성), single(싱글톤 생성)
         *    , bind(종속시킬 class나 interface를 주입), get(컴포넌트내에서 알맞은 의존성을 주입함)
         * 2. Android Application Class에서 startKoin()으로 실행하기 : MyApplication.class
         * 3. 의존성 주입
         */
//        di01()
//        koin01()

//        invoke01() // invoke.람다.

//        takeIf01()
//        trim01()

    }

    /**
     * Coroutine: 비동기처리.(suspend 호출까지 순차처리)
     *
     * GlobalScope: 전역.싱글톤.사용자제.
     * CoroutineScope: lifecycle에 따라 ondestory()에서 job.cancel() 요청(ex MyActivity 참조.)
     *
     * launch : Job을 반환.join을 사용해서 끝날때까지 대기하도록 할수 있음.
     * async : Defferd를 반환(객체를리턴).불필요한 async/await 사용을 피하자.
     * withContext() : 이것은 async와 동일한 역할을 하는 키워드입니다. 차이점은 await()을 호출할 필요가 없다는 것입니다. 결과가 리턴될 때까지 기다립니다.
     *
     * job : start, cancel, join등을 시용
     *   start(코루틴상태확인, true:동작중 false:준비중/완료)
     *   join(코루틴 동작종료를 대기)
     *   cancel(CoroutineScope의 동작종료유도), cancelAndJoin(종료요청하고,대기), cancelChildren(children동작을종료유도)
     *
     * SupervisorJob : 자식들에 대한 exception이 독립적으로 하도록 한다.
     *
     * Dispatchers.Default : CPU 사용량이 많은 작업에 사용합니다. 주 스레드에서 작업하기에는 너무 긴 작업 들에게 알맞습니다.
     * Dispatchers.IO : 네트워크, 디스크 사용 할때 사용합니다. 파일 읽고, 쓰고, 소켓을 읽고, 쓰고 작업을 멈추는것에 최적화되어 있습니다.
     * Dispatchers.Main : 안드로이드의 경우 UI 스레드를 사용합니다.
     */
    fun main00() {
        CoroutineScope(Dispatchers.Main).launch {
        }
        CoroutineScope(Dispatchers.Main).async {
        }

        val scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            // 포그라운드 작업
        }
        scope.launch(Dispatchers.Default) {
            // CoroutineContext 를 변경하여 백그라운드로 전환하여 작업을 처리합니다
        }

        val scope2 = CoroutineScope(Dispatchers.Main)
        CoroutineScope(Dispatchers.Default).launch {
            // 새로운 CoroutineScope 로 동작하는 백그라운드 작업
        }
        scope2.launch(Dispatchers.Default) {
            // 기존 CoroutineScope 는 유지하되, 작업만 백그라운드로 처리
        }

        runBlocking {
            launch {
            }
        }
    }

    fun main01() {
        val scope = CoroutineScope(Dispatchers.Main)
        val job = scope.launch {
            Logger.d("main01 1.. ${System.currentTimeMillis() - time}")
            CoroutineScope(Dispatchers.Main).launch {
                // 외부 코루틴 블록이 취소 되어도 끝까지 수행됨
                Logger.d("main01 2..별도 코루틴... ${System.currentTimeMillis() - time}")
            }

            // runBlocking() 함수는 코드 블록이 작업을 완료 하기를 기다립니다.
            // 현재 쓰레드(여기선 main 쓰레드)를 블록킹 시키고 새로운 코루틴을 실행시킨다.
            runBlocking {
                delay(2000)  // delay
                Logger.d("main01 3.. runBlocking.. ${System.currentTimeMillis() - time}")
            }
            delayTime(1000)  // 1 > 3 > 4 > 2
//            delay(1000)       // 1 > 3 > 2 > 4 (delay에 포함된 suspend)

            Logger.d("main01 4.. ${System.currentTimeMillis() - time}")
        }

    }

    fun withTimeout01() {
        runBlocking {
            try {
                withTimeout(1300L) {
                    repeat(1000) { i ->
                        Logger.d("I'm sleeping $i ...")
                        delay(500L)
                    }
                }
            } catch (te: TimeoutCancellationException) {
                Logger.d("Timetout!!!")
            }
        }

        var aa: String? = null
        Logger.d("aa=" + aa?.length ?: "")
    }

    fun main02() {
        GlobalScope.launch { // launch a new coroutine in background and continue
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
            Logger.d("main02 World! ${System.currentTimeMillis() - time}")
        }
        runBlocking {     // 현재 쓰레드(여기선 main 쓰레드)를 블록킹 시키고 새로운 코루틴을 실행시킨다.
            delay(2000L)  // ... while we delay for 2 seconds to keep JVM alive
            Logger.d("main02 runBlocking")
        }
        Logger.d("main02 Hello,${System.currentTimeMillis() - time}")
    }

    // #1 > #2 > #3 출력
    // luanch블럭을 먼저 만났음에도 불구하고 1초의 소요시간이 걸리는 비동기 코드(delay) 때문에 #1이 먼저 출력되고, launch 블럭으로 돌아가 #2가 출력되고, 마지막으로 #3이 출력되는 것이다.
    // runBlocking은 간단히 { } 블록 안의 내부 코루틴(launch든 async든)이 종료되기 전 까지는 작업 중인 경량 쓰레드를 블로킹 시킨다.
    fun launch01() {
        println("launch01 start______________________________________")
        runBlocking { // 비동기 처리들이 끝날 때 까지 코드를 블로킹 시킨다
            launch(Dispatchers.Default) { // launch 블록의 코드들이 미사일에 담겨 비동기로 수행된다고 생각하자. 간단히 가벼운 백그라운드 쓰레드(코루틴)로 수행된다고 생각해도 좋을 것 같다.
                delay(1000L) // 시간이 걸리는 것을 나타냈지만, 실제로는 외부 api를 요청하거나 데이터베이스 IO작업처럼 시간이 걸리는 작업이라고 생각해도 된다
                println("world #2")
            }
            println("Hello #1")      // launch는 코드 블럭을 마치 백그라운드 쓰레드에서 실행시키는 것과 비슷하게 비동기 식으로 동작시킨다. 따라서 launch블락 다음 줄 #1 이 바로 호출된다.
        }
        println("end #3")
    }

    fun withContext01() {
        println("withContext01 start______________________________________")
        runBlocking {
            withContext(Dispatchers.Default) {
                delay(1000L)
                println("withContext01 world #2")
            }
            println("withContext01 Hello #1")
        }
        println("withContext01 end #3")

        val b = 0.1
        val c = b.isInfinite()
        val a = 3 / 0.1
        val d = a.isInfinite()
        Logger.d("a=" + a + ", d=" + d)

        var aa = ""
        Logger.d("aa=" + aa.toByteArray())

    }

    // #2 > #1 > #3 으로 출력하기,
    fun launch02() {
        println("launch02 start______________________________________")
        runBlocking { // 비동기 처리들이 끝날 때 까지 코드를 블로킹 시킨다
            var job = launch {
                delay(1000L)
                println("world #2")
            }
            // join() 함수를 만나게 되면, 그 순간 job을 확인한 후 job이 아직 완료상태가 아니라 비동기 처리중인 상태일 경우 이후의 코드들을 실행시키지 않고 대기시킨다.
            job.join()

            println("Hello #1")
        }
        println("end #3")
    }

    // 이제 비동기 요청으로 서버에 데이터를 요청했다고 가정하자.
    // 서버에 데이터를 요청하고 받아오는 시간은 1초이고, 받아온 데이터를 가지고 어떤 연산을 하고싶다.
    // 위의 코드에서 job.join()아랫줄에 연산 코드를 넣으면 되겠지만, 서버로 부터 받아온 데이터가 어디있는가? 그것을 받아오도록 코드를 바꿔보자.
    fun async01() {
        println("async01 start______________________________________")
        runBlocking { // 비동기 처리들이 끝날 때 까지 코드를 블로킹 시킨다
            var deffered = async {
                delay(1000L)
                println("world #1")
                50 // 서버로 부터 받아온 데이터를 리턴해주는 부분 return은 적지 않는다.
            }
            var dataFromServer = deffered.await()
            println("Hello data=$dataFromServer, #2")
        }
        println("end #3")
    }

    private val job = SupervisorJob()

    /**
     * GlobalScope: Application이 종료될 때 까지 코루틴을 실행시킬 수 있습니다.(Activity에서 코루틴을 GlobalScope영역에서 실행시켰다면, Activity가 종료되도 코루틴은 작업이 끝날 때까지 동작합니다.)
     *              Activity에서 보여줄 이미지를 다운받고 있는데 Activity가 종료되었다면 그 작업은 불필요한 리소스를 낭비하고 있는 것입니다.
     * GlobalScope 사용을 피하자.
     */
    fun global01() {
        Logger.d("global01", "thread1=" + Thread.currentThread().name)
        GlobalScope.launch {
            Logger.d("global01", "done something in Coroutine thread=" + Thread.currentThread().name)
            delay(2000)
            Logger.d("global01", "done something in Coroutine thread=" + Thread.currentThread().name)
        }
        Logger.d("global01", "thread2=" + Thread.currentThread().name)
    }

    fun global02() {
        runBlocking {
            GlobalScope.launch {
                Logger.d(
                    "global02",
                    "done something in Coroutine thread1=" + Thread.currentThread().name
                )
                delay(2000)
                Logger.d(
                    "global02",
                    "done something in Coroutine thread2=" + Thread.currentThread().name
                )
            }
            var value: Int = async {
                Logger.d("global02", "async thread=" + Thread.currentThread().name)
                1 + 2
            }.await()
            Logger.d("global02", "value1=$value , thread2=" + Thread.currentThread().name)

            Logger.d("global02", "Do something on IO thread")
            val name =
                withContext(Dispatchers.IO) {    // async와 동일한 역할을 하는 키워드입니다. 차이점은 await()을 호출할 필요가 없다는 것입니다. 결과가 리턴될 때까지 기다립니다.
                    delay(2000)
                    "My name is Android"
                }
            Logger.d("global02", "value2=$name , thread2=" + Thread.currentThread().name)
        }
    }

    // 예외처리:앱강제종료
    fun exception01() {
        runBlocking {
            GlobalScope.launch(Dispatchers.Default) {
                try {
                    launch {
                        throw Exception()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }

    /**
     * Job() : parent scrope은 exception이 발생시 다른 children을 모두 종료하고, 자기도 종료한다.
     * SupervisorJob() : 자식들에 대한 exception이 독립적으로 하도록 한다.
     */
    private val exjob = Job()

    //    private val exjob = SupervisorJob()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + exjob)
    fun exception02() = runBlocking {
        with(coroutineScope) {
            Logger.d("parent scrope")
            val firstChildren = launch {
                Logger.d("first children is failing")
                throw Exception("first children is exception") // exception발생시 부모에게 통지
            }
            val secondChildren = launch {
                Logger.d("secend children is success")
                delay(500)
                Logger.d("secend children is delay 500s")
            }
            firstChildren.join()
            secondChildren.join()
        }
    }

    // launch 예외처리
    fun exception03() {
        val handler = CoroutineExceptionHandler { _, exception ->
            Logger.d("launch, $exception handled! ")
        }

        runBlocking {
            GlobalScope.launch(exjob + Dispatchers.IO + handler) {
                launch {
                    throw Exception()
                }
            }
        }
    }

    // async, withContext 예외처리(불필요한 async/await 사용을 피하자.)
    fun exception04() {
        runBlocking {
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    var name = withContext(Dispatchers.Main) {
                        throw Exception()
                    }
                } catch (e: Exception) {
                    Logger.d("TAG", "withContext, $e handled!")
                }
            }
        }
    }

    fun coroutineScope() = runBlocking {
        CoroutineScope(Dispatchers.Default).launch {
//            delay(100)
            Logger.d("aaaa2")
        }
        Logger.d("aaaaa1")

    }

    suspend fun test(str: String) = withContext(Dispatchers.IO) {
        delayTime(2000)
        println("$str, test() ${System.currentTimeMillis() - time}")
    }


    fun delayTime(delayTime: Int) {
        var saveTime = System.currentTimeMillis()
        var currTime: Long = 0
        while (currTime - saveTime < delayTime) {
            currTime = System.currentTimeMillis()
        }
        println("delayTime end")
    }

    suspend fun test1() = suspendCancellableCoroutine<Boolean> {
        Logger.d("test1() 1=${System.currentTimeMillis() - time}")

        delayTime(3000)

        // cancel(), resume() 취소, 진행, 둘중먼저 호출하는것 실행..
        it.resume(false)
        it.cancel()

        Logger.d("test1() 2=${System.currentTimeMillis() - time}")
    }

    suspend fun test2() {
        println("test2() 1=${System.currentTimeMillis() - time}")
        delay(1000)
        delayTime(1000)
        println("test2() 2=${System.currentTimeMillis() - time}")
    }


    fun runCatching01() {
        val k: String? = "null"
        runCatching {
//            k ?: throw NullPointerException("runCatching01() k is null")
            "".toInt()
        }.onSuccess {
            Logger.d("onSuccess it=" + it)

            //여기서 맵핑하면 오류발생시 밖으로 빠진다.
            // "".toInt()
        }.onFailure { e ->
            Logger.d("onFailure")
        }.also {
            Logger.d("try... finally")
        }
    }

    fun runCatching02() {
//        runCatching {
//            3.14
//        }.map {
//            "".toInt() // 밖으로 빠져나감..
//        }.onSuccess {
//            Logger.d("map it="+it)
//        }.onFailure {
//            Logger.d("onFailure..")
//        }

        runCatching {
            3.24
        }.mapCatching {
            Logger.d("mapCatching")
            "".toInt()
        }.onSuccess {
            Logger.d("mapCatching onSuccess")
        }.onFailure {
            Logger.d("mapCatching onFailure..")
        }
        Logger.d("----------------------------------------------------------------------------2")

        runCatching {
            3
            "".toInt()
        }.recoverCatching {
            Logger.d("recover recoverCatching..")
            9
        }.onSuccess {
            Logger.d("recover onSuccess it=" + it)
        }.onFailure {
            Logger.d("recover onFailure..")
        }
        Logger.d("----------------------------------------------------------------------------3")

        val result = runCatching {
            "".toInt()
        }.onSuccess {
            Logger.d("get onSuccess..")
        }.onFailure {
            Logger.d("get onFailure...")
        }
        var result1 = result.getOrDefault(1) // 성공이면 결과값, 실패면 디폴트 값을 반환
        var result2 = result.getOrElse {
            when (it) {
                is NullPointerException -> 0
                is NumberFormatException -> 10
                else -> 99
            }
        }     // 성공이면 결과값, 실패면 지정된 코드를 실행
        Logger.d("result1=" + result1.toString() + ", result2=" + result2)
        Logger.d("----------------------------------------------------------------------------4")

        var sss = "aa\\/bb\\/cc"
        sss = sss.replace("\\", "")
        Logger.d("sss=" + sss)
    }

    fun runCatching03() {
        val sendObj = JSONObject()
        val jj = JSONObject()
        jj.put("certificate", "/abc")

        sendObj.put("result", jj)
        Logger.d("서버에서 보내는 값(json)=" + sendObj.toString())

        var str = sendObj.getJSONObject("result").toString()
        Logger.d("앱에서 받은값=" + str)
        str = str.replace("\\", "")
        Logger.d("앱에서 처리값=" + str)

        val set1 = JSONObject()
        set1.put("result", str)
        Logger.d("set1=" + set1.toString())

        val set2 = JSONObject()
        set2.put("result", JSONObject(str))
        Logger.d("set2=" + set2.toString())
    }

    fun letTest() {
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@forEach // 람다를 호출한 곳으로 local return. 즉, forEach 루프로 리턴합니다.
            println(it)
        }
        println("암시적 라벨 사용으로 이곳에 도달 합니다.")

        /**
         * Scope Functions(객체를 사용할 때 Scope(범위, 영역) 를 일시적으로 만들어서 속성(property)나 함수를 처리하는 용도로 사용되는 함수)
         *
         * let: 블록 내의 결과물을 반환하고 싶을 경우, null값이어도 실행됨.
         *      T?.let { } 형태에서의 let 블럭 안에는 non-null 만 들어올 수 있어 non-null 체크
         *      let 함수를 사용하면 객체의 상태를 변경할 수 있다.
         * with: non-null의 객체를 사용하고 블럭의 return 값이 필요하지 않을 때 사용한다.
         * run: 객체 없이 호출하며 익명함수로 사용할 수 있으며, 블럭내에 처리할 작업들을 넣어주면 된다. 반환값도 가능하다.
         *      run 함수를 호출하는 객체를 블록의 리시버로 전달하고, 블록의 결과 값을 반환한다.
         * apply: 블럭 함수의 입력을 람다 리시버로 받았기 때문에 블럭 안에서 객체의 프로퍼티를 호출할 때 it이나 this를 사용할 필요가 없다.
         *        run과 유사하지만 블럭에서 return 값을 받지 않으며 자기 자신인 T를 반환한다는 점이 다르다.(객체초기화, 변경)
         * also : T의 확장함수이고, 블럭 함수의 입력으로 람다 리시버를 받지 않고 this로 받았다. apply와 마찬가지로 T를 반환
         *
         */
        // let
        Logger.d("----------------------------------------------let")
        var person: Person? = Person("park", "jieun")
        person = null
        val str: String? =
            person?.let { // person. > test, person?. > null(?:앞의변수가null이면 null을 반환하고, null아닐경우 오른편 실행)
                Logger.d("run let..it=" + it)
                "test"
            }
        Logger.d("str=$str")
        Logger.d("person=" + person)

        val resultIt = person?.let {
            it.firstName = "James"
            it.lastName = "56"
            it // (T)->R 부분에서의 R에 해당하는 반환값.
        }
        Logger.d("resultIt=${resultIt}")


        // with
        Logger.d("----------------------------------------------with")
        var personw: Person? = Person("seo", "chang")
        with(personw!!) {
            Logger.d("name=${firstName}, ${this.lastName}")
            firstName = "sss"
            //자기자신을 반환해야 하는 경우 it이 아닌 this를 사용한다
        }
        Logger.d("personw=${personw}")


        // run
        Logger.d("----------------------------------------------run")
        val nextName = person?.run {
            lastName = "hyun"
        }
        Logger.d("nextName=${nextName}")


        // apply
        Logger.d("----------------------------------------------apply")
        var result = person?.apply {
            firstName = "first1"
            lastName = "last1"
        }
        Logger.d("person=${person}")
        Logger.d("result=${result}")


        // also
        Logger.d("----------------------------------------------also")
        var result_also = person?.also {
            it.firstName = "first2"
            it.lastName = "last2"
        }
        Logger.d("person=${person}")
        Logger.d("result_also=${result}")

        val numbers = arrayListOf("one", "two", "three")
        numbers
            .also { Logger.d("add 하기 전에 print: $it") }
            .add("four")
    }

    data class Person(var firstName: String = "1", var lastName: String = "2")

    // ..................................................................................................................
    // 변화를 통보하는 Observable
    class Watcher : Observable() {
        fun action3(string: String) {
            println("=======$string========")
            setChanged() // 변화가 일어났다는 것을 알리는 겁니다.
            notifyObservers("통보...")
        }
    }

    // 변화를 통보받는 직원
    class Employee(val desc: String) : Observer {
        override fun update(o: Observable?, arg: Any?) {
            if (o is Watcher) {
                println("" + arg + desc + "이 일하는 척...")
            }
        }
    }

    // 변화를 통보받는 사장 끄나풀
    class Spy(private val employee: Employee) : Observer {
        override fun update(o: Observable?, arg: Any?) {
            if (o is Watcher) {
                println("고자질쟁이가 " + employee.desc + "이 놀고 있었다고 고자질.")
            }
        }
    }

    fun runObservable() {
        val watcher = Watcher()
        val pc1 = Employee("만화책보는 놈")
        val pc2 = Employee("퍼질러 자는 놈")
        val pc3 = Employee("포카치는 놈")
        val spy = Spy(pc3) //spy는 pc3을 보고 있음.요놈은 꼰질르기의 대가.
        watcher.addObserver(pc1)
        watcher.addObserver(pc2)
        watcher.addObserver(pc3)
        watcher.addObserver { _, arg -> println("Observer 객체없는 놈") }
        watcher.addObserver(spy)
        watcher.action3("사장 뜸.")
        watcher.deleteObserver(pc3)
        watcher.deleteObserver(spy)
        watcher.action3("사장 뜸.")
    }

    fun lamda01() {
        val square = { number: Int -> number * number }
        val nameAge = { name: String, age: Int -> "My name is ${name} , age=${age}" }
        Logger.d("lamda01 ${square(12)}")
        Logger.d("lamda01 ${nameAge("hyun", 20)}")
    }

    fun lamda02() {
        // 확장함수
        val pizza: String.() -> String = {
            this + " Good"
        }
        Logger.d("domino".pizza())
    }

    fun okhttp() {
        val JSON: MediaType = "application/json; charset=utf-8".toMediaType()
        val client = OkHttpClient()
        var url = "https://openapi.naver.com/v1/papago/n2mt"
        var json = JSONObject()
        json.put("source", "ko")
        json.put("target", "en")
        json.put("text", "만나서 반갑습니다.")

        val body: RequestBody = json.toString().toRequestBody(JSON)
        val request: Request = Request.Builder()
            .header("X-Naver-Client-Id", "CFcA_D3apUgiUZ4wv0NF")
            .addHeader("X-Naver-Client-Secret", "H8rUBhrFUM")
            .url(url)
            .post(body)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                var str = response!!.body!!.string()
                println(str)

                var papagoDTO: PapagoDTO =
                    Gson().fromJson<PapagoDTO>(str, PapagoDTO::class.java)
                println(papagoDTO.message!!.result!!.translatedText)
            }

        })
    }

    // 내부에서 멤버로 생성(Person 클래스는 Dog 클래스에 의존성을 가지고 있다고 할 수 있는데 Dog 클래스에 변화가 생기면 Dog클래스를 참조하는 모든 클래스를 수정해줘야 한다)
    class Person01 {
        private val dog = Dog("Person01")
        fun walk() {
            dog.walk()
        }
    }

    // 외부에서 생성자로 주입(외부에서 Dog클래스의 인스턴스를 생성해 Person 클래스 객체를 만들 때 생성자로 주입을 해주게 되고, 만약 Dog클래스에 수정이 일어나더라도 Person클래스를 수정할 필요 없다!)
    // 이렇게 생성자 형태로 의존성을 주입하는 것을 "생성자 주입(Constructor Injection)" 이라고 한다
    class Person02(private val dog: Dog) {
        fun walk() {
            dog.walk()
        }
    }

    // 외부에서 멤버 변수에 접근(클래스가 시스템에 의해 생성되어 생성자로 주입을 할 수 없을 때 사용할 수 있다
    // 이런 방법을 "필드/setter 주입 (Field/setter injection)"이라고 한다)
    class Person03 {
        val dog by lazy {
            Dog("Person03")
        }

        fun walk() {
            dog.walk()
        }
    }

    class Dog(val name: String) {
        fun walk() {
            Logger.d("name=${name}, walk..")
        }
    }

    fun di01() {
        val person01 = Person01()
        person01.walk()

        val person02 = Person02(Dog("Person02"))
        person02.walk()

        val person03 = Person03()
        person03.walk()
    }

    fun koin01() {
        val str = myPresenter.sayHello()
        Logger.d("myPresenter=${str}")

        // plus비슷하게구현
        val str2 = cert.sayCert()
        Logger.d("cert=${str2}")
    }

    // 람다 : (인자) -> {함수내용}
    fun invoke01() {
        var str1 = MyFunction.invoke("test1")
        Logger.d("str1=${str1}")

        var str2 = MyFunction("test2") // kotlin에서는 invoke 함수명 생략가능.
        Logger.d("str2=${str2}")

        // toUpperCase는 타입이 무엇일까? Int도, String도 아니다. String을 받고, 다시 String을 반환하는 (String) -> String 타입
        val toUpperCase = { str: String -> str.toUpperCase() }
        Logger.d("toUpperCase=${toUpperCase("abc")}")

        val strList = listOf("a", "b", "c")
        println(strList.map(toUpperCase))

        val strList2 = listOf("a", "b", "c")
        println(strList2.map { str: String -> str.toUpperCase() }) // [A,B,C]
    }

    /**
     * takeIf()는 null이 아닌 객체에서 호출될 수 있고, predicate(Boolean을 리턴하는)함수를 인자로 받는다.
     * 만약 predicate가 true를 반환하는 식이라면, takeIf()는 null아닌 그 객체를 리턴할 것이고, predicate가 만족되지 않아 false를 반환한다면 null을 리턴할 것이다.
     */
    fun takeIf01() {
        val user = "KaSha"
        val str = "Sha"

        val index01 = user.indexOf(str).takeIf { it >= 0 } ?: -1
        val index02 = user.indexOf(str).takeUnless { it < 0 } ?: -1

        Logger.d("index01=" + index01 + ", index02=" + index02)
    }

    // 유니코드 표준에 따라 다른 공백 문자가 아닌 Java와 동일한 문자만 잘라내도록 만드는 것입니다.
    fun trim01() {
//        val kotlin = "\tU\t"
//        println(kotlin)
//
//        val kotlin2 = "\tU\t".trim()
//        println(kotlin2)
//
//        val kotlin3 = "\tU\t".trim{it <= ' '}
//        println(kotlin3)
//
//        val kotlin4 = "\u00A0M\u00A0".trim()
//        println(kotlin4)
//
//        val kotlin5 = "\u00A0M\u00A0".trim{it <= ' '}
//        println(kotlin5)

        val curVer = "61.1.244"
//        val curVerString = StringBuilder()
//        curVer.split(".").forEach {
//            curVerString.append(it)
//        }
//        Logger.d("curVerString="+curVerString)

        var curVerString = curVer?.substring(0, curVer.indexOf("."))

        Logger.d("curVerString=" + curVerString)
    }

    fun for01() {
        var ii = 5
        for (i in 0..ii) {
            Logger.d("i=" + i)
        }
        for (i in 0 until ii) { // 추천..
            Logger.d("iii=" + i)
        }

        var list: MutableList<String> = mutableListOf()
        for (i in 0..0) {
            list.add("tttt=" + i)
        }
        for (i in list) {
            Logger.d("kkk=" + i)
        }

        Logger.d("get=" + MainFragment)
    }

    fun null01() {
        Logger.d("null01")
        val aaa = null

        aaa ?: return
        Logger.d("not null...")
    }

    fun when01() {
        val a: String? = ""
        val b: String? = null

        when {
            a.isNullOrEmpty() -> {
                Logger.d("a isNullOrEmpty")
            }
            b.isNullOrEmpty() -> {
                Logger.d("b isNullOrEmpty")
            }
            else -> {
                Logger.d("else1")
            }
        }

        val type = "1"
        when (type) {
            "1" -> {
                Logger.d("1")
            }
            else -> {
                Logger.d("else2")
            }
        }
    }

    private fun isApplicationInstalled(
        packageName: String?
    ): Boolean {
        val intentToResolve = Intent(Intent.ACTION_MAIN)
        intentToResolve.addCategory(Intent.CATEGORY_LAUNCHER)
        intentToResolve.setPackage(packageName)
        val resolveInfo =
            packageManager?.resolveActivity(intentToResolve, 0)
        return resolveInfo != null
    }

    var testT: Timer? = null
    fun timer01() {
        Logger.d("ttttt")
//        timer02()


        testT?.cancel()
        timer02()
        delayTime(2000)

        testT?.cancel()
        Logger.d("testT="+testT)
        testT?.cancel()
        timer02()
    }

    fun timer02() {
        val task: TimerTask = object : TimerTask() {
            override fun run() {

                Logger.d("timer02...111")
            }
        }
        testT = Timer()
        testT?.schedule(task, 3000)
    }

    fun asIsTest() {
        val aaa1: Any = 0
        var vvv1: String
        if (aaa1 is String) {
            vvv1 = aaa1
        } else {
            vvv1 = "-1"
        }
        Logger.d("aaa1="+aaa1+", vvv1="+vvv1)

        /**
         * as : casting 실패하면 error
         * as? : casting 실패하면 null
         * */
        val aaa2: Any = 0
        var vvv2: String = (aaa2 as? String) ?: "-1"
        Logger.d("aaa2="+aaa2+", vvv2="+vvv2)

        val aaa3: String? = null
        Logger.d("yyy="+(aaa3=="aaa"))


//        Environment.getExternalStoragePublicDirectory()

        var bbb = Environment.DIRECTORY_DOWNLOADS + "/data/mdm2"
        Logger.d("bbb="+bbb)
//        val dir = File(bbb)
        val dir = Environment.getExternalStoragePublicDirectory(bbb)
        Logger.d("dir="+dir.exists())
        if (!dir.exists()) dir.mkdirs()
        Logger.d("dir2="+dir.exists())
    }

    fun useTest() = runBlocking {
        withContext(Dispatchers.IO) {
            Logger.d("useTest start1.......")

            /*
             * Socket을 생성하는 부분은 Exception 발생할 수 있다.
             * getInputStream 부분 역시 Exception 발생할 수 있다.
             * finally에 포함한 close 역시 Exception이 발생할 수 있다.
             */
            val socket1 = Socket("thdev.tech", 80)
            val inputStream1 = socket1.getInputStream()
            val reader1 = InputStreamReader(inputStream1)
            Logger.d("reader1="+reader1.readText()+", toString1="+reader1.toString())
            reader1.close()
            inputStream1.close()
            socket1.close()


            Logger.d("useTest start2.......")
            /*
            * use 사용으로 모든 예외처리를 한번에 단. try은 한번 사용하는것이 안전.
            * */
            try {
                Socket("thdev.tech", 80).use {
                    it.getInputStream().use {
                        InputStreamReader(it).use {
                            Logger.d("it2="+it.readText()+", toString2="+it.toString())
                        }
                    }
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    fun liveDataTest() {

    }
}







