package com.pie.whatsappstatussaver.localization

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pie.whatsappstatussaver.R
import com.pie.whatsappstatussaver.databinding.LangCardBinding
import com.pie.whatsappstatussaver.interfaces.ItemClick

class LangAdapter(
    private val list: List<LangCountryModel>,
    private val click: ItemClick,
    private val sp: SharedPref
) :
    RecyclerView.Adapter<LangAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LangCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = list[position]
        with(holder.binding) {
            name.text = currentItem.name
            flagIc.setImageResource(currentItem.flag)
            selectionBtn.setOnClickListener {
                sp.setLangName(currentItem.name)
                ForLanguageSettingsClass.setLocale(
                    holder.itemView.context,
                    currentItem.abr
                )
                click.click(position)
                notifyDataSetChanged()
            }
            if (currentItem.name == sp.getLangName()) {
                selectionBtn.setImageResource(R.drawable.language_selected_ic)
            } else {
                selectionBtn.setImageResource(R.drawable.language_unselected_ic)
            }
        }
    }

    inner class MyViewHolder(val binding: LangCardBinding) : RecyclerView.ViewHolder(binding.root)
}