package com.blogspot.shudiptotrafder.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {


    private var pointP1 = 0
    private var pointP2 = 0

    //saved instance key
    private var pointP1Key = "p1"
    private var pointP2Key = "p2"


    val spName = "sp"
    var pref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        pref = getSharedPreferences(spName, Context.MODE_PRIVATE)

        pointP1 = pref!!.getInt(pointP1Key, 0)
        pointP2 = pref!!.getInt(pointP2Key, 0)

        tv_player1.text = "Player 1:$pointP1"
        tv_player2.text = "Player 2:$pointP2"


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Want to Play again", Snackbar.LENGTH_LONG)
                    .setAction("Recreate", { recreate() }).show()
        }
    }

    fun btnClick(view: View) {

        val buSelected = view as Button

        val cellSelected: Int

        when (buSelected) {

            bu1 -> cellSelected = 1
            bu2 -> cellSelected = 2
            bu3 -> cellSelected = 3
            bu4 -> cellSelected = 4
            bu5 -> cellSelected = 5
            bu6 -> cellSelected = 6
            bu7 -> cellSelected = 7
            bu8 -> cellSelected = 8
            bu9 -> cellSelected = 9

            else -> cellSelected = 0
        }

        playGame(cellSelected, buSelected)

        //Toast.makeText(this,"Cell id $cellSelected",Toast.LENGTH_SHORT).show()

    }

    val player1 = ArrayList<Int>()
    val player2 = ArrayList<Int>()

    var ActivePlayer = 1

    private fun playGame(cellID: Int, buSelected: Button) {

        if (!buSelected.isEnabled) {
            return
        }

        if (ActivePlayer == 1) {
            buSelected.text = "X"
            buSelected.setBackgroundResource(R.color.player1)
            player1.add(cellID)
            ActivePlayer = 2
            autoPlay()

        } else {
            buSelected.text = "O"
            buSelected.setBackgroundResource(R.color.player2)
            player2.add(cellID)
            ActivePlayer = 1

        }

        if (buSelected.isEnabled) {
            checkWinner()
        }

        buSelected.isEnabled = false


    }

    private fun checkWinner() {

        var winner = -1

        //row 1
        //player 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }

        //player 2
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }

        //row 2
        //player 1
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }

        //player 2
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }

        //row 3
        //player 1
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }

        //player 2
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        //col 1
        //player 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }

        //player 2
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }

        //col 2
        //player 1
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }

        //player 2
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }

        //col 3
        //player 1
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }

        //player 2
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        //cross connection
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }

        //player 2
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }

        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }

        //player 2
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }

        if (winner != -1) {

            if (winner == 1) {
                pointP1 += 1
                tv_player1.text = "Player 1:$pointP1"
                Snackbar.make(container, "player 1 win", Snackbar.LENGTH_SHORT).show()
            } else {
                pointP2 += 1
                tv_player2.text = "Player 2:$pointP2"
                Snackbar.make(container, "player 1 win", Snackbar.LENGTH_SHORT).show()
            }

            val editor = pref!!.edit()
            editor.putInt(pointP1Key,pointP1)
            editor.putInt(pointP2Key,pointP2)
            editor.apply()

            //recreate()
        }
    }

    fun autoPlay() {

        //first device have to find empty cell
        val emptyCell = ArrayList<Int>()

        for (cellID in 1..9) {

            if (!(player1.contains(cellID) || player2.contains(cellID))) {
                emptyCell.add(cellID)
            }
        }

        val random = Random()
        val randIndex: Int

        var cellID = 0

        try {
            randIndex = random.nextInt(emptyCell.size - 0) + 0
            cellID = emptyCell[randIndex]
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("Random", e.message)
        }

        val buttonSelected: Button?

        when (cellID) {
            1 -> buttonSelected = bu1
            2 -> buttonSelected = bu2
            3 -> buttonSelected = bu3
            4 -> buttonSelected = bu4
            5 -> buttonSelected = bu5
            6 -> buttonSelected = bu6
            7 -> buttonSelected = bu7
            8 -> buttonSelected = bu8
            9 -> buttonSelected = bu9

            else ->
                buttonSelected = bu1
        }

        playGame(cellID, buttonSelected)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
