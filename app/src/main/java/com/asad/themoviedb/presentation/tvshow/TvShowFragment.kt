package com.asad.themoviedb.presentation.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.asad.themoviedb.R
import com.asad.themoviedb.databinding.FragmentTvShowBinding
import com.asad.themoviedb.presentation.utils.delegate.viewBinding

class TvShowFragment : Fragment(R.layout.fragment_tv_show) {

    private val binding by viewBinding(FragmentTvShowBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            //findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}