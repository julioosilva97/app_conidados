package com.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.convidados.R
import com.convidados.view.adapter.GuestAdapter
import com.convidados.view.listener.GuestListener
import com.convidados.viewmodel.GuestViewModel

class AllGuestFragment : Fragment() {

    private lateinit var mViewModel: GuestViewModel
    private val mAdapter: GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(GuestViewModel::class.java)

        //o root que pega os fragments
        val root = inflater.inflate(R.layout.fragment_all, container, false)

        // 1 - Obter a recycler
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guest)

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
                mViewModel.load("ALL")
            }
        }

        mAdapter.attachListener(mListener)
        observer()

        mViewModel.load("ALL")

        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load("ALL")
    }

    private fun observer() {
        mViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }
}