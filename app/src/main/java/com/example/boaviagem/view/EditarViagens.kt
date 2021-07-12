package com.example.boaviagem.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import com.example.boaviagem.domains.Viagem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditarViagens(viagem: Viagem) : Fragment() {

    private lateinit var ctx: Context
    private var viagem_obj: Viagem = viagem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_editar_viagens, container, false)
        this.ctx = container?.context!!

        set_dados_viagens(view)
        view.findViewById<Button>(R.id.salvar_nova_viagem).setOnClickListener {
            salvar_nova_viagem(view)
        }

        view.findViewById<Button>(R.id.deletar_nova_viagem).setOnClickListener {
            deletar_viagem(view)
        }

        view.findViewById<Button>(R.id.listar_gastos).setOnClickListener {
            gastos(view)
        }

        view.findViewById<Button>(R.id.voltar).setOnClickListener {
            voltar_menu_principal(view)
        }
        return view
    }

    fun set_dados_viagens(view: View) {
        view.findViewById<EditText>(R.id.destino_viagem).setText(viagem_obj.destino)
        view.findViewById<EditText>(R.id.tipo_viagem).setText(viagem_obj.tipo)
        view.findViewById<EditText>(R.id.data_chegada).setText(viagem_obj.data_chegada)
        view.findViewById<EditText>(R.id.data_partida).setText(viagem_obj.data_chegada)
        view.findViewById<EditText>(R.id.orcamento_viagem).setText(viagem_obj.orcamento)
    }

    fun salvar_nova_viagem(view: View) {
        val destino = view.findViewById<EditText>(R.id.destino_viagem).text.toString()
        val tipo = view.findViewById<EditText>(R.id.tipo_viagem).text.toString()
        val data_chegada = view.findViewById<EditText>(R.id.data_chegada).text.toString()
        val data_partida = view.findViewById<EditText>(R.id.data_partida).text.toString()
        val orcamento = view.findViewById<EditText>(R.id.orcamento_viagem).text.toString()

        viagem_obj.destino = destino
        viagem_obj.tipo = tipo
        viagem_obj.data_chegada = data_chegada
        viagem_obj.data_partida = data_partida
        viagem_obj.orcamento = orcamento

        GlobalScope.launch(Dispatchers.Main) {
            try {
                withContext(Dispatchers.IO) {
                    AppDatabase.getInstance(ctx).viagemDao().udpate(viagem_obj)
                }
            } catch (exc: ClassNotFoundException) {
                Toast.makeText(ctx, "Não foi possível salvar a viagem: ${exc}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun deletar_viagem(view: View) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                withContext(Dispatchers.IO) {
                    AppDatabase.getInstance(ctx).viagemDao().delete(viagem_obj)
                }
            } catch (exc: ClassNotFoundException) {
                Toast.makeText(ctx, "Não foi possível deletar a viagem: ${exc}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun gastos(view: View) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.framePrincipal,
            ListaGastos(viagem_obj)
        ).commit()
    }

    fun voltar_menu_principal(view: View) {
        Intent(ctx, MenuActivity::class.java).apply {
            putExtra("usuario", viagem_obj.id_usuario);
            startActivity(this)
        }
    }
}