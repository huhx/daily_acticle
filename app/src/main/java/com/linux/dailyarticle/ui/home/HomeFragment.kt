package com.linux.dailyarticle.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.linux.dailyarticle.databinding.FragmentHomeBinding
import com.linux.dailyarticle.util.DateUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel.article.observe(viewLifecycleOwner) {
            binding.article = it
        }

        binding.tvAuthor.setOnClickListener {
            viewModel.setDate(DateUtils.plusDays(viewModel.date.value, -1))
        }

        binding.tvTitle.setOnClickListener {
            viewModel.setDate(DateUtils.plusDays(viewModel.date.value, 1))
        }
        return binding.root
    }
}