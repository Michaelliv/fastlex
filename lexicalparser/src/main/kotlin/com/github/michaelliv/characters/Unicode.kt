package com.github.michaelliv.characters

import com.github.michaelliv.extensions.charSetUnion
import com.github.michaelliv.extensions.openCharSetOf
import com.github.michaelliv.extensions.toCharSet

object Unicode {

    val EXCLAMATION_MARK = openCharSetOf(
        '!',  // U+0021		EXCLAMATION MARK = factorial = bang
        '!',  // U+0021		EXCLAMATION MARK = factorial = bang
        '¡',  // U+00A1		INVERTED EXCLAMATION MARK (Spanish, Asturian, Galician)
        '‼',  // U+203C		DOUBLE EXCLAMATION MARK
        '⁈',  // U+2048		QUESTION EXCLAMATION MARK
        '❢',  // U+2762		HEAVY EXCLAMATION MARK ORNAMENT
        '❣',  // U+2763		HEAVY HEART EXCLAMATION MARK ORNAMENT
        '❗',  // U+2757	HEAVY EXCLAMATION MARK SYMBOL = obstacles on the road, ARIB STD B24
        '❕',  // U+2755	WHITE EXCLAMATION MARK ORNAMENT
        '︕',  // U+FE15	PRESENTATION FORM FOR VERTICAL EXCLAMATION MARK
        '﹗',  // U+FE57	SMALL EXCLAMATION MARK
        '！',  // U+FF01	FULLWIDTH EXCLAMATION MARK
    )

    val QUOTATION_MARK = openCharSetOf(
        '"',  // U+0022		QUOTATION MARK
        '«',  // U+00AB		LEFT-POINTING DOUBLE ANGLE QUOTATION MARK = left guillemet = chevrons (in typography)
        '»',  // U+00BB		RIGHT-POINTING DOUBLE ANGLE QUOTATION MARK = right guillemet Quotation marks and apostrophe (Use of quotation marks differs by language. The character names cannot reflect actual usage for all languages.)
        '‘',  // U+2018		LEFT SINGLE QUOTATION MARK = single turned comma quotation mark
        '’',  // U+2019		RIGHT SINGLE QUOTATION MARK = single comma quotation mark
        '‚',  // U+201A		SINGLE LOW-9 QUOTATION MARK = low single comma quotation mark
        '‛',  // U+201B		SINGLE HIGH-REVERSED-9 QUOTATION MARK = single reversed comma quotation mark
        '“',  // U+201C		LEFT DOUBLE QUOTATION MARK = double turned comma quotation mark
        '”',  // U+201D		RIGHT DOUBLE QUOTATION MARK = double comma quotation mark
        '„',  // U+201E		DOUBLE LOW-9 QUOTATION MARK = low double comma quotation mark
        '‟',  // U+201F		DOUBLE HIGH-REVERSED-9 QUOTATION MARK = double reversed comma quotation mark Quotation marks
        '‹',  // U+2039		SINGLE LEFT-POINTING ANGLE QUOTATION MARK = left pointing single guillemet
        '›',  // U+203A		SINGLE RIGHT-POINTING ANGLE QUOTATION MARK = right pointing single guillemet Ceilings and floors (These characters are tall and narrow mathematical delimiters, in contrast to the quine corners or half brackets. They are also distinct from CJK corner brackets, which are wide quotation marks.)
        '❛',  // U+275B		HEAVY SINGLE TURNED COMMA QUOTATION MARK ORNAMENT
        '❜',  // U+275C		HEAVY SINGLE COMMA QUOTATION MARK ORNAMENT
        '❝',  // U+275D		HEAVY DOUBLE TURNED COMMA QUOTATION MARK ORNAMENT
        '❞',  // U+275E		HEAVY DOUBLE COMMA QUOTATION MARK ORNAMENT
        '❟',  // U+275F		HEAVY LOW SINGLE COMMA QUOTATION MARK ORNAMENT
        '❠',  // U+2760		HEAVY LOW DOUBLE COMMA QUOTATION MARK ORNAMENT
        '＂',  // U+FF02 	FULLWIDTH QUOTATION MARK
    )

    val NUMBER_SIGN = openCharSetOf(
        '#',  // U+0023		NUMBER SIGN = pound sign, hash, crosshatch, octothorpe
        '﹟',  // U+FE5F	SMALL NUMBER SIGN
        '＃',  // U+FF03	FULLWIDTH NUMBER SIGN
        'ℂ',  // U+2102		DOUBLE-STRUCK CAPITAL C = the set of complex numbers
        'ℊ',  // U+210A	SCRIPT SMALL G = real number symbol
        'ℕ',  // U+2115		DOUBLE-STRUCK CAPITAL N = natural number
        'ℚ',  // U+211A		DOUBLE-STRUCK CAPITAL Q = the set of rational numbers
        'ℝ',  // U+211D		DOUBLE-STRUCK CAPITAL R = the set of real numbers
    )

    val AMPERSAND = openCharSetOf(
        '&',  // U+0026		AMPERSAND
        '⅋',  // U+214B		TURNED AMPERSAND
        '﹠',  // U+FE60	SMALL AMPERSAND
        '＆',  // U+FF06	FULLWIDTH AMPERSAND
    )

    val PERCENT = openCharSetOf(
        '%',  // U+0025		PERCENT SIGN
        '﹪',  // U+FE6A	SMALL PERCENT SIGN
        '％',  // U+FF05	FULLWIDTH PERCENT SIGN
        '٪',  // U+066A		ARABIC PERCENT SIGN
    )

    val NUMBER = openCharSetOf(
        '﹟',  //U+FE5F		SMALL NUMBER SIGN
        '＃',  //U+FF03		FULLWIDTH NUMBER SIGN
        '#',  //U+0023		NUMBER SIGN
    )

    val APOSTROPHE = openCharSetOf(
        '»',  // U+00BB		RIGHT-POINTING DOUBLE ANGLE QUOTATION MARK = right guillemet Quotation marks and apostrophe (Use of quotation marks differs by language. The character names cannot reflect actual usage for all languages.)
        '\'',  // U+0027	APOSTROPHE = apostrophe-quote = APL quote Quotation marks and apostrophe (Use of quotation marks differs by language. The character names cannot reflect actual usage for all languages.)
        '＇',  // U+FF07	FULLWIDTH APOSTROPHE
    )

    val LEFT_PARENTHESIS = openCharSetOf(
        '(',  // U+0028		LEFT PARENTHESIS = opening parenthesis
        '⁽',  // U+207D		SUPERSCRIPT LEFT PARENTHESIS
        '₍',  // U+208D		SUBSCRIPT LEFT PARENTHESIS
        '⎛',  // U+239B		LEFT PARENTHESIS UPPER HOOK
        '⎜',  // U+239C		LEFT PARENTHESIS EXTENSION
        '⎝',  // U+239D		LEFT PARENTHESIS LOWER HOOK
        '﴾',  // U+FD3E		ORNATE LEFT PARENTHESIS
        '︵',  // U+FE35	PRESENTATION FORM FOR VERTICAL LEFT PARENTHESIS
        '﹙',  // U+FE59	SMALL LEFT PARENTHESIS
        '（',  // U+FF08	FULLWIDTH LEFT PARENTHESIS
        '｟',  // U+FF5F	FULLWIDTH LEFT WHITE PARENTHESIS
    )

    val RIGHT_PARENTHESIS = openCharSetOf(
        ')',  // U+0029		RIGHT PARENTHESIS = closing parenthesis
        '⁾',  // U+207E		SUPERSCRIPT RIGHT PARENTHESIS
        '₎',  // U+208E		SUBSCRIPT RIGHT PARENTHESIS
        '⎞',  // U+239E		RIGHT PARENTHESIS UPPER HOOK
        '⎟',  // U+239F		RIGHT PARENTHESIS EXTENSION
        '⎠',  // U+23A0		RIGHT PARENTHESIS LOWER HOOK
        '﴿',  // U+FD3F		ORNATE RIGHT PARENTHESIS
        '︶',  // U+FE36	PRESENTATION FORM FOR VERTICAL RIGHT PARENTHESIS
        '﹚',  // U+FE5A	SMALL RIGHT PARENTHESIS
        '）',  // U+FF09	FULLWIDTH RIGHT PARENTHESIS
        '｠',  // U+FF60	FULLWIDTH RIGHT WHITE PARENTHESIS
    )

    val FULL_STOP = openCharSetOf(
        '.',  // U+002E		FULL STOP (period, dot, decimal point)
        '﹒',  // U+FE52	SMALL FULL STOP
        '．',  // U+FF0E	FULLWIDTH FULL STOP
    )

    val COMMA = openCharSetOf(
        ',',  // U+002C		COMMA = decimal separator
        '·',  // U+00B7		MIDDLE DOT = midpoint (in typography) = Georgian comma = Greek middle dot (ano teleia)
        '︐',  // U+FE10	PRESENTATION FORM FOR VERTICAL COMMA
        '︑',  // U+FE11	PRESENTATION FORM FOR VERTICAL IDEOGRAPHIC COMMA
        '﹐',  // U+FE50	SMALL COMMA
        '﹑',  // U+FE51	SMALL IDEOGRAPHIC COMMA
        '，',  // U+FF0C	FULLWIDTH COMMA
    )

    val COLON = openCharSetOf(
        ':',  // U+003A		COLON
        '≔',  // U+2254		COLON EQUALS
        '≕',  // U+2255		EQUALS COLON
        '︓',  // U+FE13	PRESENTATION FORM FOR VERTICAL COLON
        '﹕',  // U+FE55	SMALL COLON
        '：',  // U+FF1A	FULLWIDTH COLON
    )

    val SEMICOLON = openCharSetOf(
        ';',  // U+003B		SEMICOLON
        '⁏',  // U+204F		REVERSED SEMICOLON
        '︔',  // U+FE14	PRESENTATION FORM FOR VERTICAL SEMICOLON
        '﹔',  // U+FE54	SMALL SEMICOLON
        '；',  // U+FF1B	FULLWIDTH SEMICOLON
    )

    // Currency symbols (A number of currency symbols are found in other blocks. Fullwidth versions of some currency symbols are found in the Halfwidth and Fullwidth Forms block.)
    val CURRENCY_SIGN = openCharSetOf(
        '$',  // U+0024		DOLLAR SIGN = milreis, escudo
        '﹩',  // U+FE69	SMALL DOLLAR SIGN
        '＄',  // U+FF04	FULLWIDTH DOLLAR SIGN
        '₠',  // U+20A0		EURO-CURRENCY SIGN
        '₡',  // U+20A1		COLON SIGN
        '₢',  // U+20A2		CRUZEIRO SIGN
        '₣',  // U+20A3		FRENCH FRANC SIGN
        '₤',  // U+20A4		LIRA SIGN
        '₥',  // U+20A5		MILL SIGN
        '₦',  // U+20A6		NAIRA SIGN
        '₧',  // U+20A7		PESETA SIGN
        '₨',  // U+20A8		RUPEE SIGN
        '₩',  // U+20A9		WON SIGN
        '₪',  // U+20AA		NEW SHEQEL SIGN
        '₫',  // U+20AB		DONG SIGN
        '€',  // U+20AC		EURO SIGN
        '₭',  // U+20AD		KIP SIGN
        '₮',  // U+20AE		TUGRIK SIGN
        '₯',  // U+20AF		DRACHMA SIGN
        '₰',  // U+20B0		GERMAN PENNY SIGN
        '₱',  // U+20B1		PESO SIGN
        '₲',  // U+20B2		GUARANI SIGN
        '₳',  // U+20B3		AUSTRAL SIGN
        '₴',  // U+20B4		HRYVNIA SIGN
        '₵',  // U+20B5		CEDI SIGN
        '₶',  // U+20B6		LIVRE TOURNOIS SIGN
        '₷',  // U+20B7		SPESMILO SIGN
        '₸',  // U+20B8		TENGE SIGN
        '₹',  // U+20B9		INDIAN RUPEE SIGN
        'ℳ',  // U+2133	SCRIPT CAPITAL M = M-matrix (physics) = German Mark currency symbol, before WWII
        '¢',  // U+00A2		CENT SIGN
        '£',  // U+00A3		POUND SIGN (pound sterling, Irish punt, Italian lira, Turkish lira, etc.)
        '¤',  // U+00A4		CURRENCY SIGN
        '¥',  // U+00A5		YEN SIGN = yuan sign
        '￥',  // U+FFE5	FULLWIDTH YEN SIGN
        '￠',  // U+FFE0	FULLWIDTH CENT SIGN
        '￡',  // U+FFE1	FULLWIDTH POUND SIGN
        '￦',  // U+FFE6	FULLWIDTH WON SIGN
        '﷼',  // U+FDFC		RIAL SIGN
    )

    val MINUS_SIGN = openCharSetOf(
        '-',  // U+002D		HYPHEN-MINUS = hyphen or minus sign
        '－',  // U+FF0D	FULLWIDTH HYPHEN-MINUS
        '﹣',  // U+FE63	SMALL HYPHEN-MINUS
        '−',  // U+2212		MINUS SIGN
    )

    val PLUS_SIGN = openCharSetOf(
        '+',  // U+002B		PLUS SIGN
        '±',  // U+00B1		PLUS-MINUS SIGN
        '⁺',  // U+207A		SUPERSCRIPT PLUS SIGN
        '₊',  // U+208A		SUBSCRIPT PLUS SIGN
        '∓',  // U+2213		MINUS-OR-PLUS SIGN
        '∔',  // U+2214		DOT PLUS
        '﹢',  // U+FE62	SMALL PLUS SIGN
        '＋',  // U+FF0B	FULLWIDTH PLUS SIGN
    )

    val TILDE_SIGN = openCharSetOf(
        '~',  // U+007E		TILDE
        '∼',  // U+223C		TILDE OPERATOR = varies with (proportional to) = difference between = similar to = not = cycle = APL tilde
        '∽',  // U+223D		REVERSED TILDE = lazy S
        '≁',  // U+2241		NOT TILDE
    )

    val DASH = MINUS_SIGN charSetUnion openCharSetOf(
        '~',  // U+007E		TILDE
        '‐',  // U+2010		HYPHEN
        '‑',  // U+2011		NON-BREAKING HYPHEN
        '‒',  // U+2012		FIGURE DASH
        '–',  // U+2013		EN DASH
        '—',  // U+2014		EM DASH
        '―',  // U+2015		HORIZONTAL BAR = quotation dash
        '﹉',  // U+FE49	DASHED OVERLINE
        '﹊',  // U+FE4A	CENTRELINE OVERLINE
        '﹋',  // U+FE4B	WAVY OVERLINE
        '﹌',  // U+FE4C	DOUBLE WAVY OVERLINE
        '⁓',  // U+2053		SWUNG DASH
        '﹘',  // U+FE58	SMALL EM DASH
    )

    val UNDERSCORE = openCharSetOf(
        '_',  // U+005F		LOW LINE (spacing underscore)
        '﹍',  // U+FE4D	DASHED LOW LINE
        '﹎',  // U+FE4E	CENTRELINE LOW LINE
        '﹏',  // U+FE4F	WAVY LOW LINE
    )

    val SPACE = openCharSetOf(
        '	',
        ' ',  // U+0020 		SPACE
        ' ',  // U+00A0 		NO-BREAK SPACE (commonly abbreviated as NBSP)
        '␢',  // U+2422 		BLANK SYMBOL
        '␣',  // U+2423 		OPEN BOX
        '﻿',  // U+FEFF 	ZERO WIDTH NO-BREAK SPACE = BYTE ORDER MARK (BOM), ZWNBSP
    )

    val LEFT_BRACKET = openCharSetOf(
        '‹',  // U+2039		SINGLE LEFT-POINTING ANGLE QUOTATION MARK = left pointing single guillemet
        '[',  // U+005B		LEFT SQUARE BRACKET = opening square bracket
        '{',  // U+007B		LEFT CURLY BRACKET = opening curly bracket = left brace
        '⁅',  // U+2045		LEFT SQUARE BRACKET WITH QUILL
        '⎡',  // U+23A1		LEFT SQUARE BRACKET UPPER CORNER
        '⎢',  // U+23A2		LEFT SQUARE BRACKET EXTENSION
        '⎣',  // U+23A3		LEFT SQUARE BRACKET LOWER CORNER
        '⎧',  // U+23A7		LEFT CURLY BRACKET UPPER HOOK
        '⎨',  // U+23A8		LEFT CURLY BRACKET MIDDLE PIECE
        '⎩',  // U+23A9		LEFT CURLY BRACKET LOWER HOOK
        '⎪',  // U+23AA		CURLY BRACKET EXTENSION
        '⸢',  // U+2E22		TOP LEFT HALF BRACKET
        '⸤',  // U+2E24		BOTTOM LEFT HALF BRACKET
        '︗',  // U+FE17	PRESENTATION FORM FOR VERTICAL LEFT WHITE LENTICULAR BRACKET
        '︷',  // U+FE37	PRESENTATION FORM FOR VERTICAL LEFT CURLY BRACKET
        '︹',  // U+FE39	PRESENTATION FORM FOR VERTICAL LEFT TORTOISE SHELL BRACKET
        '︻',  // U+FE3B	PRESENTATION FORM FOR VERTICAL LEFT BLACK LENTICULAR BRACKET
        '︽',  // U+FE3D	PRESENTATION FORM FOR VERTICAL LEFT DOUBLE ANGLE BRACKET
        '︿',  // U+FE3F	PRESENTATION FORM FOR VERTICAL LEFT ANGLE BRACKET
        '﹁',  // U+FE41	PRESENTATION FORM FOR VERTICAL LEFT CORNER BRACKET
        '﹃',  // U+FE43	PRESENTATION FORM FOR VERTICAL LEFT WHITE CORNER BRACKET
        '﹛',  // U+FE5B	SMALL LEFT CURLY BRACKET
        '﹝',  // U+FE5D	SMALL LEFT TORTOISE SHELL BRACKET
        '［',  // U+FF3B	FULLWIDTH LEFT SQUARE BRACKET
        '｛',  // U+FF5B	FULLWIDTH LEFT CURLY BRACKET
    )

    val RIGHT_BRACKET = openCharSetOf(
        '›',  // U+203A		SINGLE RIGHT-POINTING ANGLE QUOTATION MARK = right pointing single guillemet Ceilings and floors (These characters are tall and narrow mathematical delimiters, in contrast to the quine corners or half brackets. They are also distinct from CJK corner brackets, which are wide quotation marks.)
        ']',  // U+005D		RIGHT SQUARE BRACKET = closing square bracket
        '}',  // U+007D		RIGHT CURLY BRACKET = closing curly bracket = right brace
        '⁆',  // U+2046		RIGHT SQUARE BRACKET WITH QUILL
        '⎤',  // U+23A4		RIGHT SQUARE BRACKET UPPER CORNER
        '⎥',  // U+23A5		RIGHT SQUARE BRACKET EXTENSION
        '⎦',  // U+23A6		RIGHT SQUARE BRACKET LOWER CORNER
        '⎫',  // U+23AB		RIGHT CURLY BRACKET UPPER HOOK
        '⎬',  // U+23AC		RIGHT CURLY BRACKET MIDDLE PIECE
        '⎭',  // U+23AD		RIGHT CURLY BRACKET LOWER HOOK
        '⸣',  // U+2E23		TOP RIGHT HALF BRACKET
        '⸥',  // U+2E25		BOTTOM RIGHT HALF BRACKET
        '︸',  // U+FE38	PRESENTATION FORM FOR VERTICAL RIGHT CURLY BRACKET
        '︺',  // U+FE3A	PRESENTATION FORM FOR VERTICAL RIGHT TORTOISE SHELL BRACKET
        '︼',  // U+FE3C	PRESENTATION FORM FOR VERTICAL RIGHT BLACK LENTICULAR BRACKET
        '︾',  // U+FE3E	PRESENTATION FORM FOR VERTICAL RIGHT DOUBLE ANGLE BRACKET
        '﹀',  // U+FE40	PRESENTATION FORM FOR VERTICAL RIGHT ANGLE BRACKET
        '﹂',  // U+FE42	PRESENTATION FORM FOR VERTICAL RIGHT CORNER BRACKET
        '﹄',  // U+FE44	PRESENTATION FORM FOR VERTICAL RIGHT WHITE CORNER BRACKET
        '﹜',  // U+FE5C	SMALL RIGHT CURLY BRACKET
        '﹞',  // U+FE5E	SMALL RIGHT TORTOISE SHELL BRACKET
        '］',  // U+FF3D	FULLWIDTH RIGHT SQUARE BRACKET
        '｝',  // U+FF5D	FULLWIDTH RIGHT CURLY BRACKET
    )

    val QUESTION_MARK = openCharSetOf(
        '⁈',  // U+2048		QUESTION EXCLAMATION MARK
        '?',  // U+003F		QUESTION MARK
        '¿',  // U+00BF		INVERTED QUESTION MARK = turned question mark
        '⁇',  // U+2047		DOUBLE QUESTION MARK
        '⁈',  // U+2048		QUESTION EXCLAMATION MARK
        '⁉',  // U+2049		EXCLAMATION QUESTION MARK
        '≟',  // U+225F		QUESTIONED EQUAL TO
        '❓',  // U+2753	BLACK QUESTION MARK ORNAMENT
        '❔',  // U+2754	WHITE QUESTION MARK ORNAMENT
        '⸮',  // U+2E2E		REVERSED QUESTION MARK = punctus percontativus
        '︖',  // U+FE16	PRESENTATION FORM FOR VERTICAL QUESTION MARK
        '﹖',  // U+FE56	SMALL QUESTION MARK
        '？',  // U+FF1F	FULLWIDTH QUESTION MARK
    )

    val SLASH = openCharSetOf(
        '\\',  // U+005C	REVERSE SOLIDUS = backslash
        '/',  // U+002F		SOLIDUS (slash, virgule)
        '⁄',  // U+2044		FRACTION SLASH = solidus (in typography)
        '∕',  // U+2215		DIVISION SLASH
    )

    val LINE_TERMINATION = "\n\r\\v\\f".toCharSet()

    val WHITE_SPACE = LINE_TERMINATION charSetUnion SPACE

}