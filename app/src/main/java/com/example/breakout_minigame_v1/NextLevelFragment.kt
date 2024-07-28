package com.example.breakout_minigame_v1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import java.lang.ClassCastException

class NextLevelFragment : DialogFragment() {
    interface NextLevelDialogListener{
        fun onNextLevelClick()
        fun onMainMenuClick()
    }
    private var listener: NextLevelDialogListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NextLevelDialogListener
                if(listener == null)
                {
                    throw ClassCastException("$context problem")
                }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nex_level_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.next_level).setOnClickListener{
            listener?.onNextLevelClick()
            dismiss()
        }
        view.findViewById<Button>(R.id.main_menu_button).setOnClickListener{
            listener?.onMainMenuClick()
            dismiss()
        }
        super.onViewCreated(view, savedInstanceState)
    }
    companion object{
        fun newInstance(): NextLevelFragment {
            return NextLevelFragment()
        }
    }
}