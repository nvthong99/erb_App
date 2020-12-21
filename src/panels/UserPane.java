package panels;

import javax.swing.JTabbedPane;

public class UserPane extends RootPane {
	private static final long serialVersionUID = 1L;
	public UserPane() {
		super();
	}
	
	@Override
	public void addTabItem(JTabbedPane jtabbedPane) {
		jtabbedPane.addTab("Thuê Xe", null, new UserBikeRental());
//		jtabbedPane.addTab("Quản lý bãi xe", null, new ParkManager());
//		jtabbedPane.addTab("Quản lý xe đang sử dụng", null, new UsedBikeManager());
	}
}
