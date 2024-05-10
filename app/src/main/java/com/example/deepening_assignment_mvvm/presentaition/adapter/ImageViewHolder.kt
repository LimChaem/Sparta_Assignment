package com.example.deepening_assignment_mvvm.presentaition.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deepening_assignment_mvvm.databinding.ItemRecyclerviewBinding
import com.example.deepening_assignment_mvvm.presentaition.entity.DocumentsEntity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ImageViewHolder(
    private val binding: ItemRecyclerviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var item: DocumentsEntity

    fun bind(documentsEntity: DocumentsEntity, onClick: (DocumentsEntity) -> Unit) {
        item = documentsEntity
        binding.tvDateTime.text =
            LocalDateTime.parse(item.dateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        binding.tvSiteName.text = item.displaySiteName
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