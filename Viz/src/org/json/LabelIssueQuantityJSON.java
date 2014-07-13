package org.json;

/**
 * Save a label of a specified repository and the quantity that this label appear into the repository;
 *
 */
public class LabelIssueQuantityJSON {
	
	private String label; //a specified label
	private int quantity; //quantity of a specified label into a specified repository
	
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	

}
