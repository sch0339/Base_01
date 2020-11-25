package tech.thdev.kotlin_udemy_sample.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import tech.thdev.kotlin_udemy_sample.R
import tech.thdev.kotlin_udemy_sample.util.PapagoDTO
import tech.thdev.kotlin_udemy_sample.util.replaceFragmentToActivity
import java.io.IOException
import java.util.*
import kotlin.coroutines.resume


class MainActivity : AppCompatActivity() {
    val time = System.currentTimeMillis()

    private val toolbar by lazy {
        findViewById<Toolbar>(R.id.toolbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        // TODO 유틸로 함수 변경
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

//        launch01()
//        launch02()
//        async01()

//        GlobalScope.launch {
//            try {
//                val aaa = test1()
//                println("aaa=$aaa")
//                println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
//
//                test2()
//                println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb")
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }

//        noException1()
//        noException2()


        // runObservable() // Observable(통보를 하는 "어떤 클래스"), Observer(통보를 받는 "다른 클래스")

        okhttp()
    }

    fun main00() {
        // launch : Job을 반환
        // async : Defferd를 반환
        // launch대신 async를 사용해도 문제는 없다고 한다.
        CoroutineScope(Dispatchers.Main).launch {
        }
        CoroutineScope(Dispatchers.Main).async {
        }


        // Dispatchers.Default : CPU 사용량이 많은 작업에 사용합니다. 주 스레드에서 작업하기에는 너무 긴 작업 들에게 알맞습니다.
        // Dispatchers.IO : 네트워크, 디스크 사용 할때 사용합니다. 파일 읽고, 쓰고, 소켓을 읽고, 쓰고 작업을 멈추는것에 최적화되어 있습니다.
        // Dispatchers.Main : 안드로이드의 경우 UI 스레드를 사용합니다.
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
    }

    fun main01() {
        val scope = CoroutineScope(Dispatchers.Main)
        val job = scope.launch {
            println("main01 1.. ${System.currentTimeMillis() - time}")
            CoroutineScope(Dispatchers.Main).launch {
                // 외부 코루틴 블록이 취소 되어도 끝까지 수행됨
                println("main01 in.. ${System.currentTimeMillis() - time}")
            }


            // runBlocking() 함수는 코드 블록이 작업을 완료 하기를 기다립니다.
            // 현재 쓰레드(여기선 main 쓰레드)를 블록킹 시키고 새로운 코루틴을 실행시킨다.
            runBlocking {
                delay(2000)  // delay
                println("main01 runBlocking.. ${System.currentTimeMillis() - time}")
            }
            delayTime(1000)


            println("main01 2.. ${System.currentTimeMillis() - time}")
        }
    }

    fun main02() {
        GlobalScope.launch { // launch a new coroutine in background and continue
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
            println("main02 World! ${System.currentTimeMillis() - time}")
        }
        println("main02 Hello,${System.currentTimeMillis() - time}")
        runBlocking {     // 현재 쓰레드(여기선 main 쓰레드)를 블록킹 시키고 새로운 코루틴을 실행시킨다.
            delay(10000L)  // ... while we delay for 2 seconds to keep JVM alive
        }
    }

    // #1 > #2 > #3 출력
    // luanch블럭을 먼저 만났음에도 불구하고 1초의 소요시간이 걸리는 비동기 코드(delay) 때문에
    // #1이 먼저 출력되고, 아까 발사시켰던 미사일이 1초뒤에 돌아오면 아까 멈추었던 launch 블럭으로 돌아가 #2가 출력되고,
    // 마지막으로 #3이 출력되는 것이다.
    // 왜 #1 -> #3 -> #2 순서대로 실행되지 않는지 의문이 생길 수도있다. 그것은 runBlocking때문이다.
    // 위에서 언급했듯이 runBlocking은 간단히 { } 블록 안의 내부 코루틴(launch든 async든)이 종료되기 전 까지는 작업 중인 경량 쓰레드를 블로킹 시킨다.
    fun launch01() {
        println("launch01 start______________________________________")
        runBlocking { // 비동기 처리들이 끝날 때 까지 코드를 블로킹 시킨다
            launch { // launch 블록의 코드들이 미사일에 담겨 비동기로 수행된다고 생각하자. 간단히 가벼운 백그라운드 쓰레드(코루틴)로 수행된다고 생각해도 좋을 것 같다.
                delay(1000L) // 시간이 걸리는 것(미사일이 제 역할을 하고 다시 돌아오는 시간)을 나타냈지만, 실제로는 외부 api를 요청하거나 데이터베이스 IO작업처럼 시간이 걸리는 작업이라고 생각해도 된다
                println("world #2")  // #2
            }
            println("Hello #1")      // #1 // launch는 코드 블럭을 마치 백그라운드 쓰레드에서 실행시키는 것과 비슷하게 비동기 식으로 동작시킨다. 따라서 launch블락 다음 줄 #1 이 바로 호출된다.
        }
        println("end #3")            // #3
    }

    // #2 > #1 > #3 으로 출력하기,
    fun launch02() {
        println("launch02 start______________________________________")
        runBlocking { // 비동기 처리들이 끝날 때 까지 코드를 블로킹 시킨다
            var job = launch {
                delay(1000L)
                println("world #2")  // #2
            }
            job.join()  // join() 함수를 만나게 되면, 그 순간 job을 확인한 후 job이 아직 완료상태가 아니라 비동기 처리중인 상태일 경우 join()이후의 코드들을 실행시키지 않고 대기시킨다. 그리고 job들이 완료(미사일이 돌아옴)상태가 되면 그때서야 join() 아랫줄 코드들을 실행시킨다.
            println("Hello #1")      // #1
        }
        println("end #3")            // #3
    }

    // 이제 비동기 요청으로 서버에 데이터를 요청했다고 가정하자.
    // 서버에 데이터를 요청하고 받아오는 시간은 1초이고, 받아온 데이터를 가지고 어떤 연산을 하고싶다.
    // 위의 코드에서 job.join()아랫줄에 연산 코드를 넣으면 되겠지만, 서버로 부터 받아온 데이터가 어디있는가? 그것을 받아오도록 코드를 바꿔보자.
    fun async01() {
        println("async01 start______________________________________")
        runBlocking { // 비동기 처리들이 끝날 때 까지 코드를 블로킹 시킨다
            var deffered = async {
                delay(1000L)
                println("world #2")  // #2
                50 // 서버로 부터 받아온 데이터를 리턴해주는 부분 return은 적지 않는다.
            }
            var dataFromServer = deffered.await()
            println("Hello data=$dataFromServer, #1")      // #1
        }
        println("end #3")            // #3
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
        println("test1() 1=${System.currentTimeMillis() - time}")

        delayTime(5000)

        // cancel(), resume() 취소, 진행, 둘중먼저 호출하는것 실행..
        it.cancel()
        it.resume(false)

        println("test1() 2=${System.currentTimeMillis() - time}")
    }

    suspend fun test2() {
        println("test2() 1=${System.currentTimeMillis() - time}")
        delay(1000)
        delayTime(1000)
        println("test2() 2=${System.currentTimeMillis() - time}")
    }


    fun noException1() {
        val k: String? = null

        try {
            if (k != null) {
                println("noException1() is not null")
            } else {
                throw NullPointerException("noException1() k is null")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            println("noException1() finally")
        }
    }

    fun noException2() {
        val k: String? = null

        kotlin.runCatching {
            k ?: throw NullPointerException("noException2() k is null")
        }.onSuccess {
            println("noException2() is not null")
        }.onFailure { e ->
            e.printStackTrace()
        }
    }

    fun foo() {
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@forEach // 람다를 호출한 곳으로 local return. 즉, forEach 루프로 리턴합니다.
            println(it)
        }
        println("암시적 라벨 사용으로 이곳에 도달 합니다.")
    }

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
        watcher.addObserver(spy)
        watcher.addObserver(pc1)
        watcher.action3("사장 뜸.")
        watcher.deleteObserver(pc3)
        watcher.deleteObserver(spy)
        watcher.action3("사장 뜸.")

        watcher.addObserver { o, arg ->
            if (o is Watcher) {
                println("람다 일하는 척")
            }
        }
        watcher.action3("사장 뜸.")
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

                var papagoDTO : PapagoDTO = Gson().fromJson<PapagoDTO>(str, PapagoDTO::class.java)
                println(papagoDTO.message!!.result!!.translatedText)
            }

        })
    }
}





