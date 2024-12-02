package com.example.lista3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        // Pobranie przekazanych argumentów
        val subjectName = arguments?.getString("subject") ?: ""
        val exerciseCount = arguments?.getInt("exerciseCount") ?: 0
        val exerciseContent = arguments?.getString("exerciseContent") ?: ""

        // Inicjalizacja RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Generowanie ExerciseList z odpowiednią liczbą zadań
        val generatedExerciseList = ExerciseList(
            exercises = MutableList(exerciseCount) { index ->
                Exercise(content = "$exerciseContent ${index + 1}", points = (index + 1) * 10)
            },
            subject = Subject(subjectName),
            grade = 5.0f
        )

        // Tworzymy listę ExerciseList, bo adapter oczekuje tego typu
        val exerciseList = listOf(generatedExerciseList)

        // Przypisanie adaptera
        exerciseListAdapter = ExerciseListAdapter(exerciseList)
        binding.recyclerView.adapter = exerciseListAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
