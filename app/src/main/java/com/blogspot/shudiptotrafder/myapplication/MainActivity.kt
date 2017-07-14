package com.blogspot.shudiptotrafder.myapplication

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

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

        if (!buSelected.isEnabled){
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

        buSelected.isEnabled = false
        checkWinner()

    }

    private fun checkWinner() {

        var winer = -1

        // row 1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winer=1
        }
        if(player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winer=2
        }


        // row 2
        if(player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winer=1
        }
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winer=2
        }




        // row 3
        if(player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winer=1
        }
        if(player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winer=2
        }



        // col 1
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winer=1
        }
        if(player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winer=2
        }



        // col 2
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winer=1
        }
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winer=2
        }


        // col 3
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winer=1
        }
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winer=2
        }

        //cross connection
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winer = 1
        }

        //player 2
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winer = 2
        }

        //player 1
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winer = 1
        }

        //player 2
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winer = 2
        }

        if (winer != -1) {

            if (winer==1){
                Toast.makeText(this," Player 1  win the game", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this," Player 2  win the game", Toast.LENGTH_LONG).show()

            }

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
}
