package com.aku.git.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aku.git.data.model.DetailUser
import com.aku.git.data.model.User
import com.aku.git.databinding.ActivityMainBinding
import com.aku.git.detail.DetailActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object :UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Intent (this@MainActivity,DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRA_USERNAME,data.login)
                    startActivity(it)
                }
            }

        })
        viewModel= ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter =adapter

            btnSerac.setOnClickListener{

            }

            eadit.setOnKeyListener{v, keycode, event ->
                if (event.action==KeyEvent.ACTION_DOWN&&keycode==KeyEvent.KEYCODE_ENTER){
                    searchUser()
                    return@setOnKeyListener true

                }
                return@setOnKeyListener false
            }

        }
        viewModel.getSearchUsers().observe(this,{
            if (it!=null){
                adapter.setList(it)
                showLoading(false)
            }

        })
    }

    private fun searchUser(){
        binding.apply {
            var query = eadit.text.toString()
            if (query.isEmpty())return
            showLoading(true)
            viewModel.setSearchUser(query)
        }
    }

    private fun showLoading(state:Boolean){
        if (state){
            binding.proges.visibility = View.VISIBLE

        }else{
            binding.proges.visibility = View.GONE
        }
    }
}