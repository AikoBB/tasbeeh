package com.aigerimbb.android.tasbeeh.ui.main.tasbeeh_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aigerimbb.android.tasbeeh.R
import com.aigerimbb.android.tasbeeh.data.database.entity.Tasbeeh
import com.iko.android.modularapp.base.CoreViewHolder
import kotlinx.android.synthetic.main.item_tasbeeh.view.*

class TasbeehViewHolder(itemView: View): CoreViewHolder<Tasbeeh>(itemView){

    lateinit var tasbeeh: Tasbeeh

    override fun onBind(item: Tasbeeh) {
        tasbeeh = item
        itemView.title.text = tasbeeh.name
        itemView.tv_detail.text = tasbeeh.content
    }

    companion object {

        fun create(parent: ViewGroup, listener: TasbeehAdapter.Listener): TasbeehViewHolder{
            val holder = TasbeehViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tasbeeh, parent, false))
            holder.itemView.setOnClickListener { listener.onTasbeehClick(holder.tasbeeh) }
            holder.itemView.btn_delete.setOnClickListener { listener.deleteClick(holder.tasbeeh) }
            holder.itemView.btn_edit.setOnClickListener { listener.onEditClick(holder.tasbeeh) }
            return holder
        }
    }
}