package tarc.assignment.funlearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavView.setupWithNavController(findNavController(R.id.nav_fragment))

        val settingsBtn = findViewById<View>(R.id.settings)
        val darkModeLayout = findViewById<View>(R.id.dark_mode_layout)

        settingsBtn.setOnClickListener {

            if (darkModeLayout.visibility == View.VISIBLE)
                darkModeLayout.visibility = View.GONE
            else
                darkModeLayout.visibility = View.VISIBLE
        }

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


}