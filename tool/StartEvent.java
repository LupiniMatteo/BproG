
public class StartEvent {
	Object after;
	String label;
	String poolDiRiferimento;
	
//-----------------------------------------------Costruttore---------------------------------------------------
	public StartEvent(String label) {
		this.label = label;
	}

//--------------------------------------------Getters and Setters----------------------------------------
	public Object getAfter() {
		return after;
	}

	public String getLabel() {
		return label;
	}
	
	public String getPoolDiRiferimento() {
		return poolDiRiferimento;
	}

//-------------------------------------------------------------------------------------------------------
	
	//metodo che aggiunge il riferimento all'oggetto after
	public void addAfter(Object after) {
		this.after = after;
	}
	
}
