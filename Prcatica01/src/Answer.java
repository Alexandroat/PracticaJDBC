import java.io.Serializable;


public class Answer implements Serializable{

private static final long serialVersionUID = 1L;

private String text;
private boolean Is_correct;
private int Question_id;
public Answer(String text, boolean is_correct) {
	this.text = text;
	Is_correct = is_correct;
}



public Answer(){
	
}

public String getText() {
	return text;
}

public void setText(String text) {
	this.text = text;
}

public boolean getIs_correct() {
	return Is_correct;
}

public void setIs_correct(boolean is_correct) {
	Is_correct = is_correct;
}

public int getQuestion_id() {
	return Question_id;
}

public void setQuestion_id(int question_id) {
	Question_id = question_id;
}
}
