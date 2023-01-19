package com.sebapp.challengeteco.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sebapp.challengeteco.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Developer Applicant Interview"
    }
}
