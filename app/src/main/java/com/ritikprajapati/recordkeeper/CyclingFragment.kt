package com.ritikprajapati.recordkeeper

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ritikprajapati.recordkeeper.databinding.FragmentCyclingBinding
import com.ritikprajapati.recordkeeper.databinding.FragmentRunningBinding

class CyclingFragment : Fragment() {

    private lateinit var binding: FragmentCyclingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentCyclingBinding.inflate(inflater, container, false)
        return binding.root
        /*val view=inflater.inflate(R.layout.fragment_running, container, false)
        return view; */
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    private fun setupClickListeners(){
        binding.containerLongestRide.setOnClickListener {launchRunningRecordScreenn("Longest Ride") }
        binding.containerBiggestClimb.setOnClickListener {launchRunningRecordScreenn("Biggest Climb") }
        binding.containerBestSpeed.setOnClickListener { launchRunningRecordScreenn("Best Speed")}

    }
    private fun displayRecords() {
        val cyclingPreferences = requireContext().getSharedPreferences("cycling", Context.MODE_PRIVATE)

        binding.textViewLongestRideValue.text = cyclingPreferences.getString("Longest Ride Record", null)
        binding.textViewLongestRideDate.text = cyclingPreferences.getString("Longest Ride Date", null)
        binding.textViewBiggestClimbValue.text = cyclingPreferences.getString("Biggest Climb Record", null)
        binding.textViewBiggestClimbDate.text = cyclingPreferences.getString("Biggest Climb Date", null)
        binding.textViewBestSpeedValue.text = cyclingPreferences.getString("Best Speed Record", null)
        binding.textViewBestSpeedDate.text = cyclingPreferences.getString("Best Speed Date", null)
    }

    private fun launchRunningRecordScreenn(height: String) {
        val intent= Intent(context, EditCyclingRecordActivity::class.java)
        intent.putExtra("Height", height )
        startActivity(intent)
    }
}

//if error in intent->watch lt-139 or if not replace 'this' with 'context' inside bracket