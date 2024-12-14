package com.example.lista6

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.Intent.CATEGORY_BROWSABLE
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            genExList();
            sumEx();
            Navigation(Modifier)
        }
    }
}

data class Exercise(
    val content: String,
    val points: Int
)

data class Subject (
    val name: String,
)

data class Grades (
    val subject: Subject,
    val average: Double,
    val appearances: Int
)

data class ExerciseList (
    val exercises: MutableList<Exercise>,
    val subject: Subject,
    val grade: Float,
)

val Subjects = mutableListOf(
    Subject("Matematyka"),
    Subject("PUM"),
    Subject("Fizyka"),
    Subject("Elektronika"),
    Subject("Algorytmy")
)

sealed class Screens(val route: String)
{
    data object E1 : Screens ("e1")
    data object E2 : Screens ("e2")
    data object E3 : Screens ("e3")
}

sealed class BottomBar(
    val route: String,
    val title: String,
    val icon: ImageVector
)
{
    data object E1: BottomBar(Screens.E1.route, "Exercises List", Icons.Default.Home)
    data object E2: BottomBar(Screens.E2.route, "Grades", Icons.Default.Info)
}

fun genExList()
{
    for (i in 1..20)
    {
        val ex = MutableList(Random.nextInt(1,10))
        {
            Exercise(
                content = "loremus ipsusum",
                points = Random.nextInt(1,10)
            )
        }
        exList.add(
            ExerciseList(
                exercises = ex,
                subject = Subjects[Random.nextInt(0,Subjects.size)],
                grade = (Random.nextInt(6, 11)).toFloat()/2
            )
        )
    }
}

fun sumEx(): MutableList<Grades>
{
    for (i in 0..4)
    {
        var sum = 0.0;
        var counter = 0;
        for(k in 0..19)
        {
            if(exList[k].subject.name == Subjects[i].name)
            {
                sum += exList[k].grade;
                counter++;
            }
        }
        var avg = 0.0;
        if(counter > 0)
        {
            avg = sum / counter;
        }
        sumGrades.add(Grades(Subjects[i], avg, counter))
    }
    sumGrades.removeAll { it.appearances == 0 }
    return sumGrades
}

val exList = mutableListOf<ExerciseList>()
val sumGrades = mutableListOf<Grades>()

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomMenu(navController = navController)},
        content = { NavGraph(navController = navController, modifier = modifier) }
    )
}


//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ActionBarMenu(navController: NavHostController){
//
//    var displayMenu by remember { mutableStateOf(false) }
//
//    TopAppBar(
//        title = {Text("Navigation App", color = Color.Black) },
//        actions = {
//            IconButton(onClick = { displayMenu = !displayMenu }) {
//                Icon(Icons.Default.MoreVert, "more")
//            }
//            DropdownMenu(
//                expanded = displayMenu,
//                onDismissRequest = { displayMenu = false }
//            ){
//                DropdownMenuItem(text = { Text(text = "Settings") }, onClick = { navController.navigate(Screens.SettingsPage.route) })
//            }
//        }
//    )
//}
//
@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier){
    NavHost(
        navController = navController,
        startDestination = Screens.E1.route,
    )
    {
        composable(route = Screens.E1.route){ E1(navController) }
        composable(route = Screens.E2.route){ E2() }
        composable(
            route = "${Screens.E3.route}/{index}",
            arguments = listOf(navArgument("index") {type=NavType.IntType})
            )
        {backStackEntry ->
            val index = backStackEntry.arguments!!.getInt("index");
            val exList = exList.getOrNull(index ?: -1)
            if(exList != null)
            {
                E3(index);
            }
            else
            {
                Text (text = "Cannot find exercise list with that index")
            }
        }
    }
}
//
@Composable
fun BottomMenu(navController: NavHostController)
{
    val screens = listOf(
        BottomBar.E1, BottomBar.E2
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar{
        screens.forEach{screen ->
            NavigationBarItem(
                label = { Text(text = screen.title)},
                icon = {Icon(imageVector = screen.icon, contentDescription = "icon")},
                selected = currentDestination?.hierarchy?.any {it.route == screen.route} == true,
                onClick = {navController.navigate(screen.route)}
            )
        }
    }
}

@Composable
fun E1(navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    )
    {
        Text("My Exercises List",
            modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp, 40.dp),
            fontSize = 25.sp,
            textAlign = TextAlign.Center)
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 100.dp)
        )
        {
            items(exList.size)
            {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .background(color = Color.hsl(59F, 0.21F, 0.53F))
                        .clickable { navController.navigate("${Screens.E3.route}/$it") }
                        .padding(30.dp),
                )
                {
                    var count = 0
                    for (i in 0..(exList.size-1))
                    {
                        if (exList[it].subject == exList[i].subject)
                        {
                            count++
                            if (i == it)
                                break
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {
                        Text(exList[it].subject.name, fontSize = 20.sp)
                        Text("List: " + count, fontSize = 20.sp)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {
                        Text("Exercises quantity: " + exList[it].exercises.size.toString())
                        Text("Grade: " + exList[it].grade.toString())
                    }
                }
            }
        }
    }
}

@Composable
fun E2()
{
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    )
    {
        Text("My Grades",
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp, 40.dp),
            fontSize = 30.sp,
            textAlign = TextAlign.Center)
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 100.dp)
        )
        {
            items(sumGrades.size)
            {
                Column (
                    modifier = Modifier.fillMaxWidth().padding(5.dp)
                        .background(color = Color.hsl(59F, 0.21F, 0.53F))
                        .padding(30.dp),
                )
                {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {
                        Text(sumGrades[it].subject.name, fontSize = 20.sp)
                        Text("Grade: " + String.format("%.2f", sumGrades[it].average)
                            .toDouble(), fontSize = 20.sp)
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {
                        Text("List quantity: " + sumGrades[it].appearances)
                    }
                }
            }
        }
    }
}

@Composable
fun E3(it: Int) {
    val arg = exList[it]
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp).fillMaxWidth()
    )
    {
        var count = 0
        for (n in 0..(exList.size - 1)) {
            if (exList[n].subject == arg.subject)
            {
                count++
                if (exList[n] == arg)
                    break
            }
        }
        Text("${arg.subject.name} - List $count",
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp, 40.dp),
            fontSize = 30.sp,
            textAlign = TextAlign.Center)
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 100.dp)
        )
        {
            items(arg.exercises.size)
            {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .background(color = Color.hsl(59F, 0.21F, 0.53F))
                        .padding(30.dp),
                )
                {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {
                        Text("Task ${it+1}", fontSize = 20.sp)
                        Text("Points: ${arg.exercises[it].points}", fontSize = 20.sp)
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {
                        Text(arg.exercises[it].content)
                    }
                }
            }
        }
    }
}

//
//@Composable
//fun SettingsPage(){
//    Box(
//        Modifier
//            .fillMaxSize()
//            .background(Color.Red),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = "Settings Screen",
//            fontSize = 40.sp
//        )
//    }
//}
//
//@Composable
//fun ListOfWords(){
//    LazyColumn{
//        items(50){
//            var word by remember {
//                mutableStateOf("word $it")
//            }
//            Text(
//                text = word,
//                fontSize = 32.sp,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(2.dp)
//                    .clickable { word += "Clicked!!!" }
//            )
//        }
//    }
//}
//
//
//@Composable
//fun E1(){
//
//    LazyColumn{
//        items(50){
//            var word by remember {
//                mutableStateOf("word $it")
//            }
//            Text(
//                text = word,
//                fontSize = 32.sp,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(2.dp)
//                    .clickable { word += "Clicked!!!" }
//            )
//        }
//    }
//}
//
//@Composable
//fun E2(){
//    Box(
//        Modifier
//            .fillMaxSize()
//            .background(Color.Red),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = "e2",
//            fontSize = 40.sp
//        )
//    }
//}