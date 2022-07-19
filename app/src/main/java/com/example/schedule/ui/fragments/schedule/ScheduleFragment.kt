package com.example.timetable.ui.fragments.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timetable.databinding.FragmentScheduleBinding
import com.example.timetable.repositories.ScheduleFirebaseRepository
import com.example.timetable.ui.adapters.ScheduleListAdapter
import com.example.timetable.ui.factory.ScheduleViewModelFactory
import com.example.timetable.ui.horizontalcalendar.HorizontalCalendarAdapter
import com.example.timetable.ui.horizontalcalendar.HorizontalCalendarFactory
import kotlinx.android.synthetic.main.fragment_schedule.*
import java.time.Instant

class ScheduleFragment : Fragment() {

    private val _repository = ScheduleFirebaseRepository()

    private val _horizontalCalendarFactory = HorizontalCalendarFactory { Instant.now() }

    private val _viewModel: ScheduleViewModel by viewModels {
        ScheduleViewModelFactory(_repository, _horizontalCalendarFactory)
    }

    private val _horizontalCalendarAdapter = HorizontalCalendarAdapter {
        _viewModel.selectDate(it)
    }

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
        setupHorizontalCalendar()

        _viewModel.updateGroup("ИСТб-20-4", "Институт информационных технологий и анализа данных")
    }

    private fun setupHorizontalCalendar() {
        _viewModel.horizontalCalendarSource.observe(viewLifecycleOwner) { dates ->
            _horizontalCalendarAdapter.submitList(dates)
        }

        binding.horizontalCalendar.apply {
            adapter = _horizontalCalendarAdapter
            layoutManager = LinearLayoutManager(this.context,
                LinearLayoutManager.HORIZONTAL, false)
        }
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