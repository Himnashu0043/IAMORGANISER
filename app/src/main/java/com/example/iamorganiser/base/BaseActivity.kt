package com.example.iamorganiser.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.io.File

abstract class BaseActivity<viewBinding : ViewBinding> : AppCompatActivity() {

    private var _binding: viewBinding? = null
    protected val binding get() = _binding!!

    abstract fun getLayout(): viewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getLayout()
        setContentView(binding.root)
        val dexOutputDir: File = codeCacheDir
        dexOutputDir.setReadOnly()
    }

}