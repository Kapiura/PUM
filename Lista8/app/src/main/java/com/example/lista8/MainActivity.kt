package com.example.lista8

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import com.example.lista8.ui.theme.Lista8Theme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val repo = GradeRepo(GradeDatabase.getDatabase(this).gradeDao())
        val viewModelFactory = GradeViewModelFactory(repo)
        setContent {
            Lista8Theme {
                val navController = rememberNavController()
                val viewModel: GradeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
                    factory = viewModelFactory
                )
                Navigation(navController, viewModel)
            }
        }
    }
}

// bazus danus - klasus gradus
@Entity(tableName = "grades")
data class Grade(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val subjectName: String,
    val gradeValue: Double
)

// dao bao
@Dao
interface GradeDao{
    @Query("SELECT * FROM grades")
    fun getAllGrades(): Flow<List<Grade>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrade(grade: Grade)
    
    @Update
    suspend fun updateGrade(grade: Grade)

    @Delete
    suspend fun deleteGrade(grade: Grade)
}

// data bazus
@Database(entities = [Grade::class], version = 1)
abstract class GradeDatabase: RoomDatabase()
{
    abstract fun gradeDao(): GradeDao

    companion object
    {
        @Volatile
        private var INSTANCE: GradeDatabase? = null

        fun getDatabase(context: Context): GradeDatabase
        {
            return INSTANCE?: synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GradeDatabase::class.java,
                    "grade_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

// repo
class GradeRepo(private val gradeDao:GradeDao)
{
    val allGrades: Flow<List<Grade>> = gradeDao.getAllGrades()

    suspend fun insert(grade: Grade)
    {
        gradeDao.insertGrade(grade)
    }

    suspend fun update(grade: Grade)
    {
        gradeDao.updateGrade(grade)
    }

    suspend fun delete(grade: Grade)
    {
        gradeDao.deleteGrade(grade)
    }
}

// view modelus
class GradeViewModel(private val repo: GradeRepo): ViewModel()
{
    val allGrades: Flow<List<Grade>> = repo.allGrades

    fun insert(grade: Grade) = viewModelScope.launch{
        repo.insert(grade)
    }

    fun update(grade: Grade) = viewModelScope.launch{
        repo.update(grade)
    }

    fun delete(grade: Grade) = viewModelScope.launch{
        repo.delete(grade)
    }
}

// Factorio
class GradeViewModelFactory(private val repo: GradeRepo): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GradeViewModel::class.java))
        {
            @Suppress("niesprawdzony czek")
            return GradeViewModel(repo) as T
        }
        throw IllegalArgumentException("nienznay viewmodel class")
    }
}

//main
@Composable
fun main(nav: NavController, viewModel: GradeViewModel)
{
    val grades by viewModel.allGrades.collectAsState(initial = emptyList())

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ){
        Text(
            "Moje Oceny",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ){
            items(grades){ grade ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .background(color = Color.hsl(59F, 0.21F, 0.53F))
                        .clickable { nav.navigate("edit/${grade.id}") }
                        .padding(30.dp),
                ){
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${grade.subjectName}",
                                fontSize = 20.sp
                            )
                            Text(
                                text = "${grade.gradeValue}",
                                fontSize = 20.sp)
                        }
                }
            }
        }
        Button(
            onClick = nav.navigate("add"),
            modifier = Modifier.padding(top = 15.dp)
        )
        {
            Text("Dodaj nowa ocene")
        }
    }
}

// add
@Composable
fun add(nav: NavController, viewModel: GradeViewModel)
{
    Text("add fun")
}

// editus
@Composable
fun edit(nav: NavController, viewModel: GradeViewModel, id: Int?)
{
    Text("edit fun")
}

// menu navigatus
@Composable
fun Navigation(nav: NavHostController, viewModel: GradeViewModel)
{
    NavHost(nav, startDestination = "main")
    {
        composable("main"){ main(nav, viewModel) }
        composable("add"){ add(nav, viewModel) }
        composable("edit/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt()
            edit(nav, viewModel, id)
        }
    }
}