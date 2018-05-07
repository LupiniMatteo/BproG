import java.util.ArrayList;

public class Pool {
	
	//label del Pool
	private String label;
	//oggetto start event 
	private StartEvent start;
	//oggetto end event
	private EndEvent end;
	//array che contiene tutti i task del pool
	private ArrayList<Task> taskList;
	//array che contiene i gateway
	private ArrayList<Gateway> gatewayList;
	//array che contiene la lista di tutti i messaggi
	private ArrayList<Message> messageList;
	//boolean che mi dice se il pool è un black box
	private boolean blackBox;
	
	
//----------------------------------------------Costruttore-----------------------------------------------------------------------------
	
	public Pool(String label) {
		this.label = label;
		this.blackBox = false;
	}
	
//----------------------------------------------Getters and Setters---------------------------------------------------------------------	
	
	public String getLabel() {
		return label;
	}
	
	public StartEvent getStart() {
		return start;
	}
	
	public EndEvent getEnd() {
		return end;
	}
	
	public boolean getBlackBox() {
		return blackBox;
	}
	
	public ArrayList<Task> getTaskList(){
		return this.taskList;
	}
	
//-----------------------------------------------------------------------------------------------------------------------------------
	
	//Aggiunge un task alla lista dei task
	public void addTask(Task task) {
		taskList.add(task);
	}
	
	//aggiunge un gateway alla lista dei gateway
	public void addGateway(Gateway gateway) {
		gatewayList.add(gateway);
	}
	
	//aggiunge lo start event al pool
	public void addStartEvent(StartEvent se) {
		this.start = se;
	}
	
	//aggiunge l'end event al pool
	public void addEndEvent(EndEvent ee) {
		this.end = ee;
	}
	
	public void addMessage(Message m) {
		messageList.add(m);
	}
	
	public void blackBox() {
		this.blackBox = true;
	}
	
	//metodo che prende la label del task successivo e modifica la referenza all'after del task di referenza
	public void changeAfterReferenceTaskByLabel(String labelTaskReferenza, String labelTask) {
		for(int j = 0; j < this.taskList.size(); j++) {
			if(taskList.get(j).getLabel() == labelTaskReferenza) {
				taskList.get(j).addAfter(labelTask);
			}
		}
		
	}
	
	
	
}
