import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class BProG {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// lista pool
		ArrayList<Pool> listaDeiPool = new ArrayList<Pool>();

		// indicatore del pool
		String indicatorePoolInQuestione = "";

		// id che parte da 0 per riferire tutti i gateway
		int idGateway = 0;

		// File reader
		FileReader file;

		file = new FileReader("testoStereotipato.txt");

		// buffer reader
		BufferedReader buff;

		buff = new BufferedReader(file);

		// boolean che mi indica la fine del testo
		boolean chiusura = true;

		// uscita di un file txt
		FileOutputStream log = new FileOutputStream("log.txt");

		// print stream
		PrintStream printLog = new PrintStream(log);

		//generazione dell'oggetto che andrà a popolare la lista dei pool con tutti gli elementi
		TreePoolGeneration tPG = new TreePoolGeneration();
		
		//chiamo la funzione che popola la lista dei pool
		tPG.TreePoolGenerationFunction(buff, listaDeiPool, indicatorePoolInQuestione, printLog, chiusura, idGateway);
		
		printLog.close();
		buff.close();
	}

}
