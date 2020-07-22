package es.luissachaarancibiabazan.rtdkotlin.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.luissachaarancibiabazan.rtdkotlin.R
import kotlin.math.pow
import kotlin.math.E
class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val resistencia = root.findViewById(R.id.editTextTextPersonName3) as EditText
        val temperatura = root.findViewById(R.id.editTextTextPersonName4) as EditText
        val beta = root.findViewById(R.id.editTextTextPersonName5) as EditText
        val delete = root.findViewById(R.id.floatingActionButton3) as FloatingActionButton
        val calc = root.findViewById(R.id.floatingActionButton4) as FloatingActionButton
        val resultadoLabel = root.findViewById(R.id.textView8) as TextView

        resultadoLabel.visibility = View.INVISIBLE

        calc.setOnClickListener{
            var temp = (1 / (temperatura.text.toString().toDouble() + 273.00)) - (1 / 298.00)
            var ntc = beta.text.toString().toDouble().times(temp)
            val E: Double = E
            var resultado = resistencia.text.toString().toDouble().times(E.pow(ntc))
            Toast.makeText(activity, resultado.toString(), Toast.LENGTH_LONG).show();
            resultadoLabel.text = resultado.toString() + " Ohm"
        }

        delete.setOnClickListener{
            resistencia.setText("")
            temperatura.setText("")
            beta.setText("")
            resultadoLabel.visibility = View.INVISIBLE
        }

        return root
    }
}