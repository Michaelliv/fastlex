package com.github.michaelliv.lexicon.impl

import com.github.michaelliv.characterbuffer.impl.CharacterBuffer
import com.github.michaelliv.characters.Unicode
import com.github.michaelliv.extensions.capitalCase
import com.github.michaelliv.extensions.concat
import com.github.michaelliv.lexicon.BASE_LEXICON
import com.github.michaelliv.lexicon.Lexicon
import com.github.michaelliv.trie.Node
import com.github.michaelliv.trie.impl.Trie

const val CURRENCY = "CURRENCY"

private val CURRENCY_NAMES = listOf(
    "\u00d6re", "Nakfa", "Kuna", "Guaran\u00edes", "Rial", "Kwacha", "Pataca", "Baht", "Qapik", "Bolivianos", "Birr",
    "Rubles", "Avos", "Ouguiya", "Dollars", "Baisa", "Thebe", "Tiyin", "Lira", "Centimos", "Sen", "Aurar", "Lev",
    "Cedi", "Soles", "Guaran\u00ed", "Meticais", "Tenge", "Tyiyn", "Poisha", "Millime", "Dalasis", "Lek", "Denar",
    "Riels", "Dalasi", "Lempiras", "Iraimbilanja", "Won", "Paisa", "Shekels", "Kwanza", "Riyals", "Lempira", "Dirhams",
    "Shillings", "Gourde", "Pula", "Shilling", "Kapiejka", "Ngultrum", "Yen", "Koruna", "Pesewa", "Boliviano", "Leone",
    "Colones", "Sente", "Convertible Mark", "Ngultrums", "Avo", "Pound", "Zlotys", "Patacas", "maLoti", "Bol\u00edvar",
    "Sol", "Reais", "Para", "Centimo", "Groszy", "Hal\u00e9\u0159", "Krone", "Fening", "Escudo", "Ringgit", "Toea",
    "Halalah", "Ngwee", "Pence", "Bol\u00edvares", "Euro", "Koruny", "T\u0131yn", "Sentimo", "Butut", "Chon", "Rupiah",
    "Kopek", "Col\u00f3n", "Pesos", "\u00f8re", "Centavo", "Afghani", "Leones", "m\u00f6ng\u00f6", "Lipa", "Diram",
    "Kuru\u015f", "C\u00e9ntimos", "Florin", "T\u00f6gr\u00f6g", "Rand", "Rials", "Agora", "Apsark", "Cent\u00e9simo",
    "laari", "Jeon", "Peso", "Pesewas", "Vatu", "Dong", "Fils", "Chetrum", "Santeems", "Forint", "Shekel", "Centimes",
    "Kr\u00f3nur", "Dirham", "Kina", "Tala", "Som", "Luma", "Quetzales", "Guilder", "\u00d8re", "Marks", "C\u00e9ntimo",
    "Deni", "fill\u00e9r", "Pesetas", "Dobra", "Dinars", "Dollar", "Sene", "Rupee", "Centavos", "Kr\u00f3nas", "Dram",
    "Senti", "Cedis", "Cents", "Quetzal", "Rufiyaa", "Denars", "Kr\u00f3na", "kopiyky", "Kroner", "Krona", "Ariary",
    "Halalahs", "Chetrums", "Real", "Pya", "Pounds", "C\u00f3rdoba Oro", "Ruble", "Lilangeni", "Bol\u00edvars Digital",
    "Lisente", "Seniti", "Bol\u00edvar Digital", "Hryvnias", "Agoras", "Satang", "Hryvnia", "Tambala", "Loti", "Grosz",
    "Balboa", "Kopeks", "Franc", "Francs", "Guilders", "Oyra", "Apsar", "Kronor", "Tetri", "Penny", "Lari", "Leu",
    "Pa'anga", "Metical", "Stotinki", "Yuan", "Kyat", "Cent\u00e9simos", "Emalangeni", "Pul", "H\u00e0o", "Khoums",
    "Bututs", "Lek\u00eb", "Santim", "Dinar", "Riel", "Zloty", "Kobo", "Somoni", "Att", "Paise", "Kopiyka", "Taka",
    "Cent", "Leva", "Bani", "Rupees", "Stotinka", "Manat", "Lei", "Santeem", "Qirsh", "Riyal", "Kip", "Fen", "Naira",
    "Peseta", "Gourdes", "Qindarka", "Ban", "Dobras", "Centime", "Euros"
)


private val CURRENCY_SYMBOLS = listOf(
    "\u20a9", "GBP", "Sl", "\u20a6", "m.", "\u0631.\u0639.", "HUF", "VES", "MK", "CO$", "BTN", "BD", "CLP", "VT",
    "\u20ab", "FK\u00a3", "HNL", "\u0644.\u062f.", "PT", "\u20ad", "KWD", "\u20ac", "KD", "\u20be", "\u20adN", "KZT",
    "NOK", "BGN", "INR", "IDR", "Ks", "KMF", "BWP", "CL$", "SEK", "R", "HKD", "Rs.", "\u20a3", "IQD", "FC", "AZN",
    "ZAR", "\u0441\u0443\u043c", "ANG", "SI$", "MXN", "\u0930\u0941 ", "NGN", "PRB", "JOD", "\u0e3f", "QR", "UM", "RSD",
    "AWG", "TJS", "\u0641.\u062c.", "CUC", "CV$", "YR", "DZD", "R$", "XAF", "MZN", "\u00a5\u5143", "DOP",
    "\u0440\u0443\u0431.", "PAB", "TOP", "PGK", "Ptas.", "SSP", "SDG", "SR", "den", "LAK", "ETB", "MAD", "S$", "BRL",
    "NAD", "KM", "\u043b\u0432.", "Sr$", "T$", "EGP", "\u0631.\u0633.", "\u0631.\u064a.", "Ft", "KYD", "\u20bd",
    "\ufdfc", "FJD", "RUB", "PLN", "\u0564\u0580", "FJ$", "B/.", "Nu.", "NT$", "EUR", "BHD", "GEL", "S/.", "\u20a1",
    "TT$", "\u0434\u0438\u043d", "\u00a3SD", "HRK", "kr.", "XPF", "Artsakh", "\u00a3", "ERN", "NZD", "WST",
    "z\u0142", "USh", "\u1265\u122d", "\u20b9", "BOB", "SLS", "N$", "STN", "ILS", "\u20b5", "AED", "Kz", "MRU",
    "L", "රු", "ரூ", "MRf", "HTG", "\u0623.\u0645.", "AOA", "R\u20a3", "ZK", "KES", "GMD",
    "\u0564\u0580.", "ZWL", "\u062f.\u0643.", "\u062f.\u0625.", "\u0644.\u0633.", "SZL", "PND", "K\u010d", "Sh.So.",
    "TMT", "\u17db", "BZ$", "MNT", "kr", "kn", "IRR", "KPW", "P", "QAR", "BIF", "MYR", "EHP", "UYU", "DKK", "MKD",
    "Fr", "BND", "SCR", "C$", "\u062c.\u0645.", "CZK", "CAD", "FKP", "LYD", "RON", "\u0440.", "SOS", "HK$", "ALL",
    "Bs.", "TZS", "CHF", "TND", "XCD", "ZWB", "LD", "Le", "MX$", "Br", "BYN", "MTn", "\u20b2", "\u060b", "SLL", "VND",
    "Bs.F", "RWF", "Db", "DT", "UZS", "Rs", "\u0434\u0435\u043d", "MOP", "NIO", "SVC", "IMP", "\u0631.\u0642.",
    "MWK", "AMD", "SHP", "Ssh", "\$U", "XOF", "CN\u00a5", "YER", "Nkf", "FG", "MUR", "JD", "FRw", "TSh", "CKD", "TTD",
    "MMK", "LSL", "Z$", "L$", "din", "AU$", "CRC", "G$", "ARS", "\u0192", "GGP", "JEP", "KRW", ".\u0783", "\u058f",
    "\u0930\u0942", "KSh", "AUD", "PYG", "\u062f.\u0645.", "TWD", "AR$", "JPY", "GHS", "BAM", "\u062c.\u0633.",
    "\u043c\u0430\u043d", "LL.", "OMR", "THB", "KGS", "MDL", "USD", "\u20b1", "\u062f.\u0639.", "BDT", "UAH", "\u20ae",
    "JMD", "SM", "SRD", "CI$", "SGD", "SAR", "ZMW", "LBP", "BSD", "VUV", "DH", "SBD", "GH\u20b5", "\u062f.\u062a.",
    "BZD", "ISK", "MT", "BMD", "CF", "GNF", "GIP", "\u20aa", "UGX", "KID", "MGA", "DA", "RD$", "NPR", "\u0644.\u0644.",
    "Abkhazia", "\u0441", "LKR", "MOP$", "RM", "TVD", "MVR", "Rp", "NZ$", "SS\u00a3", "Fr.", "\$MN",
    "PKR", "$", "Af", "\u20bc", "\u0433\u0440\u043d", "TRY", "\u0646\u0627\u0643\u0641\u0627", "Sh", "GTQ", "PHP",
    "GYD", "BBD", "CVE", "LS", "K", "PEN", "\u20b8", "B$", "\u5713", "DJF", "CUC$", "SYP", "CA$", "Fdj", "COP",
    "E\u00a3", "\u00a5", "\u062f.\u062c.", "BBD$", "AFN", "CNY", "\u062f.\u0623.", "VED", "\u041a\u041c", "\u20ba",
    "J$", "TL", "\u09f3", "Ar", "FOK", "LRD", "\u20b4", "FBu", "CDF", "KHR", "\u062f.\u0628.",
)

private val CURRENCY_TRIE = Trie().apply {
    CURRENCY_NAMES.map(String::lowercase).forEach(this::insert)
    CURRENCY_NAMES.map(String::uppercase).forEach(this::insert)
    CURRENCY_NAMES.map(String::capitalCase).forEach(this::insert)
    CURRENCY_SYMBOLS.map(String::lowercase).forEach(this::insert)
    CURRENCY_SYMBOLS.map(String::uppercase).forEach(this::insert)
    CURRENCY_SYMBOLS.map(String::capitalCase).forEach(this::insert)
}

private val DEFAULT_CURRENCY_PUNCTUATION = Unicode.run { FULL_STOP union COMMA }

class Currency(
    private val currencyPunctuation: Set<Char> = DEFAULT_CURRENCY_PUNCTUATION
) : Lexicon {
    override val name: String = CURRENCY
    override val overrides: Array<Lexicon> = BASE_LEXICON concat LikeNumber()
    override fun isMatch(cb: CharacterBuffer): Boolean {
        val initialIndex = cb.curIndex
        if (matchSymbolFirst(cb)) return true
        else cb.setPosition(initialIndex)
        return matchNumberFirst(cb)
    }

    private fun matchSymbolFirst(cb: CharacterBuffer): Boolean {
        val symbol = cb.advanceCharWhile(this::isCurrencySymbol)
        if (symbol == 0) return false
        val index = cb.curIndex
        val digit = cb.advanceCharWhile(this::isCurrencyPunctuation, Char::isDigit)
        if (digit.second == 0) {
            cb.setPosition(index)
            return true
        }
        if (isCurrencyPunctuation(cb.prevChar)) cb.setPosition(cb.curIndex - 1)
        return true
    }

    private fun matchNumberFirst(cb: CharacterBuffer): Boolean {
        val digit = cb.advanceCharWhile(this::isCurrencyPunctuation, Char::isDigit)
        if (isCurrencyPunctuation(cb.prevChar)) return false
        if (digit.second == 0) return false
        cb.advanceCharIf { it.isWhitespace() }
        var subNode: Node = CURRENCY_TRIE.root
        cb.advanceCharWhile {
            if (it !in subNode) return@advanceCharWhile false
            subNode = subNode.childNodes[it]!!
            return@advanceCharWhile true
        }
        if (!subNode.isWord) return false
        if (cb.curChar.isLetter()) return false
        return true
    }

    private fun isCurrencyPunctuation(char: Char) = char in currencyPunctuation
    private fun isCurrencySymbol(char: Char) = char.category == CharCategory.CURRENCY_SYMBOL
}

