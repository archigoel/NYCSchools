package com.example.nycschools.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschools.data.School
import com.example.nycschools.databinding.SchoolDetailLayoutBinding
import java.util.ArrayList

class SchoolsListViewHolder (private val binding: SchoolDetailLayoutBinding,
                             private val onItemClicked: (position: Int) -> Unit):
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    init {
        binding.root.setOnClickListener(this)
    }

    fun bind(school: School, context: Context) {
        binding.schoolName.text = school.school_name
        binding.schoolWebsite.text = school.website
        binding.schoolContactInfo.text = school.phone_number+" | "+school.school_email
    }

    override fun onClick(v: View?) {
        val position = adapterPosition
        onItemClicked(position)
    }

}