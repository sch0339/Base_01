package tech.thdev.kotlin_udemy_sample.view.sample2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import tech.thdev.kotlin_udemy_sample.R
import tech.thdev.kotlin_udemy_sample.util.replaceFragmentToActivity

class PresenterActivity2 : AppCompatActivity() {

    private val toolbar by lazy {
        findViewById<Toolbar>(R.id.toolbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "seoch"
        setSupportActionBar(toolbar)
        replaceFragmentToActivity(PresenterFragment2.getInstance(), R.id.frame_layout)
    }
}