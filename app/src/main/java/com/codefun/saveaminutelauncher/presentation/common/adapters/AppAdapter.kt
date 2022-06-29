package com.codefun.saveaminutelauncher.presentation.common.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codefun.saveaminutelauncher.databinding.ItemAppViewBinding
import com.codefun.saveaminutelauncher.domain.model.App
import com.codefun.saveaminutelauncher.util.setGone
import com.codefun.saveaminutelauncher.util.setVisible
import java.io.File
import javax.inject.Inject

/**
 * Created by Agam on 27,June,2022
 */

class AppAdapter @Inject constructor() : ListAdapter<App, AppAdapter.AppViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val binding =
            ItemAppViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return AppViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val currentApp = getItem(position)
        holder.bind(currentApp)
    }

    inner class AppViewHolder(private val binding: ItemAppViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(app: App) {

            binding.apply {
                iconImg.setImageURI(Uri.parse(app.iconUri))
                nameTxt.text = app.name
                if (app.screenTime != null) {
                    screenTimeGroup.setVisible()
                    screenTimeTxt.text = app.screenTime
                } else {
                    screenTimeGroup.setGone()
                }

                rootLayout.setOnClickListener {
                    onAppClickListener?.let { it(app) }
                }
            }

        }
    }

    private var onAppClickListener: ((App) -> Unit)? = null
    fun setOnAppClickListener(listener: (App) -> Unit) {
        onAppClickListener = listener
    }

    class DiffCallback : DiffUtil.ItemCallback<App>() {
        override fun areItemsTheSame(oldItem: App, newItem: App) =
            oldItem.packageName == newItem.packageName

        override fun areContentsTheSame(oldItem: App, newItem: App) =
            oldItem == newItem
    }
}