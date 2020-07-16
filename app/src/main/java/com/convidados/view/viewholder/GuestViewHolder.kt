package com.convidados.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.convidados.R
import com.convidados.service.model.GuestModel
import com.convidados.view.listener.GuestListener

class GuestViewHolder(itemView: View, private val listener: GuestListener) :
    RecyclerView.ViewHolder(itemView) {

    //atribui os valores aos elementos do recycler

    fun bind(guest: GuestModel) {
        val textName = itemView.findViewById<TextView>(R.id.text_name)
        textName.text = guest.name

        textName.setOnClickListener {
            listener.onClick(guest.id)
        }

        textName.setOnLongClickListener {

            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Deseja remover o convidado ?")
                .setPositiveButton("sim") { dialog, which ->
                    listener.onDelete(guest.id)
                }
                .setNegativeButton("não", null)
                .show()

            true
        }
    }
}