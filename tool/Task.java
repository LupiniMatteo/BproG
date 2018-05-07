
public class Task {
	Object labelBefore;
	Object labelAfter;
	String label;
	String poolDiRiferimento;
	
	
//--------------------------------------------------Costruttore------------------------------------------------------------
	public Task(String label) {
		this.label = label;
	}

//-----------------------------------------------Getters and Setters------------------------------------------------------
	public Object getBefore() {
		return labelBefore;
	}

	public Object getAfter() {
		return labelAfter;
	}

	public String getLabel() {
		return label;
	}
	
	public String getPoolDiRiferimento() {
		return poolDiRiferimento;
	}
	
//--------------------------------------------------------------------------------------------------------------------------
	//aggiunge la referenza all'oggetto before
	public void addBefore(String before) {
		this.labelBefore = before;
	}
	
	//aggiunge la referenza all'oggetto after
	public void addAfter(String after) {
		this.labelAfter = after;
	}
	
	public void addPoolRiferimento(String labelPoolRiferimento) {
		this.poolDiRiferimento = labelPoolRiferimento;
	}
	
	public void addBeforeGateway(int idGateway) {
		this.labelBefore = idGateway;
	}
}
