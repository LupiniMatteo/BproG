
public class Gateway {
	Object labelBefore;
	Object labelBefore1;
	Object labelAfter;
	Object labelAfter1;
	String label;
	String poolDiRiferimento;
	//id del gateway per riferimento
	int id;
	
//----------------------------------------Costruttore------------------------------------------------
	public Gateway(String label, int id) {
		this.label = label;
	}

//------------------------------------Getters and Setters---------------------------------------------
	public Object getBefore() {
		return labelBefore;
	}

	public Object getAfter() {
		return labelAfter;
	}

	public Object getAfter1() {
		return labelAfter1;
	}

	public String getLabel() {
		return label;
	}
	
	public String getPoolRiferimento() {
		return poolDiRiferimento;
	}
	
	public int getId() {
		return id;
	}
	
//-----------------------------------------------------------------------------------------------------
	
	//aggiunge un riferimento all'elemento prima
	public void addBefore(String before) {
		this.labelBefore = before;
	}
	
	public void addBeforeMerge(String label1, String label2) {
		this.labelBefore = label1;
		this.labelBefore1 = label2;
	}
	
	//aggiunge due riferimenti agli elementi successivi
	public void addAfter(String after, String after1) {
		this.labelAfter = after;
		this.labelAfter1 = after1;
	}
	
	public void addPoolRiferimento(String labelPoolRiferimento) {
		this.poolDiRiferimento = labelPoolRiferimento;
	}
}
