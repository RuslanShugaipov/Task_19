package com.example.task19

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task19.databinding.MsgItemBinding

class MsgAdapter : RecyclerView.Adapter<MsgAdapter.MsgHolder>() {
    private var msgList = ArrayList<MsgItem>()

    companion object {
        private var msgNewList: List<MsgItem>? = null

        fun setList(_msgList: List<MsgItem>) {
            msgNewList = _msgList
        }

        fun getList(): List<MsgItem>? {
            return msgNewList
        }
    }

    class MsgHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = MsgItemBinding.bind(item)

        fun bind(msgItem: MsgItem) = with(binding) {
            tvTitle.text = msgItem.title
            tvNumber.text = msgItem.id.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsgHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_item, parent, false)
        return MsgHolder(view)
    }

    override fun onBindViewHolder(holder: MsgHolder, position: Int) {
        holder.bind(msgList[position])
    }

    override fun getItemCount(): Int {
        return msgList.size
    }

    fun addMsg(msgItem: MsgItem) {
        msgList.add(msgItem)
        notifyDataSetChanged()
    }
}