package quiz.example.hift

import android.content.Intent
import android.net.Uri
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
import com.google.android.material.snackbar.Snackbar
import org.json.JSONObject
import org.json.JSONTokener
import quiz.example.hift.databinding.FragmentFirstBinding
import quiz.example.weather.model.Model
import java.net.HttpURLConnection
import java.net.URL





class FirstFragment : Fragment() {

    lateinit var bunding: FragmentFirstBinding
    var intent: Intent? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        bunding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        return bunding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bunding.buttonBin.setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

            if (bunding.editTextText.text.toString() != "") {
                getRes()
            } else {
                Snackbar.make(view, "Введите Bin карты", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
    }

    private fun getRes() {
        val bin = bunding.editTextText.text.toString()
        val url = "https://lookup.binlist.net/$bin"
        bunding.editTextText.text.clear()

        val queue = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(Request.Method.GET, url,

            {
                    responce ->
                val obg = JSONObject(responce)

                val `object` = JSONTokener(responce).nextValue() as JSONObject

                try {
                    val scheme = `object`.getString("scheme")
                    val type = `object`.getString("type")
                    val brand = `object`.getString("brand")
                    val country = obg.getJSONObject("country")
                    val prepaid = `object`.getString("prepaid")

                    val bank = obg.getJSONObject("bank")
                    val cardNumber = obg.getJSONObject("number")

                bunding.scheme.text = scheme
                bunding.type.text = type
                bunding.brand.text = brand
                bunding.prepaid.text = prepaid
                bunding.bank.text = bank.getString("name")
                bunding.bankUrl.text = bank.getString("url")
                bunding.bankPhone.text = bank.getString("phone")
                bunding.country.text = country.getString("name")
                bunding.cardNumber.text = cardNumber.getString("length")
                bunding.cardNumberLuhn.text = cardNumber.getString("luhn")
                Log.d("MyLog", "Response: $scheme")
                Log.d("MyLog", "Response: $type")
                Log.d("MyLog", "Response: $brand")
                Log.d("MyLog", "Response: $country")
                Log.d("MyLog", "Response: $prepaid")
                Log.d("MyLog", "Response: ${bank.getString("url")}")
                Log.d("MyLog", "Response: $obg")




                init(scheme,
                    type,
                    brand,
                    prepaid,
                    bank.getString("name"),
                    bank.getString("url"),
                    bank.getString("phone"),
                    country.getString("name"),
                    cardNumber.getString("length"),
                    cardNumber.getString("luhn")
                )

                bunding.bankUrl.setOnClickListener {

                    intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("http://" + bank.getString("url")))
                    startActivity(intent)

                }
                bunding.bankPhone.setOnClickListener {
                    intent = Intent(Intent.ACTION_DIAL);
                    intent!!.setData(Uri.parse(("tel:" + bank.getString("phone"))))
                    startActivity(intent);

                }
                val latitude = country.getString("latitude")
                val longitude = country.getString("longitude")
                    bunding.country.setOnClickListener {
                        intent = Intent()
                        intent!!.action = Intent.ACTION_VIEW
                        intent!!.data = Uri.parse("geo:$latitude,$longitude")
                        startActivity(intent)
                    }
                }
                catch (e:Exception){

                }


            },
            {
                Log.d("MyLog", "Response: eroror")
                view?.let { it1 ->
                    Snackbar.make(it1, "Нет данных o Bin карте", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
        )

        queue.add(stringRequest)

    }

    private fun init(
        scheme: String,
        type: String,
        brand: String,
        prepaid: String,
        bankUrl: String,
        bankPhone: String,
        bank: String,
        country: String,
        cardNumber: String,
        ardNumberLuhn: String,
    ) {
        val viewModel = ViewModelProvider(this).get(AddViewModel::class.java)
        viewModel.initDataBase()

        viewModel.insert(Model(Sсheme = scheme,
            type = type,
            brand = brand,
            prepaid = prepaid,
            bankUrl = bankUrl,
            bankPhone = bankPhone,
            bank = bank,
            country = country,
            cardNumber = cardNumber,
            cardNumberLuhn = ardNumberLuhn)) {}

    }
}



