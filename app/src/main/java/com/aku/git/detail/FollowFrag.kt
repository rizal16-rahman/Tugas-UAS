package com.aku.git.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.aku.git.R
import com.aku.git.databinding.FragFolowBinding

class FollowFrag:Fragment(R.layout.frag_folow) {

    private var _binding : FragFolowBinding? =null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragFolowBinding.bind(view)
    }
}