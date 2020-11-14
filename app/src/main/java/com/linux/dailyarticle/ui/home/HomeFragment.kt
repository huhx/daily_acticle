package com.linux.dailyarticle.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.linux.dailyarticle.MainActivity
import com.linux.dailyarticle.R
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

        viewModel.isMarked.observe(viewLifecycleOwner) {
            binding.isMark = it
        }

        binding.tvAuthor.setOnClickListener {
            viewModel.setDate(DateUtils.plusDays(viewModel.currentDate.value, -1))
        }

        binding.tvTitle.setOnClickListener {
            viewModel.setDate(DateUtils.plusDays(viewModel.currentDate.value, 1))
        }

        binding.tvWords.setOnClickListener {
            viewModel.updateFavoriteArticle()
        }
        (activity as AppCompatActivity).setSupportActionBar(binding.homeToolbar)
        binding.homeToolbar.setNavigationIcon(R.drawable.ic_menu_camera)
        binding.homeToolbar.inflateMenu(R.menu.main)
        binding.homeToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings ->
                    Toast.makeText(context, "world Hello", Toast.LENGTH_SHORT).show()
            }
            super.onOptionsItemSelected(it)
        }
        var isToolbarShown = false
        binding.scrollView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            val shouldShowToolbar = scrollY > binding.homeToolbar.height
            if (isToolbarShown != shouldShowToolbar) {
                isToolbarShown = shouldShowToolbar
                binding.appbar.isActivated = shouldShowToolbar
                binding.toolbarLayout.isTitleEnabled = shouldShowToolbar
            }

        }
        binding.homeToolbar.setNavigationOnClickListener {
            (activity as MainActivity).openCloseNavigationDrawer(it)
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
}