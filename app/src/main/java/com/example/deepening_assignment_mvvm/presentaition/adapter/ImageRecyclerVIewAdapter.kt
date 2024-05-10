package com.example.deepening_assignment_mvvm.presentaition.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deepening_assignment_mvvm.databinding.ItemRecyclerviewBinding
import com.example.deepening_assignment_mvvm.presentaition.entity.DocumentsEntity
import com.example.deepening_assignment_mvvm.presentaition.viewholder.ImageViewHolder

class ImageRecyclerVIewAdapter(private val onClick: (DocumentsEntity) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = listOf<DocumentsEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ImageViewHolder).bind(items[position]){
            onClick(it)
            Log.d("isLike", "")
            notifyItemChanged(position)// 이 위치에서 Adapter의 상태가 갱신될 때마다 호출
        }
    }
}