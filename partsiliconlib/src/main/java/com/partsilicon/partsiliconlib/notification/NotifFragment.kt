package com.partsilicon.partsiliconlib.notification

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.partsilicon.partsiliconlib.R
import com.partsilicon.partsiliconlib.notification.db.AppDatabase
import com.partsilicon.partsiliconlib.notification.db.saveNotifToDb
import com.partsilicon.partsiliconlib.notification.model.Notif
import com.partsilicon.partsiliconlib.notification.model.NotifList
import com.partsilicon.partsiliconlib.notification.webservice.MyCallback
import com.partsilicon.partsiliconlib.notification.webservice.NotifWebservices
import com.partsilicon.partsiliconlib.utils.SharedPreferencesUtility
import me.leolin.shortcutbadger.ShortcutBadger
import retrofit2.Call
import retrofit2.Response

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [NotifFragment.OnListFragmentInteractionListener] interface.
 */
class NotifFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_notif, container, false)

        val rcView = view.findViewById<RecyclerView>(R.id.list)
        val progressbar = view.findViewById<ProgressBar>(R.id.progressBarNotifList)
        // Set the adapter
        if (rcView is RecyclerView) {
            with(rcView) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                //Load from db
                val task = object : AsyncTask<Void,Void,String>(){

                    private var lst: List<Notif> = ArrayList<Notif>()

                    override fun doInBackground(vararg params: Void?):String {
                        val db = AppDatabase.getInstance(context!!)
                        lst = db?.notifDAO()?.getAll()?.reversed() ?: ArrayList<Notif>()
                        return  ""
                    }

                    override fun onPostExecute(result: String?) {
                        if(lst.size > 0 )
                            adapter = NotifRecyclerViewAdapter(lst, listener)
                    }
                }
                task.execute()
                //load from WS
                NotifWebservices(context).getNotifications(object : MyCallback<NotifList>(context){
                    override fun onFailure(call: Call<NotifList>, t: Throwable) {
                        super.onFailure(call, t)
                        progressbar.visibility = View.GONE
                    }
                    override fun onResponse(call: Call<NotifList>, response: Response<NotifList>) {
                        progressbar.visibility = View.GONE
                        //reset badge counter
                        ShortcutBadger.removeCount(context)
                        SharedPreferencesUtility(context).setUnreadCount(0)
                        //
                        if(response.isSuccessful)
                        {
                            saveNotifToDb(context,false, response.body()?.results!! )
                            adapter = NotifRecyclerViewAdapter(response.body()?.results?.reversed() ?: ArrayList<Notif>(), listener)
                            if(response.body()?.results.isNullOrEmpty()) {
                                Toast.makeText(activity, getString(R.string.notif_empty) , Toast.LENGTH_LONG).show()
                                activity?.finish()
                            }
                        }
                    }
                })

            }
        }

        return view
    }

    fun loadFromDB(){

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Notif?)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
                NotifFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}
