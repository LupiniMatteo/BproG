import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class TreePoolGeneration {

	
	public void TreePoolGenerationFunction(BufferedReader buff, ArrayList<Pool> listaDeiPool, String indicatorePoolInQuestione, PrintStream printLog, boolean chiusura, int idGateway) throws IOException {
		while (chiusura) {
			// stringa di appoggio per generare lo string tokenizer
			String stringAppoggio = buff.readLine();

			// creo lo string tokenizer
			StringTokenizer str = new StringTokenizer(stringAppoggio);

			// stringa per la label del pool dello start event
			String labelPool = "";
			
			// stringa per la label dello start event
			String labelStartEvent = "";
			
			//stringa per la label dell'end event
			String labelEndEvent = "";
			
			// inizializzo la variabile label del task
			String labelTask = "";
			
			// inizializzo la variabile label task di relazione riguardo alle referenze
			String labelTaskRelazione = "";
			
			// label che contiene il tipo di gateway
			String labelTipoGateway = "";
			
			// label task del secondo task nel gateway
			String labelTask2 = "";

			// estraggo la prima stringa della linea
			String confronto = str.nextToken();

			
// ---------------------------------------------------------------CASO DI START EVENT O END EVENT-----------------------------------------------------------------
		
			// controllo se siamo nella condizione di start event
			if (confronto == "The") {

				// salvo in label la label del pool dello start event
				labelPool = str.nextToken();

				// controllo che l'input sia giusto
				if (!(str.hasMoreTokens()))
					throw new InputMismatchException("Errore nella stringa di Input");

				// sovrascrivo confronto con un nuovo token
				confronto = str.nextToken();


				// situazione in cui il pool è composto da due parole e quindi concateno le
				// stringhe per fare una label completa
				while (confronto != "starts" || confronto != "ends") {
					labelPool = labelPool + " " + confronto;

					// controllo che l'input sia giusto
					if (!(str.hasMoreTokens()))
						throw new InputMismatchException("Errore nella stringa di Input");

					// riassegno nuovo valore a confronto
					confronto = str.nextToken();

				}
				

				//assegno il soggetto del pool
				indicatorePoolInQuestione = labelPool;
				
				//creo il pool
				Pool pool = new Pool(labelPool);
				
				//stampo sul log la creazione
				printLog.println("Creazione del pool " + labelPool);
				
				//aggiungo alla lista dei pool il pool appena creato
				listaDeiPool.add(pool);

				
//--------------------------------------------------------START EVENT----------------------------------------------------------------------------------

				
				// se entro qui significa che tutto è andato bene e il sistema ha incontrato la
				// parola chiave stars
				if (confronto == "starts") {

					// controllo che l'input sia giusto
					if (!(str.hasMoreTokens()))
						throw new InputMismatchException("Errore nella stringa di Input");

					// assegno il nuovo valore a confronto
					confronto = str.nextToken();

					// tre possibili situazioni di start event

// -------------------------------------------------------------START EVENT WITH TASK---------------------------------------------------------------------------
					// start event with task
					if (confronto == "with") {

						// controllo che l'input sia giusto
						if (!(str.hasMoreTokens()))
							throw new InputMismatchException("Errore nella stringa di Input");

						// scorro with per arrivare alla prima parola del task
						confronto = str.nextToken();

						// vado a generare la stringa label task
						labelTask = confronto;

						// controllo che l'input sia giusto
						if (!(str.hasMoreTokens()))
							throw new InputMismatchException("Errore nella stringa di Input");

						// scorro la prima parola del label
						confronto = str.nextToken();

						// creo la label del task scorrendo la riga fino alla parola chiave labeled
						while (!(confronto == "labeled")) {

							// creo la label task
							labelTask = labelTask + " " + confronto;

							// aggiorno la variabile confronto
							confronto = str.nextToken();
						}

						// USCITO DA QUI SO CHE CONFRONTO SI TROVA SU LABELED

						// controllo che l'input sia giusto
						if (!(str.hasMoreTokens()))
							throw new InputMismatchException("Errore nella stringa di Input");

						// sposto il puntatore sulla parola successiva che mi aspetto sia la prima parola del label dello start event
						confronto = str.nextToken();

						//inizio con il generare la label dello start event con la prima parola incontrata
						labelStartEvent = confronto;

						// quindi ora creo la label dello start event scorrendo fino alla fine della riga 
						//CONSUMO TUTTA LA STRINGA
						while (str.hasMoreTokens()) {

							// controllo che l'input sia giusto
							if (!(str.hasMoreTokens()))
								throw new InputMismatchException("Errore nella stringa di Input");

							// aggiorno la variabile confronto
							confronto = str.nextToken();

							// l'aggiungo alla label
							labelStartEvent = labelStartEvent + " " + confronto;
						}

						// inizio la popolazione del pool
						//creo il task
						Task task = new Task(labelTask);
						
						//creo lo start event 
						StartEvent startEvent = new StartEvent(labelStartEvent);
						
						
						
						//scorro tytta la lista dei pool e vado ad aggiungere al pool designato l'elemento start event e end event
						for(int contatore = 0; contatore < listaDeiPool.size(); contatore++) {
							if(listaDeiPool.get(contatore).getLabel() == indicatorePoolInQuestione) {
								listaDeiPool.get(contatore).addTask(task);
								listaDeiPool.get(contatore).addStartEvent(startEvent);
							}
						}
						
						//stampo il log di aggiunta dello start event al pool
						printLog.println("Al pool " + indicatorePoolInQuestione + "viene aggiunto lo start event "
								+ labelStartEvent);

						//stampo il log di aggiunta del task al pool
						printLog.println(
								"Al pool " + indicatorePoolInQuestione + "viene aggiunto il task " + labelTask);
					}

// ----------------------------------------------------------START EVENT MESSAGE RECEIVED------------------------------------------------------------------------

					// message received start event
					if (confronto == "when") {

						// controllo che l'input sia giusto
						if (!(str.hasMoreTokens()))
							throw new InputMismatchException("Errore nella stringa di Input");

						// aggiorno la variabile confronto
						confronto = str.nextToken();

						if (confronto == "received") {

							// controllo che l'input sia giusto
							if (!(str.hasMoreTokens()))
								throw new InputMismatchException("Errore nella stringa di Input");

							// aggiorno la variabile confronto con la parola che in teoria è la label del messaggio
							confronto = str.nextToken();

							// creo il messaggio
							Message messaggioDiAttivazione = new Message(confronto);

							// controllo che l'input sia giusto
							if (!(str.hasMoreTokens()))
								throw new InputMismatchException("Errore nella stringa di Input");

							// aggiorno la variabile confronto
							confronto = str.nextToken();

							if (confronto == "from") {

								// controllo che l'input sia giusto
								if (!(str.hasMoreTokens()))
									throw new InputMismatchException("Errore nella stringa di Input");

								// aggiorno la variabile confronto
								confronto = str.nextToken();

								// prendo il nome del pool
								// metto la prima parola nella label
								String labelBlackBoxPool = "";
								
								labelBlackBoxPool = confronto;

								// quindi ora creo la label del black box pool
								while (str.hasMoreTokens()) {

									// controllo che l'input sia giusto
									if (!(str.hasMoreTokens()))
										throw new InputMismatchException("Errore nella stringa di Input");

									// aggiorno la variabile confronto
									confronto = str.nextToken();

									// l'aggiungo alla label
									labelBlackBoxPool = labelBlackBoxPool + " " + confronto;

								}

								// creo il pool black box
								Pool blackBoxPool = new Pool(labelBlackBoxPool);

								// cambio il boolean del pool mettendolo a black box
								blackBoxPool.blackBox();

								// aggiungo il blackboxpool alla lista dei pool
								listaDeiPool.add(blackBoxPool);
								
								//aggiungo al log l'aggiunta alla lista dei pool di un black box pool
								printLog.println(
										"Aggiunta alla lista dei pool di un pool black box " + labelBlackBoxPool);

							}

						}

					}

// ----------------------------------------------------------------START EVENT LABELED-----------------------------------------------------------------------
					// simple start event
					if (confronto == "labeled") {
						// uscito da qui so che confronto si trova su labeled

						// controllo che l'input sia giusto
						if (!(str.hasMoreTokens()))
							throw new InputMismatchException("Errore nella stringa di Input");

						// sposto il puntatore sulla parola successiva
						confronto = str.nextToken();

						
						labelStartEvent = confronto;

						// quindi ora creo la label dello start event
						while (str.hasMoreTokens()) {

							// controllo che l'input sia giusto
							if (!(str.hasMoreTokens()))
								throw new InputMismatchException("Errore nella stringa di Input");

							// aggiorno la variabile confronto
							confronto = str.nextToken();

							// l'aggiungo alla label
							labelStartEvent = labelStartEvent + " " + confronto;
						}

						//creo l'oggetto start event con la sua label
						StartEvent startEvent = new StartEvent(labelStartEvent);
						
						//aggiungo lo start event al pool
						pool.addStartEvent(startEvent);
						
						//aggiungo ai log l'aggiunta dello start event al pool
						printLog.println("Al pool " + indicatorePoolInQuestione + "è stato aggiunto uno start event "
								+ labelStartEvent);
					}
				
				}
// -------------------------------------------------------------END EVENT-------------------------------------------------------------------------------
				
				if(confronto == "ends") {
					
					// controllo che l'input sia giusto
					if (!(str.hasMoreTokens()))
						throw new InputMismatchException("Errore nella stringa di Input");

					// sposto il puntatore sulla parola successiva
					confronto = str.nextToken();
					
					// simple end event
					if (confronto == "with") {
						// uscito da qui so che confronto si trova su with

						// controllo che l'input sia giusto
						if (!(str.hasMoreTokens()))
							throw new InputMismatchException("Errore nella stringa di Input");

						// sposto il puntatore sulla parola successiva
						confronto = str.nextToken();

						
						labelEndEvent = confronto;

						// quindi ora creo la label dell end event
						while (str.hasMoreTokens()) {

							// controllo che l'input sia giusto
							if (!(str.hasMoreTokens()))
								throw new InputMismatchException("Errore nella stringa di Input");

							// aggiorno la variabile confronto
							confronto = str.nextToken();

							// l'aggiungo alla label
							labelEndEvent = labelEndEvent + " " + confronto;
						}

						//creo l'oggetto start event con la sua label
						EndEvent endEvent = new EndEvent(labelEndEvent);
						
						for(int k = 0; k < listaDeiPool.size(); k++) {
							if(listaDeiPool.get(k).getLabel() == indicatorePoolInQuestione) {
								
								//inidicatore per prendere l'ultimo elemento della task list
								int size =listaDeiPool.get(k).getTaskList().size();
								
								//aggiungo all'end event la referenza alla label dell'ultimo elemento dei task presente nel sistema
								endEvent.addBefore(listaDeiPool.get(k).getTaskList().get(size).getLabel());
								
								printLog.print("Aggiunta la referenza all'end event :" + labelEndEvent + "con la referenza before al task " + listaDeiPool.get(k).getTaskList().get(size).getLabel());
								
								//aggiungo l'end event al pool
								listaDeiPool.get(k).addEndEvent(endEvent);
							}
						}
						
						
						//aggiungo ai log l'aggiunta dell end event al pool
						printLog.println("Al pool " + indicatorePoolInQuestione + "è stato aggiunto un end event "
								+ labelStartEvent);
						
						
						//cambio il boolean a chiusura 
						chiusura = false;
					}
				}
			}
// ------------------------------------------------------START EVENT, END EVENT CHIUSO-----------------------------------------------------------------

// ------------------------------------------------------------TASK, AND-SPLIT, OR-SPLIT---------------------------------------------------------------

			// siamo nella situaizone in cui abbiamo un task, un and split o un or split
			if (confronto == "After") {

				// controllo che l'input sia giusto
				if (!(str.hasMoreTokens()))
					throw new InputMismatchException("Errore nella stringa di Input");

				// scorro after per arrivare alla prima parola del task di referenza
				confronto = str.nextToken();

				// vado a generare la stringa label task di referenza
				labelTaskRelazione = confronto;

				// controllo che l'input sia giusto
				if (!(str.hasMoreTokens()))
					throw new InputMismatchException("Errore nella stringa di Input");

				// scorro la prima parola del task
				confronto = str.nextToken();

				// creo la label del task di referenza scorrendo la riga fino alla parola chiave is o are
				while (!(confronto == "is" || confronto == "are")) {

					// creo la label task di relazione
					labelTaskRelazione = labelTaskRelazione + " " + confronto;

					// aggiorno la variabile confronto
					confronto = str.nextToken();
				}

// -----------------------------------------------------------------TASK------------------------------------------------------------------------------

				// io so che nella parola confronto è contenuto o is o are e quindi inizio nel
				// distinguere i due casi
				if (confronto == "is") {

					// controllo che l'input sia giusto
					if (!(str.hasMoreTokens()))
						throw new InputMismatchException("Errore nella stringa di Input");

					// scorro is per arrivare alla prima parola del primo task
					confronto = str.nextToken();

					if (confronto == "performed") {

						// controllo che l'input sia giusto
						if (!(str.hasMoreTokens()))
							throw new InputMismatchException("Errore nella stringa di Input");

						// scorro performed per arrivare alla prima parola del task
						confronto = str.nextToken();

						// vado a generare la stringa label task
						labelTask = confronto;

						// controllo che l'input sia giusto
						if (!(str.hasMoreTokens()))
							throw new InputMismatchException("Errore nella stringa di Input");

						// scorro la prima parola del task
						confronto = str.nextToken();

						// creo la label del task scorrendo la riga fino a consumare la stringa
						while (str.hasMoreTokens()) {

							// creo la label task
							labelTask = labelTask + " " + confronto;

							// aggiorno la variabile confronto
							confronto = str.nextToken();
						}
					}

					//creo il task
					Task task = new Task(labelTask);
					
					//aggiungo al task la relazione con la label precedente
					task.addBefore(labelTaskRelazione);

					// scorro la lista dei pool sfruttando l'indicatore di pool per aggiungere al pool in questione il task creato
					for (int k = 0; k < listaDeiPool.size(); k++) {
						
						//confronto la label di ogni singolo pool nella lista con l'indicatore del pool soggetto
						if (listaDeiPool.get(k).getLabel() == indicatorePoolInQuestione) {

							//aggiungo il task al pool giusto
							listaDeiPool.get(k).addTask(task);

							//aggiungo al log l'informazione di aggiunta un task al pool
							printLog.println("Aggiunto il task " + labelTask + "al pool " + indicatorePoolInQuestione);
						}
					}

					//devo cercare nella lista dei pool inoltre il pool corretto che contiene il task precedente al task immesso
					//per aggiornare il suo puntatore al nuovo task creato
					for (int i = 0; i < listaDeiPool.size(); i++) {
						
						//scorro la lista dei pool
						Pool controlloPool = listaDeiPool.get(i);
						
						//una volta trovato il pool soggetto
						if (controlloPool.getLabel() == indicatorePoolInQuestione) {
							
							// andare a prendere il task relazione e andare ad aggiungere L'AFTER
							listaDeiPool.get(i).changeAfterReferenceTaskByLabel(labelTaskRelazione, labelTask);

							//stampare l'aggiunta della relazione after
							printLog.println("Aggiunta la relazione after al task " + labelTaskRelazione
									+ "con il task " + labelTask);
						}
					}

				}

// ----------------------------------------------------------------------------------------------------------------------------------------------------------

				if (confronto == "are") {

					// incremento id del gateway quindi anche dal primo giro, il primo id sarà 1.
					idGateway++;

					// controllo che l'input sia giusto
					if (!(str.hasMoreTokens()))
						throw new InputMismatchException("Errore nella stringa di Input");

					// scorro are per arrivare alla prima parola del task
					confronto = str.nextToken();

					if (confronto == "performed") {

						// controllo che l'input sia giusto
						if (!(str.hasMoreTokens()))
							throw new InputMismatchException("Errore nella stringa di Input");

						// scorro performed per arrivare alla prima parola del task
						confronto = str.nextToken();

						// vado a generare la stringa label task del primo task nel gateway
						labelTask = confronto;

						// controllo che l'input sia giusto
						if (!(str.hasMoreTokens()))
							throw new InputMismatchException("Errore nella stringa di Input");

						// scorro la prima parola del primo task nel gateway
						confronto = str.nextToken();

						// creo la label del task scorrendo la riga fino alle parole chiave and o or
						while (!(confronto == "and" || confronto == "or")) {

							// creo la label task
							labelTask = labelTask + " " + confronto;

							// aggiorno la variabile confronto
							confronto = str.nextToken();
						}

// ----------------------------------------------------------------------AND SPLIT---------------------------------------------------------------------

						// se entro qui il tipo di gateway è un and
						if (confronto == "and") {

							labelTipoGateway = "and";

							// controllo che l'input sia giusto
							if (!(str.hasMoreTokens()))
								throw new InputMismatchException("Errore nella stringa di Input");

							// scorro and per arrivare alla prima parola del secondo task
							confronto = str.nextToken();

							// vado a generare la stringa label task del secondo task
							labelTask2 = confronto;

							// controllo che l'input sia giusto
							if (!(str.hasMoreTokens()))
								throw new InputMismatchException("Errore nella stringa di Input");

							// scorro la prima parola del secondo task
							confronto = str.nextToken();

							// creo la label del task scorrendo la riga fino a consumare la stringa
							while (str.hasMoreTokens()) {

								// creo la label task
								labelTask2 = labelTask2 + " " + confronto;

								// aggiorno la variabile confronto
								confronto = str.nextToken();
							}

							// creare il gateway
							Gateway gateway = new Gateway(labelTipoGateway, idGateway);

							// aggiungo after e before al gateway
							gateway.addAfter(labelTask, labelTask2);

							gateway.addBefore(labelTaskRelazione);

							// creare i due task
							Task task1 = new Task(labelTask);

							Task task2 = new Task(labelTask2);

							// aggiungere le referenze al gateway da parte dei task
							task1.addBeforeGateway(idGateway);

							task2.addBeforeGateway(idGateway);

							// vado a mettere tutto nel pool di appartenenza
							for (int k = 0; k < listaDeiPool.size(); k++) {
								if (listaDeiPool.get(k).getLabel() == indicatorePoolInQuestione) {
									// ho trovato il pool ora vado a aggiungere la referenza del task relazione
									// l'after

									// ATTENZIONE GESTIRE LA REFERENZA AL SUCCESSIVO DI UN TASK A UN GATEWAY
									// NON GIUSTA LA RIGA DI CODICE
									listaDeiPool.get(k).changeAfterReferenceTaskByLabel(labelTaskRelazione, labelTask2);

									// aggiungo tutti gli elementi al pool
									listaDeiPool.get(k).addGateway(gateway);
									listaDeiPool.get(k).addTask(task1);
									listaDeiPool.get(k).addTask(task2);

									printLog.println("E' stato aggiunto il gateway " + labelTipoGateway + "con task "
											+ labelTask + labelTask2 + "del pool " + indicatorePoolInQuestione);
								}
							}
						}

// ---------------------------------------------------------------------------------------------------------------------------------------------------------

// ------------------------------------------------------------OR SPLIT------------------------------------------------------------------------------------

						// se entro qui il tipo di gateway è un or
						if (confronto == "or") {

							labelTipoGateway = "or";

							// controllo che l'input sia giusto
							if (!(str.hasMoreTokens()))
								throw new InputMismatchException("Errore nella stringa di Input");

							// scorro with per arrivare alla prima parola del task
							confronto = str.nextToken();

							// vado a generare la stringa label task
							labelTask2 = confronto;

							// controllo che l'input sia giusto
							if (!(str.hasMoreTokens()))
								throw new InputMismatchException("Errore nella stringa di Input");

							// scorro with per arrivare alla prima parola del task
							confronto = str.nextToken();

							// creo la label del task scorrendo la riga fino alla parola chiave labeled
							while (str.hasMoreTokens()) {

								// creo la label task
								labelTask2 = labelTask2 + " " + confronto;

								// aggiorno la variabile confronto
								confronto = str.nextToken();
							}

							// creare il gateway
							Gateway gateway = new Gateway(labelTipoGateway, idGateway);

							// aggiungo after e before al gateway
							gateway.addAfter(labelTask, labelTask2);

							gateway.addBefore(labelTaskRelazione);

							// creare i due task
							Task task1 = new Task(labelTask);

							Task task2 = new Task(labelTask2);

							// aggiungere le referenze al gateway da parte dei task
							task1.addBeforeGateway(idGateway);

							task2.addBeforeGateway(idGateway);

							// vado a mettere tutto nel pool di appartenenza
							for (int k = 0; k < listaDeiPool.size(); k++) {
								if (listaDeiPool.get(k).getLabel() == indicatorePoolInQuestione) {
									// ho trovato il pool ora vado a aggiungere la referenza del task relazione
									// l'after

									// ATTENZIONE GESTIRE LA REFERENZA AL SUCCESSIVO DI UN TASK A UN GATEWAY
									// NON GIUSTA LA RIGA DI CODICE
									listaDeiPool.get(k).changeAfterReferenceTaskByLabel(labelTaskRelazione, labelTask2);

									// aggiungo tutti gli elementi al pool
									listaDeiPool.get(k).addGateway(gateway);
									listaDeiPool.get(k).addTask(task1);
									listaDeiPool.get(k).addTask(task2);

									printLog.println("E' stato aggiunto il gateway " + labelTipoGateway + "con task "
											+ labelTask + labelTask2 + "del pool " + indicatorePoolInQuestione);
								}
							}
						}
					}
				}
			}

// --------------------------------------------------------------TASK, AND SPLIT, OR SPLIT CHIUSO-----------------------------------------------------------

// ---------------------------------------------------------------MERGE AND SPLIT, OR SPLIT--------------------------------------------------------------------------
			if(confronto == "Then") {
				//aggiorno la variabile id gateway
				idGateway++;
				
				// controllo che l'input sia giusto
				if (!(str.hasMoreTokens()))
					throw new InputMismatchException("Errore nella stringa di Input");

				// scorro then per arrivare alla prima parola del task
				confronto = str.nextToken();

				// vado a generare la stringa label task del primo task del gateway
				labelTask = confronto;

				// controllo che l'input sia giusto
				if (!(str.hasMoreTokens()))
					throw new InputMismatchException("Errore nella stringa di Input");

				// scorro la prima parola del task
				confronto = str.nextToken();

				// creo la label del task scorrendo la riga fino alla parola chiave and o or
				while (!(confronto == "and" || confronto == "or")) {

					// creo la label task
					labelTask = labelTask + " " + confronto;

					// aggiorno la variabile confronto
					confronto = str.nextToken();
				}
				
//--------------------------------------------------------------------MERGE AND SPLIT-------------------------------------------------------------------
				
				//sono arrivato alla parola and o or e quindi ora devo fare una distinzione
				// se entro qui il tipo di gateway è un and
				if (confronto == "and") {

					labelTipoGateway = "and";

					// controllo che l'input sia giusto
					if (!(str.hasMoreTokens()))
						throw new InputMismatchException("Errore nella stringa di Input");

					// scorro and per arrivare alla prima parola del secondo task
					confronto = str.nextToken();

					// vado a generare la stringa label task del secondo task
					labelTask2 = confronto;

					// controllo che l'input sia giusto
					if (!(str.hasMoreTokens()))
						throw new InputMismatchException("Errore nella stringa di Input");

					// scorro la prima parola del secondo task
					confronto = str.nextToken();

					// creo la label del task scorrendo la riga fino ad arrivare alla parola chiave merge
					while (!(confronto == "merge")) {

						// creo la label task
						labelTask2 = labelTask2 + " " + confronto;

						// aggiorno la variabile confronto
						confronto = str.nextToken();
					}
					
					//creo il gateway
					Gateway gateway = new Gateway(labelTipoGateway, idGateway);
					
					//metto gli ingressi
					gateway.addBeforeMerge(labelTask, labelTask2);
					
					
					
					
					//scorro tutti i task del pool fino a trovare i task giusti ed andare ad inserire la referenza al gateway
					for(int i = 0; i < listaDeiPool.size(); i++) {
						//cerco il pool in questione
						if(listaDeiPool.get(i).getLabel() == indicatorePoolInQuestione) {
							//del pool scorro tutti i task
							for(int contatore = 0; contatore < listaDeiPool.get(i).getTaskList().size(); contatore++) {
								//aggiungo al task la referenza after al gateway
								if(listaDeiPool.get(i).getTaskList().get(contatore).getLabel() == labelTask) {
									//ERRORE IL REFRIMENTO NON PUO' ESSERE FATTO A UNA STRINGA MA A UN ID DEL GATEWAY
									listaDeiPool.get(i).getTaskList().get(contatore).addAfter(labelTipoGateway);
								}
								
								if(listaDeiPool.get(i).getTaskList().get(contatore).getLabel() == labelTask2) {
									//ERRORE IL REFRIMENTO NON PUO' ESSERE FATTO A UNA STRINGA MA A UN ID DEL GATEWAY
									listaDeiPool.get(i).getTaskList().get(contatore).addAfter(labelTipoGateway);
								}
							}
						}
					}
					
					//inserisco il gateway nel pool
					// vado a mettere tutto nel pool di appartenenza
					for (int k = 0; k < listaDeiPool.size(); k++) {
						if (listaDeiPool.get(k).getLabel() == indicatorePoolInQuestione) {

							// aggiungo tutti gli elementi al pool
							listaDeiPool.get(k).addGateway(gateway);
							

							printLog.println("E' stato aggiunto il merge " + labelTipoGateway + "con task "
									+ labelTask + labelTask2 + "del pool " + indicatorePoolInQuestione);
						}
					}
				}
			
//---------------------------------------------------------------------MERGE OR SPLIT------------------------------------------------------------------
				
				// se entro qui il tipo di gateway è un and
				if (confronto == "or") {

					labelTipoGateway = "or";

					// controllo che l'input sia giusto
					if (!(str.hasMoreTokens()))
						throw new InputMismatchException("Errore nella stringa di Input");

					// scorro and per arrivare alla prima parola del secondo task
					confronto = str.nextToken();

					// vado a generare la stringa label task del secondo task
					labelTask2 = confronto;

					// controllo che l'input sia giusto
					if (!(str.hasMoreTokens()))
						throw new InputMismatchException("Errore nella stringa di Input");

					// scorro la prima parola del secondo task
					confronto = str.nextToken();

					// creo la label del task scorrendo la riga fino ad arrivare alla parola chiave merge
					while (!(confronto == "merge")) {

						// creo la label task
						labelTask2 = labelTask2 + " " + confronto;

						// aggiorno la variabile confronto
						confronto = str.nextToken();
					}	
					
					//creo il gateway
					Gateway gateway = new Gateway(labelTipoGateway, idGateway);
					
					//metto gli ingressi
					gateway.addBeforeMerge(labelTask, labelTask2);
					
					
					
					
					//scorro tutti i task del pool fino a trovare i task giusti ed andare ad inserire la referenza al gateway
					for(int i = 0; i < listaDeiPool.size(); i++) {
						//cerco il pool in questione
						if(listaDeiPool.get(i).getLabel() == indicatorePoolInQuestione) {
							//del pool scorro tutti i task
							for(int contatore = 0; contatore < listaDeiPool.get(i).getTaskList().size(); contatore++) {
								//aggiungo al task la referenza after al gateway
								if(listaDeiPool.get(i).getTaskList().get(contatore).getLabel() == labelTask) {
									//ERRORE IL REFRIMENTO NON PUO' ESSERE FATTO A UNA STRINGA MA A UN ID DEL GATEWAY
									listaDeiPool.get(i).getTaskList().get(contatore).addAfter(labelTipoGateway);
								}
								
								if(listaDeiPool.get(i).getTaskList().get(contatore).getLabel() == labelTask2) {
									//ERRORE IL REFRIMENTO NON PUO' ESSERE FATTO A UNA STRINGA MA A UN ID DEL GATEWAY
									listaDeiPool.get(i).getTaskList().get(contatore).addAfter(labelTipoGateway);
								}
							}
						}
					}
					
					//inserisco il gateway nel pool
					// vado a mettere tutto nel pool di appartenenza
					for (int k = 0; k < listaDeiPool.size(); k++) {
						if (listaDeiPool.get(k).getLabel() == indicatorePoolInQuestione) {

							// aggiungo tutti gli elementi al pool
							listaDeiPool.get(k).addGateway(gateway);
							

							printLog.println("E' stato aggiunto il merge " + labelTipoGateway + "con task "
									+ labelTask + labelTask2 + "del pool " + indicatorePoolInQuestione);
						}
					}
				}
				
			}
		}
	}
}
