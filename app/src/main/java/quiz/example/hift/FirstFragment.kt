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
import quiz.example.hift.model.AddViewModel
import quiz.example.weather.model.Model

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

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

            { responce ->
                val obg = JSONObject(responce)

                val `object` = JSONTokener(responce).nextValue() as JSONObject


                try {
                    var scheme: String = " "
                    var type: String = " "
                    var brand: String = " "
                    var prepaid: String = " "
                    var country: String = " "
                    var countryLatitude: String = " "
                    var countrylongitude: String = " "
                    var bankName: String = " "
                    var bankurl: String = " "
                    var bankPhone: String = " "
                    var cardNumberLengt: String = " "
                    var cardNumberluhn: String = " "
                    try {
                        scheme = `object`.getString("scheme")
                    } catch (e: Exception) {
                    }
                    try {
                        type = `object`.getString("type")
                    } catch (e: Exception) {
                    }
                    try {
                        brand = `object`.getString("brand")
                    } catch (e: Exception) {
                    }
                    try {
                        prepaid = `object`.getString("prepaid")
                    } catch (e: Exception) {
                    }
                    try {
                        val country3 = obg.getJSONObject("country")
                        country = country3.getString("name")
                    } catch (e: Exception) {
                    }
                    try {
                        val country3 = obg.getJSONObject("country")
                        countryLatitude = country3.getString("latitude")
                    } catch (e: Exception) {
                    }
                    try {
                        val country3 = obg.getJSONObject("country")
                        countrylongitude = country3.getString("longitude")
                    } catch (e: Exception) {
                    }
                    try {
                        val bank = obg.getJSONObject("bank")
                        bankName = bank.getString("name")
                    } catch (e: Exception) {
                    }
                    try {
                        val bank = obg.getJSONObject("bank")
                        bankurl = bank.getString("url")
                    } catch (e: Exception) {
                    }
                    try {
                        val bank = obg.getJSONObject("bank")
                        bankPhone = bank.getString("phone")
                    } catch (e: Exception) {
                    }
                    try {
                        val cardNumber = obg.getJSONObject("number")
                        cardNumberLengt = cardNumber.getString("length")
                    } catch (e: Exception) {
                    }
                    try {
                        val cardNumber = obg.getJSONObject("number")
                        cardNumberluhn = cardNumber.getString("luhn")
                    } catch (e: Exception) {
                    }




                    bunding.scheme.text = scheme
                    bunding.type.text = type
                    bunding.brand.text = brand
                    bunding.prepaid.text = prepaid
                    bunding.bank.text = bankName
                    bunding.bankUrl.text = bankurl
                    bunding.bankPhone.text = bankPhone
                    bunding.country.text = country
                    bunding.cardNumber.text = cardNumberLengt
                    bunding.cardNumberLuhn.text = cardNumberluhn
                    Log.d("MyLog", "Response: $scheme")
                    Log.d("MyLog", "Response: $type")
                    Log.d("MyLog", "Response: $brand")
                    Log.d("MyLog", "Response: $country")
                    Log.d("MyLog", "Response: $prepaid")
                    Log.d("MyLog", "Response: $bankurl")
                    Log.d("MyLog", "Response: $obg")


                    init(
                        scheme,
                        type,
                        brand,
                        prepaid,
                        bankName,
                        bankurl,
                        bankPhone,
                        country,
                        cardNumberLengt,
                        cardNumberluhn
                    )

                    try {
                        bunding.bankUrl.setOnClickListener {

                            intent =
                                Intent(Intent.ACTION_VIEW, Uri.parse("http://" + bankurl))
                            startActivity(intent)
                        }
                    } catch (e: Exception) {
                    }
                    try {
                        bunding.bankPhone.setOnClickListener {
                            intent = Intent(Intent.ACTION_DIAL);
                            intent!!.setData(Uri.parse("tel:" + bankPhone))
                            startActivity(intent);

                        }
                    } catch (e: Exception) {
                    }

                    try {
                        bunding.country.setOnClickListener {
                            intent = Intent()
                            intent!!.action = Intent.ACTION_VIEW
                            intent!!.data = Uri.parse("geo:$countryLatitude,$countrylongitude")
                            startActivity(intent)
                        }
                    } catch (e: Exception) {
                    }

                } catch (e: Exception) {

                }

            },
            {
                Log.d("MyLog", "Response: eroror")
                view?.let { it1 ->
                    Snackbar.make(it1, "Ошибка 404: Нет данных o Bin карте", Snackbar.LENGTH_LONG)
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

        viewModel.insert(
            Model(
                Sсheme = scheme,
                type = type,
                brand = brand,
                prepaid = prepaid,
                bankUrl = bankUrl,
                bankPhone = bankPhone,
                bank = bank,
                country = country,
                cardNumber = cardNumber,
                cardNumberLuhn = ardNumberLuhn
            )
        ) {}

    }
}



