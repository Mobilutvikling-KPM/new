package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.myapplication.event.EventFragment
import com.example.myapplication.event.Event_liste_fragment
import com.example.myapplication.fragments.Communicator
import com.example.myapplication.fragments.hjem.HjemFragment
import com.example.myapplication.fragments.mineevents.MineEventFragment
import com.example.myapplication.fragments.nyttevent.NyttEventFragment
import com.example.myapplication.fragments.kategori.KategoriFragment
import com.example.myapplication.fragments.profil.ProfilFragment
import com.example.myapplication.fragments.venner.VennerFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), Communicator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val kategoriFragment = KategoriFragment()
        val eventListeFragment = Event_liste_fragment()
        val hjemFragment = HjemFragment()
        val mineEventFragment = MineEventFragment()
        val nyttEventFragment = NyttEventFragment()
        val profilFragment = ProfilFragment()
        val vennerFragment = VennerFragment()

        makeCurrentFragment(eventListeFragment)

        val search: SearchView = findViewById(R.id.searchview)

        toolbar.setOnMenuItemClickListener() {
            when(it.itemId) {
                R.id.kategori -> makeCurrentFragment(kategoriFragment)
            }
            true
        }

        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> makeCurrentFragment(eventListeFragment)
                R.id.myevents -> makeCurrentFragment(mineEventFragment)
                R.id.newevent -> makeCurrentFragment(nyttEventFragment)
                R.id.friends -> makeCurrentFragment(vennerFragment)
                R.id.profile -> makeCurrentFragment(profilFragment)
            }
            true
        }
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {

        return super.onCreateView(name, context, attrs)
    }

     fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
//            if(fragment is Event_liste_fragment)
//                addToBackStack(null)
            commit()
        }

    override fun sendDataKomm(tittel: String, beskrivelse: String, image: String, dato: String, sted: String, antPåmeldte: String, antKommentar: String) {
        val bundle = Bundle()
        bundle.putString("tittel",tittel)
        bundle.putString("beskrivelse",beskrivelse)
        bundle.putString("image",image)
        bundle.putString("dato",dato)
        bundle.putString("sted",sted)
        bundle.putString("antPåmeldte",antPåmeldte)
        bundle.putString("antKommentar",antKommentar)

        val transaction = this.supportFragmentManager.beginTransaction()
        val eventFragment = EventFragment()
        eventFragment.arguments = bundle
        transaction.replace(R.id.container,eventFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}