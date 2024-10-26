package id.vincent.neoz

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class KalkulatorFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.kalkulator, container, false)

    }


override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    // Inisialisasi tombol dan tambahkan onClickListener
    val btnWin: Button = view.findViewById(R.id.win)
    val btnLose: Button = view.findViewById(R.id.lose)
    val btnMagicWheel: Button = view.findViewById(R.id.mgcw)
    val btnZodiac: Button = view.findViewById(R.id.zdc)
    val btnTotalMatch: Button = view.findViewById(R.id.totalmatchh)

    btnWin.setOnClickListener {
        val intent = Intent (requireContext(),win::class.java)
        startActivity(intent)
    }

    btnLose.setOnClickListener {
        val intent = Intent (requireContext(),win::class.java)
        startActivity(intent)
    }

    btnMagicWheel.setOnClickListener {
        val intent = Intent (requireContext(),win::class.java)
        startActivity(intent)
    }

    btnZodiac.setOnClickListener {
        val intent = Intent (requireContext(),win::class.java)
        startActivity(intent)
    }

    btnTotalMatch.setOnClickListener {
        val intent = Intent (requireContext(),win::class.java)
        startActivity(intent)
    }
}
}