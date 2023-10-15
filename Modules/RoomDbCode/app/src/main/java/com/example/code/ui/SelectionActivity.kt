package com.example.code.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.code.R
import com.example.code.databinding.ActivitySelectionBinding
import com.example.code.ui.compose.main.ComposeMainActivity
import com.example.code.ui.xml.main.MainActivity
import com.example.code.utils.startActivity

class SelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListeners();
    }

    private fun setClickListeners() {
        binding.apply {
            xmlBtnId.setOnClickListener { startActivity<MainActivity>() }
            composeBtnId.setOnClickListener { startActivity<ComposeMainActivity>() }
        }
    }

}