package com.example.flexpay.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flexpay.R

class MyAdapter(private val userList:ArrayList<User>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_transaction,parent,false)
    return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return userList.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem=userList[position]
        holder.amount.text= currentItem.amount.toString()
        holder.description.text=currentItem.description
        holder.date.text=currentItem.date

    }

    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

        val description:TextView=itemView.findViewById(R.id.textView_description)
        val amount:TextView=itemView.findViewById(R.id.textView_amount)
        val date:TextView=itemView.findViewById(R.id.textView_date)




    }
}