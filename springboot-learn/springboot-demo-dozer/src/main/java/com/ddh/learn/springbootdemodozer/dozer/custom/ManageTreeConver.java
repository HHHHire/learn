package com.ddh.learn.springbootdemodozer.dozer.custom;

import com.ddh.learn.springbootdemodozer.model.custom.ManageNode;
import com.ddh.learn.springbootdemodozer.model.custom.TreeNode;
import org.apache.commons.lang3.ObjectUtils;
import org.dozer.CustomConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/17 11:01
 * @desc dozer转换实体类方式，ManageNode和TreeNode双向转换
 */
public class ManageTreeConver implements CustomConverter {

    @Override
    public Object convert(Object destination, Object source, Class<?> destinationClass, Class<?> sourceClass) {
        if (!ObjectUtils.allNotNull(source)) {
            return null;
        }

        ManageNode manageNode;
        TreeNode treeNode;

        if (source instanceof TreeNode) {
            manageNode = ((ManageNode) destination);
            if (manageNode == null) {
                manageNode = new ManageNode();
            }
            treeNode = (TreeNode) source;
            convert(treeNode, manageNode);
            return manageNode;
        } else if (source instanceof ManageNode) {
            treeNode = (TreeNode) destination;
            if (treeNode == null) {
                treeNode = new TreeNode();
            }
            manageNode = (ManageNode) source;
            convert(manageNode, treeNode);
            return treeNode;
        }
        return null;
    }

    /**
     * treeNode转manageNode
     *
     * @param treeNode   treeNode对象
     * @param manageNode manageNode对象
     */
    private void convert(TreeNode treeNode, ManageNode manageNode) {
        manageNode.setFullPath(treeNode.getName());
        manageNode.setName(treeNode.getName());
        // 判断是否有子节点
        boolean empty = treeNode.getChild() != null && !treeNode.getChild().isEmpty();
        manageNode.setLeafNode(empty);
        List<ManageNode> manageNodes = new ArrayList<>();
        // 有节点
        if (empty) {
            for (TreeNode node : treeNode.getChild()) {
                ManageNode manageNode1 = new ManageNode();
                convert(node, manageNode1);
                manageNodes.add(manageNode1);
            }
            manageNode.setChildren(manageNodes);
        }
    }

    /**
     * manageNode转换成treNode类型
     *
     * @param manageNode manageNode对象
     * @param treeNode   treeNode对象
     */
    private void convert(ManageNode manageNode, TreeNode treeNode) {
        treeNode.setName(manageNode.getName());
        // 判断是否有子节点
        boolean empty = manageNode.getLeafNode();
        if (empty) {
            List<TreeNode> treeNodes = new ArrayList<>();
            for (ManageNode child : manageNode.getChildren()) {
                TreeNode treeNode1 = new TreeNode();
                convert(child, treeNode1);
                treeNodes.add(treeNode1);
            }
            treeNode.setChild(treeNodes);
        }
    }
}
