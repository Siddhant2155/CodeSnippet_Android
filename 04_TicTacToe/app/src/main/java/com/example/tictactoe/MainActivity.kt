package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var boardStatus = Array(3) { IntArray(3)}
    lateinit var btns: Array<Array<Button>>

    var PLAYER = true
    var turnCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btns = arrayOf(
            arrayOf(button1, button2, button3),
            arrayOf(button4, button5, button6),
            arrayOf(button7, button8, button9)
        )

        for(i in btns) {
            for(btn in i) {
                btn.setOnClickListener(this)
            }
        }

        initializeBoardStatus()

        ResetBtn.setOnClickListener {
            PLAYER = true
            turnCount = 0
            initializeBoardStatus()
        }
    }

    private fun initializeBoardStatus() {
        for(i in 0..2) {
            for(j in 0..2) {
                boardStatus[i][j] = -1
                btns[i][j].apply {
                    text = ""
                    isEnabled = true
                }
            }
        }
        displayPlayer.setText("Player ${if(PLAYER) "X" else "0"} TURN")

    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.button1 -> {
                updateValue(row = 0, col = 0, player = PLAYER)
            }
            R.id.button2 -> {
                updateValue(row = 0, col = 1, player = PLAYER)
            }
            R.id.button3 -> {
                updateValue(row = 0, col = 2, player = PLAYER)
            }
            R.id.button4 -> {
                updateValue(row = 1, col = 0, player = PLAYER)
            }
            R.id.button5 -> {
                updateValue(row = 1, col = 1, player = PLAYER)
            }
            R.id.button6 -> {
                updateValue(row = 1, col = 2, player = PLAYER)
            }
            R.id.button7 -> {
                updateValue(row = 2, col = 0, player = PLAYER)
            }
            R.id.button8 -> {
                updateValue(row = 2, col = 1, player = PLAYER)
            }
            R.id.button9 -> {
                updateValue(row = 2, col = 2, player = PLAYER)
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        btns[row][col].apply {
            isEnabled = false
            setText(if(player) "X" else "0")
        }
        boardStatus[row][col] = if(player) 1 else 0
        PLAYER = !PLAYER
        displayPlayer.setText("Player ${if(!player) "X" else "0"} TURN")

        if (checkWinner()) {
            for(i in 0..2) {
                for(j in 0..2) {
                    btns[i][j].apply {
                        isEnabled = false
                    }
                }
            }
        } else {
            turnCount++
            if(turnCount == 9) {
                displayPlayer.setText("GAME OVER")
            }
        }
    }

    private fun checkWinner(): Boolean {
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {
            if(boardStatus[0][0] != -1) {
                displayPlayer.setText("Player ${if(boardStatus[0][0] == 1) "X" else "0"} WINNER")
                return true
            }
        }
        if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]) {
            if(boardStatus[0][2] != -1) {
                displayPlayer.setText("Player ${if(boardStatus[0][0] == 1) "X" else "0"} WINNER")
                return true
            }
        }
        for(i in 0..2) {
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]) {
                if(boardStatus[i][0] != -1) {
                    displayPlayer.setText("Player ${if(boardStatus[i][0] == 1) "X" else "0"} WINNER")
                    return true
                }
            }
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {
                if(boardStatus[0][i] != -1) {
                    displayPlayer.setText("Player ${if(boardStatus[0][i] == 1) "X" else "0"} WINNER")
                    return true
                }
            }
        }
        return false
    }
}