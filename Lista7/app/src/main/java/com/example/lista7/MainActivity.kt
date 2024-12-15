package com.example.lista7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

object DataProvider
{
    val stu: List<Student> = listOf(
        Student(363249, "Agata", "Kowalski", 3.58, 1),
        Student(760896, "Grzegorz", "Dąbrowski", 3.58, 1),
        Student(719326, "Marek", "Lewandowski", 4.99, 2),
        Student(715275, "Monika", "Szymański", 4.12, 5),
        Student(505903, "Katarzyna", "Szymański", 2.25, 4),
        Student(476770, "Marek", "Zieliński", 4.08, 2),
        Student(417652, "Piotr", "Kaczmarek", 3.53, 4),
        Student(562992, "Jan", "Wiśniewski", 3.75, 2),
        Student(809174, "Anna", "Wójcik", 2.69, 4),
        Student(313315, "Katarzyna", "Kowalski", 4.61, 1),
        Student(464800, "Zuzanna", "Dąbrowski", 4.45, 2),
        Student(135831, "Agata", "Szymański", 3.19, 3),
        Student(361703, "Agata", "Szymański", 3.52, 2),
        Student(529772, "Grzegorz", "Kowalski", 2.32, 1),
        Student(458789, "Zuzanna", "Wiśniewski", 2.58, 4),
        Student(816281, "Katarzyna", "Kamiński", 2.45, 4),
        Student(558581, "Zuzanna", "Kaczmarek", 4.96, 4),
        Student(722595, "Agata", "Szymański", 3.15, 1),
        Student(975142, "Piotr", "Lewandowski", 2.77, 4),
        Student(359754, "Agata", "Dąbrowski", 4.85, 5)
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Pages.Students.route) {
        composable(route = Pages.Students.route) {
            MainPage { indexNumber ->
                navController.navigate(Pages.Details.createRoute(indexNumber))
            }
        }

        composable(
            route = Pages.Details.route,
            arguments = listOf(navArgument("indexNumber") { type = NavType.IntType })
        ) { backStackEntry ->
            val indexNumber = backStackEntry.arguments?.getInt("indexNumber") ?: -1
            ListDetails(indexNumber = indexNumber) { navController.popBackStack() }
        }
    }
}


data class Student(
    val indexNumber: Int,
    val name: String,
    val surname: String,
    val avgGrades: Double,
    val currStudyYear: Int
)

sealed class Pages(val route: String)
{
    data object Students: Pages("students")
    data object Details : Pages("details/{indexNumber}") {
        fun createRoute(indexNumber: Int) = "details/$indexNumber"
    }
}

class StudentViewModel: ViewModel()
{
    private var _StudentsList = mutableStateListOf<Student>()
    val stuList :  List<Student>
        get() = _StudentsList

    init
    {
        reinitialize()
    }

    fun reinitialize()
    {
        _StudentsList.clear()
        _StudentsList.addAll(DataProvider.stu)
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(onListDetails: (Int) -> Unit) {
    val viewModel: StudentViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "Students",
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp, 40.dp),
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 100.dp)
        ) {
            items(viewModel.stuList.size) { index ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .background(color = Color.hsl(59F, 0.21F, 0.53F))
                        .clickable { onListDetails(viewModel.stuList[index].indexNumber) }
                        .padding(30.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            viewModel.stuList[index].name + " " + viewModel.stuList[index].surname,
                            fontSize = 20.sp
                        )
                        Text(viewModel.stuList[index].indexNumber.toString(), fontSize = 20.sp)
                    }
                }
            }
        }
    }
}



@Composable
fun ListDetails(indexNumber: Int, onMainPage: () -> Unit) {
    val student = DataProvider.stu.find { it.indexNumber == indexNumber }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (student != null) {
            Text("Student Details", fontSize = 24.sp, modifier = Modifier.padding(8.dp))
            Text("Index: ${student.indexNumber}", fontSize = 20.sp, modifier = Modifier.padding(8.dp))
            Text("Name: ${student.name}", fontSize = 20.sp, modifier = Modifier.padding(8.dp))
            Text("Surname: ${student.surname}", fontSize = 20.sp, modifier = Modifier.padding(8.dp))
            Text("Average Grade: ${student.avgGrades}", fontSize = 20.sp, modifier = Modifier.padding(8.dp))
            Text("Year of Study: ${student.currStudyYear}", fontSize = 20.sp, modifier = Modifier.padding(8.dp))
        } else {
            Text("Student not found", fontSize = 20.sp, modifier = Modifier.padding(8.dp))
        }

        Button(onClick = onMainPage, modifier = Modifier.padding(top = 16.dp)) {
            Text("Back")
        }
    }
}

