package tarc.assignment.funlearning.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_settings.*
import tarc.assignment.funlearning.MainActivity
import tarc.assignment.funlearning.R


class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_settings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dark_mode_switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.language_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                language_spinner.adapter = adapter
            }
        }

        language_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Another interface callback
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                val languageSelected = parent?.getItemAtPosition(pos).toString()

//                val intent = Intent(requireContext(), MainActivity::class.java)
//
//                intent.putExtra(LANGUAGESELECTED, pos)
//                startActivity(intent)

                (activity as MainActivity)?.changeTopBarTitle(languageSelected)


                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
            }
        }
    }
}