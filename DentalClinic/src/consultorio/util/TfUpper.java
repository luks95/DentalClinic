package consultorio.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TfUpper extends PlainDocument {

	private static final long serialVersionUID = 1L;
	int offset;
	String str;
	AttributeSet attr;

	public TfUpper() {
		super();

	}

	public TfUpper(int offset, String str, AttributeSet attr) {
		super();
		this.offset = offset;
		this.str = str;
		this.attr = attr;
	}

	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		super.insertString(offset, str.toUpperCase(), attr);
	}

}
