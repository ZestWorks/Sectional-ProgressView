package com.zestworks.sectionalprogessview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zestworks.progressview.SectionalProgressView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
	private val xTimes: Int = 5

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		triggerTick()
	}

	private fun triggerTick() {
		GlobalScope.launch(Dispatchers.IO) {
			var progress: Int = 0
			var count: Int = 1
			do {
				progress += xTimes
				delay(1000L)
				withContext(Dispatchers.Main) {
					findViewById<SectionalProgressView>(R.id.progressView).setProgress(
						count,
						progress
					)
				}
				if (progress == 100) {
					progress = 0
					count += 1
				}
			} while (count < 4)
		}
	}
}