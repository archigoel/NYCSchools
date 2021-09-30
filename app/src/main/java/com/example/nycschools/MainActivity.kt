package com.example.nycschools

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nycschools.adapter.SchoolsListAdapter
import com.example.nycschools.data.School
import com.example.nycschools.databinding.ActivityMainBinding
import com.example.nycschools.viewmodel.SchoolsViewModel
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: SchoolsViewModel
    private  lateinit var dialog : Dialog

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        dialog = Dialog(this, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(SchoolsViewModel::class.java)
        binding.progressBar.visibility = View.VISIBLE
        viewModel.schoolsList.observe(this, Observer {
            Collections.sort(it, NameComparator())
            binding.recyclerView.adapter =
                SchoolsListAdapter(this, it as MutableList<School>) { position ->
                    onListItemClick(
                        position,
                        it as MutableList<School>
                    )
                }
            binding.progressBar.visibility = View.GONE // setting the visibility to GONE after data is populated
        })

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }
// dismisses the dialog before activity starts
    override fun onDestroy() {
        super.onDestroy()
        if (dialog != null && dialog.isShowing) {
            dialog.dismiss();
        }
    }

    private fun onListItemClick(position: Int, schoolList: MutableList<School>) {
       val view: View = layoutInflater.inflate(R.layout.sat_scores_layout, null)
        dialog.setContentView(view)
        dialog.setCancelable(true)
        dialog.show()
        lateinit var readingScore : String
        lateinit var writingScore : String
        lateinit var mathScore : String
        viewModel.satScoresList.observe(this, Observer {
            var dbn = schoolList[position].dbn
            for (score in it) {
                if (score.dbn == dbn) {
                    readingScore = score.sat_critical_reading_avg_score
                    writingScore = score.sat_writing_avg_score
                    mathScore = score.sat_math_avg_score
                }
            }
        })
        val mathScoreText = dialog.findViewById(R.id.sat_math_score) as TextView
        mathScoreText.text = "Math : "+ mathScore
        val readingScoreText = dialog.findViewById(R.id.sat_reading_score) as TextView
        readingScoreText.text= "Reading : "+readingScore
        val writingScoreText = dialog.findViewById(R.id.sat_writing_score) as TextView
        writingScoreText.text = "Writing : "+writingScore


    }

    // sort the list by school name
    class NameComparator: Comparator<School> {
        override fun compare(o1: School?, o2: School?): Int {
            if(o1 == null || o2 == null){
                return 0;
            }
            return o1.school_name.compareTo(o2.school_name)
        }
    }
}