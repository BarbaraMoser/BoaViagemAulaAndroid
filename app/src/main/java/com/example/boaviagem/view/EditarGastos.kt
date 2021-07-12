package com.example.boaviagem.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.boaviagem.R
import com.example.boaviagem.database.AppDatabase
import com.example.boaviagem.domains.Gasto
import com.example.boaviagem.domains.Viagem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditarGastos(private var gasto: Gasto, private var viagem: Viagem) : Fragment() {

    private lateinit var ctx: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_editar_gastos, container, false)
        this.ctx = container?.context!!

        set_dados_gasto(view)

        view.findViewById<Button>(R.id.voltar).setOnClickListener {
            Log.i("Voltar", "Passou aqui no voltar")
            voltar_menu_principal(view)
        }

        view.findViewById<Button>(R.id.salvar_gasto).setOnClickListener {
            Log.i("Salvar gasto", "Passou aqui")
            salvar_novo_gasto(view)
        }

        view.findViewById<Button>(R.id.deletar_gasto).setOnClickListener {
            deletar_gasto(view)
        }

        return view
    }

    fun set_dados_gasto(view: View) {
        view.findViewById<EditText>(R.id.tipo_gasto).setText(gasto.tipo)
        view.findViewById<EditText>(R.id.valor_gasto).setText(gasto.valor.toString())
        view.findViewById<EditText>(R.id.data_gasto).setText(gasto.data)
        view.findViewById<EditText>(R.id.descricao_gasto).setText(gasto.descricao)
        view.findViewById<EditText>(R.id.local_gasto).setText(gasto.local)
    }

    fun salvar_novo_gasto(view: View) {
        val tipo = view.findViewById<EditText>(R.id.tipo_gasto).text.toString()
        val valor = view.findViewById<EditText>(R.id.valor_gasto).text.toString()
        val data = view.findViewById<EditText>(R.id.data_gasto).text.toString()
        val descricao = view.findViewById<EditText>(R.id.descricao_gasto).text.toString()
        val local = view.findViewById<EditText>(R.id.local_gasto).text.toString()

        gasto.tipo = tipo
        gasto.valor = valor.toFloat()
        gasto.data = data
        gasto.descricao = descricao
        gasto.local = local

        GlobalScope.launch(Dispatchers.Main) {
            try {
                withContext(Dispatchers.IO) {
                    AppDatabase.getInstance(ctx).gastoDao().udpate(gasto)
                }
            } catch (exc: ClassNotFoundException) {
                Toast.makeText(ctx, "Não foi possível salvar o gasto: ${exc}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun deletar_gasto(view: View) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                withContext(Dispatchers.IO) {
                    AppDatabase.getInstance(ctx).gastoDao().delete(gasto)
                }
            } catch (exc: ClassNotFoundException) {
                Toast.makeText(ctx, "Não foi possível deletar o gasto: ${exc}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun voltar_menu_principal(view: View) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.framePrincipal,
            ListaGastos(viagem)
        ).commit()
    }
}