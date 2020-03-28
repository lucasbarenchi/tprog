package com.main.gui;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.main.logic.controller.ControllerFactory;
import com.main.logic.dts.UserDT;
import com.main.logic.interfaces.IUserController;

public class ListUsersJInternalFrame extends JInternalFrame {

    List<UserDT> userDTs = null;

    public ListUsersJInternalFrame() {
        setTitle("Listar usuarios");

        IUserController userController = ControllerFactory.getInstance().getUserController();

        userDTs = userController.listUsers();

        JLabel usersMsgLbl = new JLabel("Usuarios del sistema:"); 

        DefaultListModel<String> usersList = new DefaultListModel<>();

        JList usersJlist = new JList(usersList);
        JScrollPane scrollPaneListUsers = new JScrollPane(usersJlist);

        userDTs.forEach(userDT -> usersList.addElement(userDTs.indexOf(userDT) + 1 + ") "+ userDT.getNickname()));

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(scrollPaneListUsers, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                                .addComponent(usersMsgLbl))
                        .addContainerGap())
                );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(usersMsgLbl)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(scrollPaneListUsers, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                        .addContainerGap())
                );
        getContentPane().setLayout(groupLayout);		
    }
}