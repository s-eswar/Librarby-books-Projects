package com.example.vishwa.imaginators

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sathi.appimagin8ors.R
import kotlinx.android.synthetic.main.subtopic.view.*
import kotlinx.android.synthetic.main.topics.view.*

data class MainAdapter(val context: Context,val items: ArrayList<Any>, val indices: ArrayList<String>): RecyclerView.Adapter<CustomViewHolder>() {

     val TYPE_TOPIC = 1
     val TYPE_SUBTOPIC = 0

    override fun getItemViewType(position: Int): Int {
        val item = items.get(position) as LinkedHashMap<String,Int>
        if (item.containsValue(-1)){
            return TYPE_TOPIC
        } else {
            return TYPE_SUBTOPIC
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        if (viewType==1) {
            val tlayout = LayoutInflater.from(parent?.context).inflate(R.layout.topics, parent, false)
            return CustomViewHolder(tlayout)
        } else {
            val slayout = LayoutInflater.from(parent?.context).inflate(R.layout.subtopic, parent, false)
            return CustomViewHolder(slayout)
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }


    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        val topicTitle = items.get(position) as LinkedHashMap<String,Int>
        holder?.setIsRecyclable(false)
        if (holder?.itemViewType==1) {
//           holder?.view?.imageSymbol.setImageResource(R.drawable.pink)
            for ((key,value) in topicTitle){
                holder?.view?.txtName?.text=key
            }
        }
        else{
            holder?.view?.textView_index?.text=indices.get(position)
//            holder?.view?.imageView_bulb?.setImageResource(R.drawable.bulb_on)
            for ((key,value) in topicTitle){
                holder?.view?.textView_subtopic_title?.text = key
                val randomval = ((1..value as Int).shuffled().last())
                val mAlertDialogTextView = holder?.view?.textView_subtopic_title
                mAlertDialogTextView?.setOnClickListener {
                    val mAlertDialog = AlertDialog.Builder(context)
                    mAlertDialog.setMessage(key.toString())
                    mAlertDialog.setNegativeButton("cancel",{ dialogInterface: DialogInterface, i: Int -> dialogInterface.dismiss()})
                    mAlertDialog.show()
                }
                holder?.view?.textView_count?.text="$randomval of $value"
                holder?.view?.progressBar2?.setProgress(randomval)
                holder?.view?.progressBar2?.max = value
            }
            }

    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}