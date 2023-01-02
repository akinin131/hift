package quiz.example.weather.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import quiz.example.hift.APP
import quiz.example.hift.R
import quiz.example.hift.SecondFragment
import quiz.example.hift.databinding.ItemLayoutBinding

import quiz.example.weather.model.Model

import java.util.Collections.emptyList

class Adapter : RecyclerView.Adapter<Adapter.NoteViewHolder>() {


    var ListNote = emptyList<Model>()

    class NoteViewHolder (view:View):RecyclerView.ViewHolder(view){
        private val binding = ItemLayoutBinding.bind(view)
        fun bind(bin: Model) {
            binding.apply{

                binding.bankPhone.text = bin.bankPhone
                binding.bank.text = bin.bank
                binding.type.text = bin.type
                binding.bankUrl.text = bin.bankUrl
                binding.brand.text = bin.brand
                binding.scheme.text = bin.Sсheme
                binding.prepaid.text = bin.prepaid
                binding.country.text = bin.country
                binding.cardNumber.text = bin.cardNumber
                binding.cardNumberLuhn.text = bin.cardNumberLuhn

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent,false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(ListNote [position])
        holder.itemView.setOnClickListener{
            Toast.makeText(APP, ""+position,Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return ListNote.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Model>) {
        ListNote = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: NoteViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            SecondFragment.clickNote(ListNote[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: NoteViewHolder) {
        holder.itemView.setOnClickListener(null)
    }
}




