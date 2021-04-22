package com.tolegensapps.enjoyjumping.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tolegensapps.enjoyjumping.R
import com.tolegensapps.enjoyjumping.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var mBinding: FragmentProfileBinding
    private lateinit var mViewModel: ProfileViewModel
    private lateinit var mUserId: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        mUserId = arguments?.getString("objectId")!!
        mViewModel = ViewModelProvider(this, ProfileFactory(requireActivity().application, mUserId)).get(ProfileViewModel::class.java)
        bindUI()
        behaviourUI()
        mViewModel.initProfileData()

        return mBinding.getRoot()
    }

    override fun onResume() {
        super.onResume()
        startShimmerAnimation()
    }

    private fun bindUI() {
        mViewModel.userName.observe(viewLifecycleOwner, Observer {
            mBinding.userName.text = it
            stopShimmerAnimation()
        })
        mViewModel.userEmail.observe(viewLifecycleOwner, Observer {
            mBinding.userEmail.text = it
        })
        mViewModel.ticketEveningSub.observe(viewLifecycleOwner, Observer {
            mBinding.ticketEveningSub.text = it
        })
        mViewModel.ticketBalance.observe(viewLifecycleOwner, Observer {
            mBinding.ticketBalance.text = it
        })
        mViewModel.ticketValidity.observe(viewLifecycleOwner, Observer {
            mBinding.ticketValidity.text = it
        })
    }

    private fun behaviourUI() {
        mBinding.btnLogout.setOnClickListener{
            mViewModel.onClickExit()
            mViewModel.intentLogout.observe(viewLifecycleOwner, Observer {
                startActivity(it)
            })
        }

        mBinding.swipeRefresh.setOnRefreshListener {
            refreshFragment()
//            Toast.makeText(context, "Its refresh", Toast.LENGTH_SHORT).show()

        }
    }

    private fun startShimmerAnimation() {
        mBinding.shimmerUserData.startShimmerAnimation()
        mBinding.shimmerTicketData.startShimmerAnimation()
        mBinding.shimmerUserData.visibility = View.VISIBLE
        mBinding.shimmerTicketData.visibility = View.VISIBLE
    }

    private fun stopShimmerAnimation() {
        mBinding.shimmerUserData.stopShimmerAnimation()
        mBinding.shimmerTicketData.stopShimmerAnimation()
        mBinding.shimmerUserData.visibility = View.GONE
        mBinding.shimmerTicketData.visibility = View.GONE
    }

    private fun refreshFragment() {
        mViewModel.initProfileData()
        mBinding.swipeRefresh.isRefreshing = false
    }
}