package com.example.nycschools.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschools.data.School
import com.example.nycschools.databinding.SchoolDetailLayoutBinding
import com.example.nycschools.view.SchoolsListViewHolder

class SchoolsListAdapter(context: Context, var mylist: MutableList<School>,
                         private val onItemClicked: (position: Int) -> Unit)
    : RecyclerView.Adapter<SchoolsListViewHolder>() {
    var mContext: Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SchoolDetailLayoutBinding.inflate((inflater),parent, false)
        return SchoolsListViewHolder(binding, onItemClicked)
    }

    override fun getItemCount(): Int = mylist.size

    override fun onBindViewHolder(holder: SchoolsListViewHolder, position: Int) {
        holder.bind(mylist[position], mContext)
    }
}