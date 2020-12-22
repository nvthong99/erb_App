package panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import beans.User;

public class RootPane extends JPanel {
	private static final long serialVersionUID = 1L;

	public RootPane() {
		super();
		initialize();
	}

	private void initialize() {
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);

		JTabbedPane tabbedPane = new JTabbedPane();
		this.add(tabbedPane, BorderLayout.CENTER);

		RentedBikePane rentedBikePane = new RentedBikePane();
		ParkPane parkPane = new ParkPane(rentedBikePane.getTable());
		tabbedPane.addTab("Xe đang thuê", null, rentedBikePane);
		tabbedPane.addTab("Thuê Xe", null, parkPane);

//		UserPane userPane = new UserPane(User.getDataFields());
//		userPane.updateData(UserApi.getUser());
//		tabbedPane.addTab("Số dư", null, userPane);
	}
}
