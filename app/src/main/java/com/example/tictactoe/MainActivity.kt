package com.example.tictactoe

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    enum class Turn {
        NOUGHT,
        CROSS
    }

    private var firstTurn = Turn.CROSS
    private var currentTurn = Turn.CROSS

    private var boardList = mutableListOf<Button>()

    private lateinit var binding: ActivityMainBinding
    private var crossesScore = 0
    private var noughtsSore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()


    }

    private fun initBoard() {
        boardList.add(binding.a1)
        boardList.add(binding.a2)
        boardList.add(binding.a3)
        boardList.add(binding.b1)
        boardList.add(binding.b2)
        boardList.add(binding.b3)
        boardList.add(binding.c1)
        boardList.add(binding.c2)
        boardList.add(binding.c3)
    }

    fun boardTapped(view: View) {

        if (view !is Button)
            return
        addToBoard(view)

        if (checkedForVictory(NOUGHT)) {
            noughtsSore++
            result("NOUGHT WIN")
        }
        if (checkedForVictory(CROSS)) {
            crossesScore++
            result("CROSS WIN")
        }

        if (fullBoard()) {
            result("Draw")
        }


    }


    private fun fullBoard(): Boolean {
        for (button in boardList) {

            if (button.text == "")
                return false
        }
        return true

    }

    private fun result(title: String) {
        val message = "\n Noughts: $noughtsSore\n\nCrosses $crossesScore"
        AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton("Reset")
        { _, _ ->
            resetBoard()
        }
            .setCancelable(false)
            .show()
    }

    private fun resetBoard() {
        for (button in boardList)
        {
            button.text = ""
        }

        if (firstTurn == Turn.NOUGHT)
            firstTurn = Turn.CROSS
        else if (firstTurn == Turn.CROSS)
            firstTurn = Turn.NOUGHT

        currentTurn = firstTurn
        setTurnTable()
    }

    private fun checkedForVictory(s: String): Boolean {
        if (match(binding.a1, s) && match(binding.a2, s) && match(binding.a3, s))
            return true


        if (match(binding.b1, s) && match(binding.b2, s) && match(binding.b3, s))
            return true


        if (match(binding.c1, s) && match(binding.c2, s) && match(binding.c3, s))
            return true

///////////////////////////////////////////////////////7
        if (match(binding.a1, s) && match(binding.b1, s) && match(binding.c1, s))
            return true


        if (match(binding.a2, s) && match(binding.b2, s) && match(binding.c2, s))
            return true


        if (match(binding.a3, s) && match(binding.b3, s) && match(binding.c3, s))
            return true

        //////////////////////
        if (match(binding.a1, s) && match(binding.b2, s) && match(binding.c3, s))
            return true
        if (match(binding.a3, s) && match(binding.b2, s) && match(binding.c1, s))
            return true

        return false

    }


    private fun match(button: Button, symbol: String): Boolean = button.text == symbol


    private fun addToBoard(button: Button) {

        if (button.text != "")
            return


        if (currentTurn == Turn.NOUGHT) {
            button.text = NOUGHT
            currentTurn = Turn.CROSS
        } else if (currentTurn == Turn.CROSS) {
            button.text = CROSS
            currentTurn = Turn.NOUGHT
        }

        setTurnTable()
    }

    private fun setTurnTable() {
        var turnText = ""
        if (currentTurn == Turn.CROSS)
            turnText = "Turn $CROSS"
        else if (currentTurn == Turn.NOUGHT)
            turnText = "Turn $NOUGHT"

        binding.turnTV.text = turnText

    }

    companion object {
        const val NOUGHT = "O"
        const val CROSS = "X"
    }
}