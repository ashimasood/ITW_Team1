package src.ws;

import java.util.List;

import src.ws.Category;

public class CategoryCollection {
	List<Category> categories;

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "CategoryCollection [categories=" + categories + "]";
	}
	
}
