// Generated from Micro.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MicroLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, KEYWORD=34, OPERATOR=35, COMMENT=36, WHITESPACE=37, 
		IDENTIFIER=38, INTLITERAL=39, FLOATLITERAL=40, STRINGLITERAL=41;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
		"KEYWORD", "OPERATOR", "COMMENT", "WHITESPACE", "IDENTIFIER", "INTLITERAL", 
		"FLOATLITERAL", "STRINGLITERAL"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'PROGRAM'", "'BEGIN'", "'END'", "'STRING'", "':='", "';'", "'FLOAT'", 
		"'INT'", "'VOID'", "','", "'FUNCTION'", "'('", "')'", "'READ'", "'WRITE'", 
		"'RETURN'", "'+'", "'-'", "'*'", "'/'", "'IF'", "'FI'", "'ELSE'", "'<'", 
		"'>'", "'='", "'!='", "'<='", "'>='", "'WHILE'", "'ENDWHILE'", "'FOR'", 
		"'ROF'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, "KEYWORD", 
		"OPERATOR", "COMMENT", "WHITESPACE", "IDENTIFIER", "INTLITERAL", "FLOATLITERAL", 
		"STRINGLITERAL"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


		public SymbolTableStack STACK = new SymbolTableStack();
		public SymbolTable TABLE = new SymbolTable("GLOBAL");


	public MicroLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Micro.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2+\u0171\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\24"+
		"\3\24\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30"+
		"\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36"+
		"\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3!\3!\3"+
		"!\3!\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3"+
		"#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3"+
		"#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3"+
		"#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\5#\u0130"+
		"\n#\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\5$\u013c\n$\3%\3%\3%\3%\7%\u0142\n%"+
		"\f%\16%\u0145\13%\3%\3%\3&\6&\u014a\n&\r&\16&\u014b\3&\3&\3\'\3\'\7\'"+
		"\u0152\n\'\f\'\16\'\u0155\13\'\3(\6(\u0158\n(\r(\16(\u0159\3)\7)\u015d"+
		"\n)\f)\16)\u0160\13)\3)\3)\7)\u0164\n)\f)\16)\u0167\13)\3*\3*\7*\u016b"+
		"\n*\f*\16*\u016e\13*\3*\3*\3\u016c\2+\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+\3\2\t\6"+
		"\2,-//\61\61??\6\2*+..=>@@\4\2\f\f\17\17\5\2\13\f\16\17\"\"\3\2C|\4\2"+
		"\62;C|\3\2\62;\u018d\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3"+
		"\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2"+
		"\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2"+
		"\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2"+
		"\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2"+
		"\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q"+
		"\3\2\2\2\2S\3\2\2\2\3U\3\2\2\2\5]\3\2\2\2\7c\3\2\2\2\tg\3\2\2\2\13n\3"+
		"\2\2\2\rq\3\2\2\2\17s\3\2\2\2\21y\3\2\2\2\23}\3\2\2\2\25\u0082\3\2\2\2"+
		"\27\u0084\3\2\2\2\31\u008d\3\2\2\2\33\u008f\3\2\2\2\35\u0091\3\2\2\2\37"+
		"\u0096\3\2\2\2!\u009c\3\2\2\2#\u00a3\3\2\2\2%\u00a5\3\2\2\2\'\u00a7\3"+
		"\2\2\2)\u00a9\3\2\2\2+\u00ab\3\2\2\2-\u00ae\3\2\2\2/\u00b1\3\2\2\2\61"+
		"\u00b6\3\2\2\2\63\u00b8\3\2\2\2\65\u00ba\3\2\2\2\67\u00bc\3\2\2\29\u00bf"+
		"\3\2\2\2;\u00c2\3\2\2\2=\u00c5\3\2\2\2?\u00cb\3\2\2\2A\u00d4\3\2\2\2C"+
		"\u00d8\3\2\2\2E\u012f\3\2\2\2G\u013b\3\2\2\2I\u013d\3\2\2\2K\u0149\3\2"+
		"\2\2M\u014f\3\2\2\2O\u0157\3\2\2\2Q\u015e\3\2\2\2S\u0168\3\2\2\2UV\7R"+
		"\2\2VW\7T\2\2WX\7Q\2\2XY\7I\2\2YZ\7T\2\2Z[\7C\2\2[\\\7O\2\2\\\4\3\2\2"+
		"\2]^\7D\2\2^_\7G\2\2_`\7I\2\2`a\7K\2\2ab\7P\2\2b\6\3\2\2\2cd\7G\2\2de"+
		"\7P\2\2ef\7F\2\2f\b\3\2\2\2gh\7U\2\2hi\7V\2\2ij\7T\2\2jk\7K\2\2kl\7P\2"+
		"\2lm\7I\2\2m\n\3\2\2\2no\7<\2\2op\7?\2\2p\f\3\2\2\2qr\7=\2\2r\16\3\2\2"+
		"\2st\7H\2\2tu\7N\2\2uv\7Q\2\2vw\7C\2\2wx\7V\2\2x\20\3\2\2\2yz\7K\2\2z"+
		"{\7P\2\2{|\7V\2\2|\22\3\2\2\2}~\7X\2\2~\177\7Q\2\2\177\u0080\7K\2\2\u0080"+
		"\u0081\7F\2\2\u0081\24\3\2\2\2\u0082\u0083\7.\2\2\u0083\26\3\2\2\2\u0084"+
		"\u0085\7H\2\2\u0085\u0086\7W\2\2\u0086\u0087\7P\2\2\u0087\u0088\7E\2\2"+
		"\u0088\u0089\7V\2\2\u0089\u008a\7K\2\2\u008a\u008b\7Q\2\2\u008b\u008c"+
		"\7P\2\2\u008c\30\3\2\2\2\u008d\u008e\7*\2\2\u008e\32\3\2\2\2\u008f\u0090"+
		"\7+\2\2\u0090\34\3\2\2\2\u0091\u0092\7T\2\2\u0092\u0093\7G\2\2\u0093\u0094"+
		"\7C\2\2\u0094\u0095\7F\2\2\u0095\36\3\2\2\2\u0096\u0097\7Y\2\2\u0097\u0098"+
		"\7T\2\2\u0098\u0099\7K\2\2\u0099\u009a\7V\2\2\u009a\u009b\7G\2\2\u009b"+
		" \3\2\2\2\u009c\u009d\7T\2\2\u009d\u009e\7G\2\2\u009e\u009f\7V\2\2\u009f"+
		"\u00a0\7W\2\2\u00a0\u00a1\7T\2\2\u00a1\u00a2\7P\2\2\u00a2\"\3\2\2\2\u00a3"+
		"\u00a4\7-\2\2\u00a4$\3\2\2\2\u00a5\u00a6\7/\2\2\u00a6&\3\2\2\2\u00a7\u00a8"+
		"\7,\2\2\u00a8(\3\2\2\2\u00a9\u00aa\7\61\2\2\u00aa*\3\2\2\2\u00ab\u00ac"+
		"\7K\2\2\u00ac\u00ad\7H\2\2\u00ad,\3\2\2\2\u00ae\u00af\7H\2\2\u00af\u00b0"+
		"\7K\2\2\u00b0.\3\2\2\2\u00b1\u00b2\7G\2\2\u00b2\u00b3\7N\2\2\u00b3\u00b4"+
		"\7U\2\2\u00b4\u00b5\7G\2\2\u00b5\60\3\2\2\2\u00b6\u00b7\7>\2\2\u00b7\62"+
		"\3\2\2\2\u00b8\u00b9\7@\2\2\u00b9\64\3\2\2\2\u00ba\u00bb\7?\2\2\u00bb"+
		"\66\3\2\2\2\u00bc\u00bd\7#\2\2\u00bd\u00be\7?\2\2\u00be8\3\2\2\2\u00bf"+
		"\u00c0\7>\2\2\u00c0\u00c1\7?\2\2\u00c1:\3\2\2\2\u00c2\u00c3\7@\2\2\u00c3"+
		"\u00c4\7?\2\2\u00c4<\3\2\2\2\u00c5\u00c6\7Y\2\2\u00c6\u00c7\7J\2\2\u00c7"+
		"\u00c8\7K\2\2\u00c8\u00c9\7N\2\2\u00c9\u00ca\7G\2\2\u00ca>\3\2\2\2\u00cb"+
		"\u00cc\7G\2\2\u00cc\u00cd\7P\2\2\u00cd\u00ce\7F\2\2\u00ce\u00cf\7Y\2\2"+
		"\u00cf\u00d0\7J\2\2\u00d0\u00d1\7K\2\2\u00d1\u00d2\7N\2\2\u00d2\u00d3"+
		"\7G\2\2\u00d3@\3\2\2\2\u00d4\u00d5\7H\2\2\u00d5\u00d6\7Q\2\2\u00d6\u00d7"+
		"\7T\2\2\u00d7B\3\2\2\2\u00d8\u00d9\7T\2\2\u00d9\u00da\7Q\2\2\u00da\u00db"+
		"\7H\2\2\u00dbD\3\2\2\2\u00dc\u00dd\7R\2\2\u00dd\u00de\7T\2\2\u00de\u00df"+
		"\7Q\2\2\u00df\u00e0\7I\2\2\u00e0\u00e1\7T\2\2\u00e1\u00e2\7C\2\2\u00e2"+
		"\u0130\7O\2\2\u00e3\u00e4\7D\2\2\u00e4\u00e5\7G\2\2\u00e5\u00e6\7I\2\2"+
		"\u00e6\u00e7\7K\2\2\u00e7\u0130\7P\2\2\u00e8\u00e9\7G\2\2\u00e9\u00ea"+
		"\7P\2\2\u00ea\u0130\7F\2\2\u00eb\u00ec\7H\2\2\u00ec\u00ed\7W\2\2\u00ed"+
		"\u00ee\7P\2\2\u00ee\u00ef\7E\2\2\u00ef\u00f0\7V\2\2\u00f0\u00f1\7K\2\2"+
		"\u00f1\u00f2\7Q\2\2\u00f2\u0130\7P\2\2\u00f3\u00f4\7T\2\2\u00f4\u00f5"+
		"\7G\2\2\u00f5\u00f6\7C\2\2\u00f6\u0130\7F\2\2\u00f7\u00f8\7Y\2\2\u00f8"+
		"\u00f9\7T\2\2\u00f9\u00fa\7K\2\2\u00fa\u00fb\7V\2\2\u00fb\u0130\7G\2\2"+
		"\u00fc\u00fd\7K\2\2\u00fd\u0130\7H\2\2\u00fe\u00ff\7G\2\2\u00ff\u0100"+
		"\7N\2\2\u0100\u0101\7U\2\2\u0101\u0130\7G\2\2\u0102\u0103\7H\2\2\u0103"+
		"\u0130\7K\2\2\u0104\u0105\7H\2\2\u0105\u0106\7Q\2\2\u0106\u0130\7T\2\2"+
		"\u0107\u0108\7T\2\2\u0108\u0109\7Q\2\2\u0109\u0130\7H\2\2\u010a\u010b"+
		"\7E\2\2\u010b\u010c\7Q\2\2\u010c\u010d\7P\2\2\u010d\u010e\7V\2\2\u010e"+
		"\u010f\7K\2\2\u010f\u0110\7P\2\2\u0110\u0111\7W\2\2\u0111\u0130\7G\2\2"+
		"\u0112\u0113\7D\2\2\u0113\u0114\7T\2\2\u0114\u0115\7G\2\2\u0115\u0116"+
		"\7C\2\2\u0116\u0130\7M\2\2\u0117\u0118\7T\2\2\u0118\u0119\7G\2\2\u0119"+
		"\u011a\7V\2\2\u011a\u011b\7W\2\2\u011b\u011c\7T\2\2\u011c\u0130\7P\2\2"+
		"\u011d\u011e\7K\2\2\u011e\u011f\7P\2\2\u011f\u0130\7V\2\2\u0120\u0121"+
		"\7X\2\2\u0121\u0122\7Q\2\2\u0122\u0123\7K\2\2\u0123\u0130\7F\2\2\u0124"+
		"\u0125\7U\2\2\u0125\u0126\7V\2\2\u0126\u0127\7T\2\2\u0127\u0128\7K\2\2"+
		"\u0128\u0129\7P\2\2\u0129\u0130\7I\2\2\u012a\u012b\7H\2\2\u012b\u012c"+
		"\7N\2\2\u012c\u012d\7Q\2\2\u012d\u012e\7C\2\2\u012e\u0130\7V\2\2\u012f"+
		"\u00dc\3\2\2\2\u012f\u00e3\3\2\2\2\u012f\u00e8\3\2\2\2\u012f\u00eb\3\2"+
		"\2\2\u012f\u00f3\3\2\2\2\u012f\u00f7\3\2\2\2\u012f\u00fc\3\2\2\2\u012f"+
		"\u00fe\3\2\2\2\u012f\u0102\3\2\2\2\u012f\u0104\3\2\2\2\u012f\u0107\3\2"+
		"\2\2\u012f\u010a\3\2\2\2\u012f\u0112\3\2\2\2\u012f\u0117\3\2\2\2\u012f"+
		"\u011d\3\2\2\2\u012f\u0120\3\2\2\2\u012f\u0124\3\2\2\2\u012f\u012a\3\2"+
		"\2\2\u0130F\3\2\2\2\u0131\u0132\7<\2\2\u0132\u013c\7?\2\2\u0133\u013c"+
		"\t\2\2\2\u0134\u0135\7#\2\2\u0135\u013c\7?\2\2\u0136\u013c\t\3\2\2\u0137"+
		"\u0138\7>\2\2\u0138\u013c\7?\2\2\u0139\u013a\7@\2\2\u013a\u013c\7?\2\2"+
		"\u013b\u0131\3\2\2\2\u013b\u0133\3\2\2\2\u013b\u0134\3\2\2\2\u013b\u0136"+
		"\3\2\2\2\u013b\u0137\3\2\2\2\u013b\u0139\3\2\2\2\u013cH\3\2\2\2\u013d"+
		"\u013e\7/\2\2\u013e\u013f\7/\2\2\u013f\u0143\3\2\2\2\u0140\u0142\n\4\2"+
		"\2\u0141\u0140\3\2\2\2\u0142\u0145\3\2\2\2\u0143\u0141\3\2\2\2\u0143\u0144"+
		"\3\2\2\2\u0144\u0146\3\2\2\2\u0145\u0143\3\2\2\2\u0146\u0147\b%\2\2\u0147"+
		"J\3\2\2\2\u0148\u014a\t\5\2\2\u0149\u0148\3\2\2\2\u014a\u014b\3\2\2\2"+
		"\u014b\u0149\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u014e"+
		"\b&\2\2\u014eL\3\2\2\2\u014f\u0153\t\6\2\2\u0150\u0152\t\7\2\2\u0151\u0150"+
		"\3\2\2\2\u0152\u0155\3\2\2\2\u0153\u0151\3\2\2\2\u0153\u0154\3\2\2\2\u0154"+
		"N\3\2\2\2\u0155\u0153\3\2\2\2\u0156\u0158\t\b\2\2\u0157\u0156\3\2\2\2"+
		"\u0158\u0159\3\2\2\2\u0159\u0157\3\2\2\2\u0159\u015a\3\2\2\2\u015aP\3"+
		"\2\2\2\u015b\u015d\5O(\2\u015c\u015b\3\2\2\2\u015d\u0160\3\2\2\2\u015e"+
		"\u015c\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u0161\3\2\2\2\u0160\u015e\3\2"+
		"\2\2\u0161\u0165\7\60\2\2\u0162\u0164\5O(\2\u0163\u0162\3\2\2\2\u0164"+
		"\u0167\3\2\2\2\u0165\u0163\3\2\2\2\u0165\u0166\3\2\2\2\u0166R\3\2\2\2"+
		"\u0167\u0165\3\2\2\2\u0168\u016c\7$\2\2\u0169\u016b\n\4\2\2\u016a\u0169"+
		"\3\2\2\2\u016b\u016e\3\2\2\2\u016c\u016d\3\2\2\2\u016c\u016a\3\2\2\2\u016d"+
		"\u016f\3\2\2\2\u016e\u016c\3\2\2\2\u016f\u0170\7$\2\2\u0170T\3\2\2\2\f"+
		"\2\u012f\u013b\u0143\u014b\u0153\u0159\u015e\u0165\u016c\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}