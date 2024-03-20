package com.example.flexpay.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flexpay.R
import com.example.flexpay.Transaction

class TransactionAdapter(private val transactions: List<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageViewIcon: ImageView = itemView.findViewById(R.id.imageView_icon)
        var textViewDescription: TextView = itemView.findViewById(R.id.textView_description)
        var textViewDate: TextView = itemView.findViewById(R.id.textView_date)
        var textViewAmount: TextView = itemView.findViewById(R.id.textView_amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.textViewDescription.text = transaction.description
        holder.textViewDate.text = transaction.date
        holder.textViewAmount.text = transaction.amount

        // Set your image based on the transaction type or any other logic
        // holder.imageViewIcon.setImageDrawable(...)
    }

    override fun getItemCount(): Int = transactions.size
}
