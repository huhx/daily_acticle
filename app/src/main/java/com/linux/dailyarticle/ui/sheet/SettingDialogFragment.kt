package com.linux.dailyarticle.ui.sheet

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.linux.dailyarticle.databinding.FragmentSettingDialogBinding
import com.linux.dailyarticle.util.SystemUtil

class SettingDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSettingDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding.txtDownload.setOnClickListener {
            dismissAllowingStateLoss()
            SystemUtil.shortToast(requireContext(), "download")
        }
        binding.txtCopy.setOnClickListener {
            dismissAllowingStateLoss()
            SystemUtil.shortToast(requireContext(), "copy")
        }
        binding.fontsize.setOnClickListener {
            dismissAllowingStateLoss()
            SystemUtil.shortToast(requireContext(), "download")
        }
    }
}