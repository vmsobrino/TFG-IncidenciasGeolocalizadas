package edu.gimt.domain;

import java.util.regex.Pattern;

import edu.gimt.common.Constants;

public class ReplaceCharTest {

	public static void main(String[] args) {
		ReplaceCharTest rct = new ReplaceCharTest();
		rct.replace();
	}
	
	private void replace() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<sentido>VICU√&#145;A</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>VI√&#145;ASPRE</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>VENTAS DE IR√&#154;N</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>URDU√&#145;A-ORDU√&#145;A</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>TREVI√&#145;O</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>TRASLAVI√&#145;A</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>SANTUARIO DE ARANTZAZU (O√&#145;ATI)</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>SANTO√&#145;A</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>RAMAL DE EMERANDO (ME√&#145;AKA)</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>PE√&#145;ACERRADA</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>ORDU√&#145;A</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>ONSO√&#145;O</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>O√&#145;ATI</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>MI√&#145;ANO MAYOR</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>LOGRO√&#145;O</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>LOGRO√&#145;O (POR HARO)</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>LAS MU√&#145;ECAS (SOPUERTA)</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>LA SOPE√&#145;A</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>IR√&#154;N</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>IPI√&#145;ABURU</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>ERE√&#145;O (POR NABARNIZ)</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>Donostia-San Sebasti√°n</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>DONOSTIA/SAN SEBASTI√&#129;N</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>CASER√&#141;O DE ITURRIOTZ (ASTEASU)</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>BOROA (TRAVES√&#141;A AMOREBIETA)</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>BARRIO O√&#145;ATZ</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>BARRIO IBARLA (IR√&#154;N) ALTO DE ERLAITZ.</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>BARRIO DE BEGO√&#145;A (MONDRAGON)</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<sentido>ARRASATE-MONDRAG√&#147;N</sentido>" + Constants.LINE_SEPARATOR);
		buffer.append("<poblacion>Y√©cora/Iekora</poblacion>" + Constants.LINE_SEPARATOR);
		buffer.append("<poblacion>Valle de Tr√°paga-Trapagaran</poblacion>" + Constants.LINE_SEPARATOR);
		buffer.append("<poblacion>San Mill√°n/Donemiliaga</poblacion>" + Constants.LINE_SEPARATOR);
		buffer.append("<poblacion>Lagr√°n</poblacion>" + Constants.LINE_SEPARATOR);
		buffer.append("<poblacion>G√ºeÒes</poblacion>" + Constants.LINE_SEPARATOR);
		buffer.append("<poblacion>Donostia-San Sebasti√°n</poblacion>" + Constants.LINE_SEPARATOR);
		buffer.append("<poblacion>Abanto y Ci√©rvana-Abanto Zierbena</poblacion>" + Constants.LINE_SEPARATOR);
		buffer.append("<nombre>XXVII O√&#145;ATIKO DUATLOIA</nombre>" + Constants.LINE_SEPARATOR);
		buffer.append("<nombre>XVI ERE√&#145;OKO UDALA SARI NAGUSIA</nombre>" + Constants.LINE_SEPARATOR);
		buffer.append("<nombre>VII. ERE√&#145;OKO MASTER SARIA</nombre>" + Constants.LINE_SEPARATOR);
		buffer.append("<nombre>URDU√&#145;AKO MENDATEA/PUERTO DE ORDU√&#145;A</nombre>" + Constants.LINE_SEPARATOR);
		buffer.append("<nombre>ILSOGANA (LAS MU√&#145;ECAS)</nombre>" + Constants.LINE_SEPARATOR);
		buffer.append("<nombre>BIZKAIKO BEHATOKIA/BALC√&#147;N DE BIZKAIA</nombre>" + Constants.LINE_SEPARATOR);
		buffer.append("<nombre>AP-1 (BURGOS-ARMI√&#145;ON)</nombre>" + Constants.LINE_SEPARATOR);
		buffer.append("<nombre>A-625 URDU√&#145;A/ORDU√&#145;A-BILBAO</nombre>" + Constants.LINE_SEPARATOR);
		buffer.append("<nombre>A-3130 BERNEDO-URIZAHARRA/PE√&#145;ACERRADA</nombre>" + Constants.LINE_SEPARATOR);
		buffer.append("<nombre>A-3126 BERGANZO-URIZAHARRA/PE√&#145;ACERRADA</nombre>" + Constants.LINE_SEPARATOR);
		buffer.append("<nombre>A-124 BRI√&#145;AS - LOGRO√&#145;O</nombre>" + Constants.LINE_SEPARATOR);
		
		String content = buffer.toString();
		content = content.replaceAll(Pattern.quote("√≥"), "Û");
		content = content.replaceAll(Pattern.quote("√≠"), "Ì");
		content = content.replaceAll(Pattern.quote("√±"), "Ò");
		content = content.replaceAll(Pattern.quote("√©"), "È");
		content = content.replaceAll(Pattern.quote("√°"), "·");
		content = content.replaceAll(Pattern.quote("√º"), "¸");

		content = content.replaceAll(Pattern.quote("√&#129;"), "¡");
		content = content.replaceAll(Pattern.quote("√&#141;"), "Õ");
		content = content.replaceAll(Pattern.quote("√&#145;"), "—");
		content = content.replaceAll(Pattern.quote("√&#154;"), "⁄");
		content = content.replaceAll(Pattern.quote("√&#147;"), "”");

		System.out.println(content);
	}

}
