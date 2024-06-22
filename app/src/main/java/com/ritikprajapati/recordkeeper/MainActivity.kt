package com.ritikprajapati.recordkeeper

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.snackbar.Snackbar
import com.ritikprajapati.recordkeeper.databinding.ActivityMainBinding


const val RUNNING = "running"
const val CYCLING = "cycling"

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load the default fragment
        supportFragmentManager.commit {
            replace(R.id.frame_content, RunningFragment())
        }

        binding.bottomNav1.setOnItemSelectedListener(this)

    }


    //to see toolbar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true;
    }
    //make the toolbar options button working
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menuClickHandled = when(item.itemId){
            R.id.reset_running->{

                AlertDialog.Builder(this)
                    .setTitle("Warning!")
                    .setView(R.layout.warn_to_delete)
                    //.setMessage("Hola")
                    .setPositiveButton("Yes") { _, _ ->
                        getSharedPreferences(RUNNING, Context.MODE_PRIVATE).edit {
                            clear()
                            val snackbar = Snackbar.make(binding.frameContent, "Records Cleared Successfully!", Snackbar.LENGTH_LONG)
                            snackbar.anchorView = binding.bottomNav1
                                snackbar.show()
                            when (binding.bottomNav1.selectedItemId) {
                                R.id.nav_running -> onRunningClicked()
                                R.id.nav_cycling -> onCyclingClicked()
                                else -> {}
                            }
                        }
                    }
                    .setNegativeButton("No!!") {dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
                true
            }
            R.id.reset_cycling->{
                AlertDialog.Builder(this)
                    .setTitle("Warning!")
                    .setView(R.layout.warn_to_delete)
                    //.setMessage("Hola")
                    .setPositiveButton("Yes") { _, _ ->
                        getSharedPreferences(CYCLING, Context.MODE_PRIVATE).edit { clear()
                            val snackbar = Snackbar.make(binding.frameContent, "Records Cleared Successfully!", Snackbar.LENGTH_LONG)
                            snackbar.anchorView = binding.bottomNav1
                            snackbar.show()
                            when (binding.bottomNav1.selectedItemId) {
                                R.id.nav_running -> onRunningClicked()
                                R.id.nav_cycling -> onCyclingClicked()
                                else -> {}
                            }
                        }
                    }
                    .setNegativeButton("No!!") {dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
                true
                //change1
            }
            R.id.reset_all->{
                AlertDialog.Builder(this)
                    .setTitle("Warning!")
                    .setView(R.layout.warn_to_delete)
                    //.setMessage("Hola")
                    .setPositiveButton("Yes") { _, _ ->
                        getSharedPreferences(RUNNING, Context.MODE_PRIVATE).edit { clear() }
                        getSharedPreferences(CYCLING, Context.MODE_PRIVATE).edit { clear() }
                        val snackbar = Snackbar.make(binding.frameContent, "Records Cleared Successfully!", Snackbar.LENGTH_LONG)
                        snackbar.anchorView = binding.bottomNav1
                        snackbar.show()
                        when (binding.bottomNav1.selectedItemId) {
                            R.id.nav_running -> onRunningClicked()
                            R.id.nav_cycling -> onCyclingClicked()
                            else -> {}
                        }
                    }
                    .setNegativeButton("No!!") {dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
                true
            }
            else->{
                super.onOptionsItemSelected(item)
            }
        }


        return menuClickHandled
    }


    private fun onRunningClicked() {
        supportFragmentManager.commit {
            replace(R.id.frame_content, RunningFragment())
        }
    }

    private fun onCyclingClicked() {
        supportFragmentManager.commit {
            replace(R.id.frame_content, CyclingFragment())
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_cycling -> {
                onCyclingClicked()
                true
            }
            R.id.nav_running -> {
                onRunningClicked()
                true
            }
            else -> false
        }
    }
}
