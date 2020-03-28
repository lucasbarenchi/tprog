package com.main.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Objects;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.main.logic.controller.ControllerFactory;
import com.main.logic.interfaces.ICategoryController;

public class CreateCategoryJInternalFrame extends JInternalFrame {

    private JTextField txtEjDeportesVideo;

    public CreateCategoryJInternalFrame() {
        setTitle("Crear categoría");
        setBounds(100, 100, 475, 145);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel lblNombreDeLa = new JLabel("Nombre de la categoría:");
        panel.add(lblNombreDeLa);

        txtEjDeportesVideo = new JTextField();
        txtEjDeportesVideo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(txtEjDeportesVideo.getText().isEmpty()) {
                    txtEjDeportesVideo.setForeground(Color.LIGHT_GRAY);
                    txtEjDeportesVideo.setText("Ej.: Deportes, Video Juegos, etc.");				
                }
            }
        });
        txtEjDeportesVideo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(Objects.equals(txtEjDeportesVideo.getText(), "Ej.: Deportes, Video Juegos, etc.")) {
                    txtEjDeportesVideo.setText("");				
                    txtEjDeportesVideo.setForeground(Color.BLACK);
                }
            }
        });
        txtEjDeportesVideo.setForeground(Color.LIGHT_GRAY);
        txtEjDeportesVideo.setSelectedTextColor(Color.BLACK);
        txtEjDeportesVideo.setText("Ej.: Deportes, Video Juegos, etc.");
        txtEjDeportesVideo.setColumns(10);
        panel.add(txtEjDeportesVideo);

        JPanel panel_1 = new JPanel();

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ICategoryController categoryController = ControllerFactory.getInstance().getCategoryController();
                String categoryName = txtEjDeportesVideo.getText();
                if (Objects.equals(txtEjDeportesVideo.getText(), "Ej.: Deportes, Video Juegos, etc.") 
                        || Objects.equals(txtEjDeportesVideo.getText().trim(), "") ) {
                    JOptionPane.showMessageDialog(getParent(), "Por favor ingrese un nombre para la categoría");
                    return;
                }
                if (categoryController.createCategory(categoryName)) {
                    System.out.println(String.format("Categoría %s creada correctamente", categoryName));
                    JOptionPane.showMessageDialog(getParent(), "Categoría creada correctamente");

                } else {
                    System.out.println(String.format("La categoría %s ya existe!!", categoryName));
                    JOptionPane.showMessageDialog(getParent(), "La categoría ya existe!");
                }
            }
        });
        panel_1.add(btnConfirmar);
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                                .addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
                        .addContainerGap())
                );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18)
                        .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(114))
                );
        getContentPane().setLayout(groupLayout);

    }
}
