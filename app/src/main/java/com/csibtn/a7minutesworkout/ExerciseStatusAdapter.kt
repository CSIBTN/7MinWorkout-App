package com.csibtn.a7minutesworkout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.csibtn.a7minutesworkout.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(val items : List<ExerciseModel>)  : RecyclerView.Adapter<ExerciseStatusAdapter.ExerciseHolder>() {

    inner class ExerciseHolder(binding : ItemExerciseStatusBinding) : RecyclerView.ViewHolder(binding.root){
        val tvItem = binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseHolder {
        return ExerciseHolder(ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ExerciseHolder, position: Int) {
        val model : ExerciseModel = items[position]
        holder.tvItem.text = model.getId().toString()
    }
}