package panels;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import beans.User;
import helpers.FieldOption;

public class UserPane extends JPanel {
	private static final long serialVersionUID = 1L;

	private ArrayList<FieldOption> fields;
	private LinkedHashMap<String, JComponent> textFields;

	public UserPane(ArrayList<FieldOption> fields) {
		this.fields = fields;
		this.textFields = new LinkedHashMap<>();
		init();
	}

	private void init() {
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);

		GridBagLayout gbLayout = new GridBagLayout();
		JPanel panel = new JPanel();
		panel.setLayout(gbLayout);
		this.add(panel, BorderLayout.CENTER);
		GridBagConstraints c = new GridBagConstraints();

		for (int index = 0; index < fields.size(); index++) {
			FieldOption option = fields.get(index);

			JLabel label = new JLabel(option.getValue());
			c.insets = new Insets(10, 0, 5, 0);
			c.gridx = 0;
			c.gridy = index;
			panel.add(label, c);

			JComponent field;
			c.gridx = 1;
			c.gridy = index;

			if (option.isPassword()) {
				field = new JPasswordField(20);
				((JPasswordField) field).setEditable(option.isEditable());
			} else {
				field = new JTextField(20);
				((JTextField) field).setEditable(option.isEditable());
			}
			panel.add(field, c);
			textFields.put(option.getKey(), field);
		}
	}

	public void updateData(User data) {
		Field[] fields = data.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				Object value = field.get(data);
				JComponent component = textFields.get(field.getName());
				if (component != null) {
					if (component instanceof JTextField) {
						((JTextField) component).setText(value.toString());
					} else if (component instanceof JPasswordField) {
						((JPasswordField) component).setText(value.toString());
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
