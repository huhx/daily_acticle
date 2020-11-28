package com.linux.dailyarticle.ui.sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.linux.dailyarticle.databinding.FragmentSettingDialogBinding

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    SettingDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
class SettingDialogFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentSettingDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            Toast.makeText(context, "download", Toast.LENGTH_SHORT).show()
        }
        binding.txtCopy.setOnClickListener {
            dismissAllowingStateLoss()
            Toast.makeText(context, "copy", Toast.LENGTH_SHORT).show()
        }
        binding.fontsize.setOnClickListener {
            dismissAllowingStateLoss()
            Toast.makeText(context, "fontsize", Toast.LENGTH_SHORT).show()
        }
    }
}