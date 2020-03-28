package com.main.gui.utils;

import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import com.main.logic.dts.CommentDT;
import com.main.logic.dts.VideoDT;

public interface TreeUtils {

    static void createNodesRecursively(DefaultMutableTreeNode top, List<CommentDT> comments) {
        DefaultMutableTreeNode fatherComment = top;
        final DefaultMutableTreeNode children = new DefaultMutableTreeNode();
        if (comments == null) {
            return;
        }

        comments.forEach(cmt -> {
            DefaultMutableTreeNode comment = new DefaultMutableTreeNode(cmt.getText());
            createNodesRecursively(comment, cmt.getResponses());
            fatherComment.add(comment); 
        });
    }

    static JTree createScrollableTree(VideoDT video) {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode(String.format("%s :: Comentarios", video.getTitle()));
        createNodesRecursively(top, video.getComments());
        JTree tree = new JTree(top);
        return tree;
    }
}
