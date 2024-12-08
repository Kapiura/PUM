package com.example.lista3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lista3.databinding.FragmentE1Binding

class E1 : Fragment() {
    private var _binding: FragmentE1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentE1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            adapter = WordListAdapter(ExerciseList.Companion.ExerciseListProvider.allExerciseLists) { clickedItem ->
                val currentIndex = ExerciseList.Companion.ExerciseListProvider.allExerciseLists.indexOf(clickedItem)
                val listCount = ExerciseList.Companion.ExerciseListProvider.allExerciseLists
                    .subList(0, currentIndex + 1)
                    .count { it.subject == clickedItem.subject }

                val subj = clickedItem.subject.name
                val grade = clickedItem.grade
                val exerciseContent = clickedItem.exercises[currentIndex].content
                val exerciseCount = clickedItem.exercises.size

                val action = E1Directions.actionE1ToE3(subj, listCount, grade, exerciseContent, exerciseCount)
                findNavController().navigate(action)
            }
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
