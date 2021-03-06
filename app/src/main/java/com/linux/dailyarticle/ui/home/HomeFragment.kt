package com.linux.dailyarticle.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.linux.dailyarticle.databinding.FragmentHomeBinding
import com.linux.dailyarticle.ui.sheet.SettingDialogFragment
import com.linux.dailyarticle.util.DateUtils
import com.linux.dailyarticle.util.SystemUtil
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel.article.observe(viewLifecycleOwner) {
            binding.article = it
        }
        binding.linearLayout.setOnTouchListener(onTouchListener())
        return binding.root
    }

    private fun onTouchListener(): (v: View?, event: MotionEvent) -> Boolean {
        return { _, event ->
            val screenWidth = getScreenWidth()
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.x < screenWidth * 0.25) {
                    viewModel.setDate(DateUtils.plusDays(viewModel.currentDate.value, -1))
                } else if (event.x >= screenWidth * 0.25 && event.x <= screenWidth * 0.75) {
                    activity?.supportFragmentManager?.let {
                        SettingDialogFragment().apply {
                            show(it, tag)
                        }
                    }
                } else {
                    val date = DateUtils.plusDays(viewModel.currentDate.value, 1)
                    if (DateUtils.isAfter(date, Date())) {
                        SystemUtil.shortToast(requireContext(), "到底了，看不到了")
                        SystemUtil.vibration(requireContext(), 300)
                    } else {
                        viewModel.setDate(date)
                    }
                }
            }
            true
        }
    }

    private fun getScreenWidth(): Int {
        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }
}