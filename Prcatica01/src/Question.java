import java.io.Serializable;


public class Question implements Serializable{
	
private static final long serialVersionUID = 1L;

private int id;
private String text;
private int Test_id;
public Question(String text, int test_id) {
	this.text = text;
	Test_id = test_id;
}

public Question(){
	
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getText() {
	return text;
}

public void setText(String text) {
	this.text = text;
}

public int getTest_id() {
	return Test_id;
}

public void setTest_id(int test_id) {
	Test_id = test_id;
}

}
