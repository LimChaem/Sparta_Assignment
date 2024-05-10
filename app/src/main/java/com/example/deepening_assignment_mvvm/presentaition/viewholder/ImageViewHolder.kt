package com.example.deepening_assignment_mvvm.presentaition.viewholder

import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deepening_assignment_mvvm.databinding.ItemRecyclerviewBinding
import com.example.deepening_assignment_mvvm.presentaition.entity.DocumentsEntity
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ImageViewHolder(
    private val binding: ItemRecyclerviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var item: DocumentsEntity
//    private val dataFormat1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//    private val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")


    fun bind(documentsEntity: DocumentsEntity, onClick: (DocumentsEntity) -> Unit) {
//        val date = LocalDate.parse(item.dateTime, dateFormat)
        item = documentsEntity



        binding.tvSiteName.text = item.displaySiteName
        binding.tvDateTime.text = item.dateTime
        Glide.with(binding.root)
            .load(item.thumbnailUrl)
            .into(binding.ivThumbnail)
        binding.ivLike.visibility = if (documentsEntity.isLike) View.VISIBLE else View.GONE

        binding.root.setOnClickListener {
            documentsEntity.toggleLike()
            Log.d("isLike", "isLike = ${documentsEntity.isLike}")
            onClick(documentsEntity)
        }

    }
}