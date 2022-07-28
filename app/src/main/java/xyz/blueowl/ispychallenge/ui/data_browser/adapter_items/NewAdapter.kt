package xyz.blueowl.ispychallenge.ui.data_browser.adapter_items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xyz.blueowl.ispychallenge.databinding.ItemDataBinding

class NewAdapter() : RecyclerView.Adapter<NewAdapter.MyViewholder>() {
    private val list = mutableListOf<NMItems>()

    fun setData(listItems: List<NMItems>){
        list.clear()
        list.addAll(listItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewholder(binding)
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewholder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NMItems) {
            binding.apply {
                textViewWins.text = item.numberOfWins.toString()
                textViewAverage.text = item.average.toString()
                textViewDistance.text = item.distance
                textViewDescription.text = item.hint
                textViewCreator.text = item.creator
            }
        }
    }

    data class NMItems(
        val numberOfWins: Int,
        val average: Double,
        val distance: String,
        val hint: String,
        val creator: String
    )
}