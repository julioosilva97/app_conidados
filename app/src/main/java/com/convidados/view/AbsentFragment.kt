package com.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.convidados.R
import com.convidados.view.adapter.GuestAdapter
import com.convidados.view.listener.GuestListener
import com.convidados.viewmodel.GuestViewModel

class AbsentFragment : Fragment() {

    private lateinit var mViewModel: GuestViewModel

    private val mAdapter: GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(GuestViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_absent, container, false)

        // 1 - Obter a recycler
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_absent)

        //2 - Definir um adapter
        recycler.layoutManager = LinearLayoutManager(context)

        // 3 - Definir um adpter
        recycler.adapter = mAdapter

        //quando acontecer um click na viewholder, executa esse código
        mListener = object : GuestListener {

            override fun onClick(id: Int) {
                var intent = Intent(context, GuestFormActivity::class.java)

                //passar valores de uma actvity para outra
                val bundle = Bundle()
                bundle.putInt("guestID", id)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load("ABSENTS")
            }
        }

        mAdapter.attachListener(mListener)
        observer()

        mViewModel.load("ABSENTS")

        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load("ABSENTS")
    }

    private fun observer() {
        mViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }
}