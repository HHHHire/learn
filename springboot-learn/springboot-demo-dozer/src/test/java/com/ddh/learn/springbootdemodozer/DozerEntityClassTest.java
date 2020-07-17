package com.ddh.learn.springbootdemodozer;

import com.ddh.learn.springbootdemodozer.dozer.custom.ManageTreeConver;
import com.ddh.learn.springbootdemodozer.model.custom.ManageNode;
import com.ddh.learn.springbootdemodozer.model.custom.TreeNode;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/17 11:29
 * @desc
 */
@SpringBootTest
public class DozerEntityClassTest {
    @Test
    public void test1() {
        TreeNode treeNode1 = TreeNode.builder().id(1L)
                .name("zhangsan")
                .child(null).build();
        List<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.add(treeNode1);
        TreeNode treeNode = TreeNode.builder().id(1L)
                .name("zhangsan")
                .child(treeNodes).build();
        ManageTreeConver manageTreeConver = new ManageTreeConver();
        ManageNode convert = (ManageNode) manageTreeConver.convert(null, treeNode, ManageNode.class, TreeNode.class);
        System.out.println(convert);
    }

    @Test
    public void test2() {
        ManageNode node1 = ManageNode.builder().name("zangsan")
                .leafNode(false)
                .fullPath("hello")
                .children(null).build();
        List<ManageNode> manageNodes = new ArrayList<>();
        manageNodes.add(node1);
        ManageNode node = ManageNode.builder().name("zangsan")
                .leafNode(true)
                .fullPath("hello")
                .children(manageNodes).build();
        ManageTreeConver manageTreeConver = new ManageTreeConver();
        TreeNode convert = (TreeNode) manageTreeConver.convert(null, node, TreeNode.class, ManageNode.class);
        System.out.println(convert);
    }
}
