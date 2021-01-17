package com.aku.git.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.aku.git.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USERNAME="extra_username"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel:DetailUserVm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserVm::class.java)

        viewModel.setUserDetail {username}
        viewModel.getUserDetail().observe(this, {
            if(it !=null){
                binding.apply {
                ucNama.text=it.name
                    unName.text=it.login
                    ucFolow.text = "${it.followers} Followers"
                    ucFolowing.text= "${it.following} Followings"
                    Glide.with(this@DetailActivity)
                        .load(it.avatar)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(idPro)
                }
            }
        })

        val sectionPageAdapter = SectionPageAdapter(this,supportFragmentManager)
        binding.apply {
            viewspag.adapter = sectionPageAdapter
            tabs.setupWithViewPager(viewspag)
        }
    }
}