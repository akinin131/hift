package quiz.example.hift

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import org.json.JSONTokener
import quiz.example.hift.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button2.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            getRes()
        }

    }
    private fun getRes(){
        val url = "https://lookup.binlist.net/45717360"

        val queue = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(Request.Method.GET,url,
            {
                    responce->
                val obg = JSONObject(responce)

                val `object` = JSONTokener(responce).nextValue() as JSONObject
                val scheme = `object`.getString("scheme")
                val type = `object`.getString("type")
                val brand = `object`.getString("brand")
                val country = obg.getJSONObject("country")
                val bank = obg.getJSONObject("bank")

                Log.d("MyLog","Response: $scheme")
                Log.d("MyLog","Response: $type")
                Log.d("MyLog","Response: $brand")
                Log.d("MyLog","Response: $country")
                Log.d("MyLog","Response: ${bank.getString("url")}")
            },{
                Log.d("MyLog","Response: eroror")
            }
        )
        queue.add(stringRequest)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



