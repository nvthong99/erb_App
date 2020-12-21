package interfaces;

import components.CRUDTable;

public class AController<T> {
	protected CRUDTable<T> table;
	protected IApi<T> api;

	public AController(CRUDTable<T> table, IApi<T> api) {
		this.table = table;
		this.api = api;
	}

}
