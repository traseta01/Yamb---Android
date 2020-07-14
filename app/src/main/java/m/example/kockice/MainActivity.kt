package m.example.kockice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Build
import android.util.ArraySet
import android.util.Range
import com.google.android.material.snackbar.Snackbar
import java.lang.annotation.ElementType
import java.util.EnumSet.range


class MainActivity : AppCompatActivity() {

    // pomocne funkcije
    /* za polja 1-6 nadji broj ponavljanja kliknutog broja */
    fun izdvojBrojGore(nekibr: Int, nekiniz: ArrayList<Int>): Int
    {
        var brojac:Int = 0
        for(x in nekiniz)
            if (x == nekibr)
                ++brojac

        return brojac*nekibr;
    }

    /* za polja max/min izdvoj zbir kockica */
    fun izdvojZbirmm(nekiniz: ArrayList<Int>): Int
    {
        return nekiniz.sum()
    }

    /* izdvoj igru ful i vrati vrednost fula */
    fun izdvojIgruFul(nekiniz: ArrayList<Int>): Int
    {
        val skup = ArraySet<Int>()
        var suma = 0
        /* napravi skup od svih bacenih kockica */
        for (x in nekiniz)
        {
            skup.add(x)
        }

        /* da li imamo dva ili tri pojavljivanja prvog elementa seta */
        if (skup.count() == 2) {

            var brojac = 0
            for (x in nekiniz)
            {
                if (x == skup.first())
                    brojac++
            }

            /* imamo poker, vrati nulu */
            if (brojac == 4 || brojac == 1)
                return 0
            else if (brojac == 2)
                return brojac * skup.first() + 3*skup.last() + 40
            else if (brojac == 3)
                return brojac * skup.first() + 2*skup.last() + 40
        }
        else {
            return 0
        }

        return 0
    }


    /* izdvoj igru poker i vrati vrednost pokera */
    fun izdvojIgruPoker(nekiniz: ArrayList<Int>): Int
    {
        val skup = ArraySet<Int>()
        var suma = 0
        /* napravi skup od svih bacenih kockica */
        for (x in nekiniz)
        {
            skup.add(x)
        }

        /* ako je velicina seta 1, onda imamo i yamb a znaci sigurno i poker */
        if(skup.count() == 1)
            return skup.first()*4 + 50

        /* da li imamo jedno ili cetiri pojavljivanja prvog elementa seta */
        if (skup.count() == 2) {

            var brojac = 0
            for (x in nekiniz)
            {
                if (x == skup.first())
                    brojac++
            }

            if (brojac == 4)
                return skup.first() * 4 + 50
            else if (brojac == 1)
                return skup.last() * 4 + 50
        }
        else
            return 0

        return 0
    }

    /* izdvoji igru kenta, veliku i malu */
    fun izdvojIgruKenta(nekiniz: ArrayList<Int>): Int
    {

        val skup = ArraySet<Int>()
        /* napravi skup od svih bacenih kockica */
        for (x in nekiniz)
        {
            skup.add(x)
        }

        /* ako je velicina seta 5, sve kockice su razlicite, imamo kentu */
        if (skup.count() == 5)
        {
            if(skup.first() == 1)
                return 75
            else
                return 80
        }

        return 0
    }

    /* igra Yamb */
    fun izdvojIgruYamb(nekiniz: ArrayList<Int>): Int
    {
        val skup = ArraySet<Int>()
        /* napravi skup od svih bacenih kockica */
        for (x in nekiniz)
        {
            skup.add(x)
        }

        if (skup.count() == 1)
            return skup.first() * 5 + 80

        return 0
    }

    /* izracunaj proizvod razlika */
    fun proizvodRazlika(max: Int, min: Int, kecevi: Int): Int
    {
        // ako je max < od min razlika je nule
        if (max < min)
            return 0
        return kecevi * (max - min)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)

        setContentView(R.layout.activity_main)


        /* Bacanje i izbor kockica idjevi */
        var nizIdbacene = arrayOf(bacene_dugme1,
                                  bacene_dugme2,
                                  bacene_dugme3,
                                  bacene_dugme4,
                                  bacene_dugme5 )

        var nizIdzabrane = arrayOf(izabrane_dugme1,
                                   izabrane_dugme2,
                                   izabrane_dugme3,
                                   izabrane_dugme4,
                                   izabrane_dugme5 )
        /* izabrane kockice su inicijalno INVISIBLE */
        for (x in nizIdzabrane)
            x.visibility = View.INVISIBLE

        /* Polja kolona IDjevi */
        var nizIdDole = arrayOf(dugme_dole_01, dugme_dole_02, dugme_dole_03,
                                dugme_dole_04, dugme_dole_05, dugme_dole_06,
                                dugme_dole_07, dugme_dole_08, dugme_dole_09,
                                dugme_dole_10, dugme_dole_11, dugme_dole_12)

        var nizIdGore = arrayOf(dugme_gore_01, dugme_gore_02, dugme_gore_03,
                                dugme_gore_04, dugme_gore_05, dugme_gore_06,
                                dugme_gore_07, dugme_gore_08, dugme_gore_09,
                                dugme_gore_10, dugme_gore_11, dugme_gore_12)

        var nizIdSlob = arrayOf(dugme_slob_01, dugme_slob_02, dugme_slob_03,
                                dugme_slob_04, dugme_slob_05, dugme_slob_06,
                                dugme_slob_07, dugme_slob_08, dugme_slob_09,
                                dugme_slob_10, dugme_slob_11, dugme_slob_12)

        var nizIdNaja = arrayOf(dugme_naja_01, dugme_naja_02, dugme_naja_03,
                                dugme_naja_04, dugme_naja_05, dugme_naja_06,
                                dugme_naja_07, dugme_naja_08, dugme_naja_09,
                                dugme_naja_10, dugme_naja_11, dugme_naja_12)

        /* niz u kojem cemo pamtiti sume kolona, igara i ukupnu sumu */
        var nizSuma: IntArray = intArrayOf(0,0,0,0,0,0,0,0,0)



        /* click event handler for BACI button */
        baci_dugme.setOnClickListener {
            /* za svaki id bacenih kockica postavi vrednost na osnovu ida */

            for (x in nizIdbacene)
            {
                x.text = (1..6).random().toString()
            }

        }

        /* click event handler for lower dices */
        /* kad kliknes na kockicu prebaci je u gornji red */
        for (x in nizIdbacene)
        {
            x.setOnClickListener {
                val indeks = nizIdbacene.indexOf(x)
                nizIdzabrane[indeks].text = x.text
                nizIdzabrane[indeks].visibility = View.VISIBLE
                x.text = ""
                x.visibility = View.INVISIBLE
            }
        }

        /* @TO DO stable partition */

        /* click event handler for upper dices */
        /* kad kliknes na gornju kockicu prebaci je u donji red */
        for (x in nizIdzabrane)
        {
            x.setOnClickListener {
                val indeks = nizIdzabrane.indexOf(x)
                nizIdbacene[indeks].text = x.text
                nizIdbacene[indeks].visibility = View.VISIBLE
                x.text = ""
                x.visibility = View.INVISIBLE
            }
        }
            // val tekdugme: Button = pogled as Button
            // val indeks = nizIdbacene.indexOf(tekdugme)
            // nizIdzabrane[indeks].text = tekdugme.text

        /* click event handler za kolonu dole */
        for (x in nizIdDole)
        {
            x.setOnClickListener {
                /* nadji koje je polje kliknuto */
                /* ako je bilo koje sem prvog polja, proveri da li
                 * ima necega u prethodnom polju
                 */



                /* pokupi vrednosti sa svih kockica koje su visible */
                var nizBacenihBr = ArrayList<Int>()

                for (x in nizIdbacene)
                {
                    if (x.visibility == View.VISIBLE && x.text.toString() != "")
                    {
                        nizBacenihBr.add(x.text.toString().toInt())
                    }
                }

                for (x in nizIdzabrane)
                {
                    if (x.visibility == View.VISIBLE && x.text.toString() != "")
                    {
                        nizBacenihBr.add(x.text.toString().toInt())
                    }
                }

                val indeks: Int = nizIdDole.indexOf(x)

                /* prepoznaj koje je polje kliknuto i updateuj ga accordingly */
                /* uslov je: if nije kliknuto na prvo polje */
                if (indeks > 0)
                {

                    /* ako u prethodnom polju nije upisano nista ignorisi click */
                    if(nizIdDole[indeks-1].text == "" )
                    else if (nizIdDole[indeks].text == "")
                    {
                        when (indeks + 1) {
                            2, 3, 4, 5, 6 -> {
                                x.text = izdvojBrojGore(indeks + 1, nizBacenihBr).toString()
                                /* izracunaj i prikazi Z1 za kolonu dole */
                                nizSuma[0] += x.text.toString().toInt()
                                /* bonuks */
                                if (nizSuma[0] > 59)
                                    nizSuma[0] += 30
                                dugme_dole_z1.text = nizSuma[0].toString()
                                prikaz_rezultata.text = nizSuma.sum().toString()
                            }
                            7 -> {
                                x.text = izdvojZbirmm(nizBacenihBr).toString()
                            }
                            8 -> {
                                x.text = izdvojZbirmm(nizBacenihBr).toString()
                                /* izracunaj i upisi razlike */
                                nizSuma[4] = proizvodRazlika(dugme_dole_07.text.toString().toInt(),
                                            dugme_dole_08.text.toString().toInt(),
                                            dugme_dole_01.text.toString().toInt())
                                dugme_dole_z2.text = nizSuma[4].toString()
                                prikaz_rezultata.text = nizSuma.sum().toString()

                            }
                            9 -> {
                                x.text = izdvojIgruFul(nizBacenihBr).toString()
                                /* suma svih igara */
                                nizSuma[8] += x.text.toString().toInt()
                                prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                            }
                            10 -> {
                                x.text = izdvojIgruPoker(nizBacenihBr).toString() /* suma svih igara */
                                nizSuma[8] += x.text.toString().toInt()
                                prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                            }
                            11 -> {
                                x.text = izdvojIgruKenta(nizBacenihBr).toString() /* suma svih igara */
                                nizSuma[8] += x.text.toString().toInt()
                                prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                            }
                            12 -> {
                                x.text = izdvojIgruYamb(nizBacenihBr).toString() /* suma svih igara */
                                nizSuma[8] += x.text.toString().toInt()
                                prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                            }
                        }
                        // resetuj kockice
                        for (x in nizIdzabrane)
                        {
                            x.text = ""
                            x.visibility = View.INVISIBLE
                        }

                        for (x in nizIdbacene)
                        {
                            x.text = ""
                            x.visibility = View.VISIBLE
                        }
                    }
                }
                else if(nizIdDole[indeks].text == "")
                {
                    nizIdDole[indeks].text = izdvojBrojGore(indeks + 1, nizBacenihBr).toString()
                    nizSuma[0] += nizIdDole[indeks].text.toString().toInt()
                    dugme_dole_z1.text = nizSuma[0].toString()
                    prikaz_rezultata.text = "    " + nizSuma.sum().toString()

                    //resetuj kockice
                    for (x in nizIdzabrane) {
                        x.text = ""
                        x.visibility = View.INVISIBLE
                    }

                    for (x in nizIdbacene) {
                        x.text = ""
                        x.visibility = View.VISIBLE
                    }
                }
            }
        }

        /* click event handler za kolonu gore */
        for (x in nizIdGore)
        {
            x.setOnClickListener {

                /* nadji koje je polje kliknuto */
                /* ako je bilo koje sem poslednjeg polja, proveri da li
                 * ima necega u prethodnom polju
                 */

                /* pokupi vrednosti sa svih kockica koje su visible */
                var nizBacenihBr = ArrayList<Int>()

                for (x in nizIdbacene)
                {
                    if (x.visibility == View.VISIBLE && x.text.toString() != "")
                    {
                        nizBacenihBr.add(x.text.toString().toInt())
                    }
                }

                for (x in nizIdzabrane)
                {
                    if (x.visibility == View.VISIBLE && x.text.toString() != "")
                    {
                        nizBacenihBr.add(x.text.toString().toInt())
                    }
                }

                val indeks: Int = nizIdGore.indexOf(x)

                /* prepoznaj koje je polje kliknuto i updateuj ga accordingly */
                /* uslov je: if nije kliknuto na na poslednje polje */
                if (indeks < 11)
                {

                    /* ako u prethodnom polju nije upisano nista ignorisi click */
                    if(nizIdGore[indeks+1].text == "" )
                    else if (nizIdGore[indeks].text == "")
                    {
                        when (indeks + 1) {
                            1 -> {
                                x.text = izdvojBrojGore(indeks + 1, nizBacenihBr).toString()
                                nizSuma[2] += x.text.toString().toInt()
                                /* bonkus */
                                if (nizSuma[2] > 59)
                                    nizSuma[2] += 30
                                dugme_gore_z1.text = nizSuma[2].toString()
                                nizSuma[6] += proizvodRazlika(dugme_gore_07.text.toString().toInt(),
                                                              dugme_gore_08.text.toString().toInt(),
                                                              dugme_gore_01.text.toString().toInt())
                                dugme_gore_z2.text = nizSuma[6].toString()
                                prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                            }
                             2, 3, 4, 5, 6 -> {
                                x.text = izdvojBrojGore(indeks + 1, nizBacenihBr).toString()
                                nizSuma[2] += x.text.toString().toInt()
                                /* bonkus */
                                if (nizSuma[2] > 59)
                                    nizSuma[2] += 30
                                dugme_gore_z1.text = nizSuma[2].toString()
                                prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                            }
                            7, 8 -> {
                                x.text = izdvojZbirmm(nizBacenihBr).toString()
                            }
                            9 -> {
                                x.text = izdvojIgruFul(nizBacenihBr).toString()
                                /* suma svih igara */
                                nizSuma[8] += x.text.toString().toInt()
                                prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                            }
                            10 -> {
                                x.text = izdvojIgruPoker(nizBacenihBr).toString()
                                nizSuma[8] += x.text.toString().toInt()
                                prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                            }
                            11 -> {
                                x.text = izdvojIgruKenta(nizBacenihBr).toString()
                                nizSuma[8] += x.text.toString().toInt()
                                prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                            }
                        }
                        for (x in nizIdzabrane)
                        {
                            x.text = ""
                            x.visibility = View.INVISIBLE
                        }

                        for (x in nizIdbacene)
                        {
                            x.text = ""
                            x.visibility = View.VISIBLE
                        }
                    }
                }
                else if(nizIdGore[indeks].text == "")
                {
                    nizIdGore[indeks].text = izdvojIgruYamb(nizBacenihBr).toString()
                    nizSuma[8] += nizIdGore[indeks].text.toString().toInt()
                    prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                    // resetuj kockice
                    for (x in nizIdzabrane)
                    {
                        x.text = ""
                        x.visibility = View.INVISIBLE
                    }

                    for (x in nizIdbacene)
                    {
                        x.text = ""
                        x.visibility = View.VISIBLE
                    }
                }
            }
        }

        /* click event handler za kolonu slob */
        for (x in nizIdSlob)
        {
            x.setOnClickListener {

                /* pokupi vrednosti sa svih kockica koje su visible */
                var nizBacenihBr = ArrayList<Int>()

                for (x in nizIdbacene)
                {
                    if (x.visibility == View.VISIBLE && x.text.toString() != "")
                    {
                        nizBacenihBr.add(x.text.toString().toInt())
                    }
                }

                for (x in nizIdzabrane)
                {
                    if (x.visibility == View.VISIBLE && x.text.toString() != "")
                    {
                        nizBacenihBr.add(x.text.toString().toInt())
                    }
                }

                val indeks: Int = nizIdSlob.indexOf(x)

                /* ako je polje prazno upisi u njega */
                if (x.text == "")
                {

                    when (indeks + 1) {
                        1 -> {
                            x.text = izdvojBrojGore(indeks + 1, nizBacenihBr).toString()
                            nizSuma[1] += x.text.toString().toInt()
                            /* bonkus */
                            if (nizSuma[1] > 59)
                                nizSuma[1] += 30
                            dugme_slob_z1.text = nizSuma[1].toString()
                            // ako vec postoje min i max izracunaj proizvod razlika
                            if (dugme_slob_07.text != "" && dugme_slob_08.text != "")
                            {
                                nizSuma[5] += proizvodRazlika(dugme_slob_07.text.toString().toInt(),
                                                              dugme_slob_08.text.toString().toInt(),
                                                              dugme_slob_01.text.toString().toInt()
                                )
                                dugme_slob_z2.text = nizSuma[5].toString()
                            }
                            prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                        }
                        2, 3, 4, 5, 6 -> {
                            x.text = izdvojBrojGore(indeks + 1, nizBacenihBr).toString()
                            nizSuma[1] += x.text.toString().toInt()
                            /* bonkus */
                            if (nizSuma[1] > 59)
                                nizSuma[1] += 30
                            dugme_slob_z1.text = nizSuma[1].toString()
                            prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                        }
                        7 -> {
                            x.text = izdvojZbirmm(nizBacenihBr).toString()
                            // ako vec postoje min i kecevi izracunaj proizvod razlika
                            if (dugme_slob_08.text != "" && dugme_slob_01.text != "")
                            {
                                nizSuma[5] += proizvodRazlika(dugme_slob_07.text.toString().toInt(),
                                                              dugme_slob_08.text.toString().toInt(),
                                                              dugme_slob_01.text.toString().toInt()
                                )
                                dugme_slob_z2.text = nizSuma[5].toString()
                            }
                            prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                        }
                        8 -> {
                            x.text = izdvojZbirmm(nizBacenihBr).toString()
                            // ako vec postoje max i kecevi izracunaj proizvod razlika
                            if (dugme_slob_07.text != "" && dugme_slob_01.text != "")
                            {
                                nizSuma[5] += proizvodRazlika(dugme_slob_07.text.toString().toInt(),
                                    dugme_slob_08.text.toString().toInt(),
                                    dugme_slob_01.text.toString().toInt()
                                )
                                dugme_slob_z2.text = nizSuma[5].toString()
                            }
                            prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                        }
                        9 -> {
                            x.text = izdvojIgruFul(nizBacenihBr).toString()
                            nizSuma[8] += x.text.toString().toInt()
                            prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                        }
                        10 -> {
                            x.text = izdvojIgruPoker(nizBacenihBr).toString()
                            nizSuma[8] += x.text.toString().toInt()
                            prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                        }
                        11 -> {
                            x.text = izdvojIgruKenta(nizBacenihBr).toString()
                            nizSuma[8] += x.text.toString().toInt()
                            prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                        }
                        12 -> {
                            x.text = izdvojIgruYamb(nizBacenihBr).toString()
                            nizSuma[8] += x.text.toString().toInt()
                            prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                        }
                    }
                    // resetuj kockice
                    for (x in nizIdzabrane)
                    {
                        x.text = ""
                        x.visibility = View.INVISIBLE
                    }

                    for (x in nizIdbacene)
                    {
                        x.text = ""
                        x.visibility = View.VISIBLE
                    }
                    }
                }
            }

        /* click event handler za kolonu najava */
        for (x in nizIdNaja)
        {
            x.setOnClickListener {

                /* pokupi vrednosti sa svih kockica koje su visible */
                var nizBacenihBr = ArrayList<Int>()

                for (x in nizIdbacene)
                {
                    if (x.visibility == View.VISIBLE && x.text.toString() != "")
                    {
                        nizBacenihBr.add(x.text.toString().toInt())
                    }
                }

                for (x in nizIdzabrane)
                {
                    if (x.visibility == View.VISIBLE && x.text.toString() != "")
                    {
                        nizBacenihBr.add(x.text.toString().toInt())
                    }
                }

                val indeks: Int = nizIdNaja.indexOf(x)

                /* ako je polje prazno upisi u njega */
                if (x.text == "")
                {
                    when (indeks + 1) {
                        1 -> {
                            x.text = izdvojBrojGore(indeks + 1, nizBacenihBr).toString()
                            nizSuma[3] += x.text.toString().toInt()
                            /* bonkus */
                            if (nizSuma[3] > 59)
                                nizSuma[3] += 30
                            dugme_naja_z1.text = nizSuma[3].toString()
                            // ako vec postoje min i max izracunaj proizvod razlika
                            if (dugme_naja_07.text != "" && dugme_naja_08.text != "")
                            {
                                nizSuma[7] += proizvodRazlika(dugme_naja_07.text.toString().toInt(),
                                                              dugme_naja_08.text.toString().toInt(),
                                                              dugme_naja_01.text.toString().toInt())
                                dugme_naja_z2.text = nizSuma[7].toString()
                                prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                            }
                        }
                        2, 3, 4, 5, 6 -> {
                            x.text = izdvojBrojGore(indeks + 1, nizBacenihBr).toString()
                            nizSuma[3] += x.text.toString().toInt()
                            /* bonkus */
                            if (nizSuma[3] > 59)
                                nizSuma[3] += 30
                            dugme_naja_z1.text = nizSuma[3].toString()
                            prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                        }
                        7 -> {
                            x.text = izdvojZbirmm(nizBacenihBr).toString()
                            // ako vec postoje min i kecevi izracunaj proizvod razlika
                            if (dugme_naja_01.text != "" && dugme_naja_08.text != "")
                            {
                                nizSuma[7] += proizvodRazlika(dugme_naja_07.text.toString().toInt(),
                                                              dugme_naja_08.text.toString().toInt(),
                                                              dugme_naja_01.text.toString().toInt())
                                dugme_naja_z2.text = nizSuma[7].toString()
                                prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                            }
                        }
                        8 -> {
                            x.text = izdvojZbirmm(nizBacenihBr).toString()
                            // ako vec postoje max i kecevi izracunaj proizvod razlika
                            if (dugme_naja_07.text != "" && dugme_naja_01.text != "")
                            {
                                nizSuma[7] += proizvodRazlika(dugme_naja_07.text.toString().toInt(),
                                                             dugme_naja_08.text.toString().toInt(),
                                                             dugme_naja_01.text.toString().toInt())
                                dugme_naja_z2.text = nizSuma[7].toString()
                                prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                            }
                        }
                        9 -> {
                            x.text = izdvojIgruFul(nizBacenihBr).toString()
                            nizSuma[8] += x.text.toString().toInt()
                            prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                        }
                        10 -> {
                            x.text = izdvojIgruPoker(nizBacenihBr).toString()
                            nizSuma[8] += x.text.toString().toInt()
                            prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                        }
                        11 -> {
                            x.text = izdvojIgruKenta(nizBacenihBr).toString()
                            nizSuma[8] += x.text.toString().toInt()
                            prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                        }
                        12 -> {
                            x.text = izdvojIgruYamb(nizBacenihBr).toString()
                            nizSuma[8] += x.text.toString().toInt()
                            prikaz_rezultata.text = "    " + nizSuma.sum().toString()
                        }
                    }
                    // resetuj kockice
                    for (x in nizIdzabrane)
                    {
                        x.text = ""
                        x.visibility = View.INVISIBLE
                    }

                    for (x in nizIdbacene)
                    {
                        x.text = ""
                        x.visibility = View.VISIBLE
                    }
                }
            }
        }

        /* zapocni novu igru na dugacak klik */
        dugme_nova_igra.setOnLongClickListener(object: View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                for (x in nizIdDole)
                    x.text = ""
                for (x in nizIdGore)
                    x.text = ""
                for (x in nizIdNaja)
                    x.text = ""
                for (x in nizIdSlob)
                    x.text = ""
                for (x in nizIdbacene)
                {
                    x.text = ""
                    x.visibility = View.VISIBLE
                }
                for (x in nizIdzabrane)
                {
                    x.text = ""
                    x.visibility = View.INVISIBLE
                }
                dugme_slob_z1.text = ""
                dugme_slob_z2.text = ""
                dugme_dole_z1.text = ""
                dugme_dole_z2.text = ""
                dugme_gore_z1.text = ""
                dugme_gore_z2.text = ""
                dugme_naja_z1.text = ""
                dugme_naja_z2.text = ""

                for (x in 0..8)
                    nizSuma[x] = 0

                prikaz_rezultata.text = "    0"
            return true  }
        })
    }
}

