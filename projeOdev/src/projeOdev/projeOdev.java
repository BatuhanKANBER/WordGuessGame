package projeOdev;

import java.util.*;

class projeOdev {

	public static void main(String[] args) {

		Scanner girdi = new Scanner(System.in);
		sorular kelimeSorular = new sorular();
		int soruSayisi = 1;
		int toplamPuan = 0;
		int rastgele;
		int sayac = 0;

		for (int i = 0; i < 14; i++) {

			// KELÝME UZUNLUK ALMA
			int kelimeUzunluk = kelimeSorular.sorular[i][0].length();

			// KELÝMEYÝ KARAKTER DÝZÝSÝNE ATAMA
			char[] kelime = kelimeSorular.sorular[i][0].toCharArray();

			// PUANLAMA
			int puan = 100 * kelimeUzunluk;

			// SORU SAYISI BAÞLIK
			System.out.println(
					ConsoleColors.BLUE_BOLD + "********************" + soruSayisi + ".SORU" + "********************");
			System.out.println(ConsoleColors.RESET);

			// KELÝMEYÝ GÝZLEME
			char[] gizliKelime = new char[kelimeUzunluk];
			for (int k = 0; k < kelimeUzunluk; k++) {
				gizliKelime[k] = '-';
			}
			System.out.println(Arrays.toString(gizliKelime) + "(" + kelimeSorular.sorular[i][1] + ")");
			System.out.println(ConsoleColors.BLUE_BOLD + "**********************************************");
			System.out.println(ConsoleColors.RESET);

			// HARF ALMA VE TAHMÝN ETME
			ArrayList<String> rastgeleList = new ArrayList<>();
			String harfAlma = null;
			do {

				// HATALI HARF GÝRÝÞÝ
				boolean x = false;
				String harfAl = null;
				System.out.println("HARF ALMAK ÝÇÝN->(h)//TAHMÝN ÝÇÝN->(t) YAZINIZ");
				harfAl = girdi.next();
				while (!x) {
					if (harfAl.equals("h") || harfAl.equals("t")) {
						x = true;
						break;
					} else {
						System.err.println("HATALI GÝRÝÞ!!!");
						System.out.println("HARF ALMAK ÝÇÝN->(h)//TAHMÝN ÝÇÝN->(t) YAZINIZ");
						harfAl = girdi.next();
					}
				}
				harfAlma = harfAl;

				// HARF ALMA
				if (harfAlma != null && harfAlma.equals("h")) {
					sayac++;
					puan = puan - 100;
					boolean alma = false;
					while (!alma) {
						rastgele = (int) Math.round(Math.random() * (kelimeUzunluk - 1));
						if (rastgeleList.size() == 0) {
							rastgeleList.add(String.valueOf(rastgele));
							gizliKelime[rastgele] = kelime[rastgele];
							System.out.println(Arrays.toString(gizliKelime));
							alma = true;
						} else {
							for (int k = 0; k < rastgeleList.size(); k++) {
								if (rastgeleList.contains(String.valueOf(rastgele))) {
									rastgele = (int) Math.round(Math.random() * (kelimeUzunluk - 1));
								} else {
									rastgeleList.add(String.valueOf(rastgele));
									gizliKelime[rastgele] = kelime[rastgele];
									System.out.println(Arrays.toString(gizliKelime));
									alma = true;
								}
							}
						}
					}

					// BÜTÜN HARFLER AÇILIRSA
					if (sayac == kelimeUzunluk) {
						System.out.println(ConsoleColors.BLACK_BOLD + "KELÝME: " + kelimeSorular.sorular[i][0]);
						System.out.println(ConsoleColors.RED_BOLD + "BU SORUDAN PUAN ALAMADINIZ");
						System.out.println(ConsoleColors.RESET);
						sayac = 0;
						break;
					}

					// TAHMÝN ETME
				} else if (harfAlma != null && harfAlma.equals("t")) {
					System.out.print("TAHMÝNÝNÝZÝ GÝRÝNÝZ: ");
					String tahmin = girdi.next();
					tahmin = tahmin.toUpperCase();
					if (tahmin.equals(kelimeSorular.sorular[i][0])) {
						System.out.println(ConsoleColors.GREEN_BOLD + "DOÐRU TAHMÝN YAPTINIZ PUANINIZ: " + puan);
						System.out.println(ConsoleColors.RESET);
						break;
					} else {
						System.out.println(ConsoleColors.RED_BOLD + "TAHMÝNÝNÝZ YANLIÞ BU SORUDAN PUAN ALAMADINIZ");
						System.out.println(ConsoleColors.BLACK_BOLD + "DOÐRU KELÝME: " + kelimeSorular.sorular[i][0]);
						System.out.println(ConsoleColors.RESET);
						puan = 0;
						break;
					}
				}
			} while (harfAlma.equals("h") && harfAlma != null);

			// TOPLAM PUAN HESAPLAMA
			int x = puan;
			toplamPuan = toplamPuan + x;
			soruSayisi++;
		}

		// SONUÇLAR
		System.out.println(ConsoleColors.BLUE_BOLD + "**********************************************");
		System.out.println(ConsoleColors.GREEN_BOLD + "YARIÞMAMIZ BÝTTÝ KATILDIÐINIZ TEÞEKKÜRLER...");
		System.out.println("TOPLAM PUANINIZ: " + toplamPuan);
	}

}
