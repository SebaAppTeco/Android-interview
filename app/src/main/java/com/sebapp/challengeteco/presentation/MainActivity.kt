package com.sebapp.challengeteco.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sebapp.challengeteco.R
import com.sebapp.challengeteco.presentation.characterList.CharacterViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Developer Applicant Interview"

        lifecycleScope.launchWhenCreated {
            val viewModel = getViewModel<CharacterViewModel>()
            viewModel.getMainListData()
        }
    }
}
