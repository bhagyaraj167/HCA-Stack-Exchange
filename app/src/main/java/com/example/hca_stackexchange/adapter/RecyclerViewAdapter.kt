package com.example.hca_stackexchange.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hca_stackexchange.R
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter(val context:Context,val questionsList:ArrayList<String>): RecyclerView.Adapter<RecyclerViewAdapter.StackExchangeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StackExchangeViewHolder {
       return StackExchangeViewHolder(
           LayoutInflater.from(context).inflate(
               R.layout.row_layout,
               parent,
               false
           )
       )
    }

    override fun getItemCount(): Int {
        return questionsList.count()
    }

    override fun onBindViewHolder(holder: StackExchangeViewHolder, position: Int) {
     holder.tvQuestion.text = questionsList[position]
    }
    class StackExchangeViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tvQuestion = view.questionText

    }

}