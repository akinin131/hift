package quiz.example.hift

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import org.json.JSONTokener
import quiz.example.hift.databinding.FragmentFirstBinding
import quiz.example.hift.model.AddViewModel
import quiz.example.weather.model.Model

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

        binding.buttonBin.setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            getRes()

        }

    }

    private fun getRes() {
        val url = "https://lookup.binlist.net/45717360"

        val queue = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(Request.Method.GET, url,
            {

                    responce ->
                val obg = JSONObject(responce)

                val `object` = JSONTokener(responce).nextValue() as JSONObject
                val scheme = `object`.getString("scheme")
                val type = `object`.getString("type")
                val brand = `object`.getString("brand")
                val country = obg.getJSONObject("country")
                val prepaid = `object`.getString("prepaid")

                val bank = obg.getJSONObject("bank")
                val cardNumber = obg.getJSONObject("number")



                Log.d("MyLog", "Response: $scheme")
                Log.d("MyLog", "Response: $type")
                Log.d("MyLog", "Response: $brand")
                Log.d("MyLog", "Response: $country")
                Log.d("MyLog", "Response: $prepaid")
                Log.d("MyLog", "Response: ${bank.getString("url")}")
                Log.d("MyLog", "Response: $obg")

                binding.scheme.text = scheme
                binding.type.text = type
                binding.brand.text = brand
                binding.prepaid.text = prepaid
                binding.bank.text = bank.getString("name")
                binding.bankUrl.text = bank.getString("url")
                binding.bankPhone.text = bank.getString("phone")
                binding.country.text = country.getString("name")
                binding.cardNumber.text = cardNumber.getString("length")
                binding.cardNumberLuhn.text = cardNumber.getString("luhn")


                init(scheme,type,brand,prepaid,bank.getString("url"),bank.getString("phone"),country.getString("name"),country.getString("name"),cardNumber.getString("length"),cardNumber.getString("length"))

            },
            {
                Log.d("MyLog", "Response: eroror")
            }
        )
        queue.add(stringRequest)

    }

    private fun init(Sсheme:String, type: String,brand: String,prepaid: String,bankUrl: String,bankPhone:
    String,bank: String,country: String,cardNumber: String,cardNumberLuhn: String) {
        val viewModel= ViewModelProvider(this).get(AddViewModel::class.java)
            binding.buttonBin.setOnClickListener {

        }
        viewModel.insert(Model(Sсheme =Sсheme, type = type,brand=brand,prepaid=prepaid,bankUrl="bankreUrl",bankPhone=bankPhone,
            bank=bank,country=country,cardNumber=cardNumber,cardNumberLuhn=cardNumberLuhn)){}

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



