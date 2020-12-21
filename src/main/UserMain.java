package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.fasterxml.jackson.databind.MapperFeature;
import com.github.weisj.darklaf.LafManager;
//import com.github.weisj.darklaf.theme.DarculaTheme;

import common.Constants;
import panels.RootPane;

public class UserMain {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					LafManager.install(new DarculaTheme());
					LafManager.install();
					new UserMain();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UserMain() {
		initialize();
	}

	private void initialize() {
		JFrame frame = new JFrame();
		frame.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setTitle("EBR-Admin");
		frame.setLocationRelativeTo(null);

		Constants.mapper.disable(MapperFeature.USE_ANNOTATIONS);

		RootPane rootPane = new RootPane();
		frame.setContentPane(rootPane);
		frame.setVisible(true);
	}
}