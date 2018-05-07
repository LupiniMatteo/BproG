
public class EndEvent {
	//oggetto prima dell'end event
	Object before;
	//label dell'end event
	String label;
	
	String poolDiRiferimento;
	
//--------------------------------------------Costruttore----------------------------------------------------------
	public EndEvent(String label) {
		this.label = label;
	}	
//--------------------------------------------Getters and Setters--------------------------------------------------
	public Object getBefore() {
		return before;
	}

	public String getLabel() {
		return label;
	}
	
	public String getPoolDiRiferimento() {
		return poolDiRiferimento;
	}

//-----------------------------------------------------------------------------------------------------------------
	
	//Aggiunge l'elemento prima dell'end event
	public void addBefore(Object before) {
		this.before = before;
	}
}
