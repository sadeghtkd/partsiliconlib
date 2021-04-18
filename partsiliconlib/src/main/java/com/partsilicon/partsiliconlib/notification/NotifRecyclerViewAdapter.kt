package com.partsilicon.partsiliconlib.notification

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.partsilicon.partsiliconlib.R
import com.partsilicon.partsiliconlib.notification.NotifFragment.OnListFragmentInteractionListener
import com.partsilicon.partsiliconlib.notification.model.Notif
import com.partsilicon.partsiliconlib.utils.toShamsi
import kotlinx.android.synthetic.main.fragment_notif_list_item.view.*

class NotifRecyclerViewAdapter(
        private val mValues: List<Notif>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<NotifRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Notif
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate (if(viewType == DisplayTypes.Big.value) R.layout.fragment_notif_list_item_2 else R.layout.fragment_notif_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.item_title.text = item.title
        holder.item_shortDesc.text = item.shortDesc
        holder.btnAction.text = item.buttonLabel
        holder.btnAction.visibility = if(item.actionType == ActionTypes.NO_ACTION.value) View.GONE else View.VISIBLE
        //holder.item_visit_count.text = item.visitCount.toString()
        holder.item_date.text = toShamsi( item.createdAt)
        //Picasso.get().load(item.picUrl).into(holder.iv_item)
        Glide.with(holder.iv_item).load(item.picUrl).into(holder.iv_item)
        holder.item_longText.text = item.longText

        with(holder.btnAction) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    override fun getItemViewType(position: Int): Int {
        return mValues[position].displayType
    }
    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val item_title: TextView = mView.item_title
        val item_shortDesc: TextView = mView.item_shortDesc
        //val item_visit_count: TextView = mView.item_visit_count
        val iv_item: ImageView = mView.iv_item
        val btnAction: Button = mView.btnAction
        val item_date: TextView = mView.item_date
        val item_longText: TextView = mView.tvLongText
    }
}
