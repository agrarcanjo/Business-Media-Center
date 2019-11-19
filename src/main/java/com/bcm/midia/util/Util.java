package com.bcm.midia.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.InitialContext;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.bcm.midia.exception.BusinessException;
import com.bcm.midia.exception.RequiredException;


public class Util {

	public static final String STRING_VAZIA = "";

	public static final String WILDCARD = "%";

	public static final double PONTUACAO_MINIMA = 0.00;
	public static final double PONTUACAO_MAXIMA = 10.00;

	public static final double NOTA_CLASSIFICATORIA = 7.00;

	public static final Integer NUMERO_MAXIMO_CANDIDATO = 1000;

	public static final String LETRAS_COM_ACENTUACAO = "ÁÀÃÂÄÉÈÊËÍÌÏÎÓÒÕÔÖÚÙÛÜÇÑÝŸáàãâäéèêëíìïîóòõôöúùûüçñýÿ";
	public static final String LETRAS_SEM_ACENTUACAO = "AAAAAEEEEIIIIOOOOOUUUUCNYYaaaaaeeeeiiiiooooouuuucnyy";

	public static final int QTD_CARACTERES_MINIMO = 3;

	/**
	 * Tamanho mÃ¡ximo de arquivo para upload permitido.
	 */
	public static final int TAMANHO_ARQUIVO_MAXIMO = 20000000;

	public static final String HIFEN = "-";

	public static Boolean isNull(Object objeto) {
		return (objeto == null) ? Boolean.TRUE : Boolean.FALSE;
	}

	public static Boolean isEmptyString(String str) {
		return (!isNull(str) && str.length() == 0) ? Boolean.TRUE : Boolean.FALSE;
	}

	public static Boolean isOpcaoSim(char simNao) {
		switch (simNao) {
		case 's':
		case 'S':
			return Boolean.TRUE;
		default:
			return Boolean.FALSE;
		}
	}
	
	public static final Long bigDecimalToLong(BigDecimal decimal) {
		if (decimal == null) {
			return null;
		}
		
		return decimal.longValue();
	}
	
	public static final Integer bigDecimalToInteger(BigDecimal decimal) {
		if (decimal == null) {
			return null;
		}
		
		return decimal.intValue();
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNullOrEmpty(Object pObjeto) {

		if (pObjeto == null) {

			return true;

		} else if (pObjeto instanceof Collection) {

			return ((Collection) pObjeto).isEmpty();

		} else if (pObjeto instanceof String) {

			return ((String) pObjeto).trim().equals(STRING_VAZIA);

		} else if (pObjeto instanceof Integer) {

			return ((Integer) pObjeto).intValue() == 0;
		} else if (pObjeto instanceof Double) {

			return ((Double) pObjeto).doubleValue() == 0.00;
		}

		return false;
	}
	
	public static boolean isNotNullOrEmpty(Object pObjeto) {
		return !isNullOrEmpty(pObjeto);
	}

	public static boolean isNullOuVazio(Integer integer) {
		return integer == null;
	}

	public static boolean isNullOuVazioDouble(Double doubleValue) {
		return doubleValue == null;
	}

	public static String formatarNumero(String pValor) {
		String lNumeroFormatado = "";
		if (!isNullOrEmpty(pValor.trim())) {
			for (int lIndice = 0; lIndice < pValor.length(); lIndice++) {
				if (Character.isDigit(pValor.charAt(lIndice)) || pValor.charAt(lIndice) == ',' || pValor.charAt(lIndice) == '.') {
					lNumeroFormatado = lNumeroFormatado + pValor.charAt(lIndice);
				}
			}
		}
		return lNumeroFormatado;
	}

	public static boolean validaEmail(String pEmail) {
		boolean lValidade = false;
		String lEr = "[a-zA-Z]+[a-zA-Z0-9_.-]+@[a-zA-Z0-9_.-]*\\.+[a-zA-Z]{2,4}";
		Pattern lPattern = Pattern.compile(lEr);
		Matcher lMatcher = lPattern.matcher(pEmail.trim());

		if (!isNullOrEmpty(pEmail.trim()) && lMatcher.find() && lMatcher.group().equals(pEmail.trim()))
			lValidade = true;

		return lValidade;
	}

	@SuppressWarnings("rawtypes")
	public static <T> Boolean isObjectInList(T objeto, Collection lista) {
		Boolean retorno = Boolean.FALSE;
		if (lista != null) {
			for (Object t : lista) {
				if (t.equals(objeto)) {
					retorno = Boolean.TRUE;
				}
			}
		}
		return retorno;
	}

	public static boolean validaPeriodo(final Date pDate1, final Date pDate2) {

		if (isNullOrEmpty(pDate1) && isNullOrEmpty(pDate2)) {
			return true;
		}
		if (isNullOrEmpty(pDate1) && !isNullOrEmpty(pDate2) || !isNullOrEmpty(pDate1) && isNullOrEmpty(pDate2)) {
			return false;
		}
		if ((pDate1.compareTo(pDate2) > 0)) {
			return false;
		}

		return true;
	}

	/**
	 * Padds the string to the left with the given character for the specified length.
	 * 
	 * @param input
	 *            The input string.
	 * @param padding
	 *            The char used for padding.
	 * @param length
	 *            The length of the new string.
	 * @return The padded string.
	 */
	public static String lpad(String input, char padding, int length) {

		if (input == null) {
			input = new String();
		}

		if (input.length() >= length) {
			return input;
		} else {
			StringBuffer result = new StringBuffer();
			int numChars = length - input.length();
			for (int i = 0; i < numChars; i++) {
				result.append(padding);
			}
			result.append(input);
			return result.toString();
		}
	}

	public static String zeroLTrim(String substring) {
		if (!substring.isEmpty() && substring.length() > 1) {
			while (substring.startsWith("0")) {
				substring = substring.substring(1, substring.length());
			}
		}
		return substring;
	}

	public static String toMD5(String password) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		BigInteger hash = new BigInteger(1, messageDigest.digest(password.getBytes()));
		return hash.toString(16);
	}

	public static String getImageFilePath(String nome) {
		String fileName = "imagens/" + nome + ".png";
		File file = new File(fileName);
		fileName = file.getAbsolutePath();
		if (file.exists()) {
			System.out.println("Using: " + fileName);
			return fileName;
		} else {
			System.out.println("File: " + fileName + " not Found!");
			fileName = "imagens/" + nome + ".png";
			file = new File(fileName);
			fileName = file.getAbsolutePath();
			if (file.exists()) {
				System.out.println("Using: " + fileName);
				return fileName;
			} else {
				System.out.println("File: " + fileName + " not Found!");
			}
		}
		return null;
	}

	public static enum enumStringContexto {
		PESSOA, EMPREENDIMENTO
	}

	public static String getReportFilePath(String nome) {
		String fileName = "reports/sources/";
		File file = new File(fileName);
		fileName = file.getAbsolutePath();
		if (file.exists()) {
			System.out.println("Using: " + fileName);
			return fileName;
		} else {
			System.out.println("File: " + fileName + " not Found!");
			fileName = "reports/sources/";
			file = new File(fileName);
			fileName = file.getAbsolutePath();
			if (file.exists()) {
				System.out.println("Using: " + fileName);
				return fileName;
			} else {
				System.out.println("File: " + fileName + " not Found!");
			}
		}
		return null;
	}

	public static Boolean assertEquals(Object object, Object expected) {
		if (isNullOrEmpty(object) && !isNullOrEmpty(expected)) {
			return false;
		}

		if (object == expected) {
			return true;
		} else {
			return false;
		}

	}

	public static Collection<Integer> stringToArrayInt(String valor) {
		StringTokenizer token = new StringTokenizer(valor, ",");
		Collection<Integer> ides = new ArrayList<Integer>();
		while (token.hasMoreElements()) {
			ides.add(Integer.valueOf(token.nextToken()));
		}
		return ides;
	}

	public static String arrayIntToString(List<Integer> ides) {
		StringBuffer valor = new StringBuffer();

		int limite = ides.size() - 1;
		for (int i = 0; i < ides.size(); i++) {
			Integer id = ides.get(i);
			if (i != limite)
				valor.append(id + ",");
			else
				valor.append(id);
		}

		return valor.toString();
	}

	public static <T> ArrayList<T> sigletonList(Collection<T> collection) {
		Collection<T> sigleton = new HashSet<T>(collection);
		return new ArrayList<T>(sigleton);
	}

	public static String formatData(Date data) {
		if (data == null) {
			return "";
		}
		SimpleDateFormat sdate = new SimpleDateFormat("dd/MM/yyyy");
		return sdate.format(data);
	}

	public static String getStringAlfanumAleatoria(final int tam) {
		String stringAleatoria = "";

		final long milis = new java.util.GregorianCalendar().getTimeInMillis();
		final Random r = new Random(milis);

		int i = 0;
		while (i < tam) {
			final char c = (char) r.nextInt(255);
			if (((c >= '0') && (c <= '9')) || ((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z'))) {
				stringAleatoria += c;
				i++;
			}
		}
		return stringAleatoria;
	}

	/**
	 * Valida CNPJ do usuÃ¡rio.
	 * 
	 * @param cnpj
	 *            String valor com 14 dÃ­gitos
	 */
	public static Boolean validaCNPJ(String cnpj) {

		if (cnpj == null || cnpj.length() != 14) {
			return false;
		}

		try {
			Long.parseLong(cnpj);
		} catch (NumberFormatException e) { // CNPJ nÃ£o possui somente nÃºmeros
			return false;
		}

		int soma = 0;
		String cnpj_calc = cnpj.substring(0, 12);

		char chr_cnpj[] = cnpj.toCharArray();

		for (int i = 0; i < 4; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (6 - (i + 1));

		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9)
				soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));

		int dig = 11 - soma % 11;
		cnpj_calc = (new StringBuilder(String.valueOf(cnpj_calc))).append(dig != 10 && dig != 11 ? Integer.toString(dig) : "0").toString();
		soma = 0;

		for (int i = 0; i < 5; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (7 - (i + 1));

		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9)
				soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));

		dig = 11 - soma % 11;
		cnpj_calc = (new StringBuilder(String.valueOf(cnpj_calc))).append(dig != 10 && dig != 11 ? Integer.toString(dig) : "0").toString();

		if (!cnpj.equals(cnpj_calc) || cnpj.equals("00000000000000")) {
			// JsfUtil.addErrorMessage("CNPJ InvÃ¡lido!");
			return false;
		}

		return true;
	}

	/**
	 * Remove acentos de uma String
	 * 
	 * @param String
	 *            string
	 */

	public static String removeAcentos(String string) {
		return Normalizer.normalize(string.trim(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	/**
	 * Add wildcards
	 * 
	 * @param boolean left
	 * @param boolean rigth
	 */

	public static String addWildcard(String string, boolean addLeft, boolean addRight) {
		String retorno = string;
		if (addLeft) {
			retorno = WILDCARD + retorno;
		}
		if (addRight) {
			retorno = retorno + WILDCARD;
		}

		return retorno;
	}

	public static String translate(String texto) {
		String traducao = texto;
		String regex = LETRAS_COM_ACENTUACAO;
		String replacement = LETRAS_SEM_ACENTUACAO;

		if (texto != null && regex != null && replacement != null && regex.length() == replacement.length()) {
			regex.charAt(0);

			for (int i = 0; i < regex.length(); i++) {
				traducao = traducao.replace(regex.charAt(i), replacement.charAt(i));
			}
		}
		return traducao;
	}

	
	public static Criterion pesquisaPorNomeExato(String nomeParametroPesquisa, String campoBanco) {
		return Restrictions.sqlRestriction(getSqlTranslateNonLike(campoBanco, nomeParametroPesquisa));
	}

	public static Criterion pesquisaComTranslate(String campoBanco, String nomeParametroPesquisa) {
		return Restrictions.sqlRestriction(getSqlTranslate(campoBanco, nomeParametroPesquisa));
	}

	private static String getSqlTranslate(String campoBanco, String nomeParametroPesquisa) {
		String nomeModificado = WILDCARD + removeAcentos(nomeParametroPesquisa.trim().toUpperCase()) + WILDCARD;
		return String.format("TRANSLATE(UPPER(%s), '%s', '%s') LIKE '%s'", campoBanco, LETRAS_COM_ACENTUACAO, LETRAS_SEM_ACENTUACAO, nomeModificado);
	}

	private static String getSqlTranslateNonLike(String campoBanco, String nomeParametroPesquisa) {
		String nomeModificado = removeAcentos(nomeParametroPesquisa.trim().toUpperCase());
		return String.format("TRANSLATE(UPPER(%s), '%s', '%s') = '%s'", campoBanco, LETRAS_COM_ACENTUACAO, LETRAS_SEM_ACENTUACAO, nomeModificado);
	}

	public static String pesquisaPorNomeEspecifico(String nomeParametroPesquisa, String campoBanco) {
		StringBuilder hql = new StringBuilder();
		hql.append(" AND " + campoBanco + " like '" + nomeParametroPesquisa.trim() + "'");
		return hql.toString();
	}


	/**
	 * Recebe uma lista de erros em formato String que foram causados pela BusinessException e os formata como lista de html.
	 * 
	 * @param be
	 * @return
	 */
	public static String formatBusinessMessage(BusinessException be) {
		StringBuilder builder;

		if (be.getErroList() == null) {
			builder = new StringBuilder(be.getMessage());
		} else {
			// TODO: A pedido do Ricardo padronizar a mensagem para todas as ocasiões independente da quantidade de regras de negócio
			builder = new StringBuilder("<ul>");

			for (String erro : be.getErroList())
				builder.append("<li> " + erro).append("</li>");
			builder.append("</ul>");
		}

		return builder.toString();
	}

	
	/**
	 * Recebe uma lista de erros em formato String que foram causados pela RequiredException e os formata como lista de html.
	 * 
	 * @param re
	 * @return
	 */
	public static String formatRequiredMessage(RequiredException re) {
		StringBuilder builder;

		if (re.getErroList() == null) {
			builder = new StringBuilder(MensagemUtil.obterMensagem(re.getMessage()));
		} else {
			builder = new StringBuilder(MensagemUtil.obterMensagem("general.required.fields") + "<br /><ul>");

			for (String erro : re.getErroList()) {
				builder.append("<li>- " + MensagemUtil.obterMensagem(erro)).append("</li>");
			}
			builder.append("</ul>");
		}
		return builder.toString();
	}


	/**
	 * Efetua o lookup do EJB especificado.
	 * 
	 * @param ejb
	 * @return
	 */
	public static Object lookupEJB(String ejb) {
		try {
			InitialContext ctx = new InitialContext();
			return ctx.lookup(ejb);
		} catch (Exception e) {
			return null;
		}
	}

	public static String substituiNullPorVazio(String string) {
		if (isNullOrEmpty(string)) {
			return "";
		} else {
			return string;
		}
	}

	public static Character booleanToCharacter(Boolean b) {
		if (!isNullOrEmpty(b) && b) {
			return new Character('S');
		} else if (!isNullOrEmpty(b) && !b) {
			return new Character('N');
		}
		return null;
	}

	public static Boolean characterToBoolean(Character c) {
		if (Character.valueOf('S').equals(c) || Character.valueOf('s').equals(c)) {
			return Boolean.TRUE;
		}

		if (Character.valueOf('N').equals(c) || Character.valueOf('n').equals(c)) {
			return Boolean.FALSE;
		}

		return null;
	}

	/**
	 * 
	 * @param nomeArquivo
	 * @param documento
	 * @return
	 */
	public static long obterTamanhoArquivo(String nomeArquivo, byte[] documento) {
		// Criamos um nome para o arquivo
		long tamanho = 0;
		File file = new File(nomeArquivo);
		BufferedOutputStream bos;
		try {
			// Criamos o arquivo
			bos = new BufferedOutputStream(new FileOutputStream(file));
			// Gravamos os bytes lÃ¡
			bos.write(documento);
			// Fechamos o stream.
			bos.close();
			tamanho = file.length() / 1024;
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}

		return tamanho;
	}

	/**
	 * 
	 * @param nomeArquivo
	 * @param documento
	 * @return
	 */
	private static File criarArquivo(String nomeArquivo, byte[] documento) {
		// Criamos um nome para o arquivo
		File file = new File(nomeArquivo);
		BufferedOutputStream bos;
		try {
			// Criamos o arquivo
			bos = new BufferedOutputStream(new FileOutputStream(file));
			// Gravamos os bytes lÃ¡
			bos.write(documento);
			// Fechamos o stream.
			bos.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}

		return file;
	}

	/**
	 * 
	 * @param nomeArquivo
	 * @param conteudo
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws FileNotFoundException
	 */
	public static String geraHash(String nomeArquivo, byte[] conteudo) throws NoSuchAlgorithmException, FileNotFoundException {
		File file = criarArquivo(nomeArquivo, conteudo);

		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		InputStream is = new FileInputStream(file);
		byte[] buffer = new byte[8192];
		int read = 0;
		String output = null;
		try {
			while ((read = is.read(buffer)) > 0) {
				digest.update(buffer, 0, read);
			}
			byte[] md5sum = digest.digest();
			BigInteger bigInt = new BigInteger(1, md5sum);
			output = bigInt.toString(16);
		} catch (IOException e) {
		} finally {
			try {
				is.close();
			} catch (IOException e) {
			}
		}

		return output;

	}

	public static boolean verificaExtensaoArquivo(String extensaoDocumento) {
		String extensao = "doc|docx|rtf|pdf";
		Pattern p = Pattern.compile(extensao);
		Matcher m = p.matcher(extensaoDocumento);
		return m.find();
	}

	public static boolean verificaExtensaoArquivo(String extensaoDocumento, String extensoesPermitidas) {
		String extensao = extensoesPermitidas;
		Pattern p = Pattern.compile(extensao);
		Matcher m = p.matcher(extensaoDocumento);
		return m.find();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void ordenacaoDescendenteList(List lista) {
		Collections.sort(lista);
		Collections.reverse(lista);
	}

	
	
	
	
}