package com.techjd.userlisttest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.techjd.userlisttest.R
import com.techjd.userlisttest.adapters.UserAdapter
import com.techjd.userlisttest.databinding.ActivityMainBinding
import com.techjd.userlisttest.viewmodel.UserViewModel
import com.techjd.utils.InternetConnection
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var glide: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = UserAdapter(glide)

        if (InternetConnection.checkConnection(this)) {
            userViewModel.makeCall().observe(this, {users ->
                if (users != null) {
                    binding.recyclerView.adapter = adapter
                    adapter.setdata(users.data)
                    binding.progressBar.visibility = View.GONE
                } else {
                    binding.progressBar.visibility = View.VISIBLE
                }
            })
        } else {
            binding.noInternet.visibility = View.VISIBLE
            binding.progressBar.visibility = View.INVISIBLE
        }



    }
}