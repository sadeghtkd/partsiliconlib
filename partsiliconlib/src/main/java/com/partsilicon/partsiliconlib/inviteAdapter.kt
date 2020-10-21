package com.partsilicon.partsiliconlib


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.partsilicon.partsiliconlib.ItemFragment.OnListFragmentInteractionListener
import com.partsilicon.partsiliconlib.pojo.AppsObj
import kotlinx.android.synthetic.main.invite_item.view.*


/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class inviteAdapter(
        private  val context : Context,
        private val mValues: AppsObj,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<inviteAdapter.ViewHolder>() {

/*    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as String
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.invite_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues.getres()?.get(position)
        holder.tv_invitor.text = item?.getHostGift()
        holder.tv_invited.text = item?.getTargetGift()


        Glide.with(context).load(item?.getTargetAppIcon()).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.ic_notifications).error(R.drawable.ic_notifications))
                .into(holder.iv_app )

        with(holder.mView) {
            tag = item
            // setOnClickListener(mOnClickListener)
        }
        holder.ib_share.setOnClickListener(){

            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, item?.getSendingMessage() + "\nکد معرف: " + item?.getCode()+"\n\n"+item?.getLink() ) // getString(R.string.refCodeShare, General.MainURL + "TeleDl?market=" + BuildConfig.MARKET_NO,tvYourCode.text))
            intent.setType("text/plain")
            val shareIntent = Intent.createChooser(intent, null)
            context.startActivity(shareIntent)
        }
        holder.iv_app.setOnClickListener(){
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("bazaar://details?id=" + item?.getTarget())
            intent.setPackage("com.farsitel.bazaar")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int =  mValues.getres()?.count()!!

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val tv_invitor: TextView = mView.tv_invitor
        val tv_invited: TextView = mView.tv_invited
        val iv_app :ImageView = mView.iv_app
        var ib_share :ImageButton = mView.ib_share

        override fun toString(): String {
            return super.toString()
        }


    }
}
