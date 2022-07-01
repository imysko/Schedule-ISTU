package com.example.timetable.ui.fragments.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timetable.databinding.FragmentScheduleBinding
import com.example.timetable.repositories.ScheduleFirebaseRepository
import com.example.timetable.ui.adapters.ScheduleListAdapter
import com.example.timetable.ui.factory.ScheduleViewModelFactory
import kotlinx.android.synthetic.main.fragment_schedule.*

class ScheduleFragment : Fragment() {

    private val _repository = ScheduleFirebaseRepository()
    private val _viewModel: ScheduleViewModel by viewModels { ScheduleViewModelFactory(_repository) }
    private var _binding: FragmentScheduleBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
            .apply { viewmodel = _viewModel }
        _binding!!.lifecycleOwner = this.viewLifecycleOwner

        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListAdapter()

        _viewModel.updateGroup("ИСТб-20-4", "Институт информационных технологий и анализа данных")
    }

    private fun setupListAdapter() {
        _viewModel.lessonList.observe(viewLifecycleOwner) { lessons ->
            lessons_recycle_view.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(false)
                it.adapter = ScheduleListAdapter(lessons)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}