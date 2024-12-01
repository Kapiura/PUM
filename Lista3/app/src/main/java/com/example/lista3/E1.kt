package com.example.lista3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lista3.databinding.ActivityMainBinding
import com.example.lista3.databinding.FragmentE1Binding
import androidx.navigation.Navigation

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
            adapter = WordListAdapter(ExerciseList.Companion.ExerciseListProvider.allExerciseLists) {clickedItem ->
                val currentIndex = ExerciseList.Companion.ExerciseListProvider.allExerciseLists.indexOf(clickedItem)

                val listCount = ExerciseList.Companion.ExerciseListProvider.allExerciseLists
                    .subList(0, currentIndex+1)
                    .count { it.subject == clickedItem.subject  }

                val subList = ExerciseList.Companion.ExerciseListProvider.allExerciseLists.subList(0, currentIndex + 1)
                val listCount2 = subList.count { it.subject == clickedItem.subject }

                val subj = clickedItem.subject.name
                val action = E1Directions.actionE1ToE3(subj, listCount2)
                Navigation.findNavController(requireView()).navigate(action)
            }
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

