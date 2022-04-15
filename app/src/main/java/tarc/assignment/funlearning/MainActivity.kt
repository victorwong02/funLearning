package tarc.assignment.funlearning

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var toolbar : MaterialToolbar
    var language: String = "HTML"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.topAppBar)

        bottom_navigation.setupWithNavController(findNavController(R.id.nav_fragment))




//        //Home Fragment
//        // Initialize data.
//        val myDataset = Datasource().loadChapters()
//
//        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
//        recyclerView.adapter = ChpAdapter(this, this, myDataset)
//
//        // Use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        recyclerView.setHasFixedSize(true)

    }

    fun changeTopBarTitle(name: String) {
        toolbar?.title = name
        language = name
    }



}