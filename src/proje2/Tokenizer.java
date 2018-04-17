package proje2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Tokenizer {
	static Document doc,doc2;
	static StringTokenizer st;
	static StringTokenizer st2;
	static StringTokenizer st3;
	static StringTokenizer st4;
	static String[] keywordler = {"Hastane","Mahkeme","Meclis","Oda","Parlemento","Şansölye","Parti"};
	static String[] paralar = {"lira","dolar","euro","yen","cent","sterlin","dinar","ruble","dirhem","kronu","$","£"};
	static String[] ulkeler = {"Afganistan","Almanya","Amerika Birleşik Devletleri","Andorra","Angola","Antigua ve Barbuda","Arjantin",
			"Arnavutluk","Avustralya","Avusturya","Azerbaycan","Bahamalar","Bahreyn","Belçika","Bhutan","Bolivya","Botsvana","Bosna-Hersek","Brezilya","Bulgaristan","Cezayir"
			,"Çin","Danimarka","Kongo","Dominika","Ekvador","Endonezya","Ermenistan","Estonya","Etiyopya","Fas","Filipinler","Finlandiya","Fransa","Gana"
			,"Afrika","Kore","Sudan","Hırvatistan","Hindistan","Hollanda","Irak","İran","İrlanda","İspanya","İsrail","İsveç",
			"İsviçre","İtalya","İzlanda","Jamaika","Japonya","Kamboçya","Kamerun","Kanada","Katar","Kazakistan","Kıbrıs","Kırgızistan","Kolombiya","Kosova"
			,"Kuveyt","Letonya","Lübnan","Litvanya","Macaristan","Makedonya","Malezya","Malta","Meksika","Mısır","Moğolistan","Mozambik","Nepal","Nijerya"
			,"Norveç","Özbekistan","Pakistan","Panama","Polonya","Portekiz","Romanya","Rusya","Senegal","Sırbistan","Singapur",
			"Slovenya","Somali","Sudan","Suriye","Şili","Tacikistan","Tayland","Tunus","Türkiye","Türkmenistan","Ukrayna","Uruguay",
			"Vatikan","Vietnam","Yemen","Yunanistan"};
	static String[] diller = {"Almanca","Arapça","Arnavutça","Azerice","Boşnakça","Bulgarca","Çekçe","Çince","Endonezyaca","Ermenice"
			,"Estonca","Farsça","Filipince","Felemenkçe","Fransızca","Göktürkçe","Hırvatça","İbranice","İngilizce","İrlandaca","İspanyolca"
			,"İsveççe","İtalyanca","İzlandaca","Japonca","Kazakça","Kırgızca","Korece","Latince","Lehçe","Macarca","Mandarin","Moğolca"
			,"Norveççe","Osmanlıca","Özbekçe","Portekizce","Rusça","Sırpça","Slovakça","Süryanice","Türkçe","Yunanca"};
	static String b = "Ahmet Atakan , Cumhuriyet Halk Partisi asd Fatma Demirkollugiller ile 5 lira Türkiye R.T.E. cumhurbaşkanı Recep Tayyip Erdoğan'ın saat 2 de toplantısı var.Ankara Türkiye'nin başkentidir.Melek gibi bir kız."
			+ "Ahmet okula giderken 3 adet yumurtasını kırdı. Türkiye M.K.A tarafından kurulmuştur . Marketlerde 5 liralık değil, 5 dolarlık alış-veriş yapmanız önerilir. İzmir , Ankara arası benzinli araç ile 5 saat sürüyor.";
	
	
	static ArrayList<String> isimler = new ArrayList<>();
	static ArrayList<String> keywords = new ArrayList<>();
	static ArrayList<String> para = new ArrayList<>();
	static ArrayList<String> ulke = new ArrayList<>();
	static ArrayList<String> dil = new ArrayList<>();
	static ArrayList<String> sehir = new ArrayList<>();
	static ArrayList<String> kisaltmalar = new ArrayList<>();
	static String kelime= new String();
	static String sonrakiKelime;
	static String sonrakiKelime2;
	static String sonrakiKelime3;
	static  File file;
	static FileWriter fileWriter;
	static BufferedWriter bWriter;
	
	
	
	
public static void main(String[] args) throws IOException {
	
	file = new File("dosya.txt");
     if (!file.exists()) {
         file.createNewFile();
     }

     fileWriter = new FileWriter(file, false);
     bWriter = new BufferedWriter(fileWriter);

     
	
	
	String a = "Başak takımyıldızı bölgesinde bulunan tuhaf asimetrik gökada .\n" + 
			"Birçok katalogda sarmal gökada olarak sınıflandırılan  5713 , pek çok sarmal gökadadan oldukça farklıdır .\n" + 
			"Corina Casanova , İsviçre Federal Şansölyesidir .\n" + 
			"Casanova , İsviçre Federal Yüksek Mahkemesi eski Başkanı , Nay Giusep'in pratiğinde bir avukat olarak çalıştı .\n" + 
			"Corina Casanova bir federal parlementerdir .\n" + 
			"Casanova , Hristiyan Demokrat Halk Partisi üyesi üyedidir ve altı dilde konuşmaktadır : Romanş dili , Almanca , Fransızca , İtalyanca , İngilizce ve İspanyolca .\n" + 
			"	\n" ;
	
	
	String c = "Corina Casanova a Ahmet Sari b c d";
	
	String d = "M.K.A    S.A.F  Atakan";
	
	
	  
	  while(cumleBul()) {
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
	bWriter.close();
    
}

private static void varlikBul(String cumle) throws IOException{
	
	 st = new StringTokenizer(cumle);
	  st2 = new StringTokenizer(cumle);
	  st3 = new StringTokenizer(cumle);
	  st4 = new StringTokenizer(cumle);
	  
	 sonrakiKelime = st2.nextToken();
	  sonrakiKelime2 = st3.nextToken();
	  sonrakiKelime2 = st3.nextToken();
	  sonrakiKelime3 = st4.nextToken();
	  sonrakiKelime3 = st4.nextToken();
	  sonrakiKelime3 = st4.nextToken();
	  
	  
	while (st4.hasMoreTokens()) {
		
    	
   	 sonrakiKelime2 = st3.nextToken();
   	 sonrakiKelime3 = st4.nextToken();
   	 sonrakiKelime = st2.nextToken();
   	 kelime = st.nextToken();
   	 
	
	 
	
	  if(Character.isUpperCase(kelime.charAt(0))) {
      	if(kelime.contains("'")) {
      		String isim = kelime.substring(0, kelime.indexOf("'"));
      		if(!KisiAdiTanimla(isim).equals("0 ")){
      		     bWriter.write(KisiAdiTanimla(isim));
      		}
      		else if(!UlkeTanimla(isim).equals("0 ")) {
      			bWriter.write(UlkeTanimla(isim));
      		}
      		else if(!sehirTanimla(isim).equals("0 ")) {
      			bWriter.write(sehirTanimla(isim));
      		}
      		else if(!kisaltmaTanimla(isim).equals("0 ")) {
      			bWriter.write(kisaltmaTanimla(isim));
      		}
      		else if(!KeywordBul(isim).equals("0 ")) {
      			bWriter.write(KeywordBul(isim));
      		}
      		else {
      			bWriter.write("0 ");
      		}
      	
      	
      		
      	}
      	else {
      		if(!KisiAdiTanimla(kelime).equals("0 ")){
     		     bWriter.write(KisiAdiTanimla(kelime));
     		}
     		else if(!UlkeTanimla(kelime).equals("0 ")) {
     			bWriter.write(UlkeTanimla(kelime));
     		}
     		else if(!sehirTanimla(kelime).equals("0 ")) {
     			bWriter.write(sehirTanimla(kelime));
     		}
     		else if(!kisaltmaTanimla(kelime).equals("0 ")) {
     			bWriter.write(kisaltmaTanimla(kelime));
     		}
     		else if(!KeywordBul(kelime).equals("0 ")) {
     			bWriter.write(KeywordBul(kelime));
     		}
     		else {
     			bWriter.write("0 ");
     		}
      	}
      	
      }
      else {
      	paraTanimla(kelime);
      }
     
	
	
	
	}
	

	
}

private static Boolean cumleBul() throws IOException {
	for(int i =1;i<b.length();i++) {
		if((b.charAt(i)=='.') && !Character.isUpperCase(b.charAt(i-1))) {
			String a = b.substring(0,i);

			b = b.substring(i+1,b.length());
			bWriter.write("\n");
			bWriter.write(a+"------------");
			varlikBul(a);
			return true;
		}
	}
	return false;
}


private static String KeywordBul(String token) {
	for(int i = 0;i<keywordler.length;i++) {

		if((sonrakiKelime3 != null && sonrakiKelime3.contains(keywordler[i])) && !keywords.contains(token) && !keywords.contains(sonrakiKelime)&& !keywords.contains(sonrakiKelime2)&& !keywords.contains(sonrakiKelime3)) {
			keywords.add(token);
			keywords.add(sonrakiKelime);
			keywords.add(sonrakiKelime2);
			keywords.add(sonrakiKelime3);
			return "Kurum ";
			}
		else if(sonrakiKelime2 != null && sonrakiKelime2.contains(keywordler[i]) && !keywords.contains(token) && !keywords.contains(sonrakiKelime)&& !keywords.contains(sonrakiKelime2)){
			keywords.add(token);
			keywords.add(sonrakiKelime);
			keywords.add(sonrakiKelime2);
			return "Kurum ";
		}
		else if(sonrakiKelime != null && sonrakiKelime.contains(keywordler[i]) && !keywords.contains(token) && !keywords.contains(sonrakiKelime)){
			keywords.add(token);
			keywords.add(sonrakiKelime);
			return "Kurum ";
			
		}
		
	}return "0 ";
}


private static void listeYazdir(ArrayList<String> token) {
	
	
	for(int i = 0;i<token.size();i++) {
		
		System.out.print(token.get(i)+",");
	}
	
}

private static String UlkeTanimla(String token) {
	for(int i = 0;i<ulkeler.length;i++) {
		if(ulkeler[i].equals(token) && !ulke.contains(token)) {
			ulke.add(token);
			return "Location";
		}
			
	}
	return "0 ";
}

private static String paraTanimla(String token) {
	for(int i = 0;i<paralar.length;i++) {
		if(token.contains(paralar[i]) && Character.isDigit(sonrakiKelime.charAt(0)) ) {
			String eklenecekPara = token +" "+ sonrakiKelime;
			para.add(eklenecekPara);
			return "Para ";
		}
		else if(Character.isDigit(token.charAt(0)) && sonrakiKelime.contains(paralar[i])) {
			String eklenecekPara = token +" "+ sonrakiKelime;
			para.add(eklenecekPara);
			return "Para ";
		}
			
	}
	return "0 ";
}




private static String kisaltmaTanimla(String token) {
	if(token.charAt(1) == '.') {
		kisaltmalar.add(token);
		return "Kısaltma ";
	}
	
	return "0 ";
}




private static String sehirTanimla(String token) {
	try {
		doc = Jsoup.connect("http://www.tdk.gov.tr/index.php?option=com_bts&view=bts&kategori1=verilst&ayn1=bas&kelime1="+token).get();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
 	 
 	 Elements links = doc.select("div.main_body p.thomicb");
 	 
	if(links.toString().contains("illerinden") && !links.toString().contains("dillerinden")) {
		sehir.add(token);
		return "Location ";
	}
	
	
	
	return "0 ";
}





private static String YabanciKisiAdiTanimla(String token) throws IOException{
	try {
		doc2 = Jsoup.connect("http://www.behindthename.com/name/"+token).get();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
		if((doc2) != null) {
			
		
 	 Elements links2 = doc2.select("body");

 	// System.out.println(links2.toString());
 	  
 		  
 	 
 		if(links2.toString().contains("Given Name")) {
			isimler.add(token);
			
  			if(Character.isUpperCase(sonrakiKelime.charAt(0))&& !isimler.contains(sonrakiKelime)){
  					
  					
  	 				 ///coğrafi yada kurum ismi değilse kuralı uygulanacak
  	 				//if(KisiAdiTanimla(st.nextToken()) && UlkeTanimla(st.nextToken())) {
  	 					isimler.add(sonrakiKelime);
  	 					return "Person ";
  	 					
  	 				//}
  	 			
  			}	 
			return "0 ";
			 
		 }
 	 
 	
		 
		}
	return "0 ";
}





private static String KisiAdiTanimla(String token) throws IOException {
	doc = Jsoup.connect("http://www.tdk.gov.tr/index.php?option=com_bts&view=bts&kategori1=verilst&ayn1=bas&kelime1="+token).get();
  	 
  	 Elements links = doc.select("div.main_body span.comics");
  	 
  	 
  	
  		 if(links.toString().contains("Kişi Adları Sözlüğü") && !isimler.contains(token)) {
  			isimler.add(token);
  			if(st.hasMoreTokens()) {
  				if(Character.isUpperCase(sonrakiKelime.charAt(0))  && !isimler.contains(sonrakiKelime)){
  					
  	 				 ///coğrafi yada kurum ismi değilse kuralı uygulanacak
  	 				//if(KisiAdiTanimla(st.nextToken()) && UlkeTanimla(st.nextToken())) {
  	 					isimler.add(sonrakiKelime);
  	 					return "Person ";
  	 					
  	 				//}
  	 			 }
  			}
  			
  		 }
  		 else if(!token.contains("ı")&&!token.contains("ş")&&!token.contains("ö")&&
  				!token.contains("Ş")&&!token.contains("İ")&&!token.contains("ü")&&
  				!token.contains("Ö")&&!token.contains("Ü")&&!token.contains("Ç")&&!token.contains("ç")) {
  			YabanciKisiAdiTanimla(token);
  			 
  		 }
  		 else {
  			 
  			 return "0 ";
  		 }
  			 
  		 return "0 ";
  	
	}


}
