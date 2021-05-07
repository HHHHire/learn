package com.ddh.learn.gateway.utils;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/5/6 22:28
 * @des: 试用测试
 */
public class HashVirtualNodeTest {
    private static String[] servers = {"127.0.0.1:8501", "127.0.0.1:8503"};
    private final static int virtualNode = 5;
    private static SortedMap<Integer, String> virtualNodeMap = new TreeMap<>();

    static {
        int len = servers.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < virtualNode; j++) {
                String virtualNodeName = servers[i] + "&&" + "Node" + j;
                Integer hash = HashUtil.FNV1_32_HASH(virtualNodeName);
                virtualNodeMap.put(hash, virtualNodeName);
                System.out.println("hash=" + hash + " virtualNode=" + virtualNodeName);
            }
        }
    }

    public static String getServer(String node) {
        Integer hashCode = HashUtil.FNV1_32_HASH(node);
        // 获取大于当前节点的虚拟节点
        SortedMap<Integer, String> tailMap = virtualNodeMap.tailMap(hashCode);
        Integer key = 0;
        if (tailMap.isEmpty()) {
            key = virtualNodeMap.firstKey();
        } else {
            key = tailMap.firstKey();
        }
        String virtualNode = virtualNodeMap.get(key);
        return virtualNode.split("&&")[0];
    }
}
