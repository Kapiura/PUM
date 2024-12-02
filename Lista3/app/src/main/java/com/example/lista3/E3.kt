package com.example.lista3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lista3.databinding.FragmentE3Binding

class E3 : Fragment() {

    private var _binding: FragmentE3Binding? = null
    private val binding get() = _binding!!

    private lateinit var exerciseListAdapter: ExerciseListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentE3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val exerciseLists = ExerciseList.Companion.ExerciseListProvider.allExerciseLists
        exerciseListAdapter = ExerciseListAdapter(exerciseLists)
        binding.recyclerView.adapter = exerciseListAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




