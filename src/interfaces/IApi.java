package interfaces;

import java.util.ArrayList;

public interface IApi<T> {

	public ArrayList<T> getAll();

	public ArrayList<T> getAll(String text);
}
