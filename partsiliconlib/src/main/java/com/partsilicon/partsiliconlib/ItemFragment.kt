package com.partsilicon.partsiliconlib

import android.Manifest
import android.content.Context
import android.content.Context.TELEPHONY_SERVICE
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partsilicon.partsiliconlib.classes.Setting
import com.partsilicon.partsiliconlib.classes.getMarketName
import com.partsilicon.partsiliconlib.pojo.AppsObj
import com.partsilicon.partsiliconlib.viewmodels.AppsViewModel


/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [ItemFragment.OnListFragmentInteractionListener] interface.
 */
class ItemFragment : Fragment() {

    private var userId = ""

    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var viewModel : AppsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            userId = it.getString(ARG_UserID , "")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view1 = inflater.inflate(R.layout.fragment_invite_item_list, container, false)
        val listInvite = view1.findViewById<RecyclerView>(R.id.listInvite)

        viewModel = ViewModelProviders.of(requireActivity()).get(AppsViewModel::class.java)



        // Set the adapter
        if (listInvite is RecyclerView) {
            with(listInvite) {
                layoutManager = LinearLayoutManager(context)
                try {

                    var set : Setting = Setting()

                    var imei =  set.getIMEI(context)
                     viewModel.getApps(context , context.packageName.toString()
                             + getMarketName(context), imei , userId)
                    viewModel.AppsList?.observe(viewLifecycleOwner,  Observer<AppsObj>{
                        adapter = inviteAdapter(context ,  it , listener)
                    })
                }catch (e:Exception)
                {
                    e.message.toString();
                }


                /*var A: ArrayList<String> = ArrayList<String>()
                A.add("dfsa")
                A.add("aadsgds")
                A.add("fasd")
                adapter = inviteAdapter(A , listener)*/
            }
        }
        return view1
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
        fun onListFragmentInteraction(item: String?)
    }

    companion object {

        const val ARG_UserID = "userId"

        @JvmStatic
        fun newInstance(userId: String) =
                ItemFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_UserID, userId)
                    }
                }
    }
}
