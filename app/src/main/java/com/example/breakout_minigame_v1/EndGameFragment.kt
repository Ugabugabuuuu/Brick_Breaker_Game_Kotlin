package com.example.breakout_minigame_v1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class EndGameFragment : DialogFragment() {

    interface EndGameDialogListener {
        fun onRestartClick()
        fun onMainMenuClick()
    }

    private var listener: EndGameDialogListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? EndGameDialogListener
        if (listener == null) {
            throw ClassCastException("$context problem")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.end_game_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.restart_button).setOnClickListener {
            listener?.onRestartClick()
            dismiss()
        }

        view.findViewById<Button>(R.id.main_menu_button).setOnClickListener {
            listener?.onMainMenuClick()
            dismiss()
        }
    }

    companion object {
        fun newInstance(): EndGameFragment {
            return EndGameFragment()
        }
    }
}
