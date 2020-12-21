package main;

import java.awt.EventQueue;
import java.awt.desktop.SystemSleepEvent;

import javax.swing.JFrame;

import com.github.weisj.darklaf.LafManager;
//import com.github.weisj.darklaf.theme.DarculaTheme;


import api.ParkApi;

import beans.Park;

import common.Constants;
import panels.RootPane;
import panels.UserPane;

public class AdminMain {


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					LafManager.install(new DarculaTheme());
					LafManager.install();
					new AdminMain();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminMain() {
		initialize();
	}

	private void initialize() {
		JFrame frame = new JFrame();
		frame.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setTitle("EBR-Admin");
		frame.setLocationRelativeTo(null);
//		UserPane userPane = new UserPane();
		RootPane rootPane = new RootPane();
		frame.setContentPane(rootPane);
		frame.setVisible(true);
	}
}