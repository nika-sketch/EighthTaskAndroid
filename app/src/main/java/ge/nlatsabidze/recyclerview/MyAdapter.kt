package ge.nlatsabidze.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.nlatsabidze.recyclerview.databinding.ListItemBinding
import ge.nlatsabidze.recyclerview.databinding.SecondItemBinding

class MyAdapter(private val newsList: ArrayList<Data>, private val secondList: ArrayList<Second>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    companion object {
        const val FIRST_ITEM = 10
        const val SECOND_ITEM = 20
    }

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
        fun onLongClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        if (viewType == SECOND_ITEM) {
            binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        } else {
            SecondItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }
        return MyViewHolder(binding, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.tvHeading.text = currentItem.heading
    }

    override fun getItemCount() = newsList.size

    inner class MyViewHolder(val binding: ListItemBinding, listener: onItemClickListener):RecyclerView.ViewHolder(binding.root) {
        val titleImage = binding.titleImage
        val tvHeading = binding.tvHeading
        init {
            itemView.setOnClickListener {
                listener.onItemClick(absoluteAdapterPosition)
            }
        }

        init {
            itemView.setOnLongClickListener {
                listener.onLongClick(absoluteAdapterPosition)
                true
            }
        }
    }

    inner class SecondViewHolder(val binding: SecondItemBinding, listener: onItemClickListener):RecyclerView.ViewHolder(binding.root) {
        val secondTitleImage = binding.titleImage
        val secondTvHeading = binding.tvHeading

    }

    fun add(data:Data) {
        newsList.add(data)
        notifyItemInserted(newsList.size - 1)
    }

    fun remove(data: Int) {
        newsList.removeAt(data)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (newsList[position] != null) {
            return SECOND_ITEM
        } else {
            return FIRST_ITEM
        }
    }
}