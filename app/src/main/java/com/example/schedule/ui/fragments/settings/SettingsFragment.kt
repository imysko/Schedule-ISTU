package com.example.schedule.ui.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.schedule.databinding.FragmentSettingsBinding
import com.google.android.material.R

class SettingsFragment : Fragment() {

    private val _viewModel: SettingsViewModel by viewModels()

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
            .apply { viewmodel = _viewModel }
        _binding!!.lifecycleOwner = this.viewLifecycleOwner

        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.instituteAdapter = ArrayAdapter(this.context!!,
            R.layout.support_simple_spinner_dropdown_item, _viewModel.list)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}