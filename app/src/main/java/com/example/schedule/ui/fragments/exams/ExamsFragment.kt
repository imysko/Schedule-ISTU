package com.example.schedule.ui.fragments.exams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schedule.databinding.FragmentExamsBinding
import com.example.schedule.ui.factory.ExamsViewModelFactory
import com.example.schedule.repositories.ExamFirebaseRepository
import com.example.schedule.ui.adapters.ExamListAdapter
import kotlinx.android.synthetic.main.fragment_exams.*

class ExamsFragment : Fragment() {

    private val _repository = ExamFirebaseRepository()
    private val _viewModel: ExamsViewModel by viewModels {ExamsViewModelFactory(_repository)}
    private var _binding: FragmentExamsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExamsBinding.inflate(inflater, container, false)
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
        _viewModel.examList.observe(viewLifecycleOwner) { exams ->
            exams_recycle_view.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(false)
                it.adapter = ExamListAdapter(exams)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
