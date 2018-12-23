package com.aigerimbb.android.tasbeeh.ui.main.tasbeeh_list.adapter

import android.content.res.Resources
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aigerimbb.android.tasbeeh.R
import com.aigerimbb.android.tasbeeh.data.database.entity.Tasbeeh
import com.iko.android.core.viewholder.EmptyViewHolder
import com.iko.android.modularapp.base.CoreViewHolder

class TasbeehAdapter(private val listener: Listener,
                     private val resources: Resources): RecyclerView.Adapter<CoreViewHolder<*>>(){

    private val items = mutableListOf<Tasbeeh>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoreViewHolder<*> {
        return when(viewType){
            ViewType.TasbeehItem().value -> TasbeehViewHolder.create(parent, listener)
            else -> EmptyViewHolder.create(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when{
            items.isEmpty() -> ViewType.Empty().value
            else -> ViewType.TasbeehItem().value
        }
    }

    override fun onBindViewHolder(holder: CoreViewHolder<*>, position: Int) {
        when(holder){
            is TasbeehViewHolder -> holder.onBind(items[position])
            is EmptyViewHolder -> holder.onBind(resources.getString(R.string.empty_list))
        }
    }

    override fun getItemCount() = items.count()

    fun insertItems(items: MutableList<Tasbeeh>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onEditClick(tasbeeh: Tasbeeh)
        fun deleteClick(tasbeeh: Tasbeeh)
        fun onTasbeehClick(tasbeeh: Tasbeeh)
    }

    sealed class ViewType(val value: Int){
        class Empty: ViewType(0)
        class TasbeehItem: ViewType(1)
    }
}