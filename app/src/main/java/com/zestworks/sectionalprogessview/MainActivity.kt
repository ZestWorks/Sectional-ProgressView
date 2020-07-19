package com.zestworks.sectionalprogessview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val sectionsCount = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressView.setSegmentsCount(sectionsCount)
        GlobalScope.launch {
            triggerTicks(sectionsCount)
        }
    }

    private suspend fun triggerTicks(segmentCount: Int) {
        repeat(segmentCount) { section ->
            repeat(100) { progress ->
                withContext(Dispatchers.Main) {
                    progressView.setProgress(
                        segmentIndex = section + 1,
                        progressPercentage = progress + 1
                    )
                }
                delay(50L)
            }
        }
    }
}