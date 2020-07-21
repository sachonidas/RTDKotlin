package es.luissachaarancibiabazan.rtdkotlin.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.luissachaarancibiabazan.rtdkotlin.R
import java.text.DecimalFormat

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    companion object{
        var alpha = 0.0
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val nucleos = resources.getStringArray(R.array.nucleos)
        val spinner = root.findViewById<Spinner>(R.id.spinner)
        val resistencia = root.findViewById<EditText>(R.id.editTextTextPersonName)
        val temperatura = root.findViewById<EditText>(R.id.editTextTextPersonName2)
        val button = root.findViewById(R.id.floatingActionButton) as FloatingActionButton
        val delete = root.findViewById(R.id.floatingActionButton2) as FloatingActionButton
        var nucleoCalcular = ""
        spinner?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item, nucleos) } as SpinnerAdapter
        spinner?.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                nucleoCalcular = parent?.getItemAtPosition(position).toString()
                when (nucleoCalcular){
                    "Cobre" -> alpha = 0.00425
                    "Platino" -> alpha = 0.00385
                    "Niquel" -> alpha = 0.0065
                    else -> ""
                }
            }
        }

        button.setOnClickListener{
            var res = resistencia!!.text.toString().toDouble()
            var temp = temperatura!!.text.toString().toDouble()
            var resultado: Double = res.times((1+ (alpha.times(temp))))
            Toast.makeText(activity, resultado.toString(), Toast.LENGTH_LONG).show();

        }

        delete.setOnClickListener{
            resistencia.setText("")
            temperatura.setText("")
            spinner.setSelection(0)
        }

        return root
    }
}