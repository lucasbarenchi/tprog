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
import com.main.logic.dts.CategoryDT;
import com.main.logic.interfaces.ICategoryController;

public class ListCategoriesJInternalFrame extends JInternalFrame {

    public ListCategoriesJInternalFrame() {
        setTitle("Listar categorías");
        setBounds(100, 100, 450, 300);

        ICategoryController categoryController = ControllerFactory.getInstance().getCategoryController();

        List<CategoryDT> categoryDTs = categoryController.listCategories();

        JLabel lblCategorias = new JLabel("Categorías existentes:");

        DefaultListModel<String> categoriesList = new DefaultListModel<>();
        JList listCategories = new JList(categoriesList);

        JScrollPane categoriesScrollPane = new JScrollPane(listCategories);

        categoryDTs.forEach(cateogoryDT -> categoriesList.addElement(categoryDTs.indexOf(cateogoryDT) + 1 + ") " + cateogoryDT.getName()));

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(categoriesScrollPane, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                                .addComponent(lblCategorias))
                        .addContainerGap())
                );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblCategorias)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(categoriesScrollPane, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                        .addContainerGap())
                );
        getContentPane().setLayout(groupLayout);

    }
}
