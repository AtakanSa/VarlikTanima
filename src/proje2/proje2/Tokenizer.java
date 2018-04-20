package proje2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Tokenizer {
	static Document doc, doc2;
	static StringTokenizer st;
	static StringTokenizer st2;
	static StringTokenizer st3;
	static StringTokenizer st4;
	static String[] cumleArray;
	static String[] yazilacakCumle;
	
	static Arrays arrays = new Arrays();

	static ArrayList<String> dosya1 = new ArrayList<>();
	static ArrayList<String> dosya2 = new ArrayList<>();
	static ArrayList<String> isimler = new ArrayList<>();
	static ArrayList<String> keywords = new ArrayList<>();
	static ArrayList<String> para = new ArrayList<>();
	static ArrayList<String> tarih = new ArrayList<>();
	static ArrayList<String> ulke = new ArrayList<>();
	static ArrayList<String> dil = new ArrayList<>();
	static ArrayList<String> sehir = new ArrayList<>();
	static ArrayList<String> kisaltmalar = new ArrayList<>();
	static String kelime = new String();
	static String sonrakiKelime;
	static String sonrakiKelime2;
	static String sonrakiKelime3;
	static File file;
	static FileWriter fileWriter;
	static BufferedWriter bWriter;

	public static void main(String[] args) throws IOException {

		file = new File("dosya.txt");
		if (!file.exists()) {
			file.createNewFile();
		}

		fileWriter = new FileWriter(file, false);
		bWriter = new BufferedWriter(fileWriter);

		String a = "Başak takımyıldızı bölgesinde bulunan tuhaf asimetrik gökada .\n"
				+ "Birçok katalogda sarmal gökada olarak sınıflandırılan  5713 , pek çok sarmal gökadadan oldukça farklıdır .\n"
				+ "Corina Casanova , İsviçre Federal Şansölyesidir .\n"
				+ "Casanova , İsviçre Federal Yüksek Mahkemesi eski Başkanı , Nay Giusep'in pratiğinde bir avukat olarak çalıştı .\n"
				+ "Corina Casanova bir federal parlementerdir .\n"
				+ "Casanova , Hristiyan Demokrat Halk Partisi üyesi üyedidir ve altı dilde konuşmaktadır : Romanş dili , Almanca , Fransızca , İtalyanca , İngilizce ve İspanyolca .\n"
				+ "	\n";

		String c = "Corina Casanova a Ahmet Sari b c d";

		String d = "M.K.A    S.A.F  Atakan";

		while (cumleBul()) {
			System.out.println("");
		}

		System.out.print("İsimler = ");
		listeYazdir(isimler);

		System.out.println("");
		System.out.print("Ülkeler = ");
		listeYazdir(ulke);
		System.out.println("");
		System.out.print("diller = ");
		listeYazdir(dil);
		System.out.println("");
		System.out.print("Paralar = ");
		listeYazdir(para);
		System.out.println("");
		System.out.print("Şehirler = ");
		listeYazdir(sehir);
		System.out.println("");
		System.out.print("Kısaltmalar = ");
		listeYazdir(kisaltmalar);
		System.out.println("");
		System.out.print("Özel Kurumlar = ");
	    listeYazdir(keywords);
		System.out.println("");
		System.out.print("Tarihler = ");
		listeYazdir(tarih);

		bWriter.close();
		
		doğrulukHesapla();

	}

	private static void varlikBul(String[] cumle) throws IOException {

		for (int i = 0; i < cumle.length; i++) {

			if (Character.isUpperCase(cumle[i].charAt(0))) {
				if (cumle[i].contains("'")) {
					String isim = cumle[i].substring(0, cumle[i].indexOf("'"));
					KisiAdiTanimla(cumle, i);

					UlkeTanimla(cumle, i);
					
					sehirTanimla(cumle, i);

					kisaltmaTanimla(cumle, i);
					
				    KeywordBul(cumle,i);

					tarihTanimla(cumle, i);

				} else {
					KisiAdiTanimla(cumle, i);
					UlkeTanimla(cumle, i);
					sehirTanimla(cumle, i);
					kisaltmaTanimla(cumle, i);
					KeywordBul(cumle,i);
					tarihTanimla(cumle, i);

				}

			}

			else if (Character.isDigit(cumle[i].charAt(0))) {
				
					tarihTanimla(cumle, i);
				
				paraTanimla(cumle, i);
			} else {
				paraTanimla(cumle, i);
			}

		}

	}

	private static void cumleArrayCevir(String cumle) throws IOException {
		st = new StringTokenizer(cumle);
		cumleArray = new String[st.countTokens()];
		yazilacakCumle = new String[cumleArray.length];
		for (int i = 0; i < cumleArray.length; i++) {
			cumleArray[i] = st.nextToken();
		}
		varlikBul(cumleArray);
		cumleYaz(yazilacakCumle);
		normalCumleYaz(cumleArray);

	}

	private static void normalCumleYaz(String[] cumle) throws IOException {
		bWriter.write("        ");
		for (int i = 0; i < cumle.length; i++) {

			bWriter.write(cumle[i] + " ");
		}

	}

	private static void cumleYaz(String[] cumle) throws IOException {
		for (int i = 0; i < cumle.length; i++) {
			if (cumle[i] != null) {
				bWriter.write(cumle[i]);
			} else {
				bWriter.write("0 ");
			}
		}
	}

	private static Boolean cumleBul() throws IOException {
		for (int i = 1; i < arrays.course.length(); i++) {
			if ((arrays.course.charAt(i) == '.') && !Character.isUpperCase(arrays.course.charAt(i - 1))) {
				String a = arrays.course.substring(0, i+1);

				arrays.course = arrays.course.substring(i + 1, arrays.course.length());
				bWriter.write("\n");

				cumleArrayCevir(a);
				return true;
			}
		}
		return false;
	}
	
	private static void doğrulukHesapla() throws IOException {
		FileReader fileReader = new FileReader("proje2");
		String line;

		BufferedReader br = new BufferedReader(fileReader);

		while ((line = br.readLine()) != null) {

		    dosya1.add(line);

		}
		fileReader = new FileReader("dosya.txt");
		String line2;

		BufferedReader br2 = new BufferedReader(fileReader);

		while ((line2 = br2.readLine()) != null) {

		    dosya2.add(line2);

		}
		
		br.close();
		
		String orjinalMetin = "";
		String cıktıMetin = "";
		for(int i =0;i<dosya1.size();i++) {
			orjinalMetin +=dosya1.get(i);
		}
		for(int i =0;i<dosya2.size();i++) {
			cıktıMetin +=dosya2.get(i);
		}
		
		StringTokenizer st8 = new StringTokenizer(orjinalMetin);
		StringTokenizer st9 = new StringTokenizer(cıktıMetin);
		
		int dogru = 0;
		int yanlis = 0;
		String token1;
		String token2;
		while(st9.hasMoreTokens() && st8.hasMoreTokens()) {
			token1 = st8.nextToken();
			token2 = st9.nextToken();
			if(token1.equals(token2)) {
				dogru++;
				System.out.println("Doğru : " + dogru);
				System.out.println("Yanlış : " + yanlis);
			}
			else {
				yanlis++;
				System.out.println("Doğru : " + dogru);
				System.out.println("Yanlış : " + yanlis);
			}
			
		}
		int oran = dogru / (dogru + yanlis);
		System.out.println("Eşleşen Kelime Sayısı = "+ dogru +"\n" + "Yanlış Kelime Sayısı = " + yanlis);
		System.out.println("Doğruluk Oranı Yüzde : " + oran);
		
	}

	
	  private static String KeywordBul(String[] cumle, int i) { 
		  for (int x = 0; x < arrays.keywordler.length; x++) 
		  {
	  if(cumle.length>i+3) {
		  
	  
	  if ((cumle[i+3].contains(arrays.keywordler[x])) && Character.isUpperCase(cumle[i+1].charAt(0))&& Character.isUpperCase(cumle[i+2].charAt(0)))
	  {
		  
	  keywords.add(cumle[i]); 
	  keywords.add(cumle[i+1]);
	  keywords.add(cumle[i+2]); 
	  keywords.add(cumle[i+3]); 
	  if (yazilacakCumle[i] == null) {
			yazilacakCumle[i] = "B-ORGANIZATION";
		}
		if (yazilacakCumle[i+1] == null) {
			yazilacakCumle[i + 1] = "I-ORGANIZATION ";
		}
		if (yazilacakCumle[i+2] == null) {
			yazilacakCumle[i + 2] = "I-ORGANIZATION ";
		}
		if (yazilacakCumle[i+3] == null) {
			yazilacakCumle[i + 3] = "I-ORGANIZATION ";
		}
	  return "Kurum ";
	  
	  } }
	  else if(cumle.length>i+2) {
	  if (cumle[i+2] != null && cumle[i+2].contains(arrays.keywordler[x]) && Character.isUpperCase(cumle[i+1].charAt(0)))
	  { 
		  keywords.add(cumle[i]);
	  keywords.add(cumle[i+1]); 
	  keywords.add(cumle[i+2]); 
	  if (yazilacakCumle[i] == null) {
			yazilacakCumle[i] = "B-ORGANIZATION ";
		}
		if (yazilacakCumle[i+1] == null) {
			yazilacakCumle[i + 1] = "I-ORGANIZATION ";
		}
		if (yazilacakCumle[i+2] == null) {
			yazilacakCumle[i + 2] = "I-ORGANIZATION ";
		}
	  return "Kurum "; 
	  }
	  }
	  else if(cumle.length>i+1) {
	  if (cumle[i+1] != null && cumle[i+1].contains(arrays.keywordler[x]) &&
	  !keywords.contains(cumle[i]) && !keywords.contains(cumle[i+1]))
	 {
	  keywords.add(cumle[i]); 
	  keywords.add(cumle[i+1]); 
	  if (yazilacakCumle[i] == null) {
			yazilacakCumle[i] = "B-ORGANIZATION ";
		}
		if (yazilacakCumle[i+1] == null) {
			yazilacakCumle[i + 1] = "I-ORGANIZATION ";
		}
		
	  return "Kurum ";
	 }
	  }
	  
	  } 
		  return "0 "; }
	

	private static void listeYazdir(ArrayList<String> token) {

		for (int i = 0; i < token.size(); i++) {

			System.out.print(token.get(i) + ",");
		}

	}

	private static String UlkeTanimla(String[] cumle, int a) {
		for (int i = 0; i < arrays.ulkeler.length; i++) {
			if (arrays.ulkeler[i].equals(cumle[a]) && !ulke.contains(cumle[a])) {
				ulke.add(cumle[a]);
				if (yazilacakCumle[a] == null) {
					yazilacakCumle[a] = "B-LOCATION ";
				}
				return "Location";
			}

		}
		return "0 ";
	}

	private static String tarihTanimla(String[] cumle, int i) {
		for (int x = 0; x < arrays.tarihKeywords.length; x++) {
			
			if (Character.isDigit(cumle[i].charAt(0)) && cumle[i + 1].contains(arrays.tarihKeywords[x])
					 && Character.isDigit(cumle[i + 2].charAt(0))) {

				System.out.println(1);
				tarih.add(cumle[i]);
				tarih.add(cumle[i + 1]);
				tarih.add(cumle[i + 2]);
				if (yazilacakCumle[i] == null) {
					yazilacakCumle[i] = "B-DATE ";
				}
				if (yazilacakCumle[i+1] == null) {
					yazilacakCumle[i + 1] = "I-DATE ";
				}
				if (yazilacakCumle[i+2] == null) {
					yazilacakCumle[i + 2] = "I-DATE ";
				}
				return "Date ";
			}
			else if (cumle[i].contains(arrays.tarihKeywords[x]) 
					&& Character.isDigit(cumle[i + 1].charAt(0))) {
				System.out.println(2);
				tarih.add(cumle[i]);
				tarih.add(cumle[i + 1]);
				if (yazilacakCumle[i] == null) {
					yazilacakCumle[i] = "B-DATE ";
				}
				if (yazilacakCumle[i + 1] == null) {
					yazilacakCumle[i + 1] = "I-DATE";
				}
				return "Date ";
			} 
			else if (Character.isDigit(cumle[i].charAt(0)) && cumle[i + 1].contains(arrays.tarihKeywords[x]))
					 {
				System.out.println(3);
				tarih.add(cumle[i]);
				tarih.add(cumle[i + 1]);
				if (yazilacakCumle[i] == null) {
					yazilacakCumle[i] = "B-DATE ";
				}
				if (yazilacakCumle[i + 1] == null) {
					yazilacakCumle[i + 1] = "I-DATE ";
				}
				return "Date ";
			}
			else if (Character.isDigit(cumle[i].charAt(0)) && cumle[i].contains("/")) {
				tarih.add(cumle[i]);
				if (yazilacakCumle[i] == null) {
					yazilacakCumle[i] = "B-DATE ";
				}
				return "Date ";
			}
			else if (Character.isDigit(cumle[i].charAt(0)) && cumle[i].contains(".")) {
				tarih.add(cumle[i]);
				if (yazilacakCumle[i] == null) {
					yazilacakCumle[i] = "B-DATE ";
				}
				return "Date ";
			}

		}
		return "0 ";
	}

	private static String paraTanimla(String[] cumle, int a) {
		for (int i = 0; i < arrays.paralar.length; i++) {
			if (cumle[a].contains(arrays.paralar[i]) && Character.isDigit(cumle[a + 1].charAt(0))) {
				String eklenecekPara = cumle[a] + " " + cumle[a + 1];
				para.add(eklenecekPara);
				if (yazilacakCumle[a] == null) {
					yazilacakCumle[a] = "B-MISC ";
				}
				if (yazilacakCumle[a + 1] == null) {
					yazilacakCumle[a + 1] = "I-MISC ";
				}
				return "Para ";
			} else if (Character.isDigit(cumle[a].charAt(0)) && cumle[a + 1].contains(arrays.paralar[i])) {
				String eklenecekPara = cumle[a] + " " + cumle[a + 1];
				para.add(eklenecekPara);
				if (yazilacakCumle[a] == null) {
					yazilacakCumle[a] = "B-MISC ";
				}
				if (yazilacakCumle[a + 1] == null) {
					yazilacakCumle[a + 1] = "I-MISC ";
				}
				return "Para ";
			}

		}
		return "0 ";
	}

	private static String kisaltmaTanimla(String[] cumle, int i) {
		if (cumle[i].charAt(1) == '.') {
			kisaltmalar.add(cumle[i]);
			if (yazilacakCumle[i] == null) {
				yazilacakCumle[i] = "Kısaltma ";
			}
			return "B-MISC ";
		}

		return "0 ";
	}

	private static String sehirTanimla(String[] cumle, int i) {
		try {
			doc = Jsoup.connect(
					"http://www.tdk.gov.tr/index.php?option=com_bts&view=bts&kategori1=verilst&ayn1=bas&kelime1="
							+ cumle[i])
					.get();
		} catch (IOException e) {

			e.printStackTrace();
		}

		Elements links = doc.select("div.main_body p.thomicb");

		if (links.toString().contains("illerinden") && !links.toString().contains("dillerinden")) {
			sehir.add(cumle[i]);
			if (yazilacakCumle[i] == null) {
				yazilacakCumle[i] = "B-LOCATION ";
			}
			return "B-LOCATION ";
		}

		return "0 ";
	}

	private static String YabanciKisiAdiTanimla(String[] cumle, int i) throws IOException {
		try {
			doc2 = Jsoup.connect("http://www.behindthename.com/name/" + cumle[i]).get();
		} catch (IOException e) {

			e.printStackTrace();
		}

		if ((doc2) != null) {

			Elements links2 = doc2.select("body");

			if (links2.toString().contains("Given Name")) {
				isimler.add(cumle[i]);
				if (yazilacakCumle[i] == null) {
					yazilacakCumle[i] = "B-PERSON ";
				}
				if (Character.isUpperCase(cumle[i + 1].charAt(0))) {

					isimler.add(cumle[i + 1]);
					if (yazilacakCumle[i + 1] == null) {
						yazilacakCumle[i + 1] = "I-PERSON ";
					}
					return "Person ";

				}

				
			}

		}
		return "0 ";
	}

	private static String KisiAdiTanimla(String[] cumle, int i) throws IOException {
		doc = Jsoup
				.connect("http://www.tdk.gov.tr/index.php?option=com_bts&view=bts&kategori1=verilst&ayn1=bas&kelime1="
						+ cumle[i])
				.get();

		Elements links = doc.select("div.main_body span.comics");

		if (links.toString().contains("Kişi Adları Sözlüğü") && !isimler.contains(cumle[i])) {
			isimler.add(cumle[i]);
			if (yazilacakCumle[i] == null) {
				yazilacakCumle[i] = "B-PERSON ";
			}

			if (cumle[i + 1] != null) {
				if (Character.isUpperCase(cumle[i + 1].charAt(0)) && !isimler.contains(cumle[i + 1])) {

					/// coğrafi yada kurum ismi değilse kuralı uygulanacak
					// if(KisiAdiTanimla(st.nextToken()) && UlkeTanimla(st.nextToken())) {
					if (yazilacakCumle[i + 1] == null) {
						yazilacakCumle[i + 1] = "I-PERSON ";
					}

					return "Person ";

					// }
				}
			}

			return "Person ";

		} else if (!cumle[i].contains("ı") && !cumle[i].contains("ş") && !cumle[i].contains("ö")
				&& !cumle[i].contains("Ş") && !cumle[i].contains("İ") && !cumle[i].contains("ü")
				&& !cumle[i].contains("Ö") && !cumle[i].contains("Ü") && !cumle[i].contains("Ç")
				&& !cumle[i].contains("ç")) {
			YabanciKisiAdiTanimla(cumle, i);

		} else {

			return "0 ";
		}

		return "0 ";

	}

}
